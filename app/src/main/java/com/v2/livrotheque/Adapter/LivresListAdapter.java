package com.v2.livrotheque.Adapter;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.v2.livrotheque.Model.Livre;
import com.v2.livrotheque.R;

import java.util.ArrayList;
import java.util.List;

public class LivresListAdapter extends BaseAdapter{

    Activity activity;
    List<Livre> listLivres = new ArrayList<Livre>();
    Livre livre = new Livre() ;
    static LayoutInflater inflater = null;



    public LivresListAdapter(Activity a, List<Livre> l) {
        activity = a;
        listLivres = l;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public LivresListAdapter(Activity activity) {
        this.activity = activity;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // We expose the List so we can modify it from outside
    public List<Livre> livres() {
        return this.listLivres;
    }

    public int getCount() {
        return listLivres.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi=convertView;
        if(convertView==null)
            vi = inflater.inflate(R.layout.livres_list_item, null);

        TextView titreLivre = (TextView)vi.findViewById(R.id.title); // title
        TextView auteurLivre = (TextView)vi.findViewById(R.id.author); // artist name
        TextView dateParutionLivre= (TextView)vi.findViewById(R.id.releaseYear); // duration
        ImageView imageLivre=(ImageView)vi.findViewById(R.id.thumbnail); // thumb image


        livre = listLivres.get(position);

        // Setting all values in listview
        titreLivre.setText(livre.get_titre());
        auteurLivre.setText(livre.get_auteur());
        dateParutionLivre.setText(livre.get_dateParution());

        Bitmap bitmap = BitmapFactory.decodeByteArray(livre.get_cover(), 0, livre.get_cover().length);
        imageLivre.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageLivre.setAdjustViewBounds(true);
        imageLivre.setImageBitmap(bitmap);
        return vi;
    }
}
