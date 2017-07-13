/*
 * To changeUser this license header, choose License Headers in Project Properties.
 * To changeUser this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import br.eng.williamcarpeggiani.dao.UserDAO;
import br.eng.williamcarpeggiani.dao.DeviceDAO;
import br.eng.williamcarpeggiani.model.User;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author william
 */
@ManagedBean(name = "userMBean")
@ViewScoped
@RequestScoped
public class UserMBean extends AbstractMBean implements Serializable {

    //public static final String INJECTION_NAME = "#{userMBean}";

    private User user;
    private List<User> userU;
    private List<User> userA;
    private List<User> listUsers;
    private UserDAO userDAO;
    private DeviceDAO deviceDAO;
    private boolean addNewReg;

    private List<User> filteredUsers;

    public UserMBean() {
    }

    public void addUser() {
        try {
            getUserDao().create(user);
            DialogClose();
            UserInfoMessage("User created with success!");
            resetUser();
            loadUsers();
            System.out.println("@UserMBean.addUser");
        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, User can not be created. Try again later!");
            e.printStackTrace();
        }
    }

    public void changeUser() {
        try {
            getUserDao().update(user);
            DialogClose();
            UserInfoMessage("User changed with success!");
            resetUser();
            loadUsers();
        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, User can not be changed. Try again later!");
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (addNewReg) {
                addUser();
            } else {
                changeUser();
            }
        } finally {
            getListUsers();
        }
    }

    public String deleteUser() {
        try {
            getUserDao().delete(user);
            DialogClose();
            UserInfoMessage("User deleted with success!");
            resetUser();
            loadUsers();

        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, User can not be deleted. Try again later!");
            e.printStackTrace();

        }
        return "deleteUser";
    }

    public DeviceDAO getDeviceDAO() {
        return deviceDAO;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<User> getListUsers() {
        if (listUsers == null) {
            loadUsers();
        }
        return listUsers;
    }

    public UserDAO getUserDao() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    //método que lista apenas os usuários do tipo CLIENTE
    public List<User> getUsersU() {
        userU = getUserDao().getUsersByAccess(2);
        return userU;
    }

    //método que lista apenas os usuários do tipo ADMIN
    public List<User> getUsersA() {
        userA = getUserDao().getUsersByAccess(1);
        return userA;
    }

    public boolean isAddNewReg() {
        return addNewReg;
    }

    //seta bool pra addUser novo usuário
    public void setAddNewReg(boolean addNewReg) {
        this.addNewReg = addNewReg;
    }

    //carregar usuários
    private void loadUsers() {
        listUsers = getUserDao().getUsers();
    }

    //instanciar um novo objeto usuario
    public void resetUser() {
        user = new User();
    }

    public User getUser() {
        if (this.user == null) {
            this.user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getUserU() {
        return userU;
    }

    public void setUserU(List<User> userU) {
        this.userU = userU;
    }

    public List<User> getUserA() {
        return userA;
    }

    public void setUserA(List<User> userA) {
        this.userA = userA;
    }

    public List<User> getUsers() {
        return listUsers;
    }

    public void setUsers(List<User> users) {
        this.listUsers = users;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    //metodo chamado ao realizar logout
    public String Logout() {
        getRequest().getSession().invalidate(); //torna sessão inválida
        return "/Pages/Public/login.xhtml?faces-redirect=true";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public List<User> getFilteredUsers() {
        return filteredUsers;
    }

    public void setFilteredUsers(List<User> filteredUsers) {
        this.filteredUsers = filteredUsers;
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("User Edited", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((User) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
