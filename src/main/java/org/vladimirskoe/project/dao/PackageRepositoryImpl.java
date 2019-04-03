package org.vladimirskoe.project.dao;

import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Package;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    private List<Package> packageList = new ArrayList<>();
    private int index = 0;

    public Package addPackage(Package pack) {
        pack.setId(++index);
        packageList.add(pack);
        return pack;
    }

    public Package getPackageById(Integer id) {
        Package value = null;
        for (Package pack : packageList) {
            if (pack.getId().equals(id)) {
                value = pack;
            }
        }
        return value;
    }

    public List<Package> getAllPackages() {
        return packageList;
    }

    public Package updatePackage(Package pack) {
        int id = pack.getId();
        Package value = getPackageById(id);

        value.setName(pack.getName());
        value.setAmount(pack.getAmount());
        value.setProduct(pack.getProduct());

        return value;
    }

    public void deletePackage(Integer id) {
        Package value = getPackageById(id);
        packageList.remove(value);
    }
}