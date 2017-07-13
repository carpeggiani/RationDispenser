/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.RegisterOfFeeding;
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
public class RegisterOfFeedingDAO implements RegisterOfFeedingInterface {

    @Override
    public void create(RegisterOfFeeding register) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(register);
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
    public RegisterOfFeeding read(int idRegister) {
        RegisterOfFeeding register = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(RegisterOfFeeding.class);
            criteria.add(Restrictions.eq("id_user", idRegister));
            register = (RegisterOfFeeding) criteria.uniqueResult();
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
        return register;
    }

    @Override
    public void update(RegisterOfFeeding register) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(register);
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
    public void delete(RegisterOfFeeding register) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
//            Customer cust = (Customer) session.load(Customer.class, new Integer(custid));
            session.delete(this.read(register.getId_reg()));
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
    public List<RegisterOfFeeding> getRegisters(int idDevice) {
        Session session = null;
        Transaction transaction = null;
        List<RegisterOfFeeding> listRegisters = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(RegisterOfFeeding.class);
            listRegisters = criteria.list();
            transaction.commit();

        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.flush();
            session.close();
        }
        return listRegisters;
    }

    @Override
    public List<RegisterOfFeeding> getRegisters() {
        Session session = null;
        Transaction transaction = null;
        List<RegisterOfFeeding> listDevices = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(RegisterOfFeeding.class);
            listDevices = criteria.list();
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
        return listDevices;
    }

}
