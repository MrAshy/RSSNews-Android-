package com.example.iliuxa.refactorbalina.view.Activities;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.OffersAdapter;
import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.presenter.OffersPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.OffersActivityView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import java.sql.SQLException;
import java.util.List;

@EActivity(R.layout.activity_offers)
public class OffersActivity extends AppCompatActivity implements OffersActivityView {

    private OffersPresenter mPresenter ;
    private OffersAdapter mAdapter;

    @ViewById(R.id.offersList)
    RecyclerView offersList;
    @Extra("id")
    int id;

    @AfterViews
    void initPresenter()  {
        try {
            mPresenter = new OffersPresenter(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @AfterViews
    void initViews(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        mAdapter = new OffersAdapter();
    }
    @AfterViews
    void getData(){
        mPresenter.getDataForList(id);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                this.finish();
                return true;
        }
        return false;
    }

    @Override
    public void showOffersList(List data) {
        offersList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        offersList.setAdapter(mAdapter);
        try {
            mAdapter.setData(DataBaseFactory.getHelper().getOfferDao().getOffersByCategoryId(id));
            mAdapter.notifyDataSetChanged();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
