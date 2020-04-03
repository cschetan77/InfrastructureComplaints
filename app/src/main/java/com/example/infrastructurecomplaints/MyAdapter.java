package com.example.infrastructurecomplaints;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<String> cmplist;
    Context context;
    public MyAdapter(Context context,ArrayList<String> cmplist) {
        this.context = context;
        this.cmplist = cmplist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_item,parent,false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.text_subject.setText(cmplist.get(position));
    }

    @Override
    public int getItemCount() {
        return cmplist.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView text_subject;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text_subject = (TextView)itemView.findViewById(R.id.text_subject);
        }
    }
}
