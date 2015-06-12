package com.v2.livrotheque;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;


public class ResultsSearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results_search);
        Intent intent = getIntent();
        // handleIntent(intent);
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


    //
    protected void onNewIntent(Intent intent) {

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

     /*   if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            TextView textView = (TextView) findViewById(R.id.textView);
            TextView textView2 = (TextView) findViewById(R.id.textView2);
            TextView textView3 = (TextView) findViewById(R.id.textView3);
            TextView textView4 = (TextView) findViewById(R.id.textView4);
            TextView textView5 = (TextView) findViewById(R.id.textView5);

            // Get position from table
            String[] countries = getResources().getStringArray(R.array.countries);
            // lower case countires
            for (int i=0;i<countries.length;i++) {
                countries[i]=countries[i].toLowerCase();
            }
            int position = Arrays.asList(countries).indexOf(query.toLowerCase());
            // si le pays n'exite pas
            if (position==-1) {
                textView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                textView3.setVisibility(View.INVISIBLE);
                textView4.setVisibility(View.INVISIBLE);
                textView5.setVisibility(View.VISIBLE);

            }
            else {
                textView.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.VISIBLE);
                textView3.setVisibility(View.VISIBLE);
                textView4.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.INVISIBLE);
                Resources rs = getResources();
                String[] capitals = rs.getStringArray(R.array.capitals);
                String[] population = rs.getStringArray(R.array.population);

                String country = countries[position];
                // On affiche les détails
                // On récupère l'dentifiant de l'image
                // chaque image a le nom "ic_nom du pays"
                int i = rs.getIdentifier("ic_" + country, "drawable", "esi.dz.myapplication");
                imageView.setImageResource(i);
                // On récupère la capitale du pays et la population
                textView2.setText(capitals[position]);
                textView4.setText(population[position]);*/
            }
        }