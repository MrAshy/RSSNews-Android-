package com.example.iliuxa.refactorbalina.model;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;



public class DataBaseFactory {
    private static DataBaseHelper dataBaseHelper;

    /**Get DataBase Object*/
    public static DataBaseHelper getHelper(){
        return dataBaseHelper;
    }

    /**Set DataBase Object*/
    public static void setHelper(Context context){
        dataBaseHelper = OpenHelperManager.getHelper(context,DataBaseHelper.class);
    }

    /**Close DataBase Object*/
    public static void releaseHelper(){
        OpenHelperManager.releaseHelper();
        dataBaseHelper = null;
    }
}
