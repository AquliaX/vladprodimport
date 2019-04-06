package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Package;

import java.util.List;

public interface PackageRepository {

    Package addPackage(Package pack);

    Package getPackageById(Integer id);

    List<Package> getAllPackages();

    Package updatePackage(Integer id, Package pack);

    void deletePackage(Integer id);
}
