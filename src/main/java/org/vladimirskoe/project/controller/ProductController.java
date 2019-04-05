package org.vladimirskoe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vladimirskoe.project.entity.Product;
import org.vladimirskoe.project.service.ProductService;

import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Product getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @GetMapping(consumes = "application/json", produces = "application/json")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PutMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public Product updateProduct(@ PathVariable Integer id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping(path = "/{id}", consumes = "application/json", produces = "application/json")
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
