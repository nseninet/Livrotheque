package com.v2.livrotheque;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class LivresDetailsActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Sur un smartphone on lance l'acivité détails
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livres_detail);

        LivresDetailsFragment livresDetailsFragment = new LivresDetailsFragment();
        Bundle bundle = new Bundle();

        // on crée un bundle est on sauvegarde les valeurs de l'intent
        bundle.putByteArray("cover", getIntent().getByteArrayExtra("cover"));
        bundle.putString("titre", getIntent().getStringExtra("titre"));
        bundle.putString("auteur", getIntent().getStringExtra("auteur"));
        bundle.putString("categorie",getIntent().getStringExtra("categorie"));
        bundle.putString("resume", getIntent().getStringExtra("resume"));
        livresDetailsFragment.setArguments(bundle);


        // on lance le fragment détail et on passe la main à ce fragment pour l'affichage
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment2, livresDetailsFragment).commit();

        // La navigation vers main activity
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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
}
