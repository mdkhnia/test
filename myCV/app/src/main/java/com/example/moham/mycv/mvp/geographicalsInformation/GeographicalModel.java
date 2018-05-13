package com.example.moham.mycv.mvp.geographicalsInformation;

import android.widget.Toast;

import com.example.moham.mycv.Models.webModels.YahooModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GeographicalModel {
    GeographicalContract.Presenter presenter;

    public GeographicalModel(GeographicalContract.Presenter presenter) {
        this.presenter = presenter;
    }

    public void getValues(String cityName) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api api = retrofit.create(Api.class);
        String query = "select * from weather.forecast where woeid in (SELECT woeid FROM geo.places WHERE text=\"(" + cityName + ")\")\n";
        Call<YahooModel> call = api.getYahoo(query);
        call.enqueue(new Callback<YahooModel>() {
            @Override
            public void onResponse(Call<YahooModel> call, Response<YahooModel> response) {
                presenter.getValuesFromModel(response.body());
            }

            @Override
            public void onFailure(Call<YahooModel> call, Throwable t) {
                presenter.getValuesFromModel(null);
            }
        });
    }
}
