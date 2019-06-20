package org.vladimirskoe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.vladimirskoe.project.converter.PackageConverter;
import org.vladimirskoe.project.dto.PackageDto;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.service.PackageService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/packages")
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
    public PackageDto addPackage(@RequestBody PackageDto packageDto) {
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
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PackageDto updatePackage(@PathVariable Integer id, @RequestBody PackageDto packageDto) {
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
