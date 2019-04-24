package org.vladimirskoe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vladimirskoe.project.converter.ProductConverter;
import org.vladimirskoe.project.dto.ProductDto;
import org.vladimirskoe.project.entity.Product;
import org.vladimirskoe.project.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
@CrossOrigin
public class ProductController {

    private ProductService productService;
    private ProductConverter productConverter;

    @Autowired
    private ProductController(ProductService productService, ProductConverter productConverter) {
        this.productService = productService;
        this.productConverter = productConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto addProduct(@RequestBody ProductDto productDto) {
        Product product = productConverter.fromDtoToProduct(productDto);
        productService.addProduct(product);
        return productConverter.fromProductToDto(product);
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Integer id) {
        Product product = productService.getProductById(id);
        return productConverter.fromProductToDto(product);
    }

    @GetMapping
    public List<ProductDto> getAllProducts() {
        return productService.getAllProducts()
                .stream()
                .map(product -> productConverter.fromProductToDto(product))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ProductDto updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Product product = productConverter.fromDtoToProduct(productDto);
        productService.updateProduct(id, product);
        return productConverter.fromProductToDto(product);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
    }
}
