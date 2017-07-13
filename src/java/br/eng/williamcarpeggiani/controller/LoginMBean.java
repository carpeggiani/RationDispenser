/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import br.eng.williamcarpeggiani.dao.UserDAO;
import br.eng.williamcarpeggiani.model.User;
import static com.sun.faces.facelets.util.Path.context;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author william
 */
@ManagedBean
@RequestScoped
public class LoginMBean {

    //@ManagedProperty(value = UserMBean.INJECTION_NAME)
    private UserMBean userMBean;
    private String username;
    private String password;

    public LoginMBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) throws IOException {
//        FacesContext context = FacesContext.getCurrentInstance();  //verificar
//        FacesMessage message = null;
//        boolean loggedIn = false;
//
//        UserDAO userDAO = new UserDAO();
//        User userTemp = new User();
//        userTemp.setUsername(username);
//        userTemp.setPassword(password);
//        userTemp = userDAO.validateUser(userTemp);
//
//        if (userTemp != null) {
//            userMBean.setUser(userTemp);
//            loggedIn = true;
//            
//            HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
//            request.getSession().setAttribute("user", userTemp);
//            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
//            if (userTemp.getAccessLevel() == 1) {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("./Protected/manageUsers.xhtml?facesRedirect=true");
//            } else if (userTemp.getAccessLevel() == 2) {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("./Protected/manageDevices.xhtml?facesRedirect=true");
//            }
//        } else {
//            loggedIn = false;
//            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials!");
//        }
//
//        FacesContext.getCurrentInstance().addMessage(null, message);

    }

    public void setUsuarioMB(UserMBean usuMB) {
        this.userMBean = usuMB;
    }

}
