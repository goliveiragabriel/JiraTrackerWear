package domain.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@DatabaseTable(tableName = "QueryResult")
public class QueryResult {

    @DatabaseField(columnName = "id", generatedId = true, allowGeneratedIdInsert = true)
    public int id;

    @SerializedName("expand")
    @DatabaseField(columnName = "expand")
    public String expand;

    @SerializedName("startAt")
    @DatabaseField(columnName = "startAt")
    public int startAt;

    @SerializedName("maxResults")
    @DatabaseField(columnName = "maxResults")
    public int maxResults;

    @SerializedName("total")
    @DatabaseField(columnName = "total")
    public int total;

    @ForeignCollectionField(eager=false, columnName = "issues")
    public Collection<Issues> dbIssues;

    @SerializedName("issues")
    public List<Issues> issues;

    @DatabaseField(foreign = true, canBeNull = true, foreignAutoRefresh = true)
    public User user;


}
