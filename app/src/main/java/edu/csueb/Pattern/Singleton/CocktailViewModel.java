package edu.csueb.Pattern.Singleton;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.csueb.Model.CocktailModel;
import edu.csueb.Model.FuelStationModel;

public class CocktailViewModel {
    // Instantiate an instance of itself.
    private static final CocktailViewModel obj = new CocktailViewModel();

    // Use this method to get an instance of this object.
    private final ArrayList<CocktailModel> model;

    public static CocktailViewModel getInstance() {
        return obj;
    }

    // Constructor is Private...only this class 'CocktailViewModel' can instantiate it.
    private CocktailViewModel() {
        model = new ArrayList<>();
    }

    // Anyone can get a list of models.
    public ArrayList<CocktailModel> getData() {
        return model;
    }
}

/*
From Android Documentation
Create LiveData objects

LiveData is a wrapper that can be used with any data, including objects that implement Collections, such as List.
A LiveData object is usually stored with in a viewModel object and is accessed via a getter method, as demonstrated in the following example:
 */