package com.v2.livrotheque;


import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.media.RingtoneManager;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.v2.livrotheque.Asynctask.GetCountLivresTask;
import com.v2.livrotheque.Database.DataBaseHandler;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Notification extends BroadcastReceiver {


    public void onReceive(Context context, Intent intent) {

        int count = -1;
        ArrayList<String> categorie = new ArrayList<>();

        try {
            Resources resources = context.getResources();
            String[] categories = resources.getStringArray(R.array.dropdown);

            //attention
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

            for (int i=0 ; i<categories.length; i++){

                if (preferences.getBoolean(categories[i],false)){

                        // serveur
                        GetCountLivresTask getCountLivresTask = new GetCountLivresTask(context);
                        count = getCountLivresTask.execute(new String[]{categories[i]}).get();
                       // local
                        DataBaseHandler dataBaseHandler = new DataBaseHandler(context);
                        int previousCount = dataBaseHandler.getCount(categories[i]);

                        if (count > previousCount){
                            dataBaseHandler.updateCount(categories[i], count);
                            categorie.add(categories[i]);
                        }
                    }
                }

            if (!categorie.isEmpty()) notification(context, categorie);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Dispositif hors connexion! ", Toast.LENGTH_SHORT);
        }
    }

    private void notification(Context context,ArrayList<String> categories) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        Uri alarm = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        builder.setSound(alarm);

        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle("Livrothèque : nouveauté(s) dans catégorie(s)");
        String query = "";
        for (int i=0; i<categories.size(); i++){
            query += categories.get(i);
            if (i!=categories.size()-1) query += ", ";
        }
        builder.setContentText(query);

        //builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, builder.build());
    }
}

