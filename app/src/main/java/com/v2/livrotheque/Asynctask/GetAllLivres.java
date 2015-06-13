package com.v2.livrotheque.Asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;


import com.v2.livrotheque.Adapter.LivresListAdapter;
import com.v2.livrotheque.Model.Livre;
import com.v2.livrotheque.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




public class GetAllLivres extends AsyncTask<Void,Integer,String> {
    private Context context;
    ProgressDialog pd = null;

    public GetAllLivres(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = ProgressDialog.show(context, "title", "Veuillez patienter ...",true);
    }

    @Override
    protected String doInBackground(Void... params) {

        /*
        //test au cas où on utilise un émulateur
        String url ="http://10.0.2.2:8080/getallflights";
        */

        String url ="http://192.168.43.143:8080/getalllivres";
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
        List<Livre> list = new ArrayList<>();
        try {
            JSONArray jsonArray = new JSONArray(s);
            for(int i=0 ; i<jsonArray.length() ; i++) {

                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Livre livre = new Livre();
                livre.set_titre(jsonObject.getString("_titre"));
                livre.set_auteur(jsonObject.getString("_auteur"));
                livre.set_resume(jsonObject.getString("_resume"));
                livre.set_dateParution(jsonObject.getString("_dateParation"));
                livre.set_categorie(jsonObject.getString("_categorie"));
                livre.set_cover((byte[])jsonObject.get("_cover"));
                list.add(livre);
            }

          ListView listView = (ListView) ((Activity)context).findViewById(R.id.listView);
          listView.setAdapter(new LivresListAdapter((Activity)context, list));
        }

        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
