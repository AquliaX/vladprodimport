package org.vladimirskoe.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.service.PackageService;

import java.util.List;

@RestController
@RequestMapping(path = "/packages", consumes = "application/json", produces = "application/json")
public class PackageController {

    private PackageService packageService;

    @Autowired
    private PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @PostMapping
    public Package addPackage(@RequestBody Package pack) {
        return packageService.addPackage(pack);
    }

    @GetMapping("/{id}")
    public Package getPackageById(@PathVariable Integer id) {
        return packageService.getPackageById(id);
    }

    @GetMapping
    public List<Package> getAllPackages() {
        return packageService.getAllPackages();
    }

    @PutMapping("/{id}")
    public Package updatePackage(@PathVariable Integer id, @RequestBody Package pack) {
        return packageService.updatePackage(id, pack);
    }

    @DeleteMapping("/{id}")
    public void deletePackage(@PathVariable Integer id) {
        packageService.deletePackage(id);
    }

}
