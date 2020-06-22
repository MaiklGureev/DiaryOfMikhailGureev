package com.example.diaryofmikhailgureev.adapters;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.activitys.AddOrEditTaskActivity;
import com.example.diaryofmikhailgureev.activitys.TaskDescriptionActivity;
import com.example.diaryofmikhailgureev.entities.Task;
import com.example.diaryofmikhailgureev.entities.TimeInterval;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimeIntervalAdapter extends RecyclerView.Adapter<TimeIntervalAdapter.TimeIntervalViewHolder> {

    private List<Task> tasks;
    private List<TimeInterval> timeIntervals = new CopyOnWriteArrayList<>();

    public TimeIntervalAdapter(List<Task> tasks,Calendar targetTime) {
        this.tasks = tasks;
        initTimeIntervalList(targetTime);
        decomposeTasksToIntervals();
    }

    @NonNull
    @Override
    public TimeIntervalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_interval_and_tasks_layout, parent, false);
        return new TimeIntervalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TimeIntervalViewHolder holder, int position) {
        holder.bind(timeIntervals.get(position));
    }

    @Override
    public int getItemCount() {
        return timeIntervals.size();
    }


    public void decomposeTasksToIntervals() {
        for (TimeInterval timeInterval : timeIntervals) {
            for (Task task : tasks) {
                timeInterval.addTaskToInterval(task);
            }
        }
    }

    public void initTimeIntervalList(Calendar targetTime) {

        Calendar time00 = new GregorianCalendar();
        Calendar time01 = new GregorianCalendar();
        Calendar time02 = new GregorianCalendar();
        Calendar time03 = new GregorianCalendar();
        Calendar time04 = new GregorianCalendar();
        Calendar time05 = new GregorianCalendar();
        Calendar time06 = new GregorianCalendar();
        Calendar time07 = new GregorianCalendar();
        Calendar time08 = new GregorianCalendar();
        Calendar time09 = new GregorianCalendar();
        Calendar time10 = new GregorianCalendar();
        Calendar time11 = new GregorianCalendar();
        Calendar time12 = new GregorianCalendar();
        Calendar time13 = new GregorianCalendar();
        Calendar time14 = new GregorianCalendar();
        Calendar time15 = new GregorianCalendar();
        Calendar time16 = new GregorianCalendar();
        Calendar time17 = new GregorianCalendar();
        Calendar time18 = new GregorianCalendar();
        Calendar time19 = new GregorianCalendar();
        Calendar time20 = new GregorianCalendar();
        Calendar time21 = new GregorianCalendar();
        Calendar time22 = new GregorianCalendar();
        Calendar time23 = new GregorianCalendar();

        time00.setTime(targetTime.getTime());
        time01.setTime(targetTime.getTime());
        time02.setTime(targetTime.getTime());
        time03.setTime(targetTime.getTime());
        time04.setTime(targetTime.getTime());
        time05.setTime(targetTime.getTime());
        time06.setTime(targetTime.getTime());
        time07.setTime(targetTime.getTime());
        time08.setTime(targetTime.getTime());
        time09.setTime(targetTime.getTime());
        time10.setTime(targetTime.getTime());
        time11.setTime(targetTime.getTime());
        time12.setTime(targetTime.getTime());
        time13.setTime(targetTime.getTime());
        time14.setTime(targetTime.getTime());
        time15.setTime(targetTime.getTime());
        time16.setTime(targetTime.getTime());
        time17.setTime(targetTime.getTime());
        time18.setTime(targetTime.getTime());
        time19.setTime(targetTime.getTime());
        time20.setTime(targetTime.getTime());
        time21.setTime(targetTime.getTime());
        time22.setTime(targetTime.getTime());
        time23.setTime(targetTime.getTime());

        time00.set(Calendar.HOUR_OF_DAY, 0);
        time01.set(Calendar.HOUR_OF_DAY, 1);
        time02.set(Calendar.HOUR_OF_DAY, 2);
        time03.set(Calendar.HOUR_OF_DAY, 3);
        time04.set(Calendar.HOUR_OF_DAY, 4);
        time05.set(Calendar.HOUR_OF_DAY, 5);
        time06.set(Calendar.HOUR_OF_DAY, 6);
        time07.set(Calendar.HOUR_OF_DAY, 7);
        time08.set(Calendar.HOUR_OF_DAY, 8);
        time09.set(Calendar.HOUR_OF_DAY, 9);
        time10.set(Calendar.HOUR_OF_DAY, 10);
        time11.set(Calendar.HOUR_OF_DAY, 11);
        time12.set(Calendar.HOUR_OF_DAY, 12);
        time13.set(Calendar.HOUR_OF_DAY, 13);
        time14.set(Calendar.HOUR_OF_DAY, 14);
        time15.set(Calendar.HOUR_OF_DAY, 15);
        time16.set(Calendar.HOUR_OF_DAY, 16);
        time17.set(Calendar.HOUR_OF_DAY, 17);
        time18.set(Calendar.HOUR_OF_DAY, 18);
        time19.set(Calendar.HOUR_OF_DAY, 19);
        time20.set(Calendar.HOUR_OF_DAY, 20);
        time21.set(Calendar.HOUR_OF_DAY, 21);
        time22.set(Calendar.HOUR_OF_DAY, 22);
        time23.set(Calendar.HOUR_OF_DAY, 23);

        timeIntervals.add(new TimeInterval(time00));
        timeIntervals.add(new TimeInterval(time01));
        timeIntervals.add(new TimeInterval(time02));
        timeIntervals.add(new TimeInterval(time03));
        timeIntervals.add(new TimeInterval(time04));
        timeIntervals.add(new TimeInterval(time05));
        timeIntervals.add(new TimeInterval(time06));
        timeIntervals.add(new TimeInterval(time07));
        timeIntervals.add(new TimeInterval(time08));
        timeIntervals.add(new TimeInterval(time09));
        timeIntervals.add(new TimeInterval(time10));
        timeIntervals.add(new TimeInterval(time11));
        timeIntervals.add(new TimeInterval(time12));
        timeIntervals.add(new TimeInterval(time13));
        timeIntervals.add(new TimeInterval(time14));
        timeIntervals.add(new TimeInterval(time15));
        timeIntervals.add(new TimeInterval(time16));
        timeIntervals.add(new TimeInterval(time17));
        timeIntervals.add(new TimeInterval(time18));
        timeIntervals.add(new TimeInterval(time19));
        timeIntervals.add(new TimeInterval(time20));
        timeIntervals.add(new TimeInterval(time21));
        timeIntervals.add(new TimeInterval(time22));
        timeIntervals.add(new TimeInterval(time23));


    }

    static final class TimeIntervalViewHolder extends RecyclerView.ViewHolder {

        private AppCompatTextView textViewTimeInterval;
        private AppCompatTextView textViewTitleAndTime;
        private TimeInterval timeInterval = new TimeInterval();

        public TimeIntervalViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTimeInterval = itemView.findViewById(R.id.text_view_time_interval);
            textViewTitleAndTime = itemView.findViewById(R.id.text_view_title_and_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!timeInterval.isEmpty()){
                        Intent myIntent = new Intent(v.getContext(), TaskDescriptionActivity.class);
                        myIntent.putExtra("timeInterval",timeInterval);
                        v.getContext().startActivity(myIntent);
                    }
                    else {
                        Intent myIntent = new Intent(v.getContext(), AddOrEditTaskActivity.class);
                        myIntent.putExtra("timeInterval",timeInterval);
                        myIntent.putExtra("typeNewAdding","inInterval");
                        v.getContext().startActivity(myIntent);
                    }
                }
            });
        }


        private void bind(@NonNull TimeInterval timeInterval) {
            textViewTimeInterval.setText(timeInterval.getFormattedInterval());
            textViewTitleAndTime.setText(timeInterval.getTextField());
            this.timeInterval.setTimeStart(timeInterval.getTimeStart());
            this.timeInterval.setTimeFinish(timeInterval.getTimeFinish());
            this.timeInterval.setTasks(timeInterval.getTasks());
        }
    }
}
