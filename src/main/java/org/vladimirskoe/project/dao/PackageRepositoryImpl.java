package org.vladimirskoe.project.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.entity.Product;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PackageRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Assert.notNull(this.sessionFactory, "Session factory cannot be null");
    }

    @Override
    public Package addPackage(Package pack) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        if (pack.getProduct() != null) {
            int id = pack.getProduct().getId();
            Product product = session.get(Product.class, id);
            pack.setProduct(product);
        }
        session.save(pack);
        session.getTransaction().commit();
        return pack;
    }

    @Override
    public Optional<Package> getPackageById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Package pack = session.get(Package.class, id);
        session.getTransaction().commit();
        return Optional.ofNullable(pack);
    }

    @Override
    public List<Package> getAllPackages() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Package> packages = session.createQuery("from Package", Package.class).list();
        session.getTransaction().commit();
        return packages;
    }

    @Override
    public Package updatePackage(Package pack) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(pack);
        session.getTransaction().commit();
        return pack;
    }

    @Override
    public void deletePackage(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Package pack = session.load(Package.class, id);
        session.delete(pack);
        session.getTransaction().commit();
    }
}