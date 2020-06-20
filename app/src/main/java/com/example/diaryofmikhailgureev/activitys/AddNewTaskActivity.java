package com.example.diaryofmikhailgureev.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.Realm.ConnectorToRealm;
import com.example.diaryofmikhailgureev.Realm.RealmOperations;
import com.example.diaryofmikhailgureev.entities.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.PriorityQueue;

public class AddNewTaskActivity extends AppCompatActivity {

    TextView currentDateTime;
    Calendar dateAndTime = Calendar.getInstance();

    private Button buttonAddNewTask;
    private Button buttonSelectDateStart;
    private Button buttonSelectTimeStart;
    private Button buttonSelectDateFinish;
    private Button buttonSelectTimeFinish;
    private TextInputEditText inputTextTitle;
    private TextInputEditText inputTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_task);

        //currentDateTime = findViewById(R.id.currentDateTime);
        //setInitialDateTime();

        buttonAddNewTask = findViewById(R.id.button_add_new_task);
        inputTextDescription = findViewById(R.id.text_input_field_description);

        buttonAddNewTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Calendar timeStart = new GregorianCalendar(2020, Calendar.JUNE, 18, 15, 00);
//                Calendar timeFinish = new GregorianCalendar(2020, Calendar.JUNE, 18, 16, 30);
//                String title = "сходить в магазин";
//                String description = "купить колбасы";
//                Task task1 = new Task(title, description, timeStart.getTime(), timeFinish.getTime());
//
//                Calendar timeStart2 = new GregorianCalendar(2020, Calendar.JUNE, 18, 10, 00);
//                Calendar timeFinish2 = new GregorianCalendar(2020, Calendar.JUNE, 18, 15, 30);
//                String title2 = "выйти погулять";
//                String description2 = "пофотографироваться";
//                Task task2 = new Task(title2, description2, timeStart2.getTime(), timeFinish2.getTime());
//
//                Calendar timeStart3 = new GregorianCalendar(2020, Calendar.JUNE, 19, 10, 30);
//                Calendar timeFinish3 = new GregorianCalendar(2020, Calendar.JUNE, 19, 15, 30);
//                String title3 = "бубубубубубубу";
//                String description3 = "договориться о встрече с Васей";
//                Task task3 = new Task(title3, description3, timeStart3.getTime(), timeFinish3.getTime());
//                task3.setId(0);
//                RealmOperations.deleteTask(v.getContext(),task3);

//                RealmOperations.createTask(v.getContext(), task1);
//                RealmOperations.createTask(v.getContext(), task2);
//                RealmOperations.createTask(v.getContext(), task3);

                List<Task> tasks = RealmOperations.getTasks(v.getContext());
                String message = new String();
                for(Task task:tasks){
                    message += task.getId()+" "+task.getTitle()+ "\n";
                    Log.i("buttonAddNewTask",message);
                }
               inputTextDescription.setText(message);


            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        return super.onCreateView(parent, name, context, attrs);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ConnectorToRealm.closeRealm();
    }

    // отображаем диалоговое окно для выбора даты
    public void setDate(View v) {

        // установка обработчика выбора даты
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                setInitialDateTime();
            }
        };
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v) {
        // установка обработчика выбора времени
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateAndTime.set(Calendar.MINUTE, minute);
                setInitialDateTime();
            }
        };

        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    // установка начальных даты и времени
    private void setInitialDateTime() {
        currentDateTime.setText(DateUtils.formatDateTime(this,
                dateAndTime.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }


}
