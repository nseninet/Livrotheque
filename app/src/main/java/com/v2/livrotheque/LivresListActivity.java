package com.v2.livrotheque;

import android.app.SearchManager;
import android.content.Context;
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

    // action bar
    private ActionBar actionBar;

    // Title navigation Spinner data
    //private ArrayList<SpinnerNavItem> navSpinner;
    private String[] dropDownValues = null;

    // Navigation adapter
    //private TitleNavAdapter adapter;
    private ArrayAdapter arrayAdapter;
    private FragmentManager fm = getSupportFragmentManager();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres_list);

        actionBar = getSupportActionBar();

        // Hide the action bar title
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);


        // Specify a SpinnerAdapter to populate the dropdown list
        dropDownValues = getResources().getStringArray(R.array.dropdown);
        ArrayAdapter arrayAdapter = new ArrayAdapter(actionBar.getThemedContext(),
                android.R.layout.simple_spinner_item, android.R.id.text1, dropDownValues);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        actionBar.setListNavigationCallbacks(arrayAdapter,this);

       /*
        // Spinner title navigation data
        navSpinner = new ArrayList<SpinnerNavItem>();
        navSpinner.add(new SpinnerNavItem("Programmation"));
        navSpinner.add(new SpinnerNavItem("Réseaux"));
        navSpinner.add(new SpinnerNavItem("Sécurité"));
        navSpinner.add(new SpinnerNavItem("Bases de données"));
        navSpinner.add(new SpinnerNavItem("Systèmes d'exploitation"));

        // title drop down adapter
        adapter = new TitleNavAdapter(LivresListActivity.this, navSpinner);
        */

        // assigning the spinner navigation

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName()));
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
        return super.onOptionsItemSelected(item);
    }


   @Override
    public boolean onNavigationItemSelected(int i, long l) {

       Toast.makeText(this,dropDownValues[i],Toast.LENGTH_SHORT).show();
       LivresListFragment listFragment = (LivresListFragment)fm.findFragmentById(R.id.fragment1);
       listFragment.lastSpinner = i;
       listFragment.setData(i);
       return false;
    }
}
