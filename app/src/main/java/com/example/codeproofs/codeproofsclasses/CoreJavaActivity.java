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

public class CoreJavaActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"Java Components – jvm, jre and jdk", "Data Types and Variables", "Methods", "Decision Statements", "Looping Statements", "OOPS in Java", "Members Of Class", "Class and Object", "Constructors", "Has-A and Is-A Relationship", "Constructor Chaining", "this and super statement", "Overloading and Overriding", "Abstract class and Interface", "Type Casting", "Abstraction", "Polymorphism", "Generalization and Specialization", "Access specifiers", "Encapsulation", "Java Library", "Object Class", "String Class", "Wrapper Class", "Java Bean Class", "System Class and its members", "Scanner class", "Singleton Design pattern", "Arrays and problems on arrays", "Collection Frameworks", "Exception Handling", "File Programming"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_core_java);

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
                Intent intent = new Intent(CoreJavaActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}