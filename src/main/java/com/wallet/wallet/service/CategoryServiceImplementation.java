package com.wallet.wallet.service;

import com.wallet.wallet.dao.CategoryDao;
import com.wallet.wallet.entity.Category;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    @Override
    public Category updateCategoryName(@NonNull Long categoryId, @NonNull String categoryName) throws IllegalAccessException {
        Optional<Category> categoryOptional = findOneById(categoryId);
        if (!categoryOptional.isPresent()) {
            throw new NullPointerException("There is no Category for update with id: " + categoryId);
        } else if (categoryOptional.get().getCategoryName().equals(categoryName)) {
            throw new IllegalAccessException("Exactly same Category object exists with Name: " + categoryName);
        }

        Category categoryForUpdate = categoryOptional.get();
        categoryForUpdate.setCategoryName(categoryName);
        return categoryDao.save(categoryForUpdate);
    }
}
