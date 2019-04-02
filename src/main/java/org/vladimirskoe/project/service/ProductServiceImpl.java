package org.vladimirskoe.project.service;

import org.vladimirskoe.project.entity.Product;
import org.vladimirskoe.project.dao.ProductRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addProduct(Product product) {
        if (product == null) {
            throw new NullProductException("Input value is null and can't be added. ");
        } else {
            return productRepository.addProduct(product);
        }
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Product updateProduct(Product product) {
        if (product == null) {
            throw new NullProductException("Input value is null and can't be updated.");
        } else {
            return productRepository.updateProduct(product);
        }
    }

    public void deleteProduct(Integer id) {
        productRepository.deleteProduct(id);
    }
}
