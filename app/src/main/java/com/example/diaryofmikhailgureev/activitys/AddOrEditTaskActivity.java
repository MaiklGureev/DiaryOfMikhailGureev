package com.example.diaryofmikhailgureev.activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.diaryofmikhailgureev.R;
import com.example.diaryofmikhailgureev.Realm.ConnectorToRealm;
import com.example.diaryofmikhailgureev.Realm.RealmOperations;
import com.example.diaryofmikhailgureev.entities.Task;
import com.example.diaryofmikhailgureev.entities.TimeInterval;
import com.google.android.material.bottomnavigation.BottomNavigationMenu;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.List;

public class AddOrEditTaskActivity extends AppCompatActivity {

    private Task task;
    private TimeInterval timeInterval;
    private boolean itIsEdit = false;
    private Calendar dateAndTimeStart;
    private Calendar dateAndTimeFinish;

    private Button buttonAddOrEditTask;
    private Button buttonSelectDateStart;
    private Button buttonSelectTimeStart;
    private Button buttonSelectDateFinish;
    private Button buttonSelectTimeFinish;
    private TextInputEditText inputTextTitle;
    private TextInputEditText inputTextDescription;
    private Toolbar toolbarAddOrEditTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_or_edit_task);

        //find activity components
        dateAndTimeStart = Calendar.getInstance();
        dateAndTimeFinish = Calendar.getInstance();
        buttonSelectDateStart = findViewById(R.id.button_start_date);
        buttonSelectTimeStart = findViewById(R.id.button_start_time);
        buttonSelectDateFinish = findViewById(R.id.button_finish_date);
        buttonSelectTimeFinish = findViewById(R.id.button_finish_time);
        buttonAddOrEditTask = findViewById(R.id.button_add_or_edit_task);
        inputTextDescription = findViewById(R.id.text_input_field_description);
        inputTextTitle = findViewById(R.id.text_input_field_title);
        toolbarAddOrEditTask = findViewById(R.id.toolbar_add_or_edit_task);

        openTasksListFromBundle(View.inflate(this, R.layout.activity_add_or_edit_task, null));

        //init variables
        buttonAddOrEditTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = inputTextTitle.getText().toString();
                String description = inputTextDescription.getText().toString();
                if (!title.isEmpty() && !description.isEmpty()&&datesIsCorrect()) {
                    if (!itIsEdit ) {
                        task = new Task(title, description, dateAndTimeStart, dateAndTimeFinish);
                        RealmOperations.createTask(v.getContext(), task);
                        Toast.makeText(v.getContext(), "Задача создана!", Toast.LENGTH_LONG).show();
                    } else {
                        task = new Task(task.getId(), title, description, dateAndTimeStart, dateAndTimeFinish);
                        RealmOperations.updateTask(v.getContext(), task);
                        Toast.makeText(v.getContext(), "Задача изменена!", Toast.LENGTH_LONG).show();

                    }
                    onBackPressed();
//                    inputTextTitle.setText("");
//                    inputTextDescription.setText("");
//
//                    List<Task> tasks = RealmOperations.getTasks(v.getContext());
//                    String message = "\n";
//                    for (Task task0 : tasks) {
//                        message += task0.getId() + " " + task0.getTitle()
//                                + " " + task0.getDescription()
//                                + " " + task0.getFormattedDateAndTime() + "\n";
//                        Log.i("buttonAddOrEditTask", message);
//                        inputTextDescription.setText(message);
//                    }

                } else {
                    if(!datesIsCorrect()){
                        Toast.makeText(v.getContext(), "Неверно задан временной интервал!", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(v.getContext(), "Заполните все поля!", Toast.LENGTH_LONG).show();
                    }
                }

            }
        });


        buttonSelectDateStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v, dateAndTimeStart);
            }
        });

        buttonSelectTimeStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v, dateAndTimeStart);
            }
        });

        buttonSelectDateFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate(v, dateAndTimeFinish);
            }
        });

        buttonSelectTimeFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTime(v, dateAndTimeFinish);
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
        //ConnectorToRealm.closeRealm();
    }


    // отображаем диалоговое окно для выбора даты
    public void setDate(View v, final Calendar dateAndTime) {

        // установка обработчика выбора даты
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                dateAndTime.set(Calendar.YEAR, year);
                dateAndTime.set(Calendar.MONTH, monthOfYear);
                dateAndTime.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        };
        new DatePickerDialog(this, d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH))
                .show();

    }

    // отображаем диалоговое окно для выбора времени
    public void setTime(View v, final Calendar dateAndTime) {
        // установка обработчика выбора времени
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
                dateAndTime.set(Calendar.MINUTE, minute);
            }
        };

        new TimePickerDialog(this, t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE), true)
                .show();
    }

    public void openTasksListFromBundle(View view) {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String typeNewAdding = bundle.getString("typeNewAdding");
            if (typeNewAdding.equals("edit")) {
                itIsEdit = true;
                task = (Task) bundle.get("task");
                inputTextTitle.setText(task.getTitle());
                inputTextDescription.setText(task.getDescription());
                dateAndTimeStart = task.getTimeStart();
                dateAndTimeFinish = task.getTimeFinish();
                toolbarAddOrEditTask.setTitle("Обновить задачу");
                buttonAddOrEditTask.setText("Обновить");
                setSupportActionBar(toolbarAddOrEditTask);
            } else if (typeNewAdding.equals("inInterval")) {
                timeInterval = (TimeInterval) bundle.get("timeInterval");
                dateAndTimeStart = timeInterval.getTimeStart();
                dateAndTimeFinish = timeInterval.getTimeFinish();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_delete) {
            RealmOperations.deleteTask(this, task);
            Toast.makeText(this, "Удалено!", Toast.LENGTH_LONG).show();
        } else if (item.getItemId() == R.id.action_back) {

        }
        //onBackPressed();
        return true;
    }

    public boolean datesIsCorrect() {
        if (dateAndTimeStart.before(dateAndTimeFinish) || dateAndTimeStart.equals(dateAndTimeFinish)) {
            return true;
        }
        return false;
    }
}
