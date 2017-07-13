/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author william
 */
@Entity
@Table(name = "Registeroffeeding")
public class RegisterOfFeeding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_register", unique = true)
    private int id_register;
    //@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp date;
    @Column(name = "statusFeeding")
    private String statusFeeding;
    private String ip;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_device", insertable = true, updatable = true)
    //@Cascade(CascadeType.SAVE_UPDATE)
    private Device device;

    public RegisterOfFeeding() {

    }

    public RegisterOfFeeding(Timestamp date, String statusFeeding, String ip, Device device) {
        this.date = date;
        this.statusFeeding = statusFeeding;
        this.ip = ip;
        this.device = device;
    }

    public int getId_reg() {
        return id_register;
    }

    public void setId_reg(int id_reg) {
        this.id_register = id_reg;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getStatusFeeding() {
        return statusFeeding;
    }

    public void setStatusFeeding(String statusFeeding) {
        this.statusFeeding = statusFeeding;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + this.id_register;
        hash = 97 * hash + Objects.hashCode(this.date);
        hash = 97 * hash + Objects.hashCode(this.statusFeeding);
        hash = 97 * hash + Objects.hashCode(this.ip);
        hash = 97 * hash + Objects.hashCode(this.device);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegisterOfFeeding other = (RegisterOfFeeding) obj;
        if (this.id_register != other.id_register) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.statusFeeding, other.statusFeeding)) {
            return false;
        }
        if (!Objects.equals(this.ip, other.ip)) {
            return false;
        }
        return Objects.equals(this.device, other.device);
    }

    

}
