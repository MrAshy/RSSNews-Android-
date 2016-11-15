package com.example.iliuxa.refactorbalina.viewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder {

    protected Context context;
    protected ImageView image;

    public BaseViewHolder(View view) {
        super(view);
        context = view.getContext();
    }

    abstract public void bind(T data);

    protected void loadImage(int id){
        Picasso.with(context)
                .load(context.getResources().getIdentifier("id_" + id, "drawable", context.getPackageName()))
                .resize(400,300)
                .into(image);
    }
    protected void loadImage(String path){
        Picasso.with(context)
                .load(path)
                .resize(400,300)
                .into(image);
    }
}
