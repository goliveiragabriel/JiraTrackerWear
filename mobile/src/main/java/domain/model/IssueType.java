package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@Parcel
public class IssueType {

    @SerializedName("self")
    public String self;

    @SerializedName("id")
    public int id;

    @SerializedName("description")
    public String description;

    @SerializedName("iconUrl")
    public String iconUrl;

    @SerializedName("name")
    public String name;

    @SerializedName("substask")
    public boolean substask;

}
