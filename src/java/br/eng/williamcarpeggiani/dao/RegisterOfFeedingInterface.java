/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.RegisterOfFeeding;
import java.util.List;

/**
 *
 * @author william
 */
public interface RegisterOfFeedingInterface {

    public void create(RegisterOfFeeding register);

    public void update(RegisterOfFeeding register);

    public RegisterOfFeeding read(int intRegister);

    public void delete(RegisterOfFeeding register);

    public List<RegisterOfFeeding> getRegisters(int idDevice);
    
    public List<RegisterOfFeeding> getRegisters();

}
