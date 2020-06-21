package com.example.diaryofmikhailgureev.Realm;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.example.diaryofmikhailgureev.entities.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;

public class RealmOperations {


    public synchronized static void createTask(Context context, final Task task) {
        Realm realm = ConnectorToRealm.getConnectionToDB(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Random rand = new Random();
//                    RealmResults<Task> realmResults = realm.where(Task.class).findAll();
//                    Log.i("RealmOperations", "realmResults.size()" + realmResults.size());
                int randomIntForKey = Math.abs(rand.nextInt());
                Log.i("RealmOperations", "randomIntForKey" + randomIntForKey);
                //realm.createObject(Task.class,randomIntForKey);
                Task taskForExecute = new Task();
                taskForExecute.setId(randomIntForKey);
                taskForExecute.setTitle(task.getTitle());
                taskForExecute.setDescription(task.getDescription());
                taskForExecute.setTimeStart(task.getTimeStart());
                taskForExecute.setTimeFinish(task.getTimeFinish());

                realm.insert(taskForExecute);
                Log.i("RealmOperations", "createTask");
            }
        });

    }

    public synchronized static List<Task> getTasks(Context context) {
        final List<Task> tasks = new ArrayList<>();
        Realm realm = ConnectorToRealm.getConnectionToDB(context);

            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<Task> realmResults = realm.where(Task.class).findAll();
                    int size = realmResults.size();
                    Log.i("RealmOperations", "realmResults=" + size);
                    if(realmResults!=null){
                        for (int a = 0; a < size; a++) {
                            tasks.add(realmResults.get(a));
                            Log.i("RealmOperations", "tasks size=" + tasks.get(a).toString());
                        }
                    }
                }
            });


        return tasks;
    }

    public synchronized static void updateTask(Context context, final Task task) {
        Realm realm = ConnectorToRealm.getConnectionToDB(context);

        Log.i("RealmOperations", "taskForExecute = "+task.getId()+" "+task.getTitle());
        final Task taskForExecute = realm.where(Task.class).equalTo("id", task.getId()).findFirst();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if(taskForExecute != null){
                    taskForExecute.setTitle(task.getTitle());
                    taskForExecute.setDescription(task.getDescription());
                    taskForExecute.setTimeStart(task.getTimeStart());
                    taskForExecute.setTimeFinish(task.getTimeFinish());
                    Log.i("RealmOperations", "updateTask");
                }

            }
        });


    }

    public synchronized static void deleteTask(Context context, final Task task) {
        Realm realm = ConnectorToRealm.getConnectionToDB(context);
        Log.i("RealmOperations", "deleteTask");
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Task taskForDelete = realm.where(Task.class)
                        .equalTo("id", task.getId())
                        .findFirst();
                if (taskForDelete!=null) {
                    taskForDelete.deleteFromRealm();
                } else {
                    Log.i("RealmOperations", "ничё не найдено для удаления");
                }
            }
        });

    }

    public synchronized static void dropDatabase(Context context) {
        Realm realm = ConnectorToRealm.getConnectionToDB(context);
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });
        Log.i("RealmOperations", "dropDatabase");
    }


}
