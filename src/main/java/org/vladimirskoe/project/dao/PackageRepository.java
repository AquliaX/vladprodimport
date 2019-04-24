package org.vladimirskoe.project.dao;

import java.util.List;
import java.util.Optional;
import org.vladimirskoe.project.entity.Package;

public interface PackageRepository {

    Package addPackage(Package pack);

    Optional<Package> getPackageById(Integer id);

    List<Package> getAllPackages();

    Package updatePackage(Package pack);

    void deletePackage(Integer id);
}
