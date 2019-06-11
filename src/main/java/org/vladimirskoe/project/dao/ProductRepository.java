package org.vladimirskoe.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {

}
