package com.example.moham.mycv.mvp.geographicalsInformation;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moham.mycv.BaseActivity;
import com.example.moham.mycv.Models.webModels.YahooModel;
import com.example.moham.mycv.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeographicalActivity extends BaseActivity implements GeographicalContract.View {

    TextView date;
    TextView temp;
    TextView region;
    EditText cityName;
    Button search;
    ProgressDialog pd;
    GeographicalContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geographical);
        bindViews();
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.getValues(cityName.getText().toString());
                pd.show();
            }
        });
    }

    private void bindViews() {
        date = findViewById(R.id.date);
        temp = findViewById(R.id.temp);
        region = findViewById(R.id.region);
        cityName = findViewById(R.id.cityName);
        search = findViewById(R.id.search);
        pd = new ProgressDialog(mContext);
        pd.setTitle("Please wait");
        pd.setMessage("Please wait for Yahoo's answer :-)");
        presenter = new GeographicalPresenter(this);
    }


    @Override
    public void onCityExist(desiredWeatherOutcome dWO) {
        date.setText(dWO.getDate());
        temp.setText(dWO.getTemp());
        region.setText(dWO.getRegion());
        pd.dismiss();
    }

    @Override
    public void cityNotExist() {
        Toast.makeText(this, "Entered Name is wrong, Try again plz", Toast.LENGTH_SHORT).show();
        cityName.setText("");
        pd.dismiss();
    }
}
