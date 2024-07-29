package Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {
    @SerializedName("Minimum")
    @Expose
    private Minimum minimum;
    @SerializedName("Maximum")
    @Expose
    private Maximum maximum;

    public Temperature(Minimum minimum, Maximum maximum) {
        this.minimum = minimum;
        this.maximum = maximum;
    }

    public Minimum getMinimum() {
        return minimum;
    }

    public Maximum getMaximum() {
        return maximum;
    }
}
