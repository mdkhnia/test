package com.example.moham.mycv.mvp.login;

import com.example.moham.mycv.Models.UserModel;

public class LoginPresenter implements LoginContract.Presenter{

    LoginContract.View view;
    UserModel model;

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
        model = new UserModel(this);
    }

    @Override
    public void Checklogin(String username, String password) {
        model.CheckLogin(username, password);
    }

    @Override
    public void LoginStatus(int response) {
        if(response == 1){
            view.onSuccess();
        } else if(response == 2){
            view.onFailedCredentials();
        } else if(response == 3){
            view.onFailedNetwork();
        }
    }
}
