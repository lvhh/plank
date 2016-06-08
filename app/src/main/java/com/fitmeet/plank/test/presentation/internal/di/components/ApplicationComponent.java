package com.fitmeet.plank.test.presentation.internal.di.components;

import android.content.Context;

import com.fitmeet.plank.test.domain.domain.executor.PostExecutionThread;
import com.fitmeet.plank.test.domain.domain.executor.ThreadExecutor;
import com.fitmeet.plank.test.domain.domain.repository.UserRepository;
import com.fitmeet.plank.test.presentation.internal.di.modules.ApplicationModule;
import com.fitmeet.plank.test.presentation.view.activity.BaseActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by lvhonghe on 16/6/7.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    UserRepository userRepository();
}
