package com.example.iliuxa.refactorbalina.adapter;

import android.view.ViewGroup;

import com.example.iliuxa.refactorbalina.entity.Offer;
import com.example.iliuxa.refactorbalina.viewHolders.BaseViewHolder;
import com.example.iliuxa.refactorbalina.viewHolders.OfferResources;


public class OffersAdapter extends BaseRecyclerViewAdapter<Offer> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OfferResources(parent);
    }
}
