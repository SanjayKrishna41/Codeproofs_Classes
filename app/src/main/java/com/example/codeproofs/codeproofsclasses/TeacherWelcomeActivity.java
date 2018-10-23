package com.example.codeproofs.codeproofsclasses;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;

public class TeacherWelcomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String userid = null, passwod = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_welcome);

        Toolbar toolbar = findViewById(R.id.usertoolbar);
        setSupportActionBar(toolbar);

        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getString("username", "");
        passwod = sharedPreferences.getString("password", "");


        drawerLayout = findViewById(R.id.drawlayout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_draw_open, R.string.nav_draw_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlayout, new TeacherClassFragment()).commit();
            navigationView.setCheckedItem(R.id.teachclass);
        }
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.teachclass:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlayout, new TeacherClassFragment()).commit();
                break;
            case R.id.teachprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlayout, new TeacherProfileFragment()).commit();
                break;
            case R.id.teachchange:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentlayout, new TeacherChangePasswordFragment()).commit();
                break;
            case R.id.teachlogout:
                editor.putString("username", "");
                editor.putString("password", "");
                editor.putBoolean("saveLogin", false);
                editor.commit();
                startActivity(new Intent(this, LoginActivity.class));
                finish();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

}
