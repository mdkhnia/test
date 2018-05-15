package com.example.moham.mycv.mvp.login;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backtory.java.internal.BacktoryClient;
import com.backtory.java.internal.KeyConfiguration;
import com.example.moham.mycv.BaseActivity;
import com.example.moham.mycv.R;
import com.example.moham.mycv.mvp.register.RegisterActivity;

import com.orhanobut.hawk.Hawk;

import java.util.Calendar;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginContract.View {

    TextInputLayout textInputLoginPassword, textInputLoginUsername;
    EditText username, password;
    Button btnLogin, btnHeadToRegister;
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bindViews();
        // Initializing backtory
        BacktoryClient.init(KeyConfiguration.newBuilder().
                // Enabling User Services
                        setAuthKeys("5ae97f56e4b009dc65462e5f",
                        "5ae97f56e4b065c17c4f20e7").
                // Finalizing sdk
                        build(), this);
    }

    private void bindViews() {
        textInputLoginPassword = findViewById(R.id.textInputLoginPassword);
        textInputLoginUsername = findViewById(R.id.textInputLoginUsername);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        btnLogin = findViewById(R.id.btnLogin);
        btnHeadToRegister = findViewById(R.id.btnHeadToRegister);

        presenter = new LoginPresenter(this);

        btnLogin.setOnClickListener(this);
        btnHeadToRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            boolean isValid = true;
            String user = username.getText().toString();
            String pass = password.getText().toString();
            if (user.isEmpty()) {
                textInputLoginUsername.setError("Plz Enter Your Username");
                isValid = false;
            } else if (pass.isEmpty()) {
                textInputLoginUsername.setErrorEnabled(false);
                textInputLoginPassword.setError("Plz Enter Your Password");
                isValid = false;
            } else {
                textInputLoginPassword.setErrorEnabled(false);
            }
            if (isValid) {
                presenter.Checklogin(username.getText().toString(), password.getText().toString());
            }
        } else if (v.getId() == R.id.btnHeadToRegister) {
            Intent headtoRegister = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(headtoRegister);
        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "Login Successful", Toast.LENGTH_SHORT).show();
        Hawk.put("EnterTime", ((Calendar.getInstance().getTimeInMillis()) / 1000) + 60);
    }

    @Override
    public void onFailedCredentials() {
        Toast.makeText(mContext, "Either Username or Password is wrong", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailedNetwork() {
        Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
    }
}
