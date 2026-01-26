package com.minimarket.catalogservice.repository;

import com.minimarket.catalogservice.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, JpaSpecificationExecutor<Item> {
    Page<Item> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page <Item> findByCategoryIgnoreCase(String category, Pageable pageable);
    Page <Item> findByIsAvailable(Boolean isAvailable, Pageable pageable);
}
