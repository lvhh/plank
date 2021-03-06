package com.fitmeet.plank.test.data.data.cache;

import com.fitmeet.plank.test.data.data.entity.UserEntity;

import rx.Observable;

/**
 * Created by lvhonghe on 16/6/6.
 */
public interface UserCache {

    Observable<UserEntity> get(final int userId);

    void put(UserEntity userEntity);

    boolean isCached(final int userId);

    boolean isExpired();

    void evictAll();
}
