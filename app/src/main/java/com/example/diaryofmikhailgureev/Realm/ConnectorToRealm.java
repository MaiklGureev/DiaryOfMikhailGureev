package com.example.diaryofmikhailgureev.Realm;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ConnectorToRealm {

    private static Realm realm;

    public synchronized static Realm getConnectionToDB(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("realm2.realm").build();
        Realm.setDefaultConfiguration(config);

        if (realm==null) {
            Log.i("ConnectorToRealm", "создаю новый");
            return realm = Realm.getDefaultInstance();
        }else if(realm.isClosed()){
            Log.i("ConnectorToRealm", "реалм закрылся,открываю");
            return realm = Realm.getDefaultInstance();
        }
        Log.i("ConnectorToRealm", "отдаю старый");
        return realm;
    }

    public synchronized static void closeRealm() {
        if (realm!=null &&!realm.isClosed()) {
            realm.close();
        }
    }
}


