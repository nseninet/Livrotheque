package com.v2.livrotheque;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.v2.livrotheque.Asynctask.GetLoginUserTask;


public class LoginActivity extends ActionBarActivity {
    private EditText log;
    private EditText pwd;
    private Button btn;
    private Intent detailIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        log = (EditText)findViewById(R.id.login);
        pwd = (EditText)findViewById(R.id.pwd);
        btn = (Button)findViewById(R.id.buttonLog);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = log.getText().toString();
                String password = pwd.getText().toString();

                GetLoginUserTask task = new GetLoginUserTask(LoginActivity.this);
                task.execute(new String[]{login,password});
            }
        }
        );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
