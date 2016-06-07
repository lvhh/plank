package com.fitmeet.plank.presentation.internal.di.modules;

import android.app.Activity;

import com.fitmeet.plank.presentation.internal.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lvhonghe on 16/6/7.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    @Provides @PerActivity Activity activity() {
        return  this.activity;
    }
}
