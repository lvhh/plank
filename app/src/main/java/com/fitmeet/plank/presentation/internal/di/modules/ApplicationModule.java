package com.fitmeet.plank.presentation.internal.di.modules;

import android.content.Context;

import com.fitmeet.plank.data.cache.UserCache;
import com.fitmeet.plank.data.cache.UserCacheImpl;
import com.fitmeet.plank.data.executor.JobExecutor;
import com.fitmeet.plank.data.repository.UserDataRepository;
import com.fitmeet.plank.domain.executor.PostExecutionThread;
import com.fitmeet.plank.domain.executor.ThreadExecutor;
import com.fitmeet.plank.domain.repository.UserRepository;
import com.fitmeet.plank.presentation.AndroidApplication;
import com.fitmeet.plank.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lvhonghe on 16/6/7.
 */
@Module
public class ApplicationModule {

    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {this.application = application;}

    @Provides @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    UserCache provideUserCache(UserCacheImpl userCache) {
        return userCache;
    }

    @Provides @Singleton
    UserRepository provideUserRepository(UserDataRepository userDataRepository) {
        return userDataRepository;
    }
}
