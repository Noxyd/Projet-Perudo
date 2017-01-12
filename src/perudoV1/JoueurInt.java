package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface JoueurInt extends Remote {
	public int getId_joueur() throws RemoteException;
	public String getPseudo() throws RemoteException;
	public void setPseudo(String pseudo) throws RemoteException;
	public ArrayList<De> getDe_joueur() throws RemoteException;
}
