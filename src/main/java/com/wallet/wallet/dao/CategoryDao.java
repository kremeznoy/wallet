package com.wallet.wallet.dao;

import com.wallet.wallet.entity.Category;
import org.springframework.data.jpa.repository.*;

import java.util.stream.Stream;

public interface CategoryDao extends JpaRepository<Category, Long> {
    Stream<Category> findByCategoryName(String name);
}
