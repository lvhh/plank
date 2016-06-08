package com.fitmeet.plank.test.data.data.entity.mapper;

import com.fitmeet.plank.test.data.data.entity.UserEntity;
import com.fitmeet.plank.test.domain.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by lvhonghe on 16/6/6.
 */

@Singleton
public class UserEntityDataMapper {

    @Inject
    public UserEntityDataMapper() {}

    public User transform(UserEntity userEntity) {
        User user = null;

        if(userEntity != null) {
            user = new User();
            user.setId(userEntity.getId());
            user.setWechatid(userEntity.getWecharid());
            user.setName(userEntity.getName());
            user.setDescription(userEntity.getDescription());
        }

        return user;
    }

    public List<User> transform(Collection<UserEntity> userEntityCollection) {
        List<User> userList = new ArrayList<>(20);
        User user;
        for(UserEntity userEntity : userEntityCollection) {
            user = transform(userEntity);
            if(user != null) {
                userList.add(user);
            }
        }
        return userList;
    }
}
