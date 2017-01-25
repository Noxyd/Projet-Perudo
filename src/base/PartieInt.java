package base;

import java.rmi.RemoteException;

public interface PartieInt extends java.rmi.Remote{
	
	public int getNombreJoueurs() throws java.rmi.RemoteException;
	
	public int getNombreJoueurs() throws java.rmi.RemoteException;
	
	public boolean getState() throws java.rmi.RemoteException;
	
	public void setState(boolean value) throws java.rmi.RemoteException;
	
	public void listerJoueurs() throws RemoteException;

}
