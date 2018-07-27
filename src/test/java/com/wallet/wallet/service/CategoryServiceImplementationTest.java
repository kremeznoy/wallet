package com.wallet.wallet.service;

import com.wallet.wallet.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CategoryServiceImplementationTest {
    @Autowired
    private CategoryServiceImplementation categoryServiceImplementation;
    private Category category;

    @BeforeEach
    void beforeEach() {
        /*category.setCategoryName("Nanny");
        category.setCategoryId(7L);
        category.setAmountAtStart(0.0);
        category.setCategoryStatus(true);
        category.setCurrenyIdInCategory(399L);
        category.setDateOfCreation(1531947222735L);*/
    }

    @Test
    void findOne() {
        /*category.setCategoryId(7L);
        System.out.println(category.getCategoryId());*/
        Optional<Category> categoryForComparison = categoryServiceImplementation.findOneById(7L);
        assertAll(
                () -> assertTrue(categoryForComparison.isPresent())
                //() -> assertEquals(categoryForComparison.get(), category)
        );
    }
}
