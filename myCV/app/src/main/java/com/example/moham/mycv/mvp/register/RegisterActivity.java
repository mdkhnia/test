package com.example.moham.mycv.mvp.register;

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
import com.orhanobut.hawk.Hawk;

import java.util.Calendar;

public class RegisterActivity extends BaseActivity implements View.OnClickListener, RegisterContract.View {

    EditText fname, lname, username, email, phone, pass, cpass;
    Button regBtn;
    TextInputLayout tiFname, tiLname, tiUsername, tiEmail;
    TextInputLayout tiPhone, tiPass, tiCpass;
    RegisterContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        // Initializing backtory
        BacktoryClient.init(KeyConfiguration.newBuilder().
                // Enabling User Services
                        setAuthKeys("5ae97f56e4b009dc65462e5f",
                        "5ae97f56e4b065c17c4f20e7").
                // Finalizing sdk
                        build(), this);
        bindViews();

    }

    private void bindViews() {
        fname = findViewById(R.id.fname);
        lname = findViewById(R.id.lname);
        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);

        regBtn = findViewById(R.id.regBtn);

        presenter = new RegisterPresenter(this);

        tiFname = findViewById(R.id.tiFname);
        tiLname = findViewById(R.id.tiLname);
        tiUsername = findViewById(R.id.tiUsername);
        tiEmail = findViewById(R.id.tiEmail);
        tiPhone = findViewById(R.id.tiPhone);
        tiPass = findViewById(R.id.tiPass);
        tiCpass = findViewById(R.id.tiCpass);

        regBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.regBtn) {
            //Form Validation
            boolean isValid = true;
            if (fname.getText().toString().isEmpty()) {
                tiFname.setError("First Name Field is mandatory");
                isValid = false;
            } else if (lname.getText().toString().isEmpty()) {
                tiFname.setErrorEnabled(false);
                tiLname.setError("Last Name Field is mandatory");
                isValid = false;
            } else if (username.getText().toString().isEmpty()) {
                tiLname.setErrorEnabled(false);
                tiUsername.setError("Enter Your Username Plz");
                isValid = false;
            } else if (email.getText().toString().isEmpty()) {
                tiUsername.setErrorEnabled(false);
                tiEmail.setError("Enter Your Email Plz");
                isValid = false;
            } else if (phone.getText().toString().isEmpty()) {
                tiEmail.setErrorEnabled(false);
                tiPhone.setError("Enter Your Phone Plz");
                isValid = false;
            } else if (pass.getText().toString().trim().isEmpty()) {
                tiPhone.setErrorEnabled(false);
                tiPass.setError("Password is Empty");
                isValid = false;
            } else if (cpass.getText().toString().trim().isEmpty()) {
                tiPass.setErrorEnabled(false);
                tiCpass.setError("Confirm Your Password Plz");
                isValid = false;
            } else {
                tiCpass.setErrorEnabled(false);
            }
            if (isValid) {
                //do register Stuff
                String fnameReg = fname.getText().toString();
                String lnameReg = lname.getText().toString();
                String usernameReg = username.getText().toString();
                String emailReg = email.getText().toString();
                String phoneReg = phone.getText().toString();
                String passReg = pass.getText().toString();
                String cpassReg = cpass.getText().toString();
                presenter.CheckRegister(fnameReg, lnameReg, usernameReg, emailReg, phoneReg, passReg, cpassReg);
            }

        }
    }

    @Override
    public void onSuccess() {
        Toast.makeText(mContext, "Registered Successful", Toast.LENGTH_SHORT).show();
        fname.setText("");
        lname.setText("");
        username.setText("");
        email.setText("");
        phone.setText("");
        pass.setText("");
        cpass.setText("");
        tiFname.setErrorEnabled(false);
        tiLname.setErrorEnabled(false);
        tiUsername.setErrorEnabled(false);
        tiEmail.setErrorEnabled(false);
        tiPhone.setErrorEnabled(false);
        tiPass.setErrorEnabled(false);
        tiCpass.setErrorEnabled(false);
        Hawk.put("EnterTime", ((Calendar.getInstance().getTimeInMillis()) / 1000) + 60);
    }

    @Override
    public void onRegisteredBefore() {
        Toast.makeText(mContext, "Either username or Email Registered Before", Toast.LENGTH_SHORT).show();
        username.setText("");
        email.setText("");
        tiFname.setErrorEnabled(false);
        tiLname.setErrorEnabled(false);
        tiUsername.setErrorEnabled(false);
        tiEmail.setErrorEnabled(false);
        tiPhone.setErrorEnabled(false);
        tiPass.setErrorEnabled(false);
        tiCpass.setErrorEnabled(false);
    }

    @Override
    public void onFailedNetwork() {
        Toast.makeText(mContext, "Registered Before of Network Failure, plz try Again a few minutes Later", Toast.LENGTH_SHORT).show();
        fname.setText("");
        lname.setText("");
        username.setText("");
        email.setText("");
        phone.setText("");
        pass.setText("");
        cpass.setText("");
        tiFname.setErrorEnabled(false);
        tiLname.setErrorEnabled(false);
        tiUsername.setErrorEnabled(false);
        tiEmail.setErrorEnabled(false);
        tiPhone.setErrorEnabled(false);
        tiPass.setErrorEnabled(false);
        tiCpass.setErrorEnabled(false);
    }

    @Override
    public void differentPassANDConfirmPass() {
        Toast.makeText(mContext, "Password and Confirm Password are Different, Enter again plz", Toast.LENGTH_SHORT).show();
        pass.setText("");
        cpass.setText("");
    }
}
