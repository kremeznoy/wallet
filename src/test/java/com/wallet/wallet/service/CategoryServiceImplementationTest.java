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
        Category categoryForCreation = new Category();
        categoryForCreation.setCategoryName(RandomStringGenerator.getRandomStringGenerator(5));
        categoryForCreation.setAmountAtStart(964.23);
        categoryForCreation.setCategoryStatus(true);
        categoryForCreation.setCurrentIdInCategory(currency.getCurrencyId());
        categoryForCreation.setDateOfCreation(Instant.now().getEpochSecond());

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
}
