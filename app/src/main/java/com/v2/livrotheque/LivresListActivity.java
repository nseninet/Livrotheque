package com.v2.livrotheque;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
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

import java.util.Calendar;


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

    // Shared preferences
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres_list);

        // shared preferences
        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        Intent intent = new Intent("v2.intent.notif");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY,13);
        calendar.set(Calendar.MINUTE, 6);
        manager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),1000*3600, pendingIntent);

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
        System.out.println("favmenuitem ="+favMenuItem.toString());
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
            System.out.println("value = "+var+" i = "+actionBar.getSelectedNavigationIndex());
            System.out.println("sp avant = "+preferences.getBoolean(var,false));

            if(preferences.getBoolean(var,false)){

                editor.putBoolean(var,false);
                editor.commit();
                System.out.println("sp après= "+preferences.getBoolean(var,false));
                item.setIcon(R.drawable.ic_action_not_favorite);

            }else{
                editor.putBoolean(var,true);
                editor.commit();
                System.out.println("sp après= "+preferences.getBoolean(var,false));
                item.setIcon(R.drawable.ic_action_favorite);
            }
        }
        return super.onOptionsItemSelected(item);
    }


   @Override
    public boolean onNavigationItemSelected(int i, long l) {

       LivresListFragment listFragment = (LivresListFragment)fm.findFragmentById(R.id.fragment1);

       String var = dropDownValues[i];
       System.out.println("value = "+var+" i =" + i);
       System.out.println("sp avant= "+preferences.getBoolean(var,false));

       if(preferences.getBoolean(var,false)){
           favMenuItem.setIcon(R.drawable.ic_action_favorite);

       }else{
           favMenuItem.setIcon(R.drawable.ic_action_not_favorite);
       }
       listFragment.lastSpinner = i;
       listFragment.setData(i);
       return false;
    }
}
