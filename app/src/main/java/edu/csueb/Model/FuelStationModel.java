package edu.csueb.Model;

// The DataLayer
// Job is to expose data to the rest of the app.

public class FuelStationModel {
    private String access_code;
    private String access_days_time;
    private String access_detail_code;
    private String cards_accepted;
    private String date_last_confirmed;
    private String expected_date;
    private String fuel_type_code;
    private String groups_with_access_code;
    private int id;
    private String maximum_vehicle_class;
    private String open_date;
    private String owner_type_code;
    private String restricted_access;
    private String status_code;
    private String funding_sources;
    private String facility_type;
    private String station_name;
    private String station_phone;
    private String updated_at;
    private String geocode_status;
    private String city;
    private String country;
    private String intersection_directions;
    private String plus4;
    private String state;
    private String street_address;
    private String zip;
    private String bd_blends;
    private String cng_dispenser_num;
    private String cng_fill_type_code;
    private String cng_has_rng;
    private String cng_psi;
    private String cng_renewable_source;
    private String cng_total_compression;
    private String cng_total_storage;
    private String cng_vehicle_class;
    private String e85_blender_pump;
    private String e85_other_ethanol_blends;
    /*
    private String ev_connector_types_1;
    private String ev_connector_types_2;
    private String ev_connector_types_3;
    */
    private String ev_dc_fast_num;
    private String ev_level1_evse_num;
    private String ev_level2_evse_num;
    private String ev_network;
    private String ev_network_web;
    private String ev_other_evse;
    private String ev_pricing;
    private String ev_renewable_source;
    private Boolean ev_workplace_charging;
    private String hy_is_retail;
    private String hy_pressures;
    private String hy_standards;
    private String hy_status_link;
    private String lng_has_rng;
    private String lng_renewable_source;
    private String lng_vehicle_class;
    private String lpg_nozzle_types;
    private String lpg_primary;
    private String ng_fill_type_code;
    private String ng_psi;
    private String ng_vehicle_class;
    private String rd_blended_with_biodiesel;
    private String rd_blends;
    private String rd_blends_fr;
    private String rd_max_biodiesel_level;
    private String nps_unit_name;
    private String access_days_time_fr;
    private String intersection_directions_fr;
    private String bd_blends_fr;
    private String groups_with_access_code_fr;
    private String ev_pricing_fr;

