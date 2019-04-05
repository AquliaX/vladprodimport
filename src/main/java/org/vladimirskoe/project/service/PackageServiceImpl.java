package org.vladimirskoe.project.service;

import org.vladimirskoe.project.dao.PackageRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.exception.NullObjectException;

import java.util.List;

@Service
public class PackageServiceImpl implements PackageService {

    private PackageRepository packageRepository;

    @Autowired
    private PackageServiceImpl(PackageRepository packageRepository) {
        this.packageRepository = packageRepository;
    }

    public Package addPackage(Package pack) {
        if (pack == null) {
            throw new NullObjectException("Input value is null and can't be added. ");
        } else {
            return packageRepository.addPackage(pack);
        }
    }

    public Package getPackageById(Integer id) {
        return packageRepository.getPackageById(id);
    }

    public List<Package> getAllPackages() {
        return packageRepository.getAllPackages();
    }

    public Package updatePackage(Integer id, Package pack) {
        if (pack == null) {
            throw new NullObjectException("Input value is null and can't be updated. ");
        } else {
            return packageRepository.updatePackage(id, pack);
        }
    }

    public void deletePackage(Integer id) {
        packageRepository.deletePackage(id);
    }
}
