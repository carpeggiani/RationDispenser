/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author william
 */
@ManagedBean(name = "menuMBean")
@RequestScoped
@ViewScoped
public class MenuMBean implements Serializable {

    /**
     * Creates a new instance of MenuMBean
     */
    
    private String page;
    
    public MenuMBean() {
    }
    
    public int getAction() {
        return 1;
    }
    
    public void setPage(String page) {
        this.page = page;
    }
    
    public String getPage() {
        return this.page;
    }
    
}
