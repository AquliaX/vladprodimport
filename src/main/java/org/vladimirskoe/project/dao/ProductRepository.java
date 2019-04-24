package org.vladimirskoe.project.dao;

import java.util.Optional;
import org.vladimirskoe.project.entity.Product;

import java.util.List;

public interface ProductRepository {

    Product addProduct(Product product);

    Optional<Product> getProductById(Integer id);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(Integer id);

}
