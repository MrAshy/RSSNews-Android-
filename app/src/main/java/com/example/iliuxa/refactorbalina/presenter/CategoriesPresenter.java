package com.example.iliuxa.refactorbalina.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.util.Log;

import com.example.iliuxa.refactorbalina.application.MyApplication;
import com.example.iliuxa.refactorbalina.model.DataBaseFactory;
import com.example.iliuxa.refactorbalina.entity.Category;
import com.example.iliuxa.refactorbalina.entity.Yml_catalog;
import com.example.iliuxa.refactorbalina.presenter.interfaces.CategoriesActivityPresenter;
import com.example.iliuxa.refactorbalina.view.interfaces.CategoriesView;
import com.stanfy.gsonxml.GsonXml;
import com.stanfy.gsonxml.GsonXmlBuilder;
import com.stanfy.gsonxml.XmlParserCreator;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CategoriesPresenter implements CategoriesActivityPresenter {
    private CategoriesView mView;
    private final String URL = "http://ufa.farfor.ru/getyml/?key=ukAXxeJYZN";

    public CategoriesPresenter(CategoriesView view){
        this.mView = view;
    }

    public void setCategoriesInList(List<Category> categories){
        mView.showCategoriesList(categories);
    }

    public boolean isNetworkAvailable(Context context) {
        if(mView.getContext() == null) { return false; }
        ConnectivityManager connectivityManager =
                (ConnectivityManager) mView.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    public void getDataForList() {
        LoadDataBaseTask load =new LoadDataBaseTask();
        load.execute();
    }

    private String getHttpResponse(String path) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(path)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    private XmlParserCreator getXmlPullParser(){
        XmlParserCreator parserCreator = new XmlParserCreator() {
            @Override
            public XmlPullParser createParser() {
                try {
                    return XmlPullParserFactory.newInstance().newPullParser();
                } catch (XmlPullParserException e) {
                    Log.e(MyApplication.TAG, "Xml Parser Exeption");
                        throw new RuntimeException(e);
                }
            }
        };
        return parserCreator;
    }

    private Yml_catalog getCatalog() throws IOException {
        GsonXml gsonXml = new GsonXmlBuilder()
                .setXmlParserCreator(getXmlPullParser())
                .create();
        return gsonXml.fromXml(getHttpResponse(URL), Yml_catalog.class);
    }

    private class LoadDataBaseTask extends AsyncTask<Void, Void, List<Category>> {
        private Exception mException;

        @Override
        protected List<Category> doInBackground(Void... params) {
            try {
                if (DataBaseFactory.getHelper().isDataBaseEmpty()) {
                    if(isNetworkAvailable(mView.getContext())) {
                        Yml_catalog catalog = getCatalog();
                        DataBaseFactory.getHelper().getCategoryDAO().createWithCheck(catalog.getShop().getCategories());
                        DataBaseFactory.getHelper().getOfferDao().createWithCheck(catalog.getShop().getOffers());
                        return DataBaseFactory.getHelper().getCategoryDAO().getAllCategories();
                    }else return null;
                } else return DataBaseFactory.getHelper().getCategoryDAO().getAllCategories();
            } catch (SQLException | IOException e) {
                mException = e;
                cancel(false);
                return null;
            }
        }

        @Override
        protected void onCancelled(List<Category> categories) {
            mView.showErrorDialog();
        }

        @Override
        protected void onPreExecute(){
            mView.showProgressDialog();
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            mView.closeProgressDialog();
            if (categories != null) setCategoriesInList(categories);
            else mView.showRetryDialog();
            mException = null;
        }
    }

}
