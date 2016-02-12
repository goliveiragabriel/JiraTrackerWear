package domain.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@DatabaseTable(tableName = "IssueType")
public class IssueType {

    @DatabaseField(columnName = "code", generatedId = true, allowGeneratedIdInsert = true)
    public int code;

    @SerializedName("self")
    @DatabaseField(columnName = "self")
    public String self;

    @SerializedName("id")
    @DatabaseField(columnName = "id")
    public int id;

    @SerializedName("description")
    @DatabaseField(columnName = "description")
    public String description;

    @SerializedName("iconUrl")
    @DatabaseField(columnName = "iconUrl")
    public String iconUrl;

    @SerializedName("name")
    @DatabaseField(columnName = "name")
    public String name;

    @SerializedName("substask")
    @DatabaseField(columnName = "subtask", dataType = DataType.BOOLEAN)
    public boolean substask;

}
