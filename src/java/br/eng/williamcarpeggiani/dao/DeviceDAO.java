/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.Device;
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
public class DeviceDAO implements DeviceInterface {

    @Override
    public void create(Device device) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.save(device);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
    }

    @Override
    public Device read(int idDevice) {
        Device device = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Device.class);
            criteria.add(Restrictions.eq("id_device", new Integer(idDevice)));
            device = (Device) criteria.uniqueResult();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
        return device;
    }
    
    @Override
    public Device readSerial(String serial) {
        Device device = null;
        Session session = null;
        Transaction transaction = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Device.class);
            criteria.add(Restrictions.eq("serial", serial));
            device = (Device) criteria.uniqueResult();
            transaction.commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
        return device;
    }

    @Override
    public void update(Device device) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
            session.update(device);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            //e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
    }

    @Override
    public void delete(Device device) {
         Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = session.beginTransaction();
//            Customer cust = (Customer) session.load(Customer.class, new Integer(custid));
            session.delete(this.read(device.getId_device()));
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            //session.flush();
            //session.close();
        }
    }

    @Override
    public List<Device> getDevices() {
        Session session = null;
        Transaction transaction = null;
        List<Device> listDevices = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Device.class);
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

    @Override
    public List<Device> getDevicesForUser(int idUser) {
        Session session = null;
        Transaction transaction = null;
        List<Device> listDevices = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Criteria criteria = session.createCriteria(Device.class);
            criteria.add(Restrictions.eq("id_user", new Integer(idUser)));
            listDevices = criteria.list();
            transaction.commit();
        } catch (RuntimeException e) {
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
