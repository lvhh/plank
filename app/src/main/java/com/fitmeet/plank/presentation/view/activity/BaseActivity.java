package com.fitmeet.plank.presentation.view.activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fitmeet.plank.presentation.AndroidApplication;
import com.fitmeet.plank.presentation.internal.di.components.ApplicationComponent;
import com.fitmeet.plank.presentation.internal.di.modules.ActivityModule;

/**
 * Created by lvhonghe on 16/6/7.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActicityModule() {
        return new ActivityModule(this);
    }
}
