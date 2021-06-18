/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmicalculation;

/**
 *
 * @author User
 */
import java.rmi.*;
import java.rmi.registry.*;

public class MyServer{

    public MyServer() {
        try{

    IMath stub=new MathRemote();
    
    Registry reg=LocateRegistry.createRegistry(5000);
    reg.rebind("calc", stub);
    System.out.println("Server is ready........");

}catch(Exception e){System.out.println(e);}
    }
public static void main(String args[]){

}

}
