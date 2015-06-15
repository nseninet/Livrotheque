package com.v2.livrotheque.Asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.v2.livrotheque.LivresListActivity;
import com.v2.livrotheque.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class GetLoginUserTask extends AsyncTask<String,Void,String> {

    private Context context;
    ProgressDialog pd = null;

    public GetLoginUserTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("Livrotheque");
        pd.setMessage("Connexion en cours ... Veuillez patienter");
        pd.show();
    }



    @Override
    protected String doInBackground(String... params) {
        String username = params[0];
        String password = params[1];

        // lorsque le vaio se connecte à mon tel : 192.168.43.234
        String url ="http://192.168.43.234:8080/getLoginUser?username="+username+"&password="+password;

        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result ="";
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        pd.cancel();
        try {
            JSONObject jsonObject = new JSONObject(s);
            Activity activity = (Activity) context;

            if(jsonObject != null){
                Intent detailIntent = new Intent(activity, LivresListActivity.class);
                context.startActivity(detailIntent);
                ((Activity) context).finish();

            }else{
                Toast.makeText(activity, "Les login et mot de passe sont incorrects ", Toast.LENGTH_SHORT).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
