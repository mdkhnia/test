package com.example.moham.mycv;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moham.mycv.Adapters.SliderAdapter;
import com.example.moham.mycv.mvp.home.HomeActivity;
import com.example.moham.mycv.mvp.login.LoginActivity;
import com.orhanobut.hawk.Hawk;

import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;


public class MainActivity extends BaseActivity implements View.OnClickListener {

    ViewPager mSlideViewPager;
    LinearLayout mDotLayout;
    SliderAdapter sliderAdapter;
    TextView[] mDots;
    Button nextBtn, prevBtn;
    int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        sliderAdapter = new SliderAdapter(mContext);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndecator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);
    }

    private void bindViews() {
        mSlideViewPager = findViewById(R.id.slideViewPager);
        mDotLayout = findViewById(R.id.dotsLayout);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);

        nextBtn.setOnClickListener(this);
        prevBtn.setOnClickListener(this);

    }

    private void addDotsIndecator(int position) {
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparent_white));
            mDotLayout.addView(mDots[i]);
        }

        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.error_color));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndecator(position);
            mCurrentPage = position;
            if (position == 0) {
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(false);
                prevBtn.setVisibility(View.INVISIBLE);
                nextBtn.setText("Next");
                prevBtn.setText("");
            } else if (position == mDots.length - 1) {
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                prevBtn.setText("Prev");
                nextBtn.setText("Finish");
            } else {
                nextBtn.setEnabled(true);
                prevBtn.setEnabled(true);
                prevBtn.setVisibility(View.VISIBLE);
                prevBtn.setText("Prev");
                nextBtn.setText("Next");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.nextBtn) {
            if (!nextBtn.getText().toString().equalsIgnoreCase("finish")) {
                mSlideViewPager.setCurrentItem(mCurrentPage + 1);
            } else {
                Long LoginTime = Hawk.get("EnterTime", 0L);
                if (LoginTime < Calendar.getInstance().getTimeInMillis() / 1000) {
                    Intent headToLoginActivity = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(headToLoginActivity);
                } else {
                    Intent headToHomeActivity = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(headToHomeActivity);
                }
            }
        } else if (v.getId() == R.id.prevBtn) {
            mSlideViewPager.setCurrentItem(mCurrentPage - 1);
        }
    }
}
