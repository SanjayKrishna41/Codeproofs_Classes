package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.Intent;
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

public class StudentChangePasswordFragment extends Fragment implements View.OnClickListener {
    View view;
    String userid = null, passwod = null;
    EditText oldpass, newpass, retype;
    String oldPass, newPass, reType;
    Button save;
    int size = 0;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_student_changepassword, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getString("username", "");
        passwod = sharedPreferences.getString("password", "");
        oldpass = view.findViewById(R.id.oldpassEdittext);
        newpass = view.findViewById(R.id.newpasseditText);
        retype = view.findViewById(R.id.retypeEdittext);
        save = view.findViewById(R.id.stdchangeButton);
        save.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.stdchangeButton) {
            oldPass = oldpass.getText().toString();
            newPass = newpass.getText().toString();
            reType = retype.getText().toString();

            if (oldPass.equals("") || newPass.equals("") || reType.equals("")) {
                Toast.makeText(getActivity(), "All fileds are mandatory", Toast.LENGTH_SHORT).show();
            } else if (oldPass.equals(newPass)) {
                Toast.makeText(getActivity(), "New Password cannot be same as old password", Toast.LENGTH_SHORT).show();
            } else if (!(newPass.equals(reType))) {
                Toast.makeText(getActivity(), "Password Mismatch", Toast.LENGTH_SHORT).show();
            } else if (!(oldPass.equals(passwod))) {
                Toast.makeText(getActivity(), "Incorrect Password", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    BackgroundWork backgroundWork = new BackgroundWork(getActivity());
                    backgroundWork.execute("changepassword", newPass, userid);
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
            String newpass = params[1];
            String stdid = params[2];

            if (type.equals("changepassword")) {
                String loginUrl = "http://codeproofs.com/classes/Android/changepassword.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(newpass, "UTF-8") + "&" +
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
            //Toast.makeText(getActivity(), ""+aVoid.trim(), Toast.LENGTH_SHORT).show();
            if (aVoid.trim().equals("1")) {
                editor.putString("username", "");
                editor.putString("password", "");
                editor.putBoolean("saveLogin", false);
                editor.commit();
                Toast.makeText(getActivity(), "Password Changed Successfully", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), LoginActivity.class);
                startActivity(i);
            } else {
                Toast.makeText(getActivity(), "Error While Updating Password", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
