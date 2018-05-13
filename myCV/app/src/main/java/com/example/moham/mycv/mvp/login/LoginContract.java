package com.example.moham.mycv.mvp.login;

public interface LoginContract {
    interface View {
        void onSuccess();
        void onFailedCredentials();
        void onFailedNetwork();
    }

    interface Presenter {
        void Checklogin(String username, String password);

        void LoginStatus(int response);
    }
}
