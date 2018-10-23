package com.example.codeproofs.codeproofsclasses;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class CSActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"MS.NET Framework Introduction", "VS.NET and Entry Point Method –Main", "C # Language Syntax", "defined/Custom Exception class", "IO Streams", "What is a streams?", "Types of Stream", "Standard I/O Streams Console", "Handling text in files", "Dealing with Binary files", "Serialization / Deserialization", "Unsafe Code", "Database Programming Using ADO.NET", "XML", "Delegates & Events", "Packaging and Deployment", "Debugging and Diagnostics", "HTML and JavaScript", "ASP.NET Introduction & Sample Programs", "Applying Themes and Styles to Controls", "Page Navigation Options", "ASP.NET State Management", "Databound Controls", "Globa.asax & HttpApplication", "Understanding Configuration File - Web.Config", "Web Caching", "Authentication & Authorization", "AJAX.NET"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs);

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
                Intent intent = new Intent(CSActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(), "Info", Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(), "Contact Us", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}