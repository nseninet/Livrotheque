package com.v2.livrotheque;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.v2.livrotheque.Asynctask.GetLivreByTitreTask;
import com.v2.livrotheque.Asynctask.GetLoginUserTask;
import com.v2.livrotheque.Database.DataBaseHandler;
import com.v2.livrotheque.Model.Livre;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;


public class ResultsSearchActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_results_search);
        Intent intent = getIntent();
        try {
            handleIntent(intent);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results_search, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        return super.onOptionsItemSelected(item);
    }


    //
    protected void onNewIntent(Intent intent) {
        try {
            handleIntent(intent);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void handleIntent(Intent intent) throws ExecutionException, InterruptedException {
            if (Intent.ACTION_SEARCH.equals(intent.getAction())) {

                String query = intent.getStringExtra(SearchManager.QUERY);
                DataBaseHandler dataBaseHandler = new DataBaseHandler(this);

                // recherche en local
                Livre livre = dataBaseHandler.getBookByTitle(query);

                if (livre != null) {
                    TextView emptyText = (TextView) findViewById(R.id.empty); // title
                    TextView titreLivre = (TextView) findViewById(R.id.title); // title
                    TextView auteurLivre = (TextView) findViewById(R.id.author); // artist name
                    TextView dateParutionLivre = (TextView) findViewById(R.id.releaseYear); // duration
                    ImageView imageLivre = (ImageView) findViewById(R.id.thumbnail); // thumb image

                    imageLivre.setVisibility(View.VISIBLE);
                    titreLivre.setVisibility(View.VISIBLE);
                    auteurLivre.setVisibility(View.VISIBLE);
                    dateParutionLivre.setVisibility(View.VISIBLE);
                    emptyText.setVisibility(View.INVISIBLE);

                    titreLivre.setText(livre.get_titre());
                    auteurLivre.setText(livre.get_auteur());
                    dateParutionLivre.setText(livre.get_dateParution());

                    byte[] coverImage = livre.get_cover();
                    ByteArrayInputStream imageStream = new ByteArrayInputStream(coverImage);
                    Bitmap theImage = BitmapFactory.decodeStream(imageStream);
                    imageLivre.setImageBitmap(theImage);
                } else {

                    // recherche sur le serveur
                    GetLivreByTitreTask task = new GetLivreByTitreTask(ResultsSearchActivity.this);
                    task.execute(new String[]{query});
                }
            }
        }
    }