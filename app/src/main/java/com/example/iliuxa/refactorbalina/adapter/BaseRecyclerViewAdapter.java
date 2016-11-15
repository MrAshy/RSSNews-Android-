package com.example.iliuxa.refactorbalina.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.iliuxa.refactorbalina.viewHolders.BaseViewHolder;

import java.util.List;

public class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>>{
    private List<T> mData;

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        if(mData == null)return -1;
        else return mData.size();
    }

    public BaseRecyclerViewAdapter(){
        mData = null;
    }

    public void setData(List<T> data){
        mData = data;
    }

    public void addElement(T element){
        mData.add(element);
    }

    public void addAll(List<T> data){
        mData.addAll(data);
    }

    public void removeElement(int position){
        mData.remove(position);
    }

    public void removeElement(T element){
        mData.remove(element);
    }

    public T getClickedItem(int position){
        if(mData.isEmpty())return null;
        else return mData.get(position);
    }

}
