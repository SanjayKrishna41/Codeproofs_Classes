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

public class TeacherProfileFragment extends Fragment implements View.OnClickListener {
    View view;
    String userid = null, passwod = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    EditText teachname, teachphno, teachemail, teachdes;
    String teachName, teachPhno, teachEmail, teachDes;
    Button update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_profile, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getString("username", "");
        passwod = sharedPreferences.getString("password", "");
        teachname = view.findViewById(R.id.teachnameeditText);
        teachphno = view.findViewById(R.id.teachphnoeditText);
        teachemail = view.findViewById(R.id.teachemaileditText);
        teachdes = view.findViewById(R.id.teachDesEdittext);
        update = view.findViewById(R.id.teachupdateButton);
        update.setOnClickListener(this);

        BackgroundWork1 backgroundWork1 = new BackgroundWork1(getActivity());
        backgroundWork1.execute("getteachprofile", userid);

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.teachupdateButton) {
            teachName = teachname.getText().toString();
            teachPhno = teachphno.getText().toString();
            teachEmail = teachemail.getText().toString();
            teachDes = teachdes.getText().toString();

            if (teachName.equals("") || teachPhno.equals("") || teachEmail.equals("") || teachdes.equals("")) {
                Toast.makeText(getActivity(), "All fileds are mandatory", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    BackgroundWork backgroundWork = new BackgroundWork(getActivity());
                    backgroundWork.execute("updateteacherprofile", teachName.trim(), teachPhno.trim(), teachEmail.trim(), teachDes.trim(), userid.trim());
                } catch (Exception e) {
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public class BackgroundWork1 extends AsyncTask<String, Void, String> {
        Context context;

        BackgroundWork1(Context cxt) {
            context = cxt;
        }


        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String teachid = params[1];

            if (type.equals("getteachprofile")) {
                String loginUrl = "http://codeproofs.com/classes/Android/getteachprofile.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("teachid", "UTF-8") + "=" + URLEncoder.encode(teachid, "UTF-8");

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
            if (aVoid.equals(null) || aVoid.equals("")) {
                Toast.makeText(getActivity(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
            } else {
                String setArr[] = aVoid.split("-");
                teachname.setText(setArr[0].trim());
                teachemail.setText(setArr[1].trim());
                teachphno.setText(setArr[2].trim());
                teachdes.setText(setArr[3].trim());
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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
            String des = params[4];
            String teachid = params[5];

            if (type.equals("updateteacherprofile")) {
                String loginUrl = "http://codeproofs.com/classes/Android/updateteacherprofile.php";
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
                            URLEncoder.encode("des", "UTF-8") + "=" + URLEncoder.encode(des, "UTF-8") + "&" +
                            URLEncoder.encode("teachid", "UTF-8") + "=" + URLEncoder.encode(teachid, "UTF-8");

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
