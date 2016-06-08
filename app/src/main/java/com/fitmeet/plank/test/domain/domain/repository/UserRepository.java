package com.fitmeet.plank.test.domain.domain.repository;

import com.fitmeet.plank.test.domain.domain.User;

import java.util.List;
import rx.Observable;

/**
 * Created by lvhonghe on 16/6/6.
 */
public interface UserRepository {

    Observable<List<User>> users();

    Observable<User> user(final int userId);

}
