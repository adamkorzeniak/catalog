package com.adamkorzeniak.catalog.finance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adamkorzeniak.catalog.finance.model.Category;

public interface TransactionRepository extends JpaRepository<Category, Long> {

}
