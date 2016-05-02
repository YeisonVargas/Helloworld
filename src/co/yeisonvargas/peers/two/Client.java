/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.yeisonvargas.peers.two;

import co.yeisonvargas.peers.common.Backend;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author yeison_vargas
 */
public class Client {
    
    public static void main(String[] args) throws Exception {
        String url = "192.168.0.14";
        int port = 3232;
        
        Registry conexion = LocateRegistry.getRegistry(url, port);
        
        Backend myBackend = (Backend)conexion.lookup("server");
        
        System.out.println("The server says: " + myBackend.sayHello());                
    }
    
}
