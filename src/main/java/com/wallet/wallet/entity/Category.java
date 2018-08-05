package com.wallet.wallet.entity;

import lombok.*;
import javax.persistence.*;
import java.time.Instant;

@Entity
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter
@EqualsAndHashCode
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NonNull
    private String categoryName;
    @NonNull
    private Boolean categoryStatus = true;
    @NonNull
    private Long currentIdInCategory;
    @NonNull
    private Double amountAtStart;
    @NonNull
    private Long dateOfCreation = Instant.now().getEpochSecond();

    @Builder
    public Category(String categoryName, Boolean categoryStatus, Long currentIdInCategory, Double amountAtStart, Long dateOfCreation) {
        this.categoryName = categoryName;
        if (categoryStatus == null) {
            this.categoryStatus = true;
        } else {
            this.categoryStatus = categoryStatus;
        }
        this.currentIdInCategory = currentIdInCategory;
        this.amountAtStart = amountAtStart;
        this.dateOfCreation = dateOfCreation;
    }
}
