package com.minimarket.catalogservice.specification;

import com.minimarket.catalogservice.entity.Item;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class ItemSpecifications {
    public static Specification<Item> hasCategory (String category) {
        return (root, query, cb) -> cb.equal(root.get("category"), category.toLowerCase());
    }
    public static Specification<Item> containsCategory (String category) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("category")),"%" + category.toLowerCase() + "%");
    }
    public static Specification<Item> hasName (String name) {
        return (root, query, cb) -> cb.like(cb.lower(root.get("name")),"%" +name.toLowerCase() + "%");
    }
    public static Specification<Item> containsName (String name) {
        return ((root, query, cb) -> cb.like(root.get("name"),"%" +name.toLowerCase() + "%"));
    }
    public static Specification<Item> hasPrice (BigDecimal price) {
        return (root, query, cb) -> cb.equal(root.get("price"), price);
    }
    public static Specification<Item> priceLessThan (BigDecimal price) {
        return (root, query, cb) -> cb.lessThan(root.get("price"), price);
    }
    public static Specification<Item> priceGreaterThan (BigDecimal price) {
        return (root, query, cb) -> cb.greaterThan(root.get("price"), price);
    }
    public static Specification<Item> priceLessThanEqual (BigDecimal price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Item> priceGreaterThanEqual (BigDecimal price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }
    public static Specification<Item> priceBetween(BigDecimal price1, BigDecimal price2) {
        return (root, query, cb) -> cb.between(root.get("price"), price1, price2);
    }
    public static Specification<Item> isAvailable(Boolean isAvailable) {
        return (root, query, cb) -> cb.equal(root.get("isAvailable"), isAvailable);
    }
}
