/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author yeison_vargas
 */
public interface Backend extends Remote {
    
    public String sayHello() throws RemoteException ;
    public boolean sendMessage(String message, byte [] contentFile, String nameFile) throws RemoteException;
    public ArrayList<Message> getConversation() throws RemoteException;
    
}
