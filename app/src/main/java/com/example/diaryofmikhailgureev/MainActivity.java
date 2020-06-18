package com.example.diaryofmikhailgureev;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Task> taskList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        initTasks();
        TimeIntervalAdapter timeIntervalAdapter = new TimeIntervalAdapter(taskList);
        recyclerView.setAdapter(timeIntervalAdapter);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
    }



    public void initTasks(){
        Calendar timeStart = new GregorianCalendar(2020,Calendar.JUNE,18,15,00);
        Calendar timeFinish = new GregorianCalendar(2020,Calendar.JUNE,18,16,30);
        String title = "сходить в магазин";
        String description = "купить колбасы";
        taskList.add(new Task(title,description,timeStart,timeFinish));

        Calendar timeStart2 = new GregorianCalendar(2020,Calendar.JUNE,18,10,00);
        Calendar timeFinish2 = new GregorianCalendar(2020,Calendar.JUNE,18,13,30);
        String title2 = "выйти погулять";
        String description2 = "пофотографироваться";
        taskList.add(new Task(title2,description2,timeStart2,timeFinish2));
    }
}
