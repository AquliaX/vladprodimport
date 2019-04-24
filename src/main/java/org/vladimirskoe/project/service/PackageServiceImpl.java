package org.vladimirskoe.project.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vladimirskoe.project.dao.PackageRepository;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.exception.NullObjectException;

@Service
public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;

    @Autowired
    private PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    @Override
    public Package addPackage(Package pack) {
        return packageRepository.addPackage(pack);
    }

    @Override
    public Package getPackageById(Integer id) {
        return packageRepository
                .getPackageById(id)
                .orElseThrow(() -> new NullObjectException("Package not found"));
    }

    @Override
    public List<Package> getAllPackages() {
        return packageRepository.getAllPackages();
    }

    @Override
    public Package updatePackage(Integer id, Package pack) {
        Optional<Package> optionalPackage = packageRepository.getPackageById(id);
        if (!optionalPackage.isPresent()) {
            throw new NullObjectException("Package does not exist and cannot be updated");
        }
        return packageRepository.updatePackage(pack);
    }

    @Override
    public void deletePackage(Integer id) {
        Optional<Package> optionalPackage = packageRepository.getPackageById(id);
        if (!optionalPackage.isPresent()) {
            throw new NullObjectException("Package does not exist and cannot be deleted");
        }
        packageRepository.deletePackage(id);
    }
}
