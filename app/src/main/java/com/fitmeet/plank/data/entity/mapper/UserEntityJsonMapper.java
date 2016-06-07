package com.fitmeet.plank.data.entity.mapper;

import com.fitmeet.plank.data.entity.UserEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by lvhonghe on 16/6/6.
 */
public class UserEntityJsonMapper {

    private final Gson gson;

    @Inject
    public UserEntityJsonMapper() {
        this.gson = new Gson();
    }


    public UserEntity transformUserEntity(String userJsonResponse) throws JsonSyntaxException {
        try {
            Type userEntityType = new TypeToken<UserEntity>() {}.getType();
            UserEntity userEntity = this.gson.fromJson(userJsonResponse, userEntityType);
            return userEntity;
        } catch(JsonSyntaxException exception) {
            throw exception;
        }
    }


    public List<UserEntity> transformUserEntityCollection(String userListJsonResponse) throws JsonSyntaxException {
        List<UserEntity> userEntityCollection;

        try {
            Type listOfUserEntityType = new TypeToken<List<UserEntity>>() {}.getType();
            userEntityCollection = this.gson.fromJson(userListJsonResponse, listOfUserEntityType);
            return userEntityCollection;
        } catch (JsonSyntaxException jsonException) {
            throw jsonException;
        }
    }
}