    public FuelStationModel(String access_code, String access_days_time, String access_detail_code, String cards_accepted, String date_last_confirmed, String expected_date, String fuel_type_code, String groups_with_access_code, int id, String maximum_vehicle_class, String open_date, String owner_type_code, String restricted_access, String status_code, String funding_sources, String facility_type, String station_name, String station_phone, String updated_at, String geocode_status, String city, String country, String intersection_directions, String plus4, String state, String street_address, String zip, String bd_blends, String cng_dispenser_num, String cng_fill_type_code, String cng_has_rng, String cng_psi, String cng_renewable_source, String cng_total_compression, String cng_total_storage, String cng_vehicle_class, String e85_blender_pump, String e85_other_ethanol_blends, String ev_dc_fast_num, String ev_level1_evse_num, String ev_level2_evse_num, String ev_network, String ev_network_web, String ev_other_evse, String ev_pricing, String ev_renewable_source, Boolean ev_workplace_charging, String hy_is_retail, String hy_pressures, String hy_standards, String hy_status_link, String lng_has_rng, String lng_renewable_source, String lng_vehicle_class, String lpg_nozzle_types, String lpg_primary, String ng_fill_type_code, String ng_psi, String ng_vehicle_class, String rd_blended_with_biodiesel, String rd_blends, String rd_blends_fr, String rd_max_biodiesel_level, String nps_unit_name, String access_days_time_fr, String intersection_directions_fr, String bd_blends_fr, String groups_with_access_code_fr, String ev_pricing_fr) {
        this.access_code = access_code;
        this.access_days_time = access_days_time;
        this.access_detail_code = access_detail_code;
        this.cards_accepted = cards_accepted;
        this.date_last_confirmed = date_last_confirmed;
        this.expected_date = expected_date;
        this.fuel_type_code = fuel_type_code;
        this.groups_with_access_code = groups_with_access_code;
        this.id = id;
        this.maximum_vehicle_class = maximum_vehicle_class;
        this.open_date = open_date;
        this.owner_type_code = owner_type_code;
        this.restricted_access = restricted_access;
        this.status_code = status_code;
        this.funding_sources = funding_sources;
        this.facility_type = facility_type;
        this.station_name = station_name;
        this.station_phone = station_phone;
        this.updated_at = updated_at;
        this.geocode_status = geocode_status;
        this.city = city;
        this.country = country;
        this.intersection_directions = intersection_directions;
        this.plus4 = plus4;
        this.state = state;
        this.street_address = street_address;
        this.zip = zip;
        this.bd_blends = bd_blends;
        this.cng_dispenser_num = cng_dispenser_num;
        this.cng_fill_type_code = cng_fill_type_code;
        this.cng_has_rng = cng_has_rng;
        this.cng_psi = cng_psi;
        this.cng_renewable_source = cng_renewable_source;
        this.cng_total_compression = cng_total_compression;
        this.cng_total_storage = cng_total_storage;
        this.cng_vehicle_class = cng_vehicle_class;
        this.e85_blender_pump = e85_blender_pump;
        this.e85_other_ethanol_blends = e85_other_ethanol_blends;
        /*
        this.ev_connector_types_1 = ev_connector_types_1;
        this.ev_connector_types_2 = ev_connector_types_2;
        this.ev_connector_types_3 = ev_connector_types_3;
        */
        this.ev_dc_fast_num = ev_dc_fast_num;
        this.ev_level1_evse_num = ev_level1_evse_num;
        this.ev_level2_evse_num = ev_level2_evse_num;
        this.ev_network = ev_network;
        this.ev_network_web = ev_network_web;
        this.ev_other_evse = ev_other_evse;
        this.ev_pricing = ev_pricing;
        this.ev_renewable_source = ev_renewable_source;
        this.ev_workplace_charging = ev_workplace_charging;
        this.hy_is_retail = hy_is_retail;
        this.hy_pressures = hy_pressures;
        this.hy_standards = hy_standards;
        this.hy_status_link = hy_status_link;
        this.lng_has_rng = lng_has_rng;
        this.lng_renewable_source = lng_renewable_source;
        this.lng_vehicle_class = lng_vehicle_class;
        this.lpg_nozzle_types = lpg_nozzle_types;
        this.lpg_primary = lpg_primary;
        this.ng_fill_type_code = ng_fill_type_code;
        this.ng_psi = ng_psi;
        this.ng_vehicle_class = ng_vehicle_class;
        this.rd_blended_with_biodiesel = rd_blended_with_biodiesel;
        this.rd_blends = rd_blends;
        this.rd_blends_fr = rd_blends_fr;
        this.rd_max_biodiesel_level = rd_max_biodiesel_level;
        this.nps_unit_name = nps_unit_name;
        this.access_days_time_fr = access_days_time_fr;
        this.intersection_directions_fr = intersection_directions_fr;
        this.bd_blends_fr = bd_blends_fr;
        this.groups_with_access_code_fr = groups_with_access_code_fr;
        this.ev_pricing_fr = ev_pricing_fr;
    }

    public String getAccess_code() {
        return access_code;
    }

    public String getAccess_days_time() {
        return access_days_time;
    }

    public String getAccess_detail_code() {
        return access_detail_code;
    }

    public String getCards_accepted() {
        return cards_accepted;
    }

    public String getDate_last_confirmed() {
        return date_last_confirmed;
    }

    public String getExpected_date() {
        return expected_date;
    }

    public String getFuel_type_code() {
        return fuel_type_code;
    }

    public String getGroups_with_access_code() {
        return groups_with_access_code;
    }

    public int getId() {
        return id;
    }

    public String getMaximum_vehicle_class() {
        return maximum_vehicle_class;
    }

    public String getOpen_date() {
        return open_date;
    }

    public String getOwner_type_code() {
        return owner_type_code;
    }

    public String getRestricted_access() {
        return restricted_access;
    }

