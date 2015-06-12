package com.v2.livrotheque;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


// La classe qui représente un fragment est une sous classe de la classe Fragement

public class LivresDetailsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        /*inflater.inflate retourne un élément de type view, elle convertit un layout xml en
        un objet view utilisable dans le code source
        Elle a trois paramètres :
        1. le premier est le layout
        2. le deuxième est le parent( container) de ce layout
        3. un boolean pour spécifier au système de créer ou non un parent. Toujours le mettre à false
          pour que le système utilise le parent spécifier dans le layout sans créer un autre
         */

        return inflater.inflate(R.layout.fragment_livres_detail, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        Bundle bundle= this.getArguments();
        // On récupère le bundle sauvegardé
        if (bundle!=null) {

            // On récupère la position
            int position = bundle.getInt("position");
            ImageView iv_cover = (ImageView) getActivity().findViewById(R.id.thumbD);
            TextView tv_titre = (TextView) getActivity().findViewById(R.id.titleD);
            TextView tv_auteur = (TextView) getActivity().findViewById(R.id.authorD);
            TextView tv_categorie = (TextView) getActivity().findViewById(R.id.catD);
            TextView tv_resume = (TextView) getActivity().findViewById(R.id.sumD);


            // On affiche les détails
            tv_titre.setText(bundle.getString("titre"));
            tv_auteur.setText(bundle.getString("auteur"));
            tv_categorie.setText(bundle.getString("categorie"));
            tv_resume.setText(bundle.getString("resume"));

            Bitmap bitmap = BitmapFactory.decodeByteArray(bundle.getByteArray("cover"), 0, bundle.getByteArray("cover").length);
            iv_cover.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            iv_cover.setAdjustViewBounds(true);
            iv_cover.setImageBitmap(bitmap);
        }
    }
}