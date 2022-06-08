package com.revature.pokedex.trainer;

import com.revature.pokedex.util.interfaces.Crudable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.*;
import java.util.List;

public class TrainerDao implements Crudable<Trainer> {

    @Override
    public Trainer create(Trainer newTrainer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.save(newTrainer);
            transaction.commit();
            return newTrainer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    @Override
    public List<Trainer> findAll() {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            List<Trainer> trainers = session.createQuery("FROM Trainer").list();
            transaction.commit();
            return trainers;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Override
    public Trainer findById(String email) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Trainer trainer = session.get(Trainer.class, email);
            transaction.commit();
            return trainer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }

    }

    @Override
    public boolean update(Trainer updatedTrainer) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            session.merge(updatedTrainer);
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
    public boolean delete(String email) {
        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Trainer trainer = session.load(Trainer.class, email);
            session.remove(trainer);
            transaction.commit();
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    public Trainer authenticateTrainer(String email, String password){

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Trainer where email= :email and password= :password");
            query.setParameter("email", email);
            query.setParameter("password", password);
            Trainer trainer = (Trainer) query.uniqueResult();
            transaction.commit();
            return trainer;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            HibernateUtil.closeSession();
        }

    }
    public boolean checkEmail(String email) {

        try {
            Session session = HibernateUtil.getSession();
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("from Trainer where email= :email");
            query.setParameter("email", email);
            Trainer trainer = (Trainer) query.uniqueResult();
            transaction.commit();
            if(trainer == null) return false;
            return true;
        } catch (HibernateException | IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            HibernateUtil.closeSession();
        }
    }
}
