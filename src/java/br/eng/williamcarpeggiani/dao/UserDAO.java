/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.User;
import br.eng.williamcarpeggiani.util.HibernateUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author william
 */
public class UserDAO implements UserInterface {

    @Override
    public void create(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }

    }

    @Override
    public User read(int idUser) {

        User user = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("id_user", new Integer(idUser)));
            user = (User) criteria.uniqueResult();
            transaction.commit();
            //return user;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return user;
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
//            Customer cust = (Customer) session.load(Customer.class, new Integer(custid));
            session.delete(this.read(user.getId()));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
    }

    @Override
    public List<User> getUsers() {
        Session session = null;
        Transaction transaction = null;
        List<User> listUsers = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria crit = session.createCriteria(User.class);
            listUsers = crit.list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
        return listUsers;

    }

    @Override
    public List<User> getUsersByAccess(int access) {

//        Session session = null;
//        Transaction transaction = null;
        List<User> listUsers = null;
//
//        try {
//            session = HibernateUtil.getSessionFactory().openSession();
//            transaction = session.beginTransaction();
//            Criteria crit = session.createCriteria(User.class);
//            crit.createAlias("User", "u").add(Restrictions.like("accessLevel", new Integer(access)));
//            listUsers = crit.list();
//            transaction.commit();
//        } catch (HibernateException e) {
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.flush();
//            session.close();
//        }
        return listUsers;
    }

    public List<User> list() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List lista = session.createQuery("from User").list();
        transaction.commit();
        return null;

    }

    public User validateUser(User user) {

        User userTemp = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(User.class);
            criteria.add(Restrictions.eq("username", user.getUsername()))
                    .add(Restrictions.eq("password", user.getPassword()));
            userTemp = (User) criteria.uniqueResult();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return userTemp;
    }

}
