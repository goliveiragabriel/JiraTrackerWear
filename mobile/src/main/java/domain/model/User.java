package domain.model;

import android.util.Base64;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;
import org.parceler.transfuse.annotations.Data;

import java.util.Date;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
@DatabaseTable(tableName="User")
public class User {

    @DatabaseField(columnName = "id", generatedId = true, allowGeneratedIdInsert = true)
    public int id;

    @SerializedName("userName")
    @DatabaseField(columnName = "userName")
    public String userName;

    @DatabaseField(columnName = "password")
    @SerializedName("password")
    public String password;

    @SerializedName("token")
    public Token token;

    @DatabaseField(columnName="lastAccess", dataType = DataType.DATE_STRING)
    public Date lastAccess;

    public QueryResult queryResult;

}
