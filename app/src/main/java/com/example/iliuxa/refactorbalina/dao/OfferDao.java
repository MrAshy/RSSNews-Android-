package com.example.iliuxa.refactorbalina.dao;

import com.example.iliuxa.refactorbalina.entity.Offer;
import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

/**
 * Created by Iliuxa on 03.11.16.
 */

public class OfferDao extends BaseDaoImpl<Offer,Integer> {
    public OfferDao(ConnectionSource connectionSource, Class<Offer> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }


    /**Get List of offers with same Id*/
    public List<Offer> getOffersByCategoryId(Integer categoryId)throws SQLException{
        QueryBuilder<Offer,Integer> queryBuilder = queryBuilder();
        queryBuilder.where().eq(Offer.OFFER_COLUMN_CATEGORY_ID,categoryId);
        PreparedQuery<Offer> preparedQuery = queryBuilder.prepare();
        return query(preparedQuery);
    }

    /**Get List with all offers*/
    public List<Offer> getAllOffers()throws SQLException{
        return this.queryForAll();
    }

    /**Add field to DataBase with check for existing*/
    public int createWithCheck(Collection<Offer> datas) throws SQLException {
        for(Offer data:datas){
            Offer offer = this.queryForId(data.getId());
            if(offer == null){
                this.create(data);
            }
        }
        return 0;
    }

}
