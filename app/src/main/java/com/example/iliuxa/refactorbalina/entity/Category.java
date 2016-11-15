package com.example.iliuxa.refactorbalina.entity;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "categories")
public class Category{
    @SerializedName("@id")
    @DatabaseField(id = true)
    private int idCategory;
    @SerializedName("$")
    @DatabaseField(columnName = "category_name", canBeNull = false)
    private String name;

    public Category(){}

    public String getName() {
        return name;
    }


    public int getIdCategory() {
        return idCategory;
    }
}
