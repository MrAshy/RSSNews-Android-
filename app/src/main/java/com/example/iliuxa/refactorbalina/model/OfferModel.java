package com.example.iliuxa.refactorbalina.model;

import com.example.iliuxa.refactorbalina.dao.OfferDao;
import com.example.iliuxa.refactorbalina.entity.Offer;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Iliuxa on 11.11.16.
 */

public class OfferModel {
    private OfferDao mOfferDao;

    public OfferModel() throws SQLException {
        mOfferDao = DataBaseFactory.getHelper().getOfferDao();
    }

    public List<Offer> getOffersByCategoryId(int id) throws SQLException {
        return mOfferDao.getOffersByCategoryId(id);
    }
}
