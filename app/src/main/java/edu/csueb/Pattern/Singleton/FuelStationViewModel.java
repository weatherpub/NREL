package edu.csueb.Pattern.Singleton;

import android.util.Log;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;

// The ViewModel that's part of the ui package use singleton's, not observables.

// This is a singleton example, this doesn't have to be used for MVVM.
public class FuelStationViewModel {

    // Instantiate an instance of itself.
    private static final FuelStationViewModel obj = new FuelStationViewModel();

    // Use this method to get an instance of this object.
    private final ArrayList<FuelStationModel> model;
    public static FuelStationViewModel getInstance() {
        return obj;
    }

    // Constructor is Private...only this class 'FuelStationViewModel' can instantiate it.
    private FuelStationViewModel() {
        Log.v("fs", "FuelStationViewModel -> Constructor");
        model = new ArrayList<>();
    }

    // Anyone can get a list of models.
    public ArrayList<FuelStationModel> getData() {
        return model;
    }
}

/*
From Android Documentation
Create LiveData objects

LiveData is a wrapper that can be used with any data, including objects that implement Collections, such as List.
A LiveData object is usually stored with in a viewModel object and is accessed via a getter method, as demonstrated in the following example:
 */