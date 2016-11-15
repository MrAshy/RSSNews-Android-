package com.example.iliuxa.refactorbalina.presenter.interfaces;

import java.sql.SQLException;


public interface OffersActivityPresenter {
    void getDataForList(int id) throws SQLException;
}
