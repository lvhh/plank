package com.fitmeet.plank.data.cache;

import android.content.Context;
import android.content.SharedPreferences;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lvhonghe on 16/6/6.
 */

@Singleton
public class FileManager {

    @Inject
    public FileManager() {}

    public void writeToFile(File file, String fileContent) {
        if(!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(fileContent);
                writer.close();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    public String readFileContent(File file) {
        StringBuilder fileContentBuilder = new StringBuilder();
        if(file.exists()) {
            String stringLine;
            try {
                FileReader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                while ((stringLine = bufferedReader.readLine()) != null) {
                    fileContentBuilder.append(stringLine + '\n');
                }
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileContentBuilder.toString();
    }

    public boolean exists(File file) {
        return file.exists();
    }

    public void clearDirectory(File directory) {
        if(directory.exists()) {
            for(File file : directory.listFiles()) {
                file.delete();
            }
        }
    }

    public void writeToPreferences(Context context, String preferenceFileName, String key, long value) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putLong(key, value);
        editor.apply();

    }

    public long getFromPreferences(Context context, String preferenceFileName, String key ) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceFileName, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, 0);
    }
}
