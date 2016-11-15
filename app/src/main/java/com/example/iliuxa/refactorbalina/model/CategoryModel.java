package com.example.iliuxa.refactorbalina.model;

import com.example.iliuxa.refactorbalina.dao.CategoryDao;
import com.example.iliuxa.refactorbalina.entity.Category;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Iliuxa on 11.11.16.
 */

public class CategoryModel {
    private CategoryDao mCategoryDao;

    public CategoryModel() throws SQLException {
        mCategoryDao = DataBaseFactory.getHelper().getCategoryDAO();
    }

    public List<Category> getAllCategories() throws SQLException {
        return mCategoryDao.getAllCategories();
    }
}
