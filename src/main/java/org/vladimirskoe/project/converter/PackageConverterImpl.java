package org.vladimirskoe.project.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vladimirskoe.project.dto.PackageDto;
import org.vladimirskoe.project.entity.Package;

@Component
public class PackageConverterImpl implements PackageConverter {

    private ProductConverterImpl productConverter;

    @Autowired
    private PackageConverterImpl(ProductConverterImpl productConverter) {
        this.productConverter = productConverter;
    }

    @Override
    public PackageDto fromPackageToDto(Package pack) {

        PackageDto packageDto = new PackageDto();
        BeanUtils.copyProperties(pack, packageDto, "product");
        packageDto.setProductDto(productConverter.fromProductToDto(pack.getProduct()));
        return packageDto;
    }

    @Override
    public Package fromDtoToPackage(PackageDto packageDto) {
        Package pack = new Package();
        BeanUtils.copyProperties(packageDto, pack, "productDto");
        pack.setProduct(productConverter.fromDtoToProduct(packageDto.getProductDto()));
        return pack;
    }
}
