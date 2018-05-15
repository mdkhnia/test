package com.example.moham.mycv;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import com.example.moham.mycv.mvp.home.HomeActivity;
import com.example.moham.mycv.mvp.login.LoginActivity;
import com.orhanobut.hawk.Hawk;

public class SplashScreenActivity extends BaseActivity {

    TextView appName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final ImageView splashScreenIV = (ImageView) findViewById(R.id.splashScreenIV);
        final Animation an = AnimationUtils.loadAnimation(mContext, R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
        splashScreenIV.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }
            @Override
            public void onAnimationEnd(Animation animation) {
                splashScreenIV.startAnimation(an2);
                finish();

/* if this is first run on this phone, go to tutorial, else, go to login page using Hawk */
                boolean isFirstRun = Hawk.get("isFirstRun", true);
                if (isFirstRun) {
                    Hawk.put("isFirstRun", false);
                    Intent headToMainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(headToMainActivity);
                } else {
                    Long LoginTime = Hawk.get("EnterTime", 0L);
                    if(LoginTime < Calendar.getInstance().getTimeInMillis()/1000){
                        Intent headToLoginActivity = new Intent(SplashScreenActivity.this, LoginActivity.class);
                        startActivity(headToLoginActivity);
                    } else{
                        Intent headToHomeActivity = new Intent(SplashScreenActivity.this, HomeActivity.class);
                        startActivity(headToHomeActivity);
                    }

                }

/* if this is first run on this phone, go to tutorial, else, go to login page using SharedPreferences */
//                boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
//                        .getBoolean("isFirstRun", true);
//                if (isFirstRun) {
//                    getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
//                            .putBoolean("isFirstRun", false).commit();
//                    Intent headToMainActivity = new Intent(SplashScreenActivity.this, MainActivity.class);
//                    startActivity(headToMainActivity);
//                } else {
//                    Intent headToHomeActivity = new Intent(SplashScreenActivity.this, LoginActivity.class);
//                    startActivity(headToHomeActivity);
//                }
            }
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
}
