package org.vladimirskoe.project.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.vladimirskoe.project.entity.Package;
import org.vladimirskoe.project.entity.Product;

import java.util.List;

@Repository
public class PackageRepositoryImpl implements PackageRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public PackageRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Assert.notNull(this.sessionFactory, "Session factory cannot be null");
    }

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

    public Package getPackageById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Package pack = session.get(Package.class, id);
        session.getTransaction().commit();
        return pack;
    }

    public List<Package> getAllPackages() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Package> packages = session.createQuery("from Package", Package.class).list();
        session.getTransaction().commit();
        return packages;
    }

    public Package updatePackage(Integer id, Package pack) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        pack.setId(id);
        session.saveOrUpdate(pack);
        session.getTransaction().commit();
        return pack;
    }

    public void deletePackage(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Package pack = session.load(Package.class, id);
        session.delete(pack);
        session.getTransaction().commit();
    }
}