package org.vladimirskoe.project.service;

import org.vladimirskoe.project.entity.Package;

import java.util.List;

public interface PackageService {

    Package addPackage(Package pack);

    Package getPackageById(Integer id);

    List<Package> getAllPackages();

    Package updatePackage(Integer id, Package pack);

    void deletePackage(Integer id);
}
