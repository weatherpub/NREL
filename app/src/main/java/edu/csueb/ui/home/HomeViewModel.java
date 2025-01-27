package edu.csueb.ui.home;

import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import edu.csueb.Model.FuelStationModel;
import edu.csueb.Pattern.Singleton.FuelStationViewModel;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<FuelStationModel>> liveData;

    // Singleton - Android Programming for Beginners [423]
    public FuelStationViewModel fuelStationViewModel = FuelStationViewModel.getInstance();
    public ArrayList<FuelStationModel> model = fuelStationViewModel.getData();

    /*
        A map is an object that stores associations between keys and values, or key/value pairs.
        Given a key, you can find its value.
        Both keys and values are objects.
        The keys must be unique, but the value may be duplicate.
        Some maps can accept a null key and null values, others cannot.
    */

    public HomeViewModel() {
        HashMap<String, String> url = new HashMap<>();

        ArrayList<String> fuel_type = new ArrayList<>();
        fuel_type.add("BD");
        fuel_type.add("E85");
        fuel_type.add("ELEC");
        fuel_type.add("HY");
        fuel_type.add("LNG");
        fuel_type.add("LPG");
        fuel_type.add("RD");

        url.put("apiKey", "bbbDBUupnRSpC9c6dUlXHcDRWhBuynPtyMyK2L0f");
        url.put("fuelType", fuel_type.get(0) + "," + fuel_type.get(1) + "," + fuel_type.get(2) + "," + fuel_type.get(3) + "," + fuel_type.get(4) + "," + fuel_type.get(5) + "," + fuel_type.get(6));
        url.put("state", "CA");
        url.put("limit", "6");

        Log.i("log", "url -> " + url.entrySet());

        liveData = new MutableLiveData<>();

        new FSAsyncTask().execute("https://developer.nrel.gov/api/alt-fuel-stations/v1.geojson?api_key=" + url.get("apiKey") + "&fuel_type=" + url.get("fuelType") + "&state=" + url.get("state") + "&limit=" + url.get("limit"));
    }

    public LiveData<ArrayList<FuelStationModel>> getLiveData() {
        return liveData;
    }

    /**
     * 1. Make an HTTP Request to the API and save the json to disk.
     * 2. Open the file (that was just downloaded) and parse it using: StringBuilder
     * 3. The StringBuilder is then passed to JSONObject(StringBuilder sb) to create the json object
     */

    public class FSAsyncTask extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         *
         * @param param
         * @return
         */
        @Override
        protected String doInBackground(String... param) {
            Log.v("log", "doInBackground()");

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(param[0]).build();

            Log.v("log", "doInBackground() after Request request = new" + request);

            try {
                Response response = client.newCall(request).execute();

                if (!response.isSuccessful())
                    return null;

                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        protected void onProgressUpdate(String... values) {

        }

        protected void onPostExecute() {

        }

        /**
         *
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            Log.v("log", "onPostExecute()" + result);
            super.onPostExecute(result);

            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray obj = jsonObject.getJSONArray("features");

                Log.i("log", "obj" + "release: 1.0.1 access_code " + obj.getJSONObject(0).getJSONObject("properties").getString("access_code"));
                Log.i("log", "obj" + "release: 1.0.1 access_days_time " + obj.getJSONObject(0).getJSONObject("properties").getString("access_days_time"));
                // Log.i("fs", "obj" + "release: 1.0.1 ev_connector_types " + obj.getJSONObject(0).getJSONObject("properties").getJSONArray("ev_connector_types").getJSONObject(0).toString());
                for (int i = 0; i < obj.length(); i++) {
                    model.add(new FuelStationModel(
                            obj.getJSONObject(0).getJSONObject("properties").getString("access_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("access_days_time"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("access_detail_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cards_accepted"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("date_last_confirmed"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("expected_date"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("fuel_type_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("groups_with_access_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getInt("id"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("maximum_vehicle_class"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("open_date"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("owner_type_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("restricted_access"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("status_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("funding_sources"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("facility_type"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("station_name"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("station_phone"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("updated_at"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("geocode_status"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("city"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("country"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("intersection_directions"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("plus4"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("state"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("street_address"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("zip"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("bd_blends"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_dispenser_num"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_fill_type_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_has_rng"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_psi"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_renewable_source"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_total_compression"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_total_storage"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("cng_vehicle_class"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("e85_blender_pump"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("e85_other_ethanol_blends"),
                            /*
                            obj.getJSONObject(i).getJSONObject("properties").getJSONArray("ev_connector_types").getString("ev_connector_types_1"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_connector_types_2"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_connector_types_3"),
                            obj.getJSONObject(i).getJSONObject("properties").getJSONArray("ev_connector_types").getJSONObject(0).getString("0"),
                            obj.getJSONObject(i).getJSONObject("properties").getJSONArray("ev_connector_types").getString(1),
                            obj.getJSONObject(i).getJSONObject("properties").getJSONArray("ev_connector_types").getString(2),
                            */
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_dc_fast_num"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_level1_evse_num"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_level2_evse_num"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_network"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_network_web"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_other_evse"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_pricing"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_renewable_source"),
                            obj.getJSONObject(i).getJSONObject("properties").getBoolean("ev_workplace_charging"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("hy_is_retail"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("hy_pressures"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("hy_standards"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("hy_status_link"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("lng_has_rng"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("lng_renewable_source"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("lng_vehicle_class"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("lpg_nozzle_types"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("lpg_primary"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ng_fill_type_code"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ng_psi"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ng_vehicle_class"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("rd_blended_with_biodiesel"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("rd_blends"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("rd_blends_fr"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("rd_max_biodiesel_level"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("nps_unit_name"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("access_days_time_fr"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("intersection_directions_fr"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("bd_blends_fr"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("groups_with_access_code_fr"),
                            obj.getJSONObject(i).getJSONObject("properties").getString("ev_pricing_fr")));
                }
                liveData.setValue(model); // pass the model to the ViewModel
                // not this! data.setValue(data.getValue());
            } catch(JSONException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
// https://developer.nrel.gov/docs/transportation/alt-fuel-stations-v1/all/