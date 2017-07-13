/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.model;

import java.io.Serializable;
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
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 *
 * @author william
 */
@Entity
@Table(name = "Controlloffeeding")
public class ControllOfFeeding implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_controll", unique = true)
    private int id_controll;
    @Column(name = "status")
    private boolean status;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_device", insertable = true, updatable = true)
    //@Cascade(CascadeType.SAVE_UPDATE)
    private Device id_device;

    public ControllOfFeeding() {
        
    }
    
    public ControllOfFeeding(boolean status, Device device) {
        this.status = status;
        this.id_device = device;
    }

    public int getId_controll() {
        return id_controll;
    }

    public void setId_controll(int id_controll) {
        this.id_controll = id_controll;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Device getId_device() {
        return id_device;
    }

    public void setId_device(Device id_device) {
        this.id_device = id_device;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id_controll;
        hash = 53 * hash + (this.status ? 1 : 0);
        hash = 53 * hash + Objects.hashCode(this.id_device);
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
        final ControllOfFeeding other = (ControllOfFeeding) obj;
        if (this.id_controll != other.id_controll) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.id_device, other.id_device)) {
            return false;
        }
        return true;
    }

}
