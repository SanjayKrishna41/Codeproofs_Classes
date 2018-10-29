package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class AdvanceJavaActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"Introduction to J2EE", "Data Handling and Functions", "Packages and Multi Threading", "Collections", "XML", "JDBC", "Servlets", "JSP", "Hibernate", "Spring", "Spring, Ajax and Design Patterns", "SOA", "Web Services and Project ", "project using Spring and Hibernate"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_java);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        enquire = findViewById(R.id.enquireButton);
        enquire.setOnClickListener(this);

        MyListAdapter adapter = new MyListAdapter(this, listItem);
        listView = findViewById(R.id.details);
        listView.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.enquireButton) {
            Intent i = new Intent(getApplicationContext(), EnquireActivity.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item1:
                Intent intent = new Intent(AdvanceJavaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public final int isInternetOn() {

        final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);
        final android.net.NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        final android.net.NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifi.isConnectedOrConnecting()) {
            return 1;
        } else if (mobile.isConnectedOrConnecting()) {
            return 2;
        } else {
            return 0;
        }
    }
}
