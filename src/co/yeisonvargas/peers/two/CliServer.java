/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.two;

import java.rmi.RemoteException;

/**
 *
 * @author yeison_vargas
 */
public class CliServer {
    
    public static void main(String[] args) throws RemoteException {
        Server greatServer = new Server(null);
        greatServer.startServer();        
    }
    
}
