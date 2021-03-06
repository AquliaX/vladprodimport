package org.vladimirskoe.project.converter.implementation;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vladimirskoe.project.converter.PackageConverter;
import org.vladimirskoe.project.converter.ProductConverter;
import org.vladimirskoe.project.dto.PackageDto;
import org.vladimirskoe.project.entity.Package;

@Component
public class PackageConverterImpl implements PackageConverter {

    private ProductConverter productConverter;

    @Autowired
    private PackageConverterImpl(ProductConverter productConverter) {
        this.productConverter = productConverter;
    }

    @Override
    public PackageDto fromPackageToDto(Package pack) {
        PackageDto packageDto = new PackageDto();
        BeanUtils.copyProperties(pack, packageDto, "product");
        packageDto.setProduct(productConverter.fromProductToDto(pack.getProduct()));
        return packageDto;
    }

    @Override
    public Package fromDtoToPackage(PackageDto packageDto) {
        Package pack = new Package();
        BeanUtils.copyProperties(packageDto, pack, "product");
        pack.setProduct(productConverter.fromDtoToProduct(packageDto.getProduct()));
        return pack;
    }
}
