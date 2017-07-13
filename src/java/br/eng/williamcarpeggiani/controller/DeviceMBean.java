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
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author william
 */
@ManagedBean(name = "deviceMBean")
@ViewScoped
@RequestScoped
public class DeviceMBean extends AbstractMBean implements Serializable {

    private Device device;
    private List<Device> listDevices;
    private DeviceDAO deviceDAO;
    private ControllOfFeeding controll;
    private ControllOfFeedingDAO controllDAO;
    private User user;
    private UserDAO userDAO;
    private boolean addNewReg;

    private List<Device> filteredDevices;

    public DeviceMBean() {
    }

    public void addDevice() {
        try {
            getDeviceDAO().create(device);
            DialogClose();
            UserInfoMessage("Device created with success!");
            resetDevice();
            loadDevices();
            System.out.println("@DeviceMBean.addDevice");
        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, Device can not be created. Try again later!");
            e.printStackTrace();
        }
    }

    public void changeDevice() {
        try {
            getDeviceDAO().update(device);
            DialogClose();
            UserInfoMessage("Device changed with success!");
            resetDevice();
            loadDevices();
        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, Device can not be changed. Try again later!");
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            if (addNewReg) {
                addDevice();
            } else {
                changeDevice();
            }
        } finally {
            getListDevices();
        }
    }

    public String deleteDevice() {
        try {       
            
            setControll(getControllDAO().read(device.getId_device()));
            getControllDAO().delete(controll);
            getDeviceDAO().delete(device);
            DialogClose();
            UserInfoMessage("Device deleted with success!");
            resetDevice();
            loadDevices();

        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, Device can not be deleted. Try again later!");
            e.printStackTrace();

        }
        return "deleteDevice";
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

    public UserDAO getUserDAO() {
        if (this.userDAO == null) {
            this.userDAO = new UserDAO();
        }
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<Device> getListDevices() {
        if (listDevices == null) {
            loadDevices();
        }
        return listDevices;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Device> getDevices() {
        return listDevices;
    }

    public void setDevices(List<Device> devices) {
        this.listDevices = devices;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<Device> getFilteredDevices() {
        return filteredDevices;
    }

    public void setFilteredDevices(List<Device> filteredDevices) {
        this.filteredDevices = filteredDevices;
    }

    public DeviceDAO getDeviceDAO() {
        if (deviceDAO == null) {
            deviceDAO = new DeviceDAO();
        }
        return deviceDAO;
    }

    public ControllOfFeedingDAO getControllDAO() {
        if (controllDAO == null) {
            controllDAO = new ControllOfFeedingDAO();
        }
        return controllDAO;
    }

    public ControllOfFeeding getControll() {
        if (controll == null) {
            controll = new ControllOfFeeding();
        }
        return controll;
    }

    public void setControll(ControllOfFeeding controll) {
        this.controll = controll;
    }
    
    

    public void setControllDAO(ControllOfFeedingDAO controllDAO) {
        this.controllDAO = controllDAO;
    }

    public boolean isAddNewReg() {
        return addNewReg;
    }

    //seta bool pra addDevice novo usuário
    public void setAddNewReg(boolean addNewReg) {
        this.addNewReg = addNewReg;
    }

    //carregar usuários
    private void loadDevices() {
        listDevices = getDeviceDAO().getDevices();
    }

    //instanciar um novo objeto usuario
    public void resetDevice() {
        device = new Device();
    }

    public Device getDevice() {
        if (this.device == null) {
            this.device = new Device();
        }
        return device;

    }

    //metodo chamado ao realizar logout
    public String Logout() {
        getRequest().getSession().invalidate(); //torna sessão inválida
        return "/Pages/Public/login.xhtml?faces-redirect=true";
    }

    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public void onRowEdit(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Device Edited", ((Device) event.getObject()).getName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", ((Device) event.getObject()).getName());
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
