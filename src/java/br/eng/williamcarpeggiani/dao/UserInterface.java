/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.User;
import java.util.List;

/**
 *
 * @author william
 */
public interface UserInterface {

    public void create(User user);

    public User read(int idUser);

    public void update(User user);

    public void delete(User user);

    public List<User> getUsers();
    
    public List<User> getUsersByAccess(int access);
    
    public List<User> list();
    
    public User validateUser(User user);

}
