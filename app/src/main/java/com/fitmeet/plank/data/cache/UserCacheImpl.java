package com.fitmeet.plank.data.cache;

import android.content.Context;

import com.fitmeet.plank.data.entity.UserEntity;
import com.fitmeet.plank.domain.executor.ThreadExecutor;

import java.io.File;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by lvhonghe on 16/6/6.
 */
@Singleton
public class UserCacheImpl implements UserCache {

    private static final String SETTING_FILE_NAME = "setting_file_name";
    private static final String SETTING_KEY_LAST_CACHE_UPDATE = "last_cache_update";

    private static final String DEFAULT_FILE_NAME = "user_";
    private static final long EXPIRATION_TIME = 60 * 10 * 1000;

    private final Context context;
    private final File cacheDir;
    private final JsonSerializer serializer;
    private final FileManager fileManager;
    private final ThreadExecutor threadExecutor;


    @Inject
    public UserCacheImpl(Context context,
                         JsonSerializer serializer,
                         FileManager fileManager,
                         ThreadExecutor threadExecutor) {
        if(context == null || serializer == null || fileManager == null || threadExecutor == null) {
            throw new IllegalArgumentException("Invalid null parameter");
        }
        this.context = context.getApplicationContext();
        this.cacheDir = this.context.getCacheDir();
        this.serializer = serializer;
        this.fileManager = fileManager;
        this.threadExecutor = threadExecutor;
    }


    @Override
    public Observable<UserEntity> get(int userId) {
        return Observable.create(subscriber -> {
            File userEntityFile = UserCacheImpl.this.buildFile(userId);
            String fileContent = UserCacheImpl.this.fileManager.readFileContent(userEntityFile);
            UserEntity userEntity = UserCacheImpl.this.serializer.deserialize(fileContent);

            if (userEntity != null) {
                subscriber.onNext(userEntity);
                subscriber.onCompleted();
            } else {
                subscriber.onError(new Exception());
            }
        });
    }

    @Override
    public void put(UserEntity userEntity) {
        if (userEntity != null) {
            File userEntityFile = this.buildFile(userEntity.getId());
            if(!isCached(userEntity.getId())) {
                String jsonString = this.serializer.serialize(userEntity);
                this.executeAsynchronously(new CacheWriter(this.fileManager, userEntityFile, jsonString));
                setLastCacheUpdateTimeMillis();
            }
        }
    }

    @Override
    public boolean isCached(int userId) {
        File userEntityFile = this.buildFile(userId);
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void evictAll() {

    }


    private File buildFile(int userId) {
        StringBuilder fileNameBuilder = new StringBuilder();
        fileNameBuilder.append(this.cacheDir.getPath());
        fileNameBuilder.append(File.separator);
        fileNameBuilder.append(DEFAULT_FILE_NAME);
        fileNameBuilder.append(userId);
        return new File(fileNameBuilder.toString());
    }

    private void setLastCacheUpdateTimeMillis() {
        long currentMillis = System.currentTimeMillis();
        this.fileManager.writeToPreferences(this.context, SETTING_FILE_NAME, SETTING_KEY_LAST_CACHE_UPDATE, currentMillis);
    }

    private long getLastCacheUpdateTimeMillis() {
        return this.fileManager.getFromPreferences(this.context, SETTING_FILE_NAME, SETTING_KEY_LAST_CACHE_UPDATE);
    }

    private void executeAsynchronously(Runnable runnable) {
        this.threadExecutor.execute(runnable);
    }


    private static class CacheWriter implements  Runnable {

        private final FileManager fileManager;
        private final File fileToWrite;
        private final String fileContent;

        public CacheWriter(FileManager fileManager, File fileToWrite, String fileContent) {
            this.fileManager = fileManager;
            this.fileToWrite = fileToWrite;
            this.fileContent = fileContent;
        }

        @Override
        public void run() {
            this.fileManager.writeToFile(fileToWrite, fileContent);
        }
    }

    private static class CacheEvictor implements Runnable {

        private final FileManager fileManager;
        private final File cacheDir;

        CacheEvictor(FileManager fileManager, File cacheDir) {
            this.fileManager = fileManager;
            this.cacheDir = cacheDir;
        }

        @Override
        public void run() {
            this.fileManager.clearDirectory(this.cacheDir);
        }
    }
}
