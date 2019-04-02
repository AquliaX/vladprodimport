package org.vladimirskoe.project.dao;

import org.vladimirskoe.project.entity.Package;

import java.util.ArrayList;
import java.util.List;

public class PackageRepositoryImpl implements PackageRepository {

    private List<Package> packageList = new ArrayList<Package>();
    private int index = 0;

    public void addPackage(Package pack) {
        pack.setId(++index);
        packageList.add(pack);
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

    public void updatePackage(Package pack) {
        int id = pack.getId();
        Package value = getPackageById(id);

        value.setName(pack.getName());
        value.setAmount(pack.getAmount());
        value.setProduct(pack.getProduct());
    }

    public void deletePackage(Integer id) {
        Package value = getPackageById(id);
        packageList.remove(value);
    }
}