package com.wallet.wallet.service;

import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public abstract class AbstractServiceImplementation<T> implements AbstractService<T> {
    abstract CrudRepository<T, Long> getDao();

    @Override
    public Optional<T> findOneById(Long id) { return getDao().findById(id); }

    @Override
    public void delete(Long id) {
        if (id == null) {
            throw new NullPointerException("You cannot delete object with null id");
        }
        if (!findOneById(id).isPresent()) {
            throw new NullPointerException("You cannot delete non-existing object with id: " + id);
        }
        getDao().findById(id);
    }
}
