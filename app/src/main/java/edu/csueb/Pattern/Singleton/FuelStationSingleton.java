package edu.csueb.Pattern.Singleton;

import android.util.Log;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;

// This is a singleton example, this doesn't have to be used for MVVM.
public class FuelStationSingleton {

    // Instantiate an instance of itself.
    private static final FuelStationSingleton obj = new FuelStationSingleton();

    // Use this method to get an instance of this object.
    private final ArrayList<FuelStationModel> model;
    public static FuelStationSingleton getInstance() {
        return obj;
    }

    // Constructor is Private...only this class 'FuelStationViewModel' can instantiate it.
    private FuelStationSingleton() {
        Log.v("fs", "FuelStationSingleton -> Constructor");
        model = new ArrayList<>();
    }

    // Anyone can get a list of models.
    public ArrayList<FuelStationModel> getData() {
        return model;
    }
}