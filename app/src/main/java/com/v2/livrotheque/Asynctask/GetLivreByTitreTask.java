package com.v2.livrotheque.Asynctask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.v2.livrotheque.R;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;


public class GetLivreByTitreTask extends AsyncTask<String,Void,String> {

    private Context context;
    ProgressDialog pd = null;

    public GetLivreByTitreTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(context);
        pd.setTitle("title");
        pd.setMessage("Veuillez patienter ...");
        pd.show();
    }


    @Override
    protected String doInBackground(String... params) {
        String num = params[0];

        // lorsque le vaio se connecte à mon tel : 192.168.43.234
        String url ="http://192.168.43.234:8080/getlivrebytitre?titrelivre="+num;
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

            ImageView iv_cover = (ImageView) activity.findViewById(R.id.thumbD);
            TextView tv_titre = (TextView) activity.findViewById(R.id.titleD);
            TextView tv_auteur = (TextView) activity.findViewById(R.id.authorD);
            TextView tv_categorie = (TextView) activity.findViewById(R.id.catD);
            TextView tv_resume = (TextView) activity.findViewById(R.id.sumD);
          //TextView tv_dateParution = (TextView) activity.findViewById(R.id.dateD);
            //rajouter la date de paration


            tv_titre.setText(jsonObject.getString("_titre"));
            tv_auteur.setText(jsonObject.getString("_auteur"));
            tv_resume.setText(jsonObject.getString("_resume"));
          //tv_dateParution.setText(jsonObject.getString("_dateParation"));
            tv_categorie.setText(jsonObject.getString("_categorie"));

            Bitmap bitmap = BitmapFactory.decodeByteArray((byte[])jsonObject.get("_cover"), 0,
                    ((byte[]) jsonObject.get("_cover")).length);
            iv_cover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            iv_cover.setAdjustViewBounds(true);
            iv_cover.setImageBitmap(bitmap);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}