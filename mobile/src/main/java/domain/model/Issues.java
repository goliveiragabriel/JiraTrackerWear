package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@Parcel
public class Issues {

    @SerializedName("expand")
    public String expand;

    @SerializedName("id")
    public int id;

    @SerializedName("self")
    public String self;

    @SerializedName("key")
    public String key;

    @SerializedName("fields")
    public Field fields;

    @SerializedName("customfield_10071")
    public String customfield_10071;

    @SerializedName("transitions")
    public String transitions;
}
