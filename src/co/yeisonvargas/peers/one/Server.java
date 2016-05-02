/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.one;

import co.yeisonvargas.peers.common.Message;
import co.yeisonvargas.peers.common.Backend;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author yeison_vargas
 */
public class Server extends UnicastRemoteObject implements Backend {
    
    private Registry onRmiRegistry;
    private final int port = 3232;
    private ArrayList<Message> messagesToMe;
    private JFrame contexUi;
    
    public Server(JFrame contexUi) throws RemoteException {
        super();        
        this.messagesToMe = new ArrayList<>();
        this.contexUi = contexUi;
    }
    
    public boolean startServer() {
        try {
        this.onRmiRegistry = LocateRegistry.createRegistry(this.port);
        this.onRmiRegistry.rebind("server", this);
        } catch (Exception e) {
            System.err.println("Server stopped while It was starting.");
            System.err.println(e.getMessage());            
            return false;
        }
        
        System.out.println("Server is ON.");
        return true;
    }
    
    public boolean stopServer() {
        try {
        this.onRmiRegistry.unbind("server");
        unexportObject(this, true);
        unexportObject(this.onRmiRegistry, true);
        } catch (Exception e) {
            System.err.println("Server isn't stopped.");
            System.err.println(e.getMessage()); 
            return false;
        }
        
        System.out.println("Server is stopped.");
        return true;
    }

    @Override
    public String sayHello() {
        return "Hello World!";
    }    

    @Override
    public boolean sendMessage(String message) throws RemoteException {
        this.messagesToMe.add(new Message(message, "peerTwo"));
        ((UserInterface)this.contexUi).onMessageReceived();
        return true;
    }

    @Override
    public ArrayList<Message> getConversation() throws RemoteException {
        return this.messagesToMe;
    }
    
}
