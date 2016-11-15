package com.example.iliuxa.refactorbalina.presenter;

import android.os.AsyncTask;

import com.example.iliuxa.refactorbalina.entity.Offer;
import com.example.iliuxa.refactorbalina.model.OfferModel;
import com.example.iliuxa.refactorbalina.presenter.interfaces.OffersActivityPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.OffersActivityView;

import java.sql.SQLException;
import java.util.List;


public class OffersPresenter implements OffersActivityPresenter {
    private OffersActivityView view;
    private OfferModel mOfferModel;

    public OffersPresenter(OffersActivityView view) throws SQLException {
        this.view = view;
        mOfferModel = new OfferModel();
    }


    @Override
    public void getDataForList(int id) {
        GetOffersByCategoryTask getOffersByCategory = new GetOffersByCategoryTask();
        getOffersByCategory.execute(id);
    }

    private class GetOffersByCategoryTask extends AsyncTask<Integer,Void,List<Offer>>{

        @Override
        protected List<Offer> doInBackground(Integer... params) {
            try {
                return mOfferModel.getOffersByCategoryId(params[0]);
            } catch (SQLException e) {
                throw new RuntimeException("DataBase cannnot read offers");
                //todo: dialog
            }
        }

        @Override
        protected void onPostExecute(List<Offer> offers) {
            view.showOffersList(offers);
        }
    }

}
