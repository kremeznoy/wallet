package com.wallet.wallet.service;

import com.wallet.wallet.entity.Category;

public interface CategoryService extends AbstractService<Category> {
    Category create(Category category);
}
