/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import br.eng.williamcarpeggiani.dao.ControllOfFeedingDAO;
import br.eng.williamcarpeggiani.dao.DeviceDAO;
import br.eng.williamcarpeggiani.dao.RegisterOfFeedingDAO;
import br.eng.williamcarpeggiani.dao.UserDAO;
import br.eng.williamcarpeggiani.model.ControllOfFeeding;
import br.eng.williamcarpeggiani.model.Device;
import br.eng.williamcarpeggiani.model.RegisterOfFeeding;
import br.eng.williamcarpeggiani.model.User;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author william
 */
@ManagedBean(name = "controlDeviceMBean")
@RequestScoped
public class ControllDeviceMBean extends AbstractMBean implements Serializable {

    private Device device;
    private List<Device> devices;
    private DeviceDAO deviceDAO;
    private ControllOfFeeding controll;
    private ControllOfFeedingDAO controllDAO;
    private RegisterOfFeeding register;
    private RegisterOfFeedingDAO registerDAO;
    private List<RegisterOfFeeding> listRegister;
    private User user;
    private UserDAO userDAO;
    private boolean addNewReg;

    private List<RegisterOfFeeding> filteredRegisters;

    public ControllDeviceMBean() {
    }

    public void changeDevice() {
        try {
            setDevice(getDeviceDAO().readSerial("1234567890"));
            setControll(getControllDAO().read(device.getId_device()));
            controll.setStatus(true);
            getControllDAO().update(controll);

            DialogClose();
            UserInfoMessage("Status Device changed with success!");
            resetDevice();
            loadRegisters();
        } catch (Exception e) {
            DialogOpenKeep();
            UserErrorMessage("Ops, Status Device can not be changed. Try again later!");
            e.printStackTrace();
        }
    }
    
    public String redirectControll() {
        
        return "controllDevice.xhtml?faces-redirect=true";
    }

    public RegisterOfFeeding getRegister() {
        if (this.register == null) {
            this.register = new RegisterOfFeeding();
        }
        return register;
    }

    public void setRegister(RegisterOfFeeding register) {
        this.register = register;
    }

    public RegisterOfFeedingDAO getRegistarDAO() {
        if (this.registerDAO == null) {
            this.registerDAO = new RegisterOfFeedingDAO();
        }
        return registerDAO;
    }

    public void setRegistarDAO(RegisterOfFeedingDAO registarDAO) {
        this.registerDAO = registarDAO;
    }

    public List<RegisterOfFeeding> getListRegister() {
        if (listRegister == null) {
            loadRegisters();
        }
        return listRegister;
    }

    public void setListRegister(List<RegisterOfFeeding> listRegister) {
        this.listRegister = listRegister;
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
        if (device == null) {
            loadRegisters();
        }
        return devices;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void setDeviceDAO(DeviceDAO deviceDAO) {
        this.deviceDAO = deviceDAO;
    }

    public List<RegisterOfFeeding> getFilteredRegisters() {
        return filteredRegisters;
    }

    public void setFilteredRegisters(List<RegisterOfFeeding> filteredRegisters) {
        this.filteredRegisters = filteredRegisters;
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
    private void loadRegisters() {
        listRegister = getRegistarDAO().getRegisters();
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
