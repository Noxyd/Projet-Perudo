package perudoV1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Partie extends UnicastRemoteObject implements PartieInt {
	
			//attributs
			//@SuppressWarnings("unused")
			//private int id_partie;
			private int joueurcourant;
			private ArrayList<Joueur> joueurs;
			private boolean state; //indique l'�tat de la partie - en cours (true) ou salle d'attente (false)
			
			//constructeur
			public Partie() throws java.rmi.RemoteException{
				//this.id_partie = id;
				this.joueurcourant = 0; //on met un joueur qui est creer plus haut 
										//car au débbut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = new ArrayList<Joueur>();
				//this.joueurs = map; //se remplie lors de l'arrivé des joueurs donc ici vide.
			}	
			
			//methodes
			public int getJoueurcourant() throws java.rmi.RemoteException{
				return joueurcourant;
			}

			public void setJoueurcourant(int joueurcourant) throws java.rmi.RemoteException{
				this.joueurcourant = joueurcourant;
			}

			public void ajouterJoueur(String e)throws java.rmi.RemoteException{
				joueurs.add(new Joueur(e));				
			}
			
			public boolean getState() throws java.rmi.RemoteException{
				return state;
			}
			
			public void setState(boolean value) throws java.rmi.RemoteException{
				state = value;
			}
			
			public int getNombreJoueurs() throws java.rmi.RemoteException{
				return joueurs.size();
			}
			
			public void listerJoueurs() throws RemoteException{
				for(int i = 0; i<joueurs.size();i++){
					System.out.println(joueurs.get(i).getPseudo());
				}
			}
			

}
