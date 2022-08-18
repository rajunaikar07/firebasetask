package com.example.firebasetask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {
    Context context;
    List<MyModel> myModelList =new ArrayList<>();

    public MyAdapter(Context context, List<MyModel> dataModelList) {
        this.context = context;
        this.myModelList = dataModelList;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom,parent,false);
        return new MyHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.name.setText(myModelList.get(position).getName());
        holder.mail.setText(myModelList.get(position).getMail());
        holder.mobile.setText(myModelList.get(position).getMobile());
        holder.gender.setText(myModelList.get(position).getGender());
        holder.state.setText(myModelList.get(position).getState());


    }

    @Override
    public int getItemCount() {
        return myModelList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView name,mail,mobile,gender,state;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name1);
            mail=itemView.findViewById(R.id.mail1);
            mobile=itemView.findViewById(R.id.mobile1);
            gender=itemView.findViewById(R.id.gender1);
            state=itemView.findViewById(R.id.state1);

        }
    }
}