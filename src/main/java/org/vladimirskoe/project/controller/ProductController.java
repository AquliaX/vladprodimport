package org.vladimirskoe.project.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.vladimirskoe.project.converter.ProductConverter;
import org.vladimirskoe.project.dto.ProductDto;
import org.vladimirskoe.project.entity.Product;
import org.vladimirskoe.project.service.ProductService;

@RestController
@RequestMapping("/api/products")
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
    public ProductDto addProduct(@Valid @RequestBody ProductDto productDto) {
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
    public ProductDto updateProduct(@PathVariable Integer id,
            @Valid @RequestBody ProductDto productDto) {
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
