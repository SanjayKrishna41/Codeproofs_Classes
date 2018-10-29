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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import java.util.ArrayList;
import java.util.List;

public class TeacherClassFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    View view;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Spinner course;
    TextView batch;
    String userid = null, passwod = null;
    List<String> sp1List = new ArrayList<String>();
    List<String> refer = new ArrayList<String>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_teacher_class, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        userid = sharedPreferences.getString("username", "");
        passwod = sharedPreferences.getString("password", "");

        batch = view.findViewById(R.id.batchDetailEdittext);
        course = view.findViewById(R.id.courseSpinner);
        course.setOnItemSelectedListener(this);
        BackgroundWork backgroundWork = new BackgroundWork(getActivity());
        backgroundWork.execute("getcourse", userid.trim());
        return view;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedItem = parent.getItemAtPosition(position).toString();
        BackgroundWork1 backgroundWork1 = new BackgroundWork1(getActivity());
        backgroundWork1.execute("getbatch", userid.trim(), selectedItem.trim());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public class BackgroundWork extends AsyncTask<String, Void, String> {
        Context context;

        BackgroundWork(Context cxt) {
            context = cxt;
        }


        @Override
        protected String doInBackground(String... params) {
            String type = params[0];
            String teachid = params[1].trim();

            if (type.equals("getcourse")) {
                String loginUrl = "http://codeproofs.com/classes/Android/getcourse.php";
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
            if (aVoid.equals(" ") || aVoid.equals("") || aVoid.equals(null)) {
                Toast.makeText(getActivity(), "Check your internet connection", Toast.LENGTH_SHORT).show();
            } else {
                String fulCourses[] = aVoid.trim().split("-");
                for (int i = 0; i < fulCourses.length; i++) {
                    String courdet[] = fulCourses[i].split("#");
                    refer.add(courdet[0]);
                    for (int j = 0; j < courdet.length; j++) {
                        String arr[] = courdet[j].split("%");
                        sp1List.add(arr[0]);
                    }
                }
                String list[] = new String[sp1List.size()];
                list = sp1List.toArray(list);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                course.setAdapter(adapter);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
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
            String teachid = params[1].trim();
            String coursename = params[2].trim();

            if (type.equals("getbatch")) {
                String loginUrl = "http://codeproofs.com/classes/Android/getbatch.php";
                try {
                    URL url = new URL(loginUrl);
                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    OutputStream outputStream = httpURLConnection.getOutputStream();
                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                    String postData = URLEncoder.encode("teachid", "UTF-8") + "=" + URLEncoder.encode(teachid, "UTF-8") + "&" +
                            URLEncoder.encode("coursename", "UTF-8") + "=" + URLEncoder.encode(coursename, "UTF-8");

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
            if (aVoid.trim().equals("No Bacthes")) {
                batch.setText(aVoid);
            } else {
                String msg[] = aVoid.trim().split("-");
                String message = "Batch ID : " + msg[0] + "\n" + "Batch Start Time : " + msg[1] + "\n" + "Batch End Time : " + msg[2] + "\n" + "Duration in Days : " + msg[3] + "\n" + "Contact Person Name : " + msg[4] + "\n" + "Number of Students : " + msg[5];
                batch.setText(message);
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
