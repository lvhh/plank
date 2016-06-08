package com.fitmeet.plank.test.presentation.internal.di.modules;

import com.fitmeet.plank.test.domain.domain.interactor.GetUserList;
import com.fitmeet.plank.test.domain.domain.interactor.UseCase;
import com.fitmeet.plank.test.presentation.internal.di.PerActivity;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lvhonghe on 16/6/7.
 */
@Module
public class UserModule {

    private int userId = -1;

    public UserModule() {}

    public UserModule(int userId) {this.userId = userId;}

    @Provides @PerActivity @Named("userList")
    UseCase provideGetUserListUseCase(GetUserList getUserList) {
        return getUserList;
    }
}
