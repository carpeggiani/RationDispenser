/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import br.eng.williamcarpeggiani.dao.ControllOfFeedingDAO;
import br.eng.williamcarpeggiani.dao.DeviceDAO;
import br.eng.williamcarpeggiani.dao.UserDAO;
import br.eng.williamcarpeggiani.model.ControllOfFeeding;
import br.eng.williamcarpeggiani.model.Device;
import br.eng.williamcarpeggiani.model.User;
import static com.sun.xml.ws.security.opt.impl.dsig.SignatureProcessor.sign;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 *
 * @author william
 */
public class Test {

    public static void main(String[] args) {

//        //CREATE
//        User user = new User("Administrador", "admin", "admin", "11111111111", "Rua A", 2, "Centro", "Concórdia", "SC", "22222222222", "teste@teste.com.br", 1);
//        User user2 = new User("William", "william", "william", "22222222222", "Rua tal", 295, "Flor da Serra", "Joacaba", "SC", "49999999999", "williamcarpeggiani@gmail.com", 2);
//        User user3 = new User("Jorge", "jorge", "jorge", "33333333333", "Rua B", 3, "Rupp", "Herval", "SC", "49999999999", "3@teste.com", 2);
//        User user4 = new User("Pedro", "pedro", "pedro", "44444444444", "Rua C", 4, "Nazare", "Concórdia", "SC", "49999999999", "4@teste.com", 2);
//        User user5 = new User("Paulo", "paulo", "paulo", "55555555555", "Rua D", 5, "Centro", "Centro", "SC", "49999999999", "5@teste.com", 2);
//        UserDAO userDao = new UserDAO();
//        userDao.create(user);
//        userDao.create(user2);
//        userDao.create(user3);
//        userDao.create(user4);
//        userDao.create(user5);
//        System.out.println("@Teste.UserDao.Create");
//
//        String date = new DateConverterMBean().getDateSystem();
//        Timestamp ti = Timestamp.valueOf(date);
//        
//        Device device = new Device("Device 1", "1234567890", ti, ti, false, 30, (float) 10.50, 20, 20, user2);
//        Device device2 = new Device("Device 2", "0123456789", ti, ti, false, 25, (float) 10.50, 20, 20, user2);
//        Device device3 = new Device("Device 3", "9012345678", ti, ti, false, 30, (float) 10.50, 20, 20, user2);
//        DeviceDAO deviceDAO = new DeviceDAO();
//        deviceDAO.create(device);
//        deviceDAO.create(device2);
//        deviceDAO.create(device3);
//        ControllOfFeedingDAO controllDAO = new ControllOfFeedingDAO();
//        ControllOfFeeding controll = new ControllOfFeeding(false, device);
//        ControllOfFeeding controll2 = new ControllOfFeeding(false, device2);
//        ControllOfFeeding controll3 = new ControllOfFeeding(false, device3);
//        controllDAO.create(controll);
//        controllDAO.create(controll2);
//        controllDAO.create(controll3);
        //READ
        //        UserDAO userDao = new UserDAO();
        //        User user = new User();
        //        user = userDao.read(1);
        //        System.out.println(user.getName());      
        //        System.out.println("@Teste.UserDao.Read");
        //GET USERS
        //        UserDAO userDao = new UserDAO();
        //        User user = new User();
        //        List<User> listUser = userDao.getUsers();
        //        
        //        for(int i = 0; i < listUser.size(); i++) {
        //            System.out.println(listUser.get(i).getName());
        //        }
        //        
        //        System.out.println("@Teste.UserDao.listUser");
        //UPDATE
        //        UserDAO userDao = new UserDAO();
        //        User user = new User();
        //        user = userDao.read(1);
        //        user.setName("Gabriel");
        //        userDao.update(user);
        //        List<User> listUser = userDao.getUsers();
        //
        //        for (int i = 0; i < listUser.size(); i++) {
        //            System.out.println(listUser.get(i).getName());
        //        }
        //     
        //EXCLUIR      
        //UserDAO userDao = new UserDAO();
        //User user = new User();
        //        user = userDao.read(7);
        //        userDao.delete(user
        //        UserDAO userDao = new UserDAO();
        //        List<User> listUser = userDao.getUsersByAccess(1);
        //
        //        for (int i = 0; i < listUser.size(); i++) {
        //            System.out.println(listUser.get(i).getName());
        //        }
        

        // DELETE DEVICE
//        DeviceDAO deviceDAO = new DeviceDAO();
//        Device device = new Device();
//        device = deviceDAO.read(3);
//        ControllOfFeedingDAO controllDAO = new ControllOfFeedingDAO();
//        ControllOfFeeding controll = new ControllOfFeeding();
//        controll = controllDAO.read(3);
//        System.out.println("Controll: "+controll.getId_controll());
//        System.out.println("Device: "+device.getId_device());
//        controllDAO.delete(controll);
//        deviceDAO.delete(device);
        
//        // READ SERIAL
//        DeviceDAO deviceDAO = new DeviceDAO();
//        Device device = new Device();
//        device = deviceDAO.readSerial("1234567890");
//        System.out.println("Device: " + device.getName());


    }

}
