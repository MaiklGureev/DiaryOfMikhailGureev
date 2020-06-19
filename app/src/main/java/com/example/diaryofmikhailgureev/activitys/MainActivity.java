package com.example.diaryofmikhailgureev.activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.enitys.Task;
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
    Calendar time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //find activity components
        recyclerView = findViewById(R.id.time_intervals_recycler_view);
        calendarView =  findViewById(R.id.calendar_view);

        //init variables
        taskList = new ArrayList<>();
        mLayoutManager = new LinearLayoutManager(this);
        time =  new GregorianCalendar();
        time.setTime( new Date(calendarView.getDate()));
        time.set(Calendar.HOUR_OF_DAY,00);
        time.set(Calendar.MINUTE,00);

        //init
        initTasks(taskList);
        initCalendarView();
        updateRecyclerView(time);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0,0,0,"Добавить задачу");
        menu.add(0,1,0,"Выйти");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==0){
            Intent myIntent = new Intent(this, AddNewTaskActivity.class);
            this.startActivity(myIntent);
            //Toast.makeText(this,"Задача добавлена!",Toast.LENGTH_LONG).show();
        }else if(item.getItemId()==1){
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        return super.onOptionsItemSelected(item);
    }

    public void initTasks(List<Task> taskList){
        Calendar timeStart = new GregorianCalendar(2020,Calendar.JUNE,18,15,00);
        Calendar timeFinish = new GregorianCalendar(2020,Calendar.JUNE,18,16,30);
        String title = "сходить в магазин";
        String description = "купить колбасы";
        taskList.add(new Task(title,description,timeStart,timeFinish));

        Calendar timeStart2 = new GregorianCalendar(2020,Calendar.JUNE,18,10,00);
        Calendar timeFinish2 = new GregorianCalendar(2020,Calendar.JUNE,18,15,30);
        String title2 = "выйти погулять";
        String description2 = "пофотографироваться";
        taskList.add(new Task(title2,description2,timeStart2,timeFinish2));

        Calendar timeStart3 = new GregorianCalendar(2020,Calendar.JUNE,19,10,30);
        Calendar timeFinish3 = new GregorianCalendar(2020,Calendar.JUNE,19,15,30);
        String title3 = "повидаться с другом";
        String description3 = "договориться о встрече с Васей";
        taskList.add(new Task(title3,description3,timeStart3,timeFinish3));
    }

    public void initCalendarView(){
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                 Log.i("MY DEBUG", "ВЫЗВАЛ МЕТОД onSelectedDayChange");
                Calendar selectedTime = new GregorianCalendar(year,month,dayOfMonth);
                selectedTime.set(Calendar.HOUR_OF_DAY,00);
                selectedTime.set(Calendar.MINUTE,00);
                updateRecyclerView(selectedTime);
            }
        });

    }

    public void updateRecyclerView(Calendar targetTime){
         Log.i("MY DEBUG", "ВЫЗВАЛ МЕТОД updateRecyclerView");
         Log.i("MY DEBUG", "targetTime " + targetTime.getTime());
        timeIntervalAdapter = new TimeIntervalAdapter(taskList,targetTime);
        recyclerView.setAdapter(timeIntervalAdapter);
        recyclerView.setLayoutManager(mLayoutManager);
    }
}
