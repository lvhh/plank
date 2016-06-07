package com.fitmeet.plank.domain.repository;

import com.fitmeet.plank.domain.User;

import java.util.List;
import rx.Observable;

/**
 * Created by lvhonghe on 16/6/6.
 */
public interface UserRepository {

    Observable<List<User>> users();

    Observable<User> user(final int userId);

}
