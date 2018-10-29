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

public class CPPActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"Introduction C++ with object oriented programming", "Operators and expressions", "Control statements", "Storage types", "Functions", "Arrays", "Strings", "Pointers", "Structures", "File structures", "File Handling", "Inheritance Pointer", "Running a complete C program", "Operators and expressions", "Virtual Function", "Streams and Files", "Templates and Exceptions"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cpp);
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
                Intent intent = new Intent(CPPActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
