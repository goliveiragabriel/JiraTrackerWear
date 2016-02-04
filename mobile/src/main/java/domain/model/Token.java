package domain.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
@Parcel
public class Token {

    @SerializedName("session")
    public Session session;

    @SerializedName("loginInfo")
    public LoginInfo loginInfo;


}
