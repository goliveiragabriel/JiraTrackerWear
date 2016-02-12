package domain.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@DatabaseTable(tableName = "Issues")
public class Issues {

    @DatabaseField(columnName = "code", generatedId = true, allowGeneratedIdInsert = true)
    public int code;

    @SerializedName("expand")
    @DatabaseField(columnName = "expand")
    public String expand;

    @SerializedName("id")
    @DatabaseField(columnName = "id")
    public int id;

    @SerializedName("self")
    @DatabaseField(columnName = "self")
    public String self;

    @SerializedName("key")
    @DatabaseField(columnName = "key")
    public String key;

    @SerializedName("fields")
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public Field fields;

    @SerializedName("customfield_10071")
    @DatabaseField(columnName = "customfield_10071")
    public String customfield_10071;

    @SerializedName("transitions")
    @DatabaseField(columnName = "transitions")
    public String transitions;

    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    private transient QueryResult queryResult;
}
