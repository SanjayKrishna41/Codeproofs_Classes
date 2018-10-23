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

public class WebServiceActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"WEBTECHNOLOGIES", "HTML", "HTML Introduction", "HTML Basic tags", "HTML- Formatting", "HTML Attributes", "Meta tags", "HTML Comments", "HTML Images", "HTML- Tables", "HTML- Lists", "HTML Text-Links", "HTML Blocks", "HTML Forms", "HTML Embed- Multimedia", "HTML Marquees", "HTML Frames", "CSS", "CSS Overview", "CSS Syntax", "CSS Selectors", "CSS Types", "CSS Colors", "CSS Backgrounds", "CSS Fonts", "CSS Table", "CSS List", "CSS BOX Model", "CSS Padding", "CSS Dimensions", "CSS Pseudo Classes", "Java Script", "JS Overview", "JS Syntax", "JS Variables", "JS Operators", "JS Control Statements", "JS Functions", "JS Events", "JS Dialog Boxes", "JS Objects", "JS Error & Exceptions", "JS Form Validation"
            , "WEB SERVICE", "Webservices Introduction", "What is Webservices", "Why Webservices", "Real Time Examples of Webservices", "Different Webservices", "SOAP", "REST", "About HTTPS", "Understanding SOAP & REST Webservices", "Requirement of Webservices", "Advantage of Webservices", "Understanding XML & JSON", "JSON to Java Object and Vice-versa using GSON framework", "XML to Java Object and Vice-versa using JAXB framework", "Understanding XPath", "SOAP Webservice using Spring & Apache CXF", "REST Webservice using Spring-REST & Apache Jersey"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);

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
                Intent intent = new Intent(WebServiceActivity.this, LoginActivity.class);
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
