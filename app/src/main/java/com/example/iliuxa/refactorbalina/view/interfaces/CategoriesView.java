package com.example.iliuxa.refactorbalina.view.interfaces;

import android.content.Context;

import java.util.List;

//PB_AI don't use concrete implementation words like activity/fragment
//      in view/presenter interface naming. Because you can change your view, but interface
//      will be the same.
public interface CategoriesView {
    void showCategoriesList(List data);
    void showRetryDialog();
    void showProgressDialog();
    void closeProgressDialog();
    void showErrorDialog();
    Context getContext();
}
