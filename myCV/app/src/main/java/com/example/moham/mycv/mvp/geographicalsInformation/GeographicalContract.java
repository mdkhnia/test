package com.example.moham.mycv.mvp.geographicalsInformation;

import com.example.moham.mycv.Models.desiredWeatherOutcome;
import com.example.moham.mycv.Models.webModels.YahooModel;

public interface GeographicalContract {

    interface View {
        void onCityExist(desiredWeatherOutcome dWO);
        void cityNotExist();
    }

    interface Presenter {
        void getValues(String cityName);
        void getValuesFromModel(YahooModel model);
    }
}
