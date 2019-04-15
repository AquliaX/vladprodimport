package org.vladimirskoe.project.converter;

import org.vladimirskoe.project.dto.ProductDto;
import org.vladimirskoe.project.entity.Product;

public interface ProductConverter {

    ProductDto fromProductToDto(Product product);

    Product fromDtoToProduct(ProductDto productDto);

}
