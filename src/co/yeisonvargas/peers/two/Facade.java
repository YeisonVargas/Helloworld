/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.two;

import co.yeisonvargas.peers.common.Backend;
import co.yeisonvargas.peers.common.Message;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;

/**
 *
 * @author yeison_vargas
 */
public class Facade {
    
    private final String ownAddress;
    private final int ownPort;
    private final Server ownRmiServer;
    private final String externalAddress;
    private final int externalPort;
    
    public Facade(JFrame contexUi, String externalAddress, int externalPort) throws RemoteException {
        this.ownAddress = "localhost";
        this.ownPort = 3233;        
        this.ownRmiServer = new Server(contexUi);
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                ownRmiServer.startServer();   
            }
        }, "serverPeerTwo").run();
        */
        this.ownRmiServer.startServer();         
        this.externalAddress = externalAddress;
        this.externalPort = externalPort;
    }
    
    public boolean sendMessageClientOne(String message, byte [] pathAttachment, String name) {       
        try {
            Registry myRegister = LocateRegistry.getRegistry(this.externalAddress, 
                    this.externalPort);       
            Backend backend = (Backend) (myRegister.lookup("server"));        
            backend.sendMessage(message, pathAttachment, name);
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return false;
        }
        
        return true;        
    }
    
    public ArrayList<String[]> getConversation() {
        ArrayList<Message> messagesSendToClientOne;
        ArrayList<Message> messagesSendFromClientOne;
        Registry myRegister;
        Backend backend;

        try {
            myRegister = LocateRegistry.getRegistry(this.externalAddress, 
                    this.externalPort);       
            backend = (Backend)myRegister.lookup("server");        
            messagesSendToClientOne = backend.getConversation();
            System.out.println(messagesSendToClientOne.size());
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
        
        try {
            myRegister = LocateRegistry.getRegistry(this.ownAddress, 
                    this.ownPort);       
            backend = (Backend)myRegister.lookup("server");        
            messagesSendFromClientOne = backend.getConversation();
            System.out.println(messagesSendFromClientOne.size());
        } catch (RemoteException | NotBoundException e) {
            System.err.println(e.getMessage());
            return null;
        }
                
        ArrayList<Message> messages = messagesSendFromClientOne;
        
        messages.addAll(messagesSendToClientOne);
        
        
        return normalizate(messages);
    }
        
    private ArrayList<String []> normalizate(ArrayList<Message> allMessages) {
        Collections.sort(allMessages);
        ArrayList<String []> result = new ArrayList<>();
        SimpleDateFormat defaultFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        
        for(Message item: allMessages) {
            String [] resumeItem = new String[2];
            resumeItem[0] = item.getContent();
            resumeItem[1] = defaultFormat.format(item.getDate());
            result.add(resumeItem);
        }
        
        return result;        
    }
    
    
    
}
