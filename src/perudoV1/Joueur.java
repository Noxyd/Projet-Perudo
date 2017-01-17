package perudoV1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;

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
	
	public void ajoutDe1(){
		De de = new De();
		de_joueur.add(de);
	}
	
	public void ajoutDe5(){
		De de1 = new De();
		De de2 = new De();
		De de3 = new De();
		De de4 = new De();
		De de5 = new De();
		de_joueur.add(de1);
		de_joueur.add(de2);
		de_joueur.add(de3);
		de_joueur.add(de4);
		de_joueur.add(de5);
	}
	
	
	public void suppDe(int nbe){
		int nbede = de_joueur.size();
		int endsize = nbede - nbe;
			while(nbede != endsize){
			de_joueur.remove(nbede);	
			nbede = de_joueur.size();
			}
	}
	
	//connaitre le nombre de De total
	public int getDeTotal(){
		int nbde = de_joueur.size();
		return nbde;
	}
	
	//connaitre le nombre de De qui ont la valeur rentree.
	public int getDeVal(int val){
		int nbde = 0;
		Iterator<De> it = de_joueur.iterator();
			while (it.hasNext()) {
				De d = it.next();
				if (d.getValeurDe() == val){
					nbde =+ 1;   	       
				}
			}
		return nbde;
	}
	
	
	public void lancerDe(){
	Iterator<De> it = de_joueur.iterator();
		while (it.hasNext()) {
			De d = it.next();
			d.changeValeur();
		}
		
	}

	public void mise(int nb, int val){

		//gestion des mise ? à voir
		
	}

}
