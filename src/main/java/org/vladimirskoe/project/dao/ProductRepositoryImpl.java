package org.vladimirskoe.project.dao;

import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.vladimirskoe.project.entity.Product;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public ProductRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

        Assert.notNull(this.sessionFactory, "Session factory cannot be null");
    }

    @Override
    public Product addProduct(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(product);
        session.getTransaction().commit();
        return product;
    }

    @Override
    public Optional<Product> getProductById(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.get(Product.class, id);
        session.getTransaction().commit();
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> getAllProducts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Product> products = session.createQuery("from Product", Product.class).list();
        session.getTransaction().commit();
        return products;
    }

    @Override
    public Product updateProduct(Product product) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(product);
        session.getTransaction().commit();
        return product;
    }

    @Override
    public void deleteProduct(Integer id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Product product = session.load(Product.class, id);
        session.delete(product);
        session.getTransaction().commit();
    }

}
