package com.siy.siyresource.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface CommonRepository<T, Id extends Serializable> extends Repository<T, Id> {

    <E extends T> E saveAndFlush(E entity);
    <E extends T> E save(E entity);
    <E extends T> E findById(Id id);
    <E extends T> void delete(E var1);
    <E extends T> List<E> findAll();
    void deleteById(Id id);
}
