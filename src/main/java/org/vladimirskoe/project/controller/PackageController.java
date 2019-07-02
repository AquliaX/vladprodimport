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
import org.vladimirskoe.project.converter.PackageConverter;
import org.vladimirskoe.project.dto.PackageDto;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.service.PackageService;

@RestController
@RequestMapping("/api/packages")
@CrossOrigin
public class PackageController {

    private PackageConverter packageConverter;
    private PackageService packageService;

    @Autowired
    private PackageController(PackageService packageService, PackageConverter packageConverter) {
        this.packageService = packageService;
        this.packageConverter = packageConverter;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PackageDto addPackage(@Valid @RequestBody PackageDto packageDto) {
        Package pack = packageConverter.fromDtoToPackage(packageDto);
        packageService.addPackage(pack);
        return packageConverter.fromPackageToDto(pack);
    }

    @GetMapping("/{id}")
    public PackageDto getPackageById(@PathVariable Integer id) {
        Package pack = packageService.getPackageById(id);
        return packageConverter.fromPackageToDto(pack);
    }

    @GetMapping
    public List<PackageDto> getAllPackages() {
        return packageService.getAllPackages()
                .stream()
                .map(pack -> packageConverter.fromPackageToDto(pack))
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public PackageDto updatePackage(@PathVariable Integer id,
            @Valid @RequestBody PackageDto packageDto) {
        Package pack = packageConverter.fromDtoToPackage(packageDto);
        packageService.updatePackage(id, pack);
        return packageConverter.fromPackageToDto(pack);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePackage(@PathVariable Integer id) {
        packageService.deletePackage(id);
    }

}
