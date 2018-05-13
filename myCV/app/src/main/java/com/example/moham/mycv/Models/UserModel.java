package com.example.moham.mycv.Models;


import android.widget.Toast;

import com.backtory.java.HttpStatusCode;
import com.backtory.java.internal.BacktoryCallBack;
import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.BacktoryResponse;
import com.backtory.java.internal.BacktoryUser;
import com.backtory.java.internal.KeyConfiguration;
import com.example.moham.mycv.mvp.login.LoginContract;

public class UserModel {
    LoginContract.Presenter presenter;



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
}
