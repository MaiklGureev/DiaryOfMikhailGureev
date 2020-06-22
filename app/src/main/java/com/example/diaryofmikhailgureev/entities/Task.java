package com.example.diaryofmikhailgureev.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Task extends RealmObject implements  Parcelable {

    @PrimaryKey private int id;
    private String title;
    private String description;
    private String timeStart;
    private String timeFinish;

    public Task() {
    }

    public Task(int id, String title, String description, Calendar timeStart, Calendar timeFinish) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.timeStart = String.valueOf(timeStart.getTime().getTime());
        this.timeFinish =String.valueOf(timeFinish.getTime().getTime());
    }

    public Task(String title, String description, Calendar timeStart, Calendar timeFinish) {
        this.title = title;
        this.description = description;
        this.timeStart = String.valueOf(timeStart.getTime().getTime());
        this.timeFinish =String.valueOf(timeFinish.getTime().getTime());
    }

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        description = in.readString();
        timeStart = in.readString();
        timeFinish = in.readString();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public String getFormattedTime() {
        Calendar timeStart = new GregorianCalendar();
        Calendar timeFinish = new GregorianCalendar();
        timeStart.setTime(new Date(Long.valueOf(this.timeStart)));
        timeFinish.setTime(new Date(Long.valueOf(this.timeFinish)));
        String time1 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeStart.getTime());
        String time2 = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeFinish.getTime());
        String timeFinal = String.format("%s - %s", time1, time2);
        return timeFinal;
    }

    public String getFormattedDateAndTime() {
        Calendar timeStart = new GregorianCalendar();
        Calendar timeFinish = new GregorianCalendar();
        timeStart.setTime(new Date(Long.valueOf(this.timeStart)));
        timeFinish.setTime(new Date(Long.valueOf(this.timeFinish)));
        String time1 = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(timeStart.getTime());
        String time2 = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(timeFinish.getTime());
        String timeFinal = String.format("%s - %s", time1, time2);
        return timeFinal;
    }

    public String getFormattedTitle() {
        String timeFinal = String.format("Задача: %s", getTitle());
        return timeFinal;
    }

    public String getFormattedDescriptionAndTime() {
        String timeFinal = String.format("Описание: %s\nВремя: %s", getDescription(), getFormattedTime());
        return timeFinal;
    }

    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public void setTimeFinish(String timeFinish) {
        this.timeFinish = timeFinish;
    }

    public Calendar getTimeStart() {
        Calendar timeStart = new GregorianCalendar();
        timeStart.setTime(new Date(Long.valueOf(this.timeStart)));
        return timeStart;
    }

    public Calendar getTimeFinish() {

        Calendar timeFinish = new GregorianCalendar();
        timeFinish.setTime(new Date(Long.valueOf(this.timeFinish)));
        return timeFinish;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTimeStart(Calendar timeStart) {
        this.timeStart = String.valueOf(timeStart.getTime().getTime());
    }

    public void setTimeFinish(Calendar timeFinish) {
        this.timeFinish =String.valueOf(timeFinish.getTime().getTime());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(timeStart);
        dest.writeString(timeFinish);
    }
}
