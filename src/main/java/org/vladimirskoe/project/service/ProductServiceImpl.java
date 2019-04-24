package org.vladimirskoe.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladimirskoe.project.dao.ProductRepository;
import org.vladimirskoe.project.entity.Product;
import org.vladimirskoe.project.exception.NullObjectException;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addProduct(Product product) {
        return productRepository.addProduct(product);
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository
                .getProductById(id)
                .orElseThrow(() -> new NullObjectException("Product not found"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public Product updateProduct(Integer id, Product product) {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        if (!optionalProduct.isPresent()) {
            throw new NullObjectException("Product does not exist and cannot be updated");
        }
        return productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.getProductById(id);
        if (!optionalProduct.isPresent()) {
            throw new NullObjectException("Product does not exist and cannot be deleted");
        }
        productRepository.deleteProduct(id);
    }
}
