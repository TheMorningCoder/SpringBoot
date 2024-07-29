package Model;

import com.google.gson.annotations.SerializedName;


public class Location {
    @SerializedName("Key")
    private String locationId;
    @SerializedName("LocalizedName")
    private String name;
    @SerializedName("Country.LocalizedName")
    private String country;

    public Location(){

    }

    public Location(String locationId, String name, String country) {
        this.locationId = locationId;
        this.name = name;
        this.country = country;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }
}
