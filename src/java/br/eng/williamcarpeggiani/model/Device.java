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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author william
 */
@Entity
@Table(name = "Device")
public class Device implements Serializable, Comparable<Device> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_device", unique = true)
    private int id_device;
    @Column(name = "name", unique = true)
    private String name;
    @Column(name = "serial", unique = true)
    private String serial;
    //@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp dateCreate;
    //@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Timestamp dateModify;
    private boolean statusConnection;
    private int sign;
    private float credits;
    private float sensorSilo;
    private float sensorDish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", insertable = true, updatable = true)
    //@Cascade(CascadeType.SAVE_UPDATE)
    private User user;

    public Device() {

    }

    public Device(String name, String serial, Timestamp dateCreate, Timestamp dateModify, boolean statusConnection, int sign, float credits, float sensorSilo, float sensorDish, User user) {
        this.name = name;
        this.serial = serial;
        this.dateCreate = dateCreate;
        this.dateModify = dateModify;
        this.statusConnection = statusConnection;
        this.sign = sign;
        this.credits = credits;
        this.sensorSilo = sensorSilo;
        this.sensorDish = sensorDish;
        this.user = user;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Timestamp getDateModify() {
        return dateModify;
    }

    public void setDateModify(Timestamp dateModify) {
        this.dateModify = dateModify;
    }

    public int getId_device() {
        return id_device;
    }

    public void setId_device(int id_device) {
        this.id_device = id_device;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public boolean isStatusConnection() {
        return statusConnection;
    }

    public void setStatusConnection(boolean statusConnection) {
        this.statusConnection = statusConnection;
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public float getCredits() {
        return credits;
    }

    public void setCredits(float credits) {
        this.credits = credits;
    }

    public float getSensorSilo() {
        return sensorSilo;
    }

    public void setSensorSilo(float sensorSilo) {
        this.sensorSilo = sensorSilo;
    }

    public float getSensorDish() {
        return sensorDish;
    }

    public void setSensorDish(float sensorDish) {
        this.sensorDish = sensorDish;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.id_device;
        hash = 67 * hash + Objects.hashCode(this.name);
        hash = 67 * hash + Objects.hashCode(this.serial);
        hash = 67 * hash + Objects.hashCode(this.dateCreate);
        hash = 67 * hash + Objects.hashCode(this.dateModify);
        hash = 67 * hash + (this.statusConnection ? 1 : 0);
        hash = 67 * hash + this.sign;
        hash = 67 * hash + Float.floatToIntBits(this.credits);
        hash = 67 * hash + Float.floatToIntBits(this.sensorSilo);
        hash = 67 * hash + Float.floatToIntBits(this.sensorDish);
        hash = 67 * hash + Objects.hashCode(this.user);
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
        final Device other = (Device) obj;
        if (this.id_device != other.id_device) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.serial, other.serial)) {
            return false;
        }
        if (!Objects.equals(this.dateCreate, other.dateCreate)) {
            return false;
        }
        if (!Objects.equals(this.dateModify, other.dateModify)) {
            return false;
        }
        if (this.statusConnection != other.statusConnection) {
            return false;
        }
        if (this.sign != other.sign) {
            return false;
        }
        if (Float.floatToIntBits(this.credits) != Float.floatToIntBits(other.credits)) {
            return false;
        }
        if (Float.floatToIntBits(this.sensorSilo) != Float.floatToIntBits(other.sensorSilo)) {
            return false;
        }
        if (Float.floatToIntBits(this.sensorDish) != Float.floatToIntBits(other.sensorDish)) {
            return false;
        }
        return Objects.equals(this.user, other.user);
    }

    @Override
    public int compareTo(Device o) {
        return this.serial.compareTo(o.serial);
    }

}
