package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@Parcel
public class QueryResult {

    @SerializedName("token")
    public String expand;

    @SerializedName("startAt")
    public int startAt;

    @SerializedName("maxResults")
    public int maxResults;

    @SerializedName("total")
    public int total;

    @SerializedName("issues")
    public List<Issues> issues;





}
