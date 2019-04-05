package org.vladimirskoe.project.service;

import org.vladimirskoe.project.entity.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getAllProducts();

    Product updateProduct(Integer id, Product product);

    void deleteProduct(Integer id);
}
