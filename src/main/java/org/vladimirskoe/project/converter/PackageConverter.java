package org.vladimirskoe.project.converter;

import org.vladimirskoe.project.dto.PackageDto;
import org.vladimirskoe.project.entity.Package;

public interface PackageConverter {
    PackageDto fromPackageToDto(Package pack);

    Package fromDtoToPackage(PackageDto packageDto);
}
