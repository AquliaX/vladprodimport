package org.vladimirskoe.project.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.vladimirskoe.project.dto.ProductDto;
import org.vladimirskoe.project.entity.Product;

@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public ProductDto fromProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    @Override
    public Product fromDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }
}
