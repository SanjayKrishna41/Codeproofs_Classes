package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String[] coursers = {"Teacher", "Student"};
    EditText uname, pwd;
    Button login;
    CheckBox check;
    Spinner loginspin;
    String userName = null;
    String password = null;
    String type = null;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (InternetOn() == 1) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            uname = findViewById(R.id.userNameeditText);
            pwd = findViewById(R.id.passwordeditText);
            login = findViewById(R.id.loginbutton);
            check = findViewById(R.id.remembercheckBox);
            loginspin = findViewById(R.id.loginSpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                    android.R.layout.simple_spinner_item, coursers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loginspin.setAdapter(adapter);
            login.setOnClickListener(this);
            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();

            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin == true) {
                uname.setText(loginPreferences.getString("username", ""));
                pwd.setText(loginPreferences.getString("password", ""));
                check.setChecked(true);

                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
            }

        } else if (InternetOn() == 2) {
            Toolbar toolbar = findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            uname = findViewById(R.id.userNameeditText);
            pwd = findViewById(R.id.passwordeditText);
            login = findViewById(R.id.loginbutton);
            check = findViewById(R.id.remembercheckBox);
            loginspin = findViewById(R.id.loginSpinner);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(LoginActivity.this,
                    android.R.layout.simple_spinner_item, coursers);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            loginspin.setAdapter(adapter);
            login.setOnClickListener(this);
            loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
            loginPrefsEditor = loginPreferences.edit();

            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin == true) {
                uname.setText(loginPreferences.getString("username", ""));
                pwd.setText(loginPreferences.getString("password", ""));
                check.setChecked(true);

                Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Please Login", Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(LoginActivity.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
            finish();
        }

    }

    public final int InternetOn() {

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

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.loginbutton) {
            if (isInternetOn() == true) {
                userName = uname.getText().toString();
                password = pwd.getText().toString();
                type = loginspin.getSelectedItem().toString();

                if (userName.trim().equals("") || password.trim().equals("")) {
                    Toast.makeText(this, "All Fields are mandatory", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        BackgroundWork backgroundWork = new BackgroundWork(this);
                        backgroundWork.execute("login", userName, password, type);
                    } catch (Exception e) {
                        Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                this.runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(LoginActivity.this, "Check Your internet connection", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
    }

    public final boolean isInternetOn() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec =
                (ConnectivityManager) getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if (connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED) {

            return true;

        } else if (
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.DISCONNECTED) {
            return false;
        }
        return false;
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
                Intent intent = new Intent(LoginActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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
            String pass = params[2];
            String userType = params[3];

            if (type.equals("login")) {
                String loginUrl = "http://codeproofs.com/classes/Android/login.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&" +
                            URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(userType, "UTF-8");

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
        protected void onPostExecute(final String aVoid) {
            //Toast.makeText(LoginActivity.this, ""+aVoid.trim().length(), Toast.LENGTH_SHORT).show();
            if (aVoid.trim().equals("invalid")) {
                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                final TextView message = alertLayout.findViewById(R.id.message);
                message.setText("Username or Password Incorrect");
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle("Login Failed");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            } else {

                LayoutInflater inflater = getLayoutInflater();
                View alertLayout = inflater.inflate(R.layout.layout_custom_dialog, null);
                final TextView message = alertLayout.findViewById(R.id.message);
                message.setText("Authenticating...");
                AlertDialog.Builder alert = new AlertDialog.Builder(LoginActivity.this);
                alert.setTitle("Success");
                // this is set the view from XML inside AlertDialog
                alert.setView(alertLayout);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int i = 0;
                        while (i < 100) {
                            i = i + 50;

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (i >= 100) {
                            if (check.isChecked()) {
                                loginPrefsEditor.putBoolean("saveLogin", true);
                                loginPrefsEditor.putString("username", aVoid.trim());
                                loginPrefsEditor.putString("password", password);
                                loginPrefsEditor.commit();

                                if (type.equals("Teacher")) {
                                    Intent intent = new Intent(LoginActivity.this, TeacherWelcomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            } else {
                                loginPrefsEditor.putString("username", aVoid.trim());
                                loginPrefsEditor.putString("password", password);
                                loginPrefsEditor.commit();

                                if (type.equals("Teacher")) {
                                    Intent intent = new Intent(LoginActivity.this, TeacherWelcomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        }
                    }
                }).start();
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

}
