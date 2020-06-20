package com.example.diaryofmikhailgureev.Realm;

import android.content.Context;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class ConnectorToRealm {

    private static Realm realm;

    public static Realm getConnectionToDB(Context context) {
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myrealm.realm").build();
        Realm.setDefaultConfiguration(config);

        if (realm==null) {
            Log.i("getConnectionToDB", "создаю новый");
            return realm = Realm.getDefaultInstance();
        }else if(realm.isClosed()){
            Log.i("getConnectionToDB", "реалм закрылся,открываю");
            return realm = Realm.getDefaultInstance();
        }
        Log.i("getConnectionToDB", "отдаю старый");
        return realm;
    }

    public static void closeRealm() {
        if (!realm.isClosed()) {
            realm.close();
        }
    }
}


