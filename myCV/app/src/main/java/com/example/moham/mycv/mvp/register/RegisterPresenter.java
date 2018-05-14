package com.example.moham.mycv.mvp.register;

import com.example.moham.mycv.Models.UserModel;

public class RegisterPresenter implements RegisterContract.Presenter {
    RegisterContract.View view;
    UserModel model;

    public RegisterPresenter(RegisterContract.View view) {
        this.view = view;
        model = new UserModel(this);
    }

    @Override
    public void CheckRegister(String fname, String lname, String username, String email, String phone, String pass, String cpass) {
        model.CheckRegister(fname, lname, username, email, phone, pass, cpass);
    }

    @Override
    public void CheckRegisterStatus(int response) {
        switch (response) {
            case 1:
                view.onSuccess();
                break;
            case 2:
                view.onRegisteredBefore();
                break;
            case 3:
                view.onFailedNetwork();
                break;
            case 4:
                view.differentPassANDConfirmPass();
                break;
            default:
                break;
        }
    }
}
