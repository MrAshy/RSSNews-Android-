package com.example.iliuxa.refactorbalina.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "offers")
public class Offer{
    public static final String OFFER_COLUMN_CATEGORY_ID = "category_id";

    @SerializedName("@id")
    @DatabaseField(id = true)
    private int id;

    @DatabaseField(columnName = "offer_name")
    private String name;

    @DatabaseField
    private String price;

    @DatabaseField
    private String description;

    @DatabaseField
    private String picture;

    @DatabaseField(columnName = "category_id")
    private int categoryId;

    public Offer(){
        super();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public int getCategoryId() {
        return categoryId;
    }

    //PB_AI In every Entity class you should override equals() and hashCode() methods
}
