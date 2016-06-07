package com.fitmeet.plank.domain.interactor;

/**
 * Created by lvhonghe on 16/6/6.
 */
public class DefaultSubscriber<T> extends rx.Subscriber {
    /**
     * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
     * <p/>
     * The {@link Observable} will not call this method if it calls {@link #onError}.
     */
    @Override
    public void onCompleted() {

    }

    /**
     * Notifies the Observer that the {@link Observable} has experienced an error condition.
     * <p/>
     * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
     * {@link #onCompleted}.
     *
     * @param e the exception encountered by the Observable
     */
    @Override
    public void onError(Throwable e) {

    }

    /**
     * Provides the Observer with a new item to observe.
     * <p/>
     * The {@link Observable} may call this method 0 or more times.
     * <p/>
     * The {@code Observable} will not call this method again after it calls either {@link #onCompleted} or
     * {@link #onError}.
     *
     * @param o the item emitted by the Observable
     */
    @Override
    public void onNext(Object o) {

    }
}
