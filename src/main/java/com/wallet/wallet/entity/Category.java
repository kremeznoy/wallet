package com.wallet.wallet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@EqualsAndHashCode
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter
@Table(name = "category")
public class Category {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @NonNull
    private String categoryName;
    @NonNull
    private Boolean categoryStatus = true;
    @NonNull
    private Long currenyIdInCategory;
    @NonNull
    private Double amountAtStart;
    @NonNull
    private Long dateOfCreation = Instant.now().getEpochSecond();
}
