/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.dao;

import br.eng.williamcarpeggiani.model.ControllOfFeeding;

/**
 *
 * @author william
 */
public interface ControllOfFeedingInterface {

    public void create(ControllOfFeeding controll);

    public ControllOfFeeding read(int idDevice);

    public void update(ControllOfFeeding controll);

    public void delete(ControllOfFeeding controll);

}
