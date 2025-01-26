package edu.csueb.ViewModel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

import edu.csueb.Model.FuelStationModel;

// LiveData is lifecycle-aware, meaning it respects the lifecycle of other app components
// such as activities, fragments, or services.
// this awareness ensures LiveData only updates app component observers that are in an active lifecycle state.
public class FSViewModel extends ViewModel {

    /**
     * MutableLiveData => should be used within the ViewModel or data repository to manage the data and update it when necessary.
     */
    public MutableLiveData<FuelStationModel> model;

    /*
    public FSViewModel(MutableLiveData<ArrayList<FuelStationModel>> liveData) {
        model = new MutableLiveData<>();
    }
    */

    /**
     * LiveData => should be used to expose data to the UI components,
     * ensuring the data is not modified outside of its managing class.
     * @return model
     */
    // public LiveData<FuelStationModel> getModel() {
    // public LiveData<FuelStationModel> getModel() {
    public MutableLiveData<FuelStationModel> getModel() {
        if(model == null) {
            model = new MutableLiveData<>();
        }
        return model;
    }
}

/*
public class TrackViewModel extends ViewModel {
    private static final TrackViewModel obj = new TrackViewModel();

    private final ArrayList<TrackModel> trackModel;

    private TrackViewModel() {
        trackModel = new ArrayList<>();
    }

    public static TrackViewModel getInstance() {
        return obj;
    }

    public ArrayList<TrackModel> getData() {
        return trackModel;
    }
}
*/