package com.example.diaryofmikhailgureev;

import com.example.diaryofmikhailgureev.entities.Task;

import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Calendar calendarStart = new GregorianCalendar(2020,01,01,10,00,00);
        Calendar calendarFinish = new GregorianCalendar(2020,01,01,12,00,00);
        Task task = new Task(123,"MyTask","MyDescription",calendarStart,calendarFinish);
        assertEquals("MyTask", task.getTitle());
        assertEquals("MyDescription", task.getDescription());
        assertEquals("Задача: MyTask", task.getFormattedTitle());
        assertEquals("01.02.2020 10:00 - 01.02.2020 12:00", task.getFormattedDateAndTime());
        assertEquals("10:00 - 12:00", task.getFormattedTime());

    }
}