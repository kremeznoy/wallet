package com.wallet.wallet.service;

import com.wallet.wallet.Common.RandomStringGenerator;
import com.wallet.wallet.entity.Category;
import com.wallet.wallet.entity.Currency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategoryServiceImplementationTest {
    private static Category category;
    private Currency currency;
    @Autowired
    private CategoryServiceImplementation categoryServiceImplementation;

    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        // values(7, 'Nanny', 'true', 399, '0.0', 1531947222735);
        category = new Category();
        category.setCategoryName("Nanny");
        category.setCategoryId(7L);
        category.setAmountAtStart(0.0);
        category.setCategoryStatus(true);
        category.setCurrentIdInCategory(399L);
        category.setDateOfCreation(1531947222735L);

        currency = new Currency();
        currency.setCurrencyId(345L);
        currency.setCurrencyName("EUR");
    }

    @Test
    void findOneBiId() {
        Optional<Category> categoryForComparison = categoryServiceImplementation.findOneById(category.getCategoryId());
        assertAll(
                () -> assertTrue(categoryForComparison.isPresent()),
                () -> assertEquals(categoryForComparison.get(), category)
        );
    }

    @Test
    void createNewCategory() {
        Category categoryForCreation = Category.builder().dateOfCreation(Instant.now().getEpochSecond())
                .categoryName(RandomStringGenerator.getRandomStringGenerator(5))
                .categoryStatus(true).currentIdInCategory(currency.getCurrencyId())
                .amountAtStart(964.23).build();

        Category savedCategory = categoryServiceImplementation.create(categoryForCreation);
        Optional<Category> retrievedCategory = categoryServiceImplementation.findOneById(savedCategory.getCategoryId());

        assertAll(
                () -> assertNotNull(savedCategory),
                () -> assertTrue(retrievedCategory.isPresent()),
                () -> assertEquals(categoryForCreation.getAmountAtStart(), savedCategory.getAmountAtStart()),
                () -> assertEquals(categoryForCreation.getCategoryName(), savedCategory.getCategoryName()),
                () -> assertEquals(savedCategory, retrievedCategory.get())
        );
    }

    @Test
    void deleteSomeCategory() {
        Category categoryForCreation = Category.builder().dateOfCreation(Instant.now().getEpochSecond())
                .categoryName(RandomStringGenerator.getRandomStringGenerator(5))
                .categoryStatus(true).currentIdInCategory(currency.getCurrencyId())
                .amountAtStart(0.32).build();

        Category savedCategory = categoryServiceImplementation.create(categoryForCreation);
        Optional<Category> retrievedCategory = categoryServiceImplementation.findOneById(savedCategory.getCategoryId());

        assertAll(
                () -> assertEquals(categoryForCreation.getCategoryName(), savedCategory.getCategoryName()),
                () -> assertEquals(savedCategory, retrievedCategory.get())
        );

        categoryServiceImplementation.delete(savedCategory.getCategoryId());

        Optional<Category> retrievedCategoryAfterDeletion = categoryServiceImplementation.findOneById(savedCategory.getCategoryId());
        assertAll(
                () -> assertTrue(!retrievedCategoryAfterDeletion.isPresent())
        );
    }

    @Test
    void updateExistentCategory() {
        Category categoryForFirstCreation = Category.builder().dateOfCreation(Instant.now().getEpochSecond())
                .categoryName(RandomStringGenerator.getRandomStringGenerator(5))
                .categoryStatus(false).currentIdInCategory(currency.getCurrencyId())
                .amountAtStart(-444.809).build();

        Category savedCategory = categoryServiceImplementation.create(categoryForFirstCreation);
        Optional<Category> retrievedCategory = categoryServiceImplementation.findOneById(savedCategory.getCategoryId());

        assertAll(
                () -> assertEquals(categoryForFirstCreation.getCategoryName(), savedCategory.getCategoryName()),
                () -> assertEquals(savedCategory, retrievedCategory.get())
        );

        String newCategoryName = RandomStringGenerator.getRandomStringGenerator(7);

        try {
            categoryServiceImplementation.updateCategoryName(savedCategory.getCategoryId(), newCategoryName);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        Optional<Category> retrievedCategoryAfterNameUpdate = categoryServiceImplementation.findOneById(savedCategory.getCategoryId());

        assertAll(
                () -> assertNotNull(savedCategory),
                () -> assertTrue(retrievedCategoryAfterNameUpdate.isPresent()),
                () -> assertEquals(categoryForFirstCreation.getAmountAtStart(), retrievedCategoryAfterNameUpdate.get().getAmountAtStart()),
                () -> assertNotEquals(categoryForFirstCreation.getCategoryName(), retrievedCategoryAfterNameUpdate.get().getCategoryName()),
                () -> assertEquals(newCategoryName, retrievedCategoryAfterNameUpdate.get().getCategoryName()),
                () -> assertNotEquals(savedCategory, retrievedCategoryAfterNameUpdate.get())
        );
    }
}
