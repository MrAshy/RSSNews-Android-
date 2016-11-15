package com.example.iliuxa.refactorbalina.view.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.iliuxa.refactorbalina.R;
import com.example.iliuxa.refactorbalina.adapter.CategoriesAdapter;
import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.presenter.CategoriesPresenter;
import com.example.iliuxa.refactorbalina.view.RecyclerItemClickListener;
import com.example.iliuxa.refactorbalina.view.interfaces.CategoriesView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.List;

@EActivity(R.layout.activity_categories)
public class CategoriesActivity extends AppCompatActivity implements CategoriesView {

    private CategoriesAdapter mAdapter;
    private ProgressDialog mProgressDialog;
    private AlertDialog.Builder mAlertDialog;
    private final CategoriesPresenter mPresenter = new CategoriesPresenter(this);
    private final int LAYOUT_RESOURCE = R.layout.simple_category_list_item;
    private RecyclerItemClickListener.OnItemClickListener mRecyclerViewClickedListener;

    @ViewById(R.id.categoriesList)
    RecyclerView mCategoriesList;

    @AfterViews
    void initViews(){
        mProgressDialog = new ProgressDialog(CategoriesActivity.this);
        mProgressDialog.setTitle("Загрузка данных");
        mProgressDialog.setMessage("Пожалуйста, подождите");
        mAlertDialog = new AlertDialog.Builder(
                CategoriesActivity.this);
        mAdapter = new CategoriesAdapter();
    }
    @AfterViews
    void initListener(){
        mRecyclerViewClickedListener = new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startOfferActivity(position);
            }

            @Override
            public void onLongItemClick(View view, int position) {

            }
        };
    }
    @AfterViews
    void downloadData() {
        mPresenter.getDataForList();
    }

    @Override
    public void showErrorDialog() {
        openAlertDialog("Непредвиденная ошибка",null);
    }

    @Override
    public void showCategoriesList(List data) {
        mAdapter.setData(data);
        mAdapter.notifyDataSetChanged();
        mCategoriesList.setAdapter(mAdapter);
        mCategoriesList.setLayoutManager(new GridLayoutManager(this, 2));
        mCategoriesList.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(),mCategoriesList, mRecyclerViewClickedListener));
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showRetryDialog() {
        openAlertDialog(getString(R.string.Dialog_title_network_unavaliable),getString(R.string.Dialog_negative_retry));
    }

    private void startOfferActivity(int categoryId) {
        OffersActivity_.intent(this)
                .extra("id",(mAdapter.getClickedItem(categoryId)).getIdCategory())
                .start();
    }

    @Override
    public void onBackPressed() {
        openAlertDialog(getString(R.string.Dialog_title_exit),"Отмена");
    }

    private void openAlertDialog(String title,String negativeWord) {
        final String negativeText;
        negativeText = negativeWord;
        mAlertDialog.setTitle(title);
        mAlertDialog.setPositiveButton("Выйти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        if(negativeText != null) {
            mAlertDialog.setNegativeButton(negativeText, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if (negativeText.equals(getString(R.string.Dialog_negative_cancel))) {
                        dialog.dismiss();
                    } else if (negativeText.equals(getString(R.string.Dialog_negative_retry))) {
                        downloadData();
                        dialog.dismiss();
                    }
                }
            });
        }
        mAlertDialog.show();
    }

    @Override
    public void showProgressDialog(){
        mProgressDialog.show();
    }

    @Override
    public void closeProgressDialog() {
        mProgressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DataBaseFactory.releaseHelper();
    }
}
