package com.example.a31b;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    Context context;

    ArrayList<his> list;

    public MyAdapter(Context context, ArrayList<his> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        his hist = list.get(position);
        holder.Date_Filed.setText(hist.getDate_Filed());
        holder.Reference_Number.setText(hist.getReference_Number());
       // holder.username.setText(hist.getUsername());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView Date_Filed, Reference_Number, username;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            Date_Filed = itemView.findViewById(R.id.firstname);
            Reference_Number = itemView.findViewById(R.id.lastname);
          //  username = itemView.findViewById(R.id.username);
        }
    }
}
