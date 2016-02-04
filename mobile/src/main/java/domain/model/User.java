package domain.model;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
@Parcel
public class User {

    @SerializedName("userName")
    public String userName;

    @SerializedName("password")
    public String password;

    @SerializedName("token")
    public Token token;

    private String getToken() {
        byte[] bytesEncoded = Base64.encode((this.userName + ":" + this.password).getBytes(), Base64.URL_SAFE);
        return new String(bytesEncoded);
    }

}
