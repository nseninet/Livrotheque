package com.v2.livrotheque;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.v2.livrotheque.Adapter.LivresListAdapter;
import com.v2.livrotheque.Database.DataBaseHandler;
import com.v2.livrotheque.Model.Livre;

import java.util.ArrayList;
import java.util.List;


// La classe qui représente un fragment est une sous classe de la classe Fragement

public class LivresListFragment extends Fragment {

    ListView listView;
    LivresListAdapter livresListAdapter ;
    DataBaseHandler dataBaseHandler;
    int lastSpinner ;


    List<Livre> livresList = new ArrayList<Livre>();

    // livres par categories
    List<Livre> livresListProg = new ArrayList<Livre>();
    List<Livre> livresListRes = new ArrayList<Livre>();
    List<Livre> livresListSec = new ArrayList<Livre>();
    List<Livre> livresListBDD = new ArrayList<Livre>();
    List<Livre> livresListOS = new ArrayList<Livre>();



    /* La spécification du layout du fragment se fait au niveau de la méthode OnCreateView
     OnCreateView ext exécutée quand le système dessine les élements de l'interface
     La méthode onCreateView doit retourner un élément de type View
    */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /*inflater.inflate retourne un élément de type view, elle convertit un layout xml en
        un objet view utilisable dans le code source
        Elle a trois paramètres :
        1. le premier est le layout
        2. le deuxième est le parent(container) de ce layout
        3. un boolean pour spécifier au système de créer ou non un parent. Toujours le mettre à false
          pour que le système utilise le parent spécifié dans le layout sans créer un autre
         */
        View view = inflater.inflate(R.layout.livres_list, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        dataBaseHandler = new DataBaseHandler(getActivity());
    }

    /**
     *  OnStart: le Fragment a chargé l'interface et l'activité est créée
     */
    @Override
    public void onStart() {
        super.onStart();

        listView = (ListView) getActivity().findViewById(R.id.listView);
        livresList = dataBaseHandler.getAllBooks();

        livresListProg = dataBaseHandler.getBooksByCategory("Programmation");
        livresListRes = dataBaseHandler.getBooksByCategory("Réseaux");
        livresListSec = dataBaseHandler.getBooksByCategory("Sécurité");
        livresListBDD = dataBaseHandler.getBooksByCategory("BDD");
        livresListOS = dataBaseHandler.getBooksByCategory("OS");

        livresListAdapter = new LivresListAdapter(getActivity(),livresList);
        listView.setAdapter(livresListAdapter);
        setData(lastSpinner);


        // appeler l'évènement onClick de la liste
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // On vérifie la configuration
                if(getResources().getConfiguration().smallestScreenWidthDp>=600) {

                    // Tablette
                    // on remplace le framelayout par le fragment detail
                    LivresDetailsFragment livresDetailsFragment = new LivresDetailsFragment();
                    Bundle bundle = new Bundle();

                    // On sauvegarde la position du clique dans un object bundle
                    bundle.putByteArray("cover", livresList.get(position).get_cover());
                    bundle.putString("titre", livresList.get(position).get_titre());
                    bundle.putString("auteur", livresList.get(position).get_auteur());
                    bundle.putString("categorie", livresList.get(position).get_categorie());
                    bundle.putString("resume", livresList.get(position).get_resume());
                    livresDetailsFragment.setArguments(bundle);

                    // On lance le FragmementManger pour remplacer le frameLyout par le fragment
                    FragmentManager fragmentManager = getFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    // On remplace le frameLyout qui a un id "R.id.fragment" par le fragment
                    fragmentTransaction.replace(R.id.fragment2, livresDetailsFragment).commit();
                }
                else {
                    // Smartphone
                    // Dans ce cas on lance l'activié détail
                    Intent intent = new Intent("com.v2.livrotheque.intent.details");

                    // On envoie à l'activité détail la position du clique
                    intent.putExtra("cover", livresList.get(position).get_cover());
                    intent.putExtra("titre", livresList.get(position).get_titre());
                    intent.putExtra("auteur", livresList.get(position).get_auteur());
                    intent.putExtra("categorie", livresList.get(position).get_categorie());
                    intent.putExtra("resume", livresList.get(position).get_resume());
                    startActivity(intent);
                }
            }
        });
    }

    public void setData(int i) {
        switch(i){
            case 0:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListProg);
                break;
            case 1:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListRes);
                break;
            case 2:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListSec);
                break;
            case 3:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListBDD);
                break;
            case 4:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListOS);
                break;
            default:
                livresListAdapter.livres().clear();
                livresListAdapter.livres().addAll(livresListProg);
                break;
        }
        livresListAdapter.notifyDataSetChanged();
    }
}
