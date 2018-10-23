package com.example.codeproofs.codeproofsclasses;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdopter extends RecyclerView.Adapter<RecyclerViewAdopter.MyViewHolder> {

    private Context context;
    private List<CourseDetails> data;

    public RecyclerViewAdopter(Context context, List<CourseDetails> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        view = layoutInflater.inflate(R.layout.carviewitems, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.courseTitle.setText(data.get(i).getCourseName());
        myViewHolder.courseImage.setImageResource(data.get(i).getThumbnail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView courseTitle;
        ImageView courseImage;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            courseImage = itemView.findViewById(R.id.courseimage);
            courseTitle = itemView.findViewById(R.id.course_title);
        }
    }
}
