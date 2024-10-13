package com.codigomaestro.taskly.ui.tareas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codigomaestro.taskly.R;

import java.util.List;
import java.util.Map;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.TaskViewHolder> {

    private List<Map<String, Object>> tasks;

    public TasksAdapter(List<Map<String, Object>> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Map<String, Object> task = tasks.get(position);
        holder.taskName.setText((String) task.get("nombre"));
        holder.taskSubject.setText((String) task.get("materia"));
        holder.taskDeadline.setText((String) task.get("fecha_limite"));
        holder.taskDescription.setText((String) task.get("descripcion"));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        TextView taskName, taskSubject, taskDeadline, taskDescription;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            taskName = itemView.findViewById(R.id.task_name);
            taskSubject = itemView.findViewById(R.id.task_subject);
            taskDeadline = itemView.findViewById(R.id.task_deadline);
            taskDescription = itemView.findViewById(R.id.task_description);
        }
    }
}