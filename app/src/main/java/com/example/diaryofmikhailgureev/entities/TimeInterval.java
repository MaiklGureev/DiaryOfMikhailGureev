package com.example.diaryofmikhailgureev.entities;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

public class TimeInterval implements Parcelable {

    private Calendar timeStart = new GregorianCalendar();
    private Calendar timeFinish = new GregorianCalendar();
    private String timeStartString;
    private String timeFinishString;
    private List<Task> tasks = new CopyOnWriteArrayList<>();
    private String textField = "";
    private int taskCounter = 1;


    protected TimeInterval(Parcel in) {
        tasks = in.createTypedArrayList(Task.CREATOR);
        textField = in.readString();
        taskCounter = in.readInt();
        timeStartString = in.readString();
        timeFinishString = in.readString();
        timeStart.setTimeInMillis(Long.valueOf(timeStartString));
        timeFinish.setTimeInMillis(Long.valueOf(timeFinishString));
    }

    public static final Creator<TimeInterval> CREATOR = new Creator<TimeInterval>() {
        @Override
        public TimeInterval createFromParcel(Parcel in) {
            return new TimeInterval(in);
        }

        @Override
        public TimeInterval[] newArray(int size) {
            return new TimeInterval[size];
        }
    };

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeFinish(Calendar timeFinish) {
        this.timeFinish = timeFinish;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void setTextField(String textField) {
        this.textField = textField;
    }

    public void setTaskCounter(int taskCounter) {
        this.taskCounter = taskCounter;
    }

    public Calendar getTimeStart() {
        return timeStart;
    }

    public Calendar getTimeFinish() {
        return timeFinish;
    }

    public int getTaskCounter() {
        return taskCounter;
    }

    public TimeInterval() {
    }

    public TimeInterval(Calendar timeStart) {
        this.timeStart.setTime(timeStart.getTime());
        timeFinish.setTime(timeStart.getTime());
        timeFinish.add(Calendar.HOUR_OF_DAY, 1);
//        Log.i("MY DEBUG","timeStart "+this.timeStart.getTime());
//        Log.i("MY DEBUG","timeFinish "+timeFinish.getTime());
    }


    public void addTaskToInterval(Task task) {
        Log.i("MY DEBUG", "ВЫЗВАЛ МЕТОД ДОБАВЛЕНИЯ ЗАДАЧ");
        if (timeAndDayIsRight(task)) {
            tasks.add(task);
            addText(task);
            Log.i("timeAndDayIsRight", "ДОБАВИЛ ЗАДАЧУ");
        } else {
            Log.i("timeAndDayIsRight", "НЕ ДОБАВИЛ ЗАДАЧУ");
        }
    }


    public boolean timeAndDayIsRight(Task task) {

        Log.i("timeAndDayIsRight", "timeAndDayIsRight");
        Log.i("timeAndDayIsRight", "timeAndDayIsRight INTERVAL timeStart " + timeStart.getTime());
        Log.i("timeAndDayIsRight", "timeAndDayIsRight INTERVAL timeFinish " + timeFinish.getTime());
        Log.i("timeAndDayIsRight", "timeAndDayIsRight TASK timeStart " + task.getTimeStart().getTime());
        Log.i("timeAndDayIsRight", "timeAndDayIsRight TASK timeFinish " + task.getTimeFinish().getTime());
        Log.i("timeAndDayIsRight", "timeAndDayIsRight TASK title " + task.getTitle());

        // after после
        // before до
        //boolean after(Date date) - если объект класса Date содержит более позднюю дату, чем указано в параметре, то возвращается true
        //boolean before(Date date) - если объект класса Date содержит более раннюю дату, чем указано в параметре, то возвращается true

        timeStart.set(Calendar.SECOND, 00);
        timeFinish.set(Calendar.MILLISECOND, 00);

        //если время задачи внутри интервала
        if ((task.getTimeStart().after(timeStart) && task.getTimeFinish().before(timeFinish))) {
            Log.i("timeAndDayIsRight", "условие 1 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        //если время равно интервалу
        if ((task.getTimeStart().equals(timeStart) && task.getTimeFinish().equals(timeFinish))) {
            Log.i("timeAndDayIsRight", "условие 2 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        if ((task.getTimeStart().equals(timeStart) && (task.getTimeFinish().after(timeStart)) && task.getTimeFinish().after(timeFinish))) {
            Log.i("timeAndDayIsRight", "условие 3 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        if (timeStart.before(task.getTimeStart())&& timeFinish.after(task.getTimeStart())&&timeFinish.before(task.getTimeFinish())) {
            Log.i("timeAndDayIsRight", "условие 4 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        if ((task.getTimeStart().before(timeStart) && task.getTimeFinish().after(timeStart))) {
            Log.i("timeAndDayIsRight", "условие 5 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        if ((task.getTimeStart().before(timeFinish) && task.getTimeFinish().equals(timeFinish))) {
            Log.i("timeAndDayIsRight", "условие 6 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }

        if (task.getTimeStart().before(timeStart)
                && task.getTimeFinish().after(timeFinish)
                && !task.getTimeFinish().equals(timeFinish)
                && !task.getTimeStart().equals(timeStart)) {
            Log.i("timeAndDayIsRight", "условие 6 " + task.getTitle() + " " + getFormattedInterval());
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        if (tasks.size() == 0) {
            return true;
        }
        return false;
    }

    public String getTextField() {
        return textField;
    }

    public String getFormattedInterval() {
        String time1 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeStart.getTime());
        String time2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeFinish.getTime());
        String timeFinal = String.format("%s - %s", time1, time2);
        return timeFinal;
    }

    public void addText(Task task) {
        if (!textField.equals("")) {
            textField += String.format("\n%d. Задача: %s\n    Время: %s",
                    taskCounter, task.getTitle(), task.getFormattedTime());
        } else {
            textField += String.format("%d. Задача: %s\n    Время: %s",
                    taskCounter, task.getTitle(), task.getFormattedTime());
        }
        taskCounter++;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void removeTasks() {

        tasks.clear();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(tasks);
        dest.writeString(textField);
        dest.writeInt(taskCounter);
        dest.writeString(String.valueOf(timeStart.getTimeInMillis()));
        dest.writeString(String.valueOf(timeFinish.getTimeInMillis()));
    }

}
