package com.fitmeet.plank.domain.executor;

import rx.Scheduler;

/**
 * Created by lvhonghe on 16/6/6.
 */
public interface PostExecutionThread {
    Scheduler getSubeduler();
}
