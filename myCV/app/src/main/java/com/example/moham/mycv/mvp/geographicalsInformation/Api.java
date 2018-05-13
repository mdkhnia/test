package com.example.moham.mycv.mvp.geographicalsInformation;

import com.example.moham.mycv.Models.webModels.YahooModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    String BASE_URL = "https://query.yahooapis.com/v1/public/";
    @GET("yql?format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<YahooModel> getYahoo(@Query("q") String q );
}
