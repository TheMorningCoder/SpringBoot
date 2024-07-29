package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Maximum {
    @SerializedName("Value")
    @Expose
    private double value;
    @SerializedName("Unit")
    @Expose
    private String unit;

    public Maximum(double value, String unit) {
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }
}
