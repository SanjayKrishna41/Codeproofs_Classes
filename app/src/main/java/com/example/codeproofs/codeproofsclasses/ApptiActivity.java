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

public class ApptiActivity extends AppCompatActivity implements View.OnClickListener {
    ListView listView;
    Button enquire;
    String[] listItem = {"Grammar:-", "What is grammar?", "What is a sentence?", "Subject and Predicate", "Phrase and Clause", "What are the eight different parts of speech?", "Conversion of a sentence from Active voice to Passive voice.", "Tenses", "How to convert a direct speech sentence to indirect speech?", "Verbal Aptitude:-", "Analogy", "Synonyms", "Antonyms", "Sentence correction", "Sentence completion", "Verbal reasoning", "Reading comprehension", "Soft Skills:-", "GD tips", "Telephone Etiquettes: How to prepare for telephonic interviews?", "Classroom Etiquettes: Some tips for behaviour in classroom during training period in corporate industry.", "Interview Skills: Various types of HR questions and tips to crack HR round.", "Body language: How to portray yourself without speaking, to create a positive impact on the interviewer?", "Office Etiquettes: Office etiquette or manners is about conducting yourself respectfully and courteously in the office or workplace.", "Dressing Etiquettes: How to dress appropriately for the interview?"
            , "GENERAL APTITUDE" + "\n" + "The test of general aptitude is the challenging first stage of assessment at almost all recruitment drives across sectors. It thus crucial for a prospective hire to pass the test to be able to progess further to the interview stages. The aptitude training program covers topics that are most commonly tested while being adaptive to current trends in the area of aptitude testing. The module equips students with the necessary concepts, tools and techniques to succeed at this initial stage of screening.  Below is a snapshot of the curriculum.", "A. Quantitative Aptitude", "1. Numbers", "1.	Number system", "2.	Divisibility rules", "3.	Remainder theorem", "4.	LCM and HCF (with modules)", "5.	Unit place digits", "2. Arithmetic", "1.	Ratio and proportion", "2.	Percentages", "3.	Averages", "4.	Time and work", "5.	Time, speed and distance", "6.	Alligation and mixtures", "7.	Partnership", "8.	Profit and loss", "9.	Simple and Compound Interest", "10.	Clocks", "3. Algebra", "1.	Fundamental equations", "2.	Calendars", "3.	Set theory", "4.	Progressions", "5.	Ages", "4. Geometry", "1.	Mensuration", "2.	Basic Trigonometry", "B. Logical Reasoning", "1.	Artificial language (Coding and decoding)", "2.	Syllogisms", "3.	Blood Relations", "4.	Direction Sense", "5.	Seating Arrangements (linear and circular)", "6.	Visual Reasoning (two and three dimensional)", "7.	Ranking", "C. Verbal Ability", "1.	Tenses", "2.	Subject Verb Agreement", "3.	Active and Passive Voice", "4.	Conditionals", "5.	Prepositions", "6.	Articles", "7.	Direct and Indirect Speech", "8.	Word Analogies", "9.	Parajumbles", "10.	Critical Reasoning", "11.	Reading Comprehension", "12.	Vocabulary Building"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appti);

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
                Intent intent = new Intent(ApptiActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
