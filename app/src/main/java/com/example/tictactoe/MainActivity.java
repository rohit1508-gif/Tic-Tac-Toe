package com.example.tictactoe;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    FragmentManager manager;
    TextView tvHeading;

    public static int turn;

    public static String playerX;
    public static String playerO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turn = 0;

        ActionBar actionBar = getSupportActionBar();

        actionBar.setIcon(R.mipmap.icon);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);

        manager = getSupportFragmentManager();

        manager.beginTransaction()
                .add(R.id.fragHolder, new LoginFrag())
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == R.id.restart)
        {
            if(turn == 0)
            {
                Toast.makeText(this, "Press again to restart", Toast.LENGTH_SHORT).show();
            }
            else
            {
                tvHeading = (TextView) findViewById(R.id.tvHeading);
                tvHeading.setText(R.string.welcome_to_tic_tac_toe);

                manager.beginTransaction()
                        .replace(R.id.fragHolder, new LoginFrag())
                        .commit();
            }

            turn = ++turn % 2;
        }

        return super.onOptionsItemSelected(item);
    }
}