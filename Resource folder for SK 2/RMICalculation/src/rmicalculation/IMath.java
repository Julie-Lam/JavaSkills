package rmicalculation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
import java.rmi.*;


public interface IMath extends Remote {
    public int add(int x,int y)throws RemoteException; 
    public int minus(int x,int y)throws RemoteException; 

    public double divide(int x,int y)throws RemoteException; 
    public int multiply(int x,int y)throws RemoteException; 
}
