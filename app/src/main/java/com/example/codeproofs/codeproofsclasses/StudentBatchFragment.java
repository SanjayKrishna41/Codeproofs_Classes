package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class StudentBatchFragment extends Fragment {
    View view;
    ListView listView;
    String[] listItem = null;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_student_batches, container, false);
        sharedPreferences = this.getActivity().getSharedPreferences("StudentData", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        size = sharedPreferences.getInt("size", 0);
        listItem = new String[size];
        for (int i = 0; i < size; i++) {
            listItem[i] = sharedPreferences.getString("studentdetail" + i, "");
        }
        String Student[] = new String[10];
        Student[0] = listItem[4];
        Student[1] = listItem[5];
        Student[2] = listItem[6];
        Student[3] = listItem[7];
        Student[4] = listItem[8];
        Student[5] = listItem[9];
        Student[6] = listItem[10];
        Student[7] = listItem[11];
        Student[8] = listItem[12];
        Student[9] = listItem[13];
        MyListAdapter adapter = new MyListAdapter(getActivity(), Student);
        listView = view.findViewById(R.id.studentbatch);
        listView.setAdapter(adapter);
        return view;
    }
}
