package com.phineasnferb.taskmanager;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.MyViewHolder> {
    Context context;
    ArrayList<myTask> myTasks;
    private Object ViewGroup;

    public taskAdapter(Context c,ArrayList<myTask> p){
        context =c;
        myTasks = p;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, (android.view.ViewGroup) ViewGroup,  false ));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.tasktitle.setText(myTasks.get(i).getTasktitle());
        holder.taskdesc.setText(myTasks.get(i).getTaskdesc());
        holder.taskdate.setText(myTasks.get(i).getTaskdate());





        final String getTasktitle=myTasks.get(i).getTasktitle();
        final String getTaskdesc=myTasks.get(i).getTaskdesc();
        final String getTaskdate=myTasks.get(i).getTaskdate();
        final String getKey=myTasks.get(i).getKey();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent open= new Intent(context,EditTask.class);
                open.putExtra("tasktitle",getTasktitle);
                open.putExtra("taskdesc",getTaskdesc);
                open.putExtra("taskdate",getTaskdate);
                open.putExtra("key",getKey);
                context.startActivity(open);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myTasks.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tasktitle,taskdesc,taskdate,key;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tasktitle =(TextView) itemView.findViewById(R.id.tasktitle);
            taskdesc =(TextView) itemView.findViewById(R.id.taskdesc);
            taskdate =(TextView) itemView.findViewById(R.id.taskdate);



        }
    }

}
