package domain.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import org.parceler.Parcel;

import java.util.Date;

/**
 * Created by gabriel.goncalves on 04/02/2016.
 */
@DatabaseTable(tableName = "Field")
public class Field {

    @DatabaseField(columnName = "id", generatedId = true, allowGeneratedIdInsert = true)
    public int id;

    @SerializedName("summary")
    @DatabaseField(columnName = "summary")
    public String summary;

    @SerializedName("timetracking")
    @DatabaseField(columnName = "timeTracking", dataType = DataType.DATE_STRING)
    public Date timetracking;

    @SerializedName("issueType")
    @DatabaseField(foreign = true, foreignAutoRefresh = true)
    public IssueType issueType;


}
