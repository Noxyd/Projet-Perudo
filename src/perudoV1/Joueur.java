package perudoV1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Joueur extends UnicastRemoteObject implements JoueurInt{
	

	
	//attributs
	private int id_joueur;
	private String pseudo;
	private ArrayList<De> de_joueur;
	private int index;
		
	//constructeur
	public Joueur(String pseudo) throws RemoteException {
		super();
		this.index = 0;
		id_joueur = genererID();
		this.pseudo = pseudo;
		this.de_joueur = null;
	}


	//methodes	

	public String getPseudo() throws RemoteException{
		return pseudo;
	}

	public void setPseudo(String pseudo)  throws RemoteException{
		this.pseudo = pseudo;
	}


	/*public ArrayList<De> getDe_joueur() throws RemoteException{
		return de_joueur;
	}
	*/
	public int genererID() throws RemoteException{
		
		this.index += 1;
		
		return index;
	}

}
