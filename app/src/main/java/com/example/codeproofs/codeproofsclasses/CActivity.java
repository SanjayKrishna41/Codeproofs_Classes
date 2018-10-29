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

public class CActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"Introduction to Problem Solving", "Programs written in C",
            "C Language preliminaries", "symbolic constants", "Input-Output", "Pre-processor commands", "Running a complete C program", "Operators and expressions", "Control statements",
            "comma operators", "Storage types", "Functions", "Arrays", "Strings", "Pointers", "Structures",
            "File structures", "File Handling", "Pre-requisites" + "\n" + "There are no prerequisites for this course, except that anyone who wants to learn C should have an analytical blend of mind and logic reasoning.",
            "Why Learn C Programming & Data Structures?" + "\n" + "Most companies test your knowledge of C and Data Structures in their recruitment process. Whether you aim for a career in IT industry or want to become an embedded software developer it is important to master C and Data Structures.",
            "Whether you want to develop your own iPhone or iPad apps, create unique web applications, or create games, C Programming is the place to begin. Many of the concepts of high level languages today have been borrowed from the C programming language and hence learning C helps you easily grasp any other language."};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

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
                Intent intent = new Intent(CActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}