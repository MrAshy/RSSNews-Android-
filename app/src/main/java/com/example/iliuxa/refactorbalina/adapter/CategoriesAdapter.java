package com.example.iliuxa.refactorbalina.adapter;

import android.view.ViewGroup;

import com.example.iliuxa.refactorbalina.entity.Category;
import com.example.iliuxa.refactorbalina.viewHolders.BaseViewHolder;
import com.example.iliuxa.refactorbalina.viewHolders.CategoryResources;


public class CategoriesAdapter extends BaseRecyclerViewAdapter<Category> {
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CategoryResources(parent);
    }
}
