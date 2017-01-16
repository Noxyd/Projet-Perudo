package perudoV1;

import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Partie extends UnicastRemoteObject implements PartieInt {
	
	private static final long serialVersionUID = 1L;
			//attributs
			@SuppressWarnings("unused")
			private int id_partie;
			private Joueur joueurcourant;
			private ArrayList<Joueur> joueurs;
			private boolean state; //indique l'�tat de la partie - en cours (true) ou salle d'attente (false)
			
			//constructeur
			public Partie(int id, ArrayList<Joueur> map) throws java.rmi.RemoteException{
				this.id_partie = id;
				this.joueurcourant = null; //on met un joueur qui est creer plus haut 
										//car au débbut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = map; //se remplie lors de l'arrivé des joueurs donc ici vide.
			}	
			
			//methodes
			public Joueur getJoueurcourant() throws java.rmi.RemoteException{
				return joueurcourant;
			}

			public void setJoueurcourant(Joueur joueurcourant) throws java.rmi.RemoteException{
				this.joueurcourant = joueurcourant;
			}

			public void ajouterJoueur(Joueur e)throws java.rmi.RemoteException{
				joueurs.add(e);				
			}
			
			public int getNombreJoueur() throws java.rmi.RemoteException {
				int nbjoueur = joueurs.size();
				return nbjoueur;
			}
			
	
			public String printHello()throws java.rmi.RemoteException{
				return "Hello";
			}
			
			public boolean getState(){
				return state;
			}
			
			public void setState(boolean value){
				state = value;
			}
			
			public int getNombreJoueurs(){
				return joueurs.size();
			}

}
