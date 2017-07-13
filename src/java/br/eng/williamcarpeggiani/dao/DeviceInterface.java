/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.Device;
import java.util.List;

/**
 *
 * @author william
 */
public interface DeviceInterface {

    public void create(Device device);

    public Device read(int idDevice);
    
    public Device readSerial(String serial);
    
    public void update(Device device);

    public void delete(Device device);

    public List<Device> getDevices();

    public List<Device> getDevicesForUser(int idUser);

}
