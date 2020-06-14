package com.saswat.myfouthtask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.MyViewHolder> {
    private Context context;
    private ArrayList std_id;
    private ArrayList std_name;
    private ArrayList std_guardian_name;
    private ArrayList std_email_address;
    private ArrayList std_phone_no;
    Activity activity;


    public CustomAdaptor(Activity activity, Context context, ArrayList<String> std_id, ArrayList<String> std_name, ArrayList<String> std_guardian_name, ArrayList<String> std_email, ArrayList<String> std_phone) {
        this.activity = activity;
        this.context = context;
        this.std_id = std_id;
        this.std_name = std_name;
        this.std_guardian_name = std_guardian_name;
        this.std_email_address = std_email;
        this.std_phone_no = std_phone;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.std_id.setText(String.valueOf(std_id.get(position)));
        holder.std_name.setText(String.valueOf(std_name.get(position)));
        holder.std_guardian_name.setText(String.valueOf(std_guardian_name.get(position)));
        holder.std_email.setText(String.valueOf(std_email_address.get(position)));
        holder.std_phone.setText(String.valueOf(std_phone_no.get(position)));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(std_id.get(position)));
                intent.putExtra("name", String.valueOf(std_name.get(position)));
                intent.putExtra("parent", String.valueOf(std_guardian_name.get(position)));
                intent.putExtra("email", String.valueOf(std_email_address.get(position)));
                intent.putExtra("phone", String.valueOf(std_phone_no.get(position)));
                activity.startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return std_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView std_id, std_name, std_guardian_name, std_email, std_phone;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            std_id = itemView.findViewById(R.id.std_id);
            std_name = itemView.findViewById(R.id.tv_name_value);
            std_guardian_name = itemView.findViewById(R.id.tv_guardian_name_value);
            std_email = itemView.findViewById(R.id.tv_email_address_value);
            std_phone = itemView.findViewById(R.id.tv_phone_no_value);
            linearLayout = itemView.findViewById(R.id.main_layout);

        }
    }
}
