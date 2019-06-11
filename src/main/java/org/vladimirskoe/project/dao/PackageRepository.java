package org.vladimirskoe.project.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.vladimirskoe.project.entity.Package;

@Repository
public interface PackageRepository extends CrudRepository<Package, Integer> {
}
