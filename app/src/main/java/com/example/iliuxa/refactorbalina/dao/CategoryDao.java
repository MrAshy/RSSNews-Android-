package com.example.iliuxa.refactorbalina.dao;

import com.example.iliuxa.refactorbalina.entity.Category;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Iliuxa on 03.11.16.
 */

public class CategoryDao extends BaseDaoImpl<Category,Integer>{

    public CategoryDao(ConnectionSource connectionSource
            , Class<Category> dataClass)throws SQLException{
        super(connectionSource,dataClass);
    }


    /**Get List of categories*/
    public List<Category> getAllCategories()throws SQLException{
        return this.queryForAll();
    }

    /**Add field to DataBase with check for existing*/
    public int createWithCheck(Collection<Category> datas) throws SQLException {
        for(Category data:datas){
            Category category = this.queryForId(data.getIdCategory());
            if(category == null){
                this.create(data);
            }
        }
        return 0;
    }
}
