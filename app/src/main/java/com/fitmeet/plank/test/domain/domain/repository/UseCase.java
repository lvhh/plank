package com.fitmeet.plank.test.domain.domain.repository;

import com.fitmeet.plank.test.domain.domain.executor.PostExecutionThread;
import com.fitmeet.plank.test.domain.domain.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Created by lvhonghe on 16/6/6.
 */
public abstract class UseCase {

    private final ThreadExecutor mThreadExecutor;
    private final PostExecutionThread mPostExecutionThread;

    private Subscription mSubscription = Subscriptions.empty();

    protected UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        mThreadExecutor = threadExecutor;
        mPostExecutionThread = postExecutionThread;
    }

    protected abstract Observable buildUseCaseObservable();

    public void execute(Subscriber UseCaseSubscriber) {
        this.mSubscription = this
                .buildUseCaseObservable()
                .subscribeOn(Schedulers.from(mThreadExecutor))
                .observeOn(mPostExecutionThread.getSubeduler())
                .subscribe(UseCaseSubscriber);
    }

    public void unsubscribe() {
        if(!mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
    }

}
