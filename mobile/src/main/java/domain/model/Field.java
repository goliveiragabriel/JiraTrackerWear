package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@Parcel
public class Field {

    @SerializedName("summary")
    public String summary;

    @SerializedName("timetracking")
    public Date timetracking;

    @SerializedName("issueType")
    public IssueType issueType;


}
