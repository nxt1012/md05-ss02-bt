package com.ra.model.repository;

import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Product> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Product", Product.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve products: +" + e.getMessage());
        }
    }

    @Override
    public Product save(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(product);
                transaction.commit();
                return product;
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to save product: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }
    }

    @Override
    public Product findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Product.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve product" + " id: " + id +" : " + e.getMessage());
        }
    }

    @Override
    public void delete(Product product) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(product);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to delete product: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }
    }
}
