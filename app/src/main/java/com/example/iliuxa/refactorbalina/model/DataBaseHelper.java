package com.example.iliuxa.refactorbalina.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.iliuxa.refactorbalina.dao.CategoryDao;
import com.example.iliuxa.refactorbalina.dao.OfferDao;
import com.example.iliuxa.refactorbalina.entity.Category;
import com.example.iliuxa.refactorbalina.entity.Offer;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;


public class DataBaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String TAG = DataBaseHelper.class.getSimpleName();
    private static final String DATABASE_NAME = "shop.db";
    private static final int DATABASE_VERSION = 1;

    private CategoryDao mCategoryDao;
    private OfferDao mOfferDao;

    public DataBaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    /**Creating table*/
    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try{
            TableUtils.createTable(connectionSource, Category.class);
            TableUtils.createTable(connectionSource, Offer.class);
        }catch (SQLException e){
            Log.e(TAG,"error creating DB" + DATABASE_NAME);
            throw new RuntimeException();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        //empty for this task
    }

    /**Singleton for categoryDao*/
    public CategoryDao getCategoryDAO()throws SQLException{
        if(mCategoryDao == null){
            mCategoryDao = new CategoryDao(getConnectionSource(), Category.class);
        }
        return mCategoryDao;
    }

    /**Singleton for mOfferDao*/
    public OfferDao getOfferDao()throws SQLException{
        if(mOfferDao == null){
            mOfferDao = new OfferDao(getConnectionSource(), Offer.class);
        }
        return mOfferDao;
    }

    /**Check for empty DataBase*/
    public boolean isDataBaseEmpty()throws SQLException{
        //LM_AI use getCategoryDAO().getAllCategories().isEmpty()
        return getCategoryDAO().getAllCategories().size() == 0 || getOfferDao().getAllOffers().size() == 0;
    }

    @Override
    public void close() {
        super.close();
        mCategoryDao = null;
        mOfferDao = null;
    }
}
