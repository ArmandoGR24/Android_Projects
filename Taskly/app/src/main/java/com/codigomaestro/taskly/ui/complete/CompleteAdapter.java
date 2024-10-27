package com.codigomaestro.taskly.ui.complete;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codigomaestro.taskly.R;
import com.codigomaestro.taskly.services.Customdb;

import java.util.List;
import java.util.Map;

public class CompleteAdapter extends RecyclerView.Adapter<CompleteAdapter.TaskViewHolder> {

    private List<Map<String, Object>> tasks_complete;
    private Customdb customdb = new Customdb();

    public CompleteAdapter(List<Map<String, Object>> tasks) {
        this.tasks_complete = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_complete, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Map<String, Object> task = tasks_complete.get(position);
        holder.taskName.setText((String) task.get("nombre"));
        holder.taskSubject.setText((String) task.get("materia"));
        holder.taskDeadline.setText((String) task.get("fecha_limite"));
        holder.taskDescription.setText((String) task.get("descripcion"));

        String documentID = (String) task.get("id");

        holder.taskCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(v.getContext(),"Tarea completada", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return tasks_complete.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, taskSubject, taskDeadline, taskDescription;
        Button taskCompleted;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_complete_name);
            taskSubject = itemView.findViewById(R.id.task_complete_subject);
            taskDeadline = itemView.findViewById(R.id.task_complete_deadline);
            taskDescription = itemView.findViewById(R.id.task_complete_description);
            taskCompleted = itemView.findViewById(R.id.btn_task_complete);
        }
    }
}