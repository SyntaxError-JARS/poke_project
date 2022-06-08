package com.revature.pokedex.element_type;

import com.revature.pokedex.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.util.List;

public class ElementTypeDao implements Crudable<ElementType> {

    @Override
    public ElementType create(ElementType newElementType) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newElementType);
            transaction.commit();
            return newElementType;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<ElementType> findAll() {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<ElementType> elementTypes = session.createQuery("FROM elemental_type").list();
            transaction.commit();
            return elementTypes;
        } catch (HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public ElementType findById(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            ElementType elementType = session.get(ElementType.class, Integer.parseInt(id));
            transaction.commit();
            return elementType;
        } catch(HibernateException | IOException e){
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean update(ElementType updatedElementType) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedElementType);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            ElementType elementType = session.load(ElementType.class, id);
            session.remove(elementType);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
