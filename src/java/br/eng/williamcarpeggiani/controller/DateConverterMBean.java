/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author william
 */
@ManagedBean(name = "dateConvertMBean")
@RequestScoped
public class DateConverterMBean implements Serializable {

    public DateConverterMBean() {
    }

    public String getFormatYear() {
        return new SimpleDateFormat("yyyy").format(new Date());
    }

    public String getFormatDate() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        
    }
    
    public String getDateSystem() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
        Date date = new Date(); 
        return dateFormat.format(date);
    }
    

}
