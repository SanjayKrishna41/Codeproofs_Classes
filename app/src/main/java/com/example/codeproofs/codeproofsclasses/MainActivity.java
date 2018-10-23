package com.example.codeproofs.codeproofsclasses;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<CourseDetails> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = new ArrayList<>();
        list.add(new CourseDetails("C", R.drawable.c));
        list.add(new CourseDetails("C++", R.drawable.cpp));
        list.add(new CourseDetails("C# and Dot Net", R.drawable.cs));
        list.add(new CourseDetails("Core Java", R.drawable.java));
        list.add(new CourseDetails("Advanced Java", R.drawable.jee));
        list.add(new CourseDetails("Android App Development", R.drawable.android));
        list.add(new CourseDetails("Software Testing", R.drawable.test));
        list.add(new CourseDetails("Web Development", R.drawable.html));
        list.add(new CourseDetails("Personality Development", R.drawable.appti));

        //link recyclerview
        RecyclerView recyclerView = findViewById(R.id.recyclerview);

        //create recylerviewadaptor object
        RecyclerViewAdopter recyclerViewAdopter = new RecyclerViewAdopter(this, list);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(recyclerViewAdopter);
    }
}
