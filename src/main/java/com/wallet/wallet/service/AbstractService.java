package com.wallet.wallet.service;

import java.util.Optional;

public interface AbstractService<T> {
    Optional<T> findOneById(Long id);
    void delete(Long id);
}
