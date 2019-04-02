package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Package;

import java.util.List;

public interface PackageRepository {

    void addPackage(Package pack);

    Package getPackageById(Integer id);

    List<Package> getAllPackages();

    void updatePackage(Package pack);

    void deletePackage(Integer id);
}
