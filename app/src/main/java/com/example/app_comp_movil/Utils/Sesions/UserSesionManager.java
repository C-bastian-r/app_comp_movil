package com.example.app_comp_movil.Utils.Sesions;

import com.example.app_comp_movil.Model.Entities.UserEntity;

//Se genera información global del usuario para poder ser accedidad desde
//cualquier parte del codigo a traves de un singleton

public class UserSesionManager {
    private static UserSesionManager instance;
    private UserEntity userEntity;

    private UserSesionManager(){}

    public static UserSesionManager getInstance(){
        if (instance == null){
            instance = new UserSesionManager();
        }
        return instance;
    }

    public void setUserEntity(UserEntity userEntity){
        this.userEntity = userEntity;
    }

    public UserEntity getUserEntity(){
        return userEntity;
    }
}
