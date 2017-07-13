/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.eng.williamcarpeggiani.controller;

import br.eng.williamcarpeggiani.util.JSFMessageUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.context.RequestContext;

/**
 *
 * @author william
 */
@ManagedBean
@RequestScoped
public class AbstractMBean {

    //know open dialog with user
    private static final String KEEP_DIALOG_OPENED = "KEEP_DIALOG_OPENED";
    
    public AbstractMBean() {
        super();
    }
    //envia uma mensagem de erro ao usuario (em um display)
    protected void UserErrorMessage(String msg) {
        JSFMessageUtil mensagem = new JSFMessageUtil();
        mensagem.sendErrorMessageToUser(msg);
    }
    //envia mensagem de informação ao usuário
    protected void UserInfoMessage(String msg) {
        JSFMessageUtil mensagem = new JSFMessageUtil();
        mensagem.sendInfoMessageToUser(msg);
    }
    //fecha dialogo
    protected void DialogClose() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, false);
    }
    //mantem dialogo aberto
    protected void DialogOpenKeep() {
        getRequestContext().addCallbackParam(KEEP_DIALOG_OPENED, true);
    }
    //retorna o request context instancia para solicitação corrente na web
    protected RequestContext getRequestContext() {
        return RequestContext.getCurrentInstance();
    }
    
}
