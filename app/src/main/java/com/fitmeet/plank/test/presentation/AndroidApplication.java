package com.fitmeet.plank.test.presentation;

import android.app.Application;

import com.fitmeet.plank.test.presentation.internal.di.components.ApplicationComponent;
import com.fitmeet.plank.test.presentation.internal.di.components.DaggerApplicationComponent;
import com.fitmeet.plank.test.presentation.internal.di.modules.ApplicationModule;

/**
 * Created by lvhonghe on 16/6/7.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

}
