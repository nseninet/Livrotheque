package com.v2.livrotheque.Asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.v2.livrotheque.Model.Livre;
import com.v2.livrotheque.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class GetLivreByTitreTask extends AsyncTask<String,Void,String> {

    private Context context;
    ProgressDialog pd = null;

    public GetLivreByTitreTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Recherche");
        pd.setMessage("Recherche au niveau du serveur... Veuillez patienter");
        pd.show();
    }


    @Override
    protected String doInBackground(String... params) {
        String num = params[0];

        // lorsque le vaio se connecte à mon tel : 192.168.43.234

        String url = "http://192.168.43.253:8080/getLivreByTitre?titrelivre=" + num;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result = "";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        pd.cancel();
        System.out.println("s = "+s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            Activity activity = (Activity) context;

            TextView emptyText = (TextView) activity.findViewById(R.id.empty); // title
            TextView titreLivre = (TextView) activity.findViewById(R.id.title); // title
            TextView auteurLivre = (TextView) activity.findViewById(R.id.author); // artist name
            TextView dateParutionLivre = (TextView) activity.findViewById(R.id.releaseYear); // duration
            ImageView imageLivre = (ImageView) activity.findViewById(R.id.thumbnail); // thumb image

            if (s != null) {
                Type type = new TypeToken<Livre>(){}.getType();
                Livre livre = new Gson().fromJson(s, type);

              /*  titreLivre.setText(jsonObject.getString("_titre"));
                auteurLivre.setText(jsonObject.getString("_auteur"));
                dateParutionLivre.setText(jsonObject.getString("_resume"));

                Bitmap bitmap = BitmapFactory.decodeByteArray((byte[]) jsonObject.get("_cover"), 0,
                        ((byte[]) jsonObject.get("_cover")).length);
                imageLivre.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageLivre.setAdjustViewBounds(true);
                imageLivre.setImageBitmap(bitmap);
                */

                titreLivre.setText(livre.get_titre());
                auteurLivre.setText(livre.get_auteur());
                dateParutionLivre.setText(livre.get_dateParution());

                Bitmap bitmap = BitmapFactory.decodeByteArray(livre.get_cover(), 0,
                        (livre.get_cover().length));
                imageLivre.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                imageLivre.setAdjustViewBounds(true);
                imageLivre.setImageBitmap(bitmap);
                emptyText.setVisibility(View.INVISIBLE);

            } else {
                imageLivre.setVisibility(View.INVISIBLE);
                titreLivre.setVisibility(View.INVISIBLE);
                auteurLivre.setVisibility(View.INVISIBLE);
                dateParutionLivre.setVisibility(View.INVISIBLE);
                emptyText.setVisibility(View.VISIBLE);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}