package org.zerock.myapp.service;

import org.zerock.myapp.domain.Categories;

public interface CategoriesService {

    public abstract void createCategories();
    public abstract Categories getCategoryByName(String name);

} // end interface
