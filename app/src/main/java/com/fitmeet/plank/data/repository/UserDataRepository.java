package com.fitmeet.plank.data.repository;

import com.fitmeet.plank.data.repository.datasource.UserDataStoreFactory;
import com.fitmeet.plank.domain.User;
import com.fitmeet.plank.domain.repository.UserRepository;

import java.util.List;

import rx.Observable;

/**
 * Created by lvhonghe on 16/6/6.
 */
public class UserDataRepository implements UserRepository {

    private final UserDataStoreFactory

    @Override
    public Observable<List<User>> users() {
        return null;
    }

    @Override
    public Observable<User> user(int userId) {
        return null;
    }
}
