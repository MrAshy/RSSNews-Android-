package com.example.iliuxa.refactorbalina.entity;

import java.util.ArrayList;

/**
 * Created by Iliuxa on 28.10.16.
 */

public class Shop {
    private ArrayList<Category> categories;
    private ArrayList<Offer> offers;

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }

    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public void setOffers(ArrayList<Offer> offers) {
        this.offers = offers;
    }
}
