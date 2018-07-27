package com.wallet.wallet.service;

import com.wallet.wallet.dao.CategoryDao;
import com.wallet.wallet.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImplementation extends AbstractServiceImplementation<Category> implements CategoryService {
    private final CategoryDao categoryDao;

    @Autowired
    public CategoryServiceImplementation(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    @Override
    CrudRepository<Category, Long> getDao() {
        return categoryDao;
    }

    @Override
    public Category create(Category category) {
        return categoryDao.save(category);
    }
}
