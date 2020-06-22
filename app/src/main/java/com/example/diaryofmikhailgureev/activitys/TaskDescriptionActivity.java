package com.example.diaryofmikhailgureev.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.Realm.RealmOperations;
import com.example.diaryofmikhailgureev.adapters.TaskAndDescriptionAdapter;
import com.example.diaryofmikhailgureev.entities.Task;
import com.example.diaryofmikhailgureev.entities.TimeInterval;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskDescriptionActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private TimeInterval timeInterval;
    private TaskAndDescriptionAdapter taskAndDescriptionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_description);

        //find activity components
        toolbar = findViewById(R.id.toolbar_task_and_description);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.recycle_view_tasks_and_descriptions);
        mLayoutManager = new LinearLayoutManager(this);
        //inits
        openTasksListFromBundle();
        updateRecyclerView();
        setSupportActionBar(toolbar);

    }

    public void openTasksListFromBundle() {
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            timeInterval = (TimeInterval) bundle.get("timeInterval");
            Log.i("TaskDescriptionActivity", "ВЫЗВАЛ МЕТОД openTasksListFromBundle и достал бандл " + timeInterval.getTextField());
            toolbar.setSubtitle(timeInterval.getFormattedInterval());
            updateRecyclerView();
        }
    }

    public void updateRecyclerView(){
        taskAndDescriptionAdapter = new TaskAndDescriptionAdapter(timeInterval.getTasks());
        recyclerView.setAdapter(taskAndDescriptionAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    public void updateEditedTasks(){
        timeInterval.removeTasks();
        List<Task> taskList = RealmOperations.getTasks(this);
        for(Task task:taskList){
            timeInterval.addTaskToInterval(task);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateEditedTasks();
        updateRecyclerView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add) {
            Intent myIntent = new Intent(this, AddOrEditTaskActivity.class);
            myIntent.putExtra("timeInterval",timeInterval);
            myIntent.putExtra("typeNewAdding","inInterval");
            startActivity(myIntent);

        } else if (item.getItemId() == R.id.action_back_in_main_activity) {
            onBackPressed();
        }
        return true;
    }
}
