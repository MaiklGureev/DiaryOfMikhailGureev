package com.example.diaryofmikhailgureev.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.entities.Task;

import java.util.List;

public class TaskAndDescriptionAdapter extends RecyclerView.Adapter<TaskAndDescriptionAdapter.TaskAndDescriptionViewHolder> {

    private List<Task> tasks;

    public TaskAndDescriptionAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    @NonNull
    @Override
    public TaskAndDescriptionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_with_description_layout, parent, false);
        return new TaskAndDescriptionAdapter.TaskAndDescriptionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAndDescriptionViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static final class TaskAndDescriptionViewHolder extends RecyclerView.ViewHolder{

        private int id;
        private String title;
        private String descriptionAndTime;

        private TextView textViewTitle;
        private TextView textViewDescriptionAndTime;

        public TaskAndDescriptionViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.text_view_title);
            textViewDescriptionAndTime = itemView.findViewById(R.id.text_view_description_and_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"Открыть окно для редактирования",Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void bind(@NonNull Task task){
            id = task.getId();
            title = task.getFormattedTitle();
            descriptionAndTime = task.getFormattedDescriptionAndTime();
            textViewTitle.setText(title);
            textViewDescriptionAndTime.setText(descriptionAndTime);
        }
    }

}
