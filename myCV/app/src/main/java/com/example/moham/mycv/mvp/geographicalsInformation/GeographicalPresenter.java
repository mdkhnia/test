package com.example.moham.mycv.mvp.geographicalsInformation;

import com.example.moham.mycv.Models.webModels.YahooModel;

public class GeographicalPresenter implements GeographicalContract.Presenter {

    GeographicalContract.View view;
    GeographicalModel model;

    public GeographicalPresenter(GeographicalContract.View view) {
        this.view = view;
        model = new GeographicalModel(this);
    }


    @Override
    public void getValues(String cityName) {
        model.getValues(cityName);
    }

    @Override
    public void getValuesFromModel(YahooModel model) {
        if (model != null) {
            String temp = model.getQuery().getResults().getChannel().getItem().getCondition().getTemp();
            String region =model.getQuery().getResults().getChannel().getLocation().getRegion();
            String date = model.getQuery().getResults().getChannel().getItem().getCondition().getDate();
            desiredWeatherOutcome dWO = new desiredWeatherOutcome(temp, region, date);
            view.onCityExist(dWO);
        } else {
            view.cityNotExist();
        }
    }
}
