package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;

public class EnquireActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] coursers = {"Courses", "Programming in C", "Programming in C++", "C# and DOT NET", "Core Java", "Advanced Java", "Web Development", "Software Testing", "Android Application Development", "Personality Development"};
    EditText name, phno, email, message;
    Button save, cancel;
    Spinner course;
    String userName, phNo, Email, Message, Course;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquire);

        if (isInternetOn() == 1) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Arrays.sort(coursers);

            name = findViewById(R.id.nameEditext);
            phno = findViewById(R.id.phnoEdittext);
            email = findViewById(R.id.emailEdittext);
            message = findViewById(R.id.messageEditText);
            save = findViewById(R.id.saveButton);
            cancel = findViewById(R.id.cancelButton);

            course = findViewById(R.id.courseSpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EnquireActivity.this,
                    android.R.layout.simple_spinner_item, coursers);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            course.setAdapter(adapter);

            save.setOnClickListener(this);
            cancel.setOnClickListener(this);

        } else if (isInternetOn() == 2) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Arrays.sort(coursers);

            name = findViewById(R.id.nameEditext);
            phno = findViewById(R.id.phnoEdittext);
            email = findViewById(R.id.emailEdittext);
            message = findViewById(R.id.messageEditText);
            save = findViewById(R.id.saveButton);
            cancel = findViewById(R.id.cancelButton);

            course = findViewById(R.id.courseSpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(EnquireActivity.this,
                    android.R.layout.simple_spinner_item, coursers);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            course.setAdapter(adapter);

            save.setOnClickListener(this);
            cancel.setOnClickListener(this);
        } else {
            Toast.makeText(EnquireActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            finish();
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
                Intent intent = new Intent(EnquireActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.saveButton) {
            userName = name.getText().toString();
            phNo = phno.getText().toString();
            Email = email.getText().toString();
            Message = message.getText().toString();
            Course = course.getSelectedItem().toString();

            if (userName.equals("") || phNo.equals("") || Email.equals("") || Message.equals("") || Course.equals("Courses") || Course.equals("")) {
                Toast.makeText(this, "All Fields are Mandatory", Toast.LENGTH_LONG).show();
            } else {
                BackgroundWork backgroundWork = new BackgroundWork(this);
                backgroundWork.execute("login", userName, phNo, Email, Course, Message);
            }
        } else {
            finish();
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

    public class BackgroundWork extends AsyncTask<String, Void, String> {
        Context context;

        BackgroundWork(Context cxt) {
            context = cxt;
        }


        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String name = params[1];
            String phno = params[2];
            String email = params[3];
            String sub = params[4];
            String msg = params[5];

            if (type.equals("login")) {
                String loginUrl = "http://codeproofs.com/classes/Android/register.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("phonenumber", "UTF-8") + "=" + URLEncoder.encode(phno, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                            URLEncoder.encode("subject", "UTF-8") + "=" + URLEncoder.encode(sub, "UTF-8") + "&" +
                            URLEncoder.encode("message", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");

                    bufferedWriter.write(postData);
                    bufferedWriter.flush();
                    bufferedWriter.close();
                    outputStream.close();
                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                    String result = "", line;
                    while ((line = bufferedReader.readLine()) != null) {
                        result += line;
                    }
                    bufferedReader.close();
                    inputStream.close();
                    httpURLConnection.disconnect();
                    return result;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String aVoid) {
            if (aVoid.trim().equals("invalid")) {
                Toast.makeText(EnquireActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(EnquireActivity.this, "Thank you for choosing Codeproofs Classes, Our team will contact you Shortly", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Thank you for choosing Codeproofs Classes, Our team will contact you Shortly");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                Intent intent = new Intent(EnquireActivity.this, SubMenuActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }

        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
