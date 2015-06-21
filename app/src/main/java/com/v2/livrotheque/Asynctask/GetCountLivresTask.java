package com.v2.livrotheque.Asynctask;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class GetCountLivresTask extends AsyncTask<String,Void,Integer> {

    private Context context;
    ProgressDialog pd = null;

    public GetCountLivresTask(Context context) {
        this.context = context;
    }


    @Override
    protected Integer doInBackground(String... params) {

        String categorie = params[0];
        String url ="http://192.168.43.253:8080/getNbLivresCategorie?categorie="+categorie;
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try {
            HttpResponse httpResponse = httpClient.execute(httpGet);
            result= EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Integer.parseInt(result);
    }
}
