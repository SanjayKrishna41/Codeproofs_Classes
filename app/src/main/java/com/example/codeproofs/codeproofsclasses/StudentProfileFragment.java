package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

public class StudentProfileFragment extends Fragment implements View.OnClickListener {
    View view;
    String userid = null, passwod = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText stdname, stdphno, stdemail, stdcity;
    String stdName, stdPhno, stdEmail, stdCity;
    Button update;
    String[] listItem = null;
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_profile, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getString("username", "");
        passwod = sharedPreferences.getString("password", "");
        stdname = view.findViewById(R.id.stdnameeditText);
        stdphno = view.findViewById(R.id.stdphnoeditText);
        stdemail = view.findViewById(R.id.stdemaileditText);
        stdcity = view.findViewById(R.id.cityEditText);
        update = view.findViewById(R.id.updateButton);
        update.setOnClickListener(this);
        sharedPreferences = this.getActivity().getSharedPreferences("StudentData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        size = sharedPreferences.getInt("size", 0);
        listItem = new String[size];
        for (int i = 0; i < size; i++) {
            listItem[i] = sharedPreferences.getString("studentdetail" + i, "");
        }
        String name[] = listItem[0].split(":");
        String city[] = listItem[2].split(":");
        String phn[] = listItem[3].split(":");
        String email[] = listItem[14].split(":");
        stdname.setText(name[1].trim());
        stdcity.setText(city[1].trim());
        stdphno.setText(phn[1].trim());
        stdemail.setText(email[1].trim());
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.updateButton) {
            stdName = stdname.getText().toString();
            stdPhno = stdphno.getText().toString();
            stdEmail = stdemail.getText().toString();
            stdCity = stdcity.getText().toString();

            if (stdName.equals("") || stdPhno.equals("") || stdEmail.equals("") || stdCity.equals("")) {
                Toast.makeText(getActivity(), "All fileds are mandatory", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    BackgroundWork backgroundWork = new BackgroundWork(getActivity());
                    backgroundWork.execute("profile", stdName, stdPhno, stdEmail, stdCity, userid);
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
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
            String city = params[4];
            String stdid = params[5];

            if (type.equals("profile")) {
                String loginUrl = "http://codeproofs.com/classes/Android/profile.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                            URLEncoder.encode("phno", "UTF-8") + "=" + URLEncoder.encode(phno, "UTF-8") + "&" +
                            URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8") + "&" +
                            URLEncoder.encode("city", "UTF-8") + "=" + URLEncoder.encode(city, "UTF-8") + "&" +
                            URLEncoder.encode("stdid", "UTF-8") + "=" + URLEncoder.encode(stdid, "UTF-8");

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
            if (aVoid.trim().equals("1")) {
                Toast.makeText(getActivity(), "Profile Information Successfully Updated", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Error While Updating Profile", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
