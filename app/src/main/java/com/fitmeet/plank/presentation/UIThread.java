package com.fitmeet.plank.presentation;

import com.fitmeet.plank.domain.executor.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Scheduler;

/**
 * Created by lvhonghe on 16/6/7.
 */

@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {}

    @Override
    public Scheduler getSubeduler() {
        return null;
    }
}
