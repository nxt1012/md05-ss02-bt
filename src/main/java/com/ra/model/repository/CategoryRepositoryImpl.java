package com.ra.model.repository;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Category", Category.class).list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve categories: +" + e.getMessage());
        }
    }

    @Override
    public Category save(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.saveOrUpdate(category);
                transaction.commit();
                return category;
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to save category: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }    }

    @Override
    public Category findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Category.class, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve category" + " id: " + id +" : " + e.getMessage());
        }    }

    @Override
    public void delete(Category category) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                session.delete(category);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw new RuntimeException("Failed to delete category: " + e.getMessage());
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to open session: " + e.getMessage());
        }
    }
}
