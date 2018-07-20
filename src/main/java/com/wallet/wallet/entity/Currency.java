package com.wallet.wallet.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@EqualsAndHashCode
@NoArgsConstructor @RequiredArgsConstructor @Setter @Getter
@Table(name = "currency")
public class Currency {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;
    @NonNull
    private String currencyName;
    @NonNull
    private Double currenyFactor;
    @NonNull
    private Long dateOfCreation = Instant.now().getEpochSecond();
}
