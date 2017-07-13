/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.model;

import com.sun.istack.NotNull;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import java.io.Serializable;
import javax.faces.bean.ApplicationScoped;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author william
 */
@Entity
@Table(name = "User")
@NamedQueries({
    @NamedQuery(name = "User.findUserById", query = "FROM User WHERE id_user = :id_user"),
    @NamedQuery(name = "User.findUserByAccess", query = "FROM User WHERE accessLevel = :accessLevel"),
    @NamedQuery(name = "User.findAllUsers", query = "FROM User")
})
@ApplicationScoped
public class User implements Serializable {

    public static final String FIND_USUARIO_BY_ID = "User.findUserById";
    public static final String FIND_USUARIO_BY_ACCESS = "User.findUserByAccess";
    public static final String FIND_ALL_USUARIOS = "User.findAllUsers";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_user", unique = true)
    private int id_user;
    private String name;
    @Column(name = "username", unique = true)
    private String username;
    private String password;
    private String cpf;
    private String address;
    private int number;
    private String neighborhood;
    private String city;
    private String uf;
    private String fone;
    private String email;
    //1-Admin / 2-User
    private int accessLevel;

    public User() {

    }

    public User(String name, String username, String password, String cpf, String address, int number, String neighborhood, String city, String uf, String fone, String email, int accessLevel) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.cpf = cpf;
        this.address = address;
        this.number = number;
        this.neighborhood = neighborhood;
        this.city = city;
        this.uf = uf;
        this.fone = fone;
        this.email = email;
        this.accessLevel = accessLevel;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public int getId() {
        return id_user;
    }

    public void setId(int id) {
        this.id_user = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + this.id_user;
        hash = 53 * hash + Objects.hashCode(this.name);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.cpf);
        hash = 53 * hash + Objects.hashCode(this.address);
        hash = 53 * hash + this.number;
        hash = 53 * hash + Objects.hashCode(this.neighborhood);
        hash = 53 * hash + Objects.hashCode(this.city);
        hash = 53 * hash + Objects.hashCode(this.uf);
        hash = 53 * hash + Objects.hashCode(this.fone);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + this.accessLevel;
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
        final User other = (User) obj;
        if (this.id_user != other.id_user) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (this.number != other.number) {
            return false;
        }
        if (!Objects.equals(this.neighborhood, other.neighborhood)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        if (!Objects.equals(this.fone, other.fone)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return this.accessLevel == other.accessLevel;
    }

}
