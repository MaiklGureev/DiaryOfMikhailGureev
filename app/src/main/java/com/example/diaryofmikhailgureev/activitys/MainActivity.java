package com.example.diaryofmikhailgureev.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.Realm.ConnectorToRealm;
import com.example.diaryofmikhailgureev.Realm.RealmOperations;
import com.example.diaryofmikhailgureev.entities.Task;
import com.example.diaryofmikhailgureev.adapters.TimeIntervalAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CalendarView calendarView;
    private List<Task> taskList;
    private TimeIntervalAdapter timeIntervalAdapter;
    private LinearLayoutManager mLayoutManager;
    private Calendar time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //find activity components
        recyclerView = findViewById(R.id.time_intervals_recycler_view);
        calendarView = findViewById(R.id.calendar_view);

        //init variables
        taskList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        time = new GregorianCalendar();
        time.setTime(new Date(calendarView.getDate()));
        time.set(Calendar.HOUR_OF_DAY, 00);
        time.set(Calendar.MINUTE, 00);
        time.set(Calendar.SECOND, 00);

        //init
        initTasks(taskList);
        initCalendarView();
        updateRecyclerView(time);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "Добавить задачу");
        menu.add(0, 1, 0, "Очистить ежедневник");
        menu.add(0, 2, 0, "Выйти");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == 0) {
            Intent myIntent = new Intent(this, AddOrEditTaskActivity.class);
            this.startActivity(myIntent);
            //Toast.makeText(this,"Задача добавлена!",Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == 1) {
            RealmOperations.dropDatabase(this);
            initTasks(taskList);
            updateRecyclerView(time);
            Toast.makeText(this, "Очищено!", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == 2) {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        return super.onOptionsItemSelected(item);
    }

    public void initTasks(List<Task> taskList) {
        taskList = RealmOperations.getTasks(this);
        this.taskList = taskList;
        Log.i("initTasks", "taskList.size() = "+taskList.size());
    }

    public void initCalendarView() {
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.i("initCalendarView", "ВЫЗВАЛ МЕТОД onSelectedDayChange");
                Calendar selectedTime = new GregorianCalendar(year, month, dayOfMonth);
                selectedTime.set(Calendar.HOUR_OF_DAY, 00);
                selectedTime.set(Calendar.MINUTE, 00);
                initTasks(taskList);
                time = selectedTime;
                updateRecyclerView(time);
            }
        });

    }

    public void updateRecyclerView(Calendar targetTime) {
        Log.i("updateRecyclerView", "ВЫЗВАЛ МЕТОД updateRecyclerView");
        Log.i("updateRecyclerView", "targetTime " + targetTime.getTime());
        timeIntervalAdapter = new TimeIntervalAdapter(taskList, targetTime);
        recyclerView.setAdapter(timeIntervalAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectorToRealm.closeRealm();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity", "ВЫЗВАЛ МЕТОД onResume");
        initTasks(taskList);
        initCalendarView();
        updateRecyclerView(time);
    }
}
