package com.example.moham.mycv.mvp.register;

public interface RegisterContract {
    interface View {
        void onSuccess();

        void onRegisteredBefore();

        void onFailedNetwork();

        void differentPassANDConfirmPass();
    }

    interface Presenter {
        void CheckRegister(String fname, String lname, String username, String email, String phone, String pass, String cpass);

        void CheckRegisterStatus(int response);
    }
}
