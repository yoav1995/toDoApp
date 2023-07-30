package com.example.todoapp.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.Models.Task;
import com.example.todoapp.R;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private ArrayList<Task> tasks;
    private Context context;

   // private VehicleResultsCallBack vehicleResultsCallBack;
    public TaskAdapter(Context context, ArrayList<Task> tasks) {
        this.context = context;
        this.tasks =tasks;
    }

    @NonNull
    @Override
    public  TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item,parent,false);
        TaskViewHolder vehicleViewHolder = new  TaskViewHolder(view);
        return vehicleViewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull  TaskViewHolder holder, int position) {
        Task task=getItem(position);
        holder.task_LBL_title.setText(task.getTask());
        holder.checkBox.setChecked(task.isDone());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked())
                    task.setDone(true);
            }
        });
    }

    private Task getItem(int position) {
        return this.tasks.get(position);
    }


    @Override
    public int getItemCount() {

        return this.tasks == null ? 0 : this.tasks.size();
    }

    //public void setVehicleResultsCallBack(VehicleResultsCallBack vehicleResultsCallBack){
   //     this.vehicleResultsCallBack=vehicleResultsCallBack;
   // }
    public class TaskViewHolder extends RecyclerView.ViewHolder{
        private MaterialTextView task_LBL_title;
        private AppCompatCheckBox checkBox;



        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            task_LBL_title =itemView.findViewById(R.id.task_LBL_title);
            checkBox=itemView.findViewById(R.id.check_box);


        }

    }

}
