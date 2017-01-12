package perudoV1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Joueur extends UnicastRemoteObject implements JoueurInt{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//attributs
	private int id_joueur;
	private String pseudo;
	private ArrayList<De> de_joueur;
	
		
	//constructeur
	public Joueur(int id_joueur, String pseudo, ArrayList<De> de_joueur) throws RemoteException {
		super();
		this.id_joueur = id_joueur;
		this.pseudo = pseudo;
		this.de_joueur = de_joueur;
	}

<<<<<<< Updated upstream
	//m�thodes	
	public int getId_joueur() throws RemoteException  {
=======
<<<<<<< HEAD
	//methodes
	public int getId_joueur() {
=======
	//m�thodes	
	public int getId_joueur() throws RemoteException  {
>>>>>>> origin/master
>>>>>>> Stashed changes
		return id_joueur;
	}

	public String getPseudo() throws RemoteException{
		return pseudo;
	}

	public void setPseudo(String pseudo)  throws RemoteException{
		this.pseudo = pseudo;
	}


	public ArrayList<De> getDe_joueur() throws RemoteException{
		return de_joueur;
	}
	

}
