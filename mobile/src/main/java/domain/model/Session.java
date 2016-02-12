package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
public class Session {

    @SerializedName("name")
    public String name;

    @SerializedName("value")
    public String value;

}
