package com.example.moham.mycv.Models;


import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.internal.KeyConfiguration;
import com.example.moham.mycv.BaseActivity;
import com.example.moham.mycv.mvp.login.LoginContract;
import com.example.moham.mycv.mvp.register.RegisterContract;

import java.util.Calendar;

public class UserModel{
    LoginContract.Presenter presenter;
    RegisterContract.Presenter regPresenter;
    String fname;
    String lname;
    String username;
    String email;
    String phone;
    String pass;

    public UserModel(RegisterContract.Presenter presenter){
        this.regPresenter = presenter;
    }

    public UserModel(LoginContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void CheckLogin(String username, String password){
        // Pass user info to login
        BacktoryUser.loginInBackground(username, password,
                new BacktoryCallBack<Void>() {
                    // Login operation done (fail or success), handling it:
                    @Override
                    public void onResponse(BacktoryResponse<Void> response) {
                        // Checking result of operation
                        if (response.isSuccessful()) {
                            // Login successfull
                            presenter.LoginStatus(1);
                        } else if (response.code() == HttpStatusCode.Unauthorized.code()) {
                            // Username 'mohx' with password '123456' is wrong
                            presenter.LoginStatus(2);
                        } else {
                            // Operation generally failed, maybe internet connection issue
                            presenter.LoginStatus(3);
                        }
                    }

                });
    }

    public void CheckRegister(String fname, String lname, String username, String email, String phone, String pass, String cpass){
        if(pass.equals(cpass)){ // pass = cpass
            BacktoryUser newUser = new BacktoryUser.
                    Builder().
                    setFirstName(fname).
                    setLastName(lname).
                    setUsername(username).
                    setEmail(email).
                    setPassword(pass).
                    setPhoneNumber(phone).
                    setAvatar("mydomain.com/avatar.png").
                    build();
            // Register operation done (fail or success), handling it:
            newUser.registerInBackground(new BacktoryCallBack<BacktoryUser>() {
                @Override
                public void onResponse(BacktoryResponse<BacktoryUser> backtoryResponse) {
                    // Checking result of operation
                    if (backtoryResponse.isSuccessful()) {
                        // Successful
                        regPresenter.CheckRegisterStatus(1);
                    } else if (backtoryResponse.code() == HttpStatusCode.Conflict.code()) {
                        // Username is invalid
                        regPresenter.CheckRegisterStatus(2);
                    } else {
                        // General failure
                        regPresenter.CheckRegisterStatus(3);
                    }
                }
            });
        } else{ // pass != cpass
            regPresenter.CheckRegisterStatus(4);
        }
    }
}
