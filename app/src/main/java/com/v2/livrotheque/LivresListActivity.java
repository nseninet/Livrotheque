package com.v2.livrotheque;

import android.app.SearchManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.v2.livrotheque.Adapter.TitleNavAdapter;
import com.v2.livrotheque.Model.SpinnerNavItem;

import java.util.ArrayList;


public class LivresListActivity extends ActionBarActivity
        implements ActionBar.OnNavigationListener{


    public final static String FAV_PROGRAMMATION = "Programmation";
    public final static String FAV_RESEAUX = "Réseaux";
    public final static String FAV_SECURITE = "Sécurité";
    public final static String FAV_BDD = "Bases de données";
    public final static String FAV_OS = "Sys. exploitation";

    // Action bar
    private ActionBar actionBar;

    //MenuItem pour les categories favorites (l'étoile)
    private MenuItem favMenuItem ;

    // Title navigation Spinner data
    private String[] dropDownValues = null;

    // Fragment
    private FragmentManager fm = getSupportFragmentManager();

    //
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres_list);

        // shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        // Hide the action bar title
        actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);


        // Specify a SpinnerAdapter to populate the dropdown list
        dropDownValues = getResources().getStringArray(R.array.dropdown);
        ArrayAdapter arrayAdapter = new ArrayAdapter(actionBar.getThemedContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1, dropDownValues);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionBar.setListNavigationCallbacks(arrayAdapter,this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        favMenuItem = menu.findItem(R.id.action_favorite);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        else if(id == R.id.action_favorite){
            String var = dropDownValues[actionBar.getSelectedNavigationIndex()];

            if(preferences.getBoolean(var,false) == true){
                editor.putBoolean(var,false);
                editor.commit();
                item.setIcon(R.drawable.ic_action_not_favorite);

            }else{
                editor.putBoolean(var,true);
                editor.commit();
                item.setIcon(R.drawable.ic_action_favorite);
            }
        }
        return super.onOptionsItemSelected(item);
    }


   @Override
    public boolean onNavigationItemSelected(int i, long l) {

       Toast.makeText(this,dropDownValues[i],Toast.LENGTH_SHORT).show();
       LivresListFragment listFragment = (LivresListFragment)fm.findFragmentById(R.id.fragment1);

       String var = dropDownValues[actionBar.getSelectedNavigationIndex()];

       if(preferences.getBoolean(var,false) == true){
           editor.putBoolean(var,false);
           editor.commit();
           favMenuItem.setIcon(R.drawable.ic_action_not_favorite);

       }else{
           editor.putBoolean(var,true);
           editor.commit();
           favMenuItem.setIcon(R.drawable.ic_action_favorite);
       }


       listFragment.lastSpinner = i;
       listFragment.setData(i);
       return false;
    }
}
