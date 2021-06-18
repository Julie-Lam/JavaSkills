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
import java.rmi.server.*;

public class MathRemote extends UnicastRemoteObject implements IMath{

    MathRemote()throws RemoteException{
        super();
    }

    public int add(int x,int y){
        return x+y;
    }
    
    public int minus (int x, int y) {
        return x-y; 
    }
    
    public double divide (int x, int y) {
        
        return x / ((double)y); 
    }
    
    public int multiply (int x, int y) {
        return x*y; 
    }

}

