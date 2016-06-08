package com.fitmeet.util;

import android.content.Context;
import android.os.Environment;

import com.snappydb.DB;
import com.snappydb.SnappyDB;
import com.snappydb.SnappydbException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lvhonghe on 16/6/1.
 */
@Singleton
public class DBUtil {

    private Context context;

    private DB plankDB;

    private static final String KEY_TIMESTAMP = "key_timestamp";
    private static final String KEY_DES = "key_des";
    private static final String KEY_TIMECOUNT = "key_timecount";



    @Inject
    public DBUtil(Context context) {
        this.context = context;
    }

    private void initDB() {
        try {
            plankDB = new SnappyDB.Builder(context)
                    .directory(Environment.getExternalStorageDirectory().getAbsolutePath())
                    .name("plank")
                    .build();
        } catch (SnappydbException e) {
            e.printStackTrace();
        }
    }

    

}
