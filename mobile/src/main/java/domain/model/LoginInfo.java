package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
@Parcel
public class LoginInfo {

    @SerializedName("failedLoginCount")
    public int failedLoginCount;

    @SerializedName("loginCount")
    public int loginCount;

    @SerializedName("lastFailedLoginTime")
    public Date lastFailedLoginTime;

    @SerializedName("previousLoginTime")
    public Date previousLoginTime;


}
