package com.example.iliuxa.refactorbalina.viewHolders;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.entity.Category;


public class CategoryResources extends BaseViewHolder<Category> {
    private TextView mCategoryName;
    public final static int LAYOUT_ID = R.layout.simple_category_list_item;

    public CategoryResources(ViewGroup parent) {
        super(LayoutInflater.from(parent.getContext()).inflate(LAYOUT_ID, parent, false));
        mCategoryName = (TextView) itemView.findViewById(R.id.categoryNameText);
        image = (ImageView) itemView.findViewById(R.id.imageCategory);
    }


    @Override
    public void bind(Category data) {
        mCategoryName.setText(data.getName());
        super.loadImage(data.getIdCategory());
    }
}