    public String getStatus_code() {
        return status_code;
    }

    public String getFunding_sources() {
        return funding_sources;
    }

    public String getFacility_type() {
        return facility_type;
    }

    public String getStation_name() {
        return station_name;
    }

    public String getStation_phone() {
        return station_phone;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public String getGeocode_status() {
        return geocode_status;
    }

    /*
    public long getLatitude() {
        return latitude;
    }

    public long getLongitude() {
        return longitude;
    }
    */

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getIntersection_directions() {
        return intersection_directions;
    }

    public String getPlus4() {
        return plus4;
    }

    public String getState() {
        return state;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getZip() {
        return zip;
    }

    public String getBd_blends() {
        return bd_blends;
    }

    public String getCng_dispenser_num() {
        return cng_dispenser_num;
    }

    public String getCng_fill_type_code() {
        return cng_fill_type_code;
    }

    public String getCng_has_rng() {
        return cng_has_rng;
    }

    public String getCng_psi() {
        return cng_psi;
    }

    public String getCng_renewable_source() {
        return cng_renewable_source;
    }

    public String getCng_total_compression() {
        return cng_total_compression;
    }

    public String getCng_total_storage() {
        return cng_total_storage;
    }

    public String getCng_vehicle_class() {
        return cng_vehicle_class;
    }

    public String getE85_blender_pump() {
        return e85_blender_pump;
    }

    public String getE85_other_ethanol_blends() {
        return e85_other_ethanol_blends;
    }

    /*
    public String getEv_connector_types_1() {
        return ev_connector_types_1;
    }

    public String getEv_connector_types_2() {
        return ev_connector_types_2;
    }

    public String getEv_connector_types_3() {
        return ev_connector_types_3;
    }
    */

    public String getEv_dc_fast_num() {
        return ev_dc_fast_num;
    }

    public String getEv_level1_evse_num() {
        return ev_level1_evse_num;
    }

    public String getEv_level2_evse_num() {
        return ev_level2_evse_num;
    }

    public String getEv_network() {
        return ev_network;
    }

    public String getEv_network_web() {
        return ev_network_web;
    }

    public String getEv_other_evse() {
        return ev_other_evse;
    }

    public String getEv_pricing() {
        return ev_pricing;
    }

    public String getEv_renewable_source() {
        return ev_renewable_source;
    }

    public Boolean getEv_workplace_charging() {
        return ev_workplace_charging;
    }

    public String getHy_is_retail() {
        return hy_is_retail;
    }

    public String getHy_pressures() {
        return hy_pressures;
    }

    public String getHy_standards() {
        return hy_standards;
    }

    public String getHy_status_link() {
        return hy_status_link;
    }

    public String getLng_has_rng() {
        return lng_has_rng;
    }

    public String getLng_renewable_source() {
        return lng_renewable_source;
    }

    public String getLng_vehicle_class() {
        return lng_vehicle_class;
    }

    public String getLpg_nozzle_types() {
        return lpg_nozzle_types;
    }

    public String getLpg_primary() {
        return lpg_primary;
    }

    public String getNg_fill_type_code() {
        return ng_fill_type_code;
    }

    public String getNg_psi() {
        return ng_psi;
    }

    public String getNg_vehicle_class() {
        return ng_vehicle_class;
    }

    public String getRd_blended_with_biodiesel() {
        return rd_blended_with_biodiesel;
    }

    public String getRd_blends() {
        return rd_blends;
    }

    public String getRd_blends_fr() {
        return rd_blends_fr;
    }

    public String getRd_max_biodiesel_level() {
        return rd_max_biodiesel_level;
    }

    public String getNps_unit_name() {
        return nps_unit_name;
    }

    public String getAccess_days_time_fr() {
        return access_days_time_fr;
    }

    public String getIntersection_directions_fr() {
        return intersection_directions_fr;
    }

    public String getBd_blends_fr() {
        return bd_blends_fr;
    }

    public String getGroups_with_access_code_fr() {
        return groups_with_access_code_fr;
    }

    public String getEv_pricing_fr() {
        return ev_pricing_fr;
    }
}