package org.vladimirskoe.project.service;

import org.vladimirskoe.project.dao.ProductRepository;
import org.vladimirskoe.project.entity.Product;

import java.util.List;

public interface ProductService extends ProductRepository {

    Product addProduct(Product product);

    Product getProductById(Integer id);

    List<Product> getAllProducts();

    Product updateProduct(Product product);

    void deleteProduct(Integer id);
}