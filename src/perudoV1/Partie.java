package perudoV1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;


@SuppressWarnings("serial")
public class Partie extends UnicastRemoteObject implements PartieInt {
	
			//attributs
			//@SuppressWarnings("unused")
			//private int id_partie;
			private Joueur joueurcourant;
			private ArrayList<Joueur> joueurs;
			private boolean state; //indique l'�tat de la partie - en cours (true) ou salle d'attente (false)
			private int nbMise = 0, valMise = 0, nbManche = 1;
			
			//constructeur
			public Partie() throws java.rmi.RemoteException{
				//this.id_partie = id;
				this.joueurcourant = null; //on met un joueur qui est creer plus haut 
										//car au débbut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = new ArrayList<Joueur>();
				//this.joueurs = map; //se remplie lors de l'arrivé des joueurs donc ici vide.
			}	
			
			//methodes
			public Joueur getJoueurcourant() {
				return joueurcourant;
			}

			public void setJoueurcourant(Joueur joueurcourant) {
				this.joueurcourant = joueurcourant;
			}

			public void ajouterJoueur(String e)throws java.rmi.RemoteException{
				joueurs.add(new Joueur());				
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
			
			public int getNombreDeTotal(){
				int nbde = 0;				
				Iterator<Joueur> it = joueurs.iterator();
					while (it.hasNext()) {
					       Joueur j = it.next();
					       nbde =+ j.getDeTotal();
					}
				return nbde;
			}
			
			
			//ici avec les perudo
			public int nombreDePerudo(int val){
				int res = 0;
				Iterator<Joueur> it = joueurs.iterator();
					while (it.hasNext()) {
					       Joueur j = it.next();
					       res =+ j.getDeVal(val) + j.getDeVal(0);
					}
				return res;
			}
			

public void traiteAnnonce(int choixAnnoce, Joueur j1, Joueur j2){
				//ajouter le a pour la boucle manche
				int nbDeVal = nombreDePerudo(valMise);
				
				switch (choixAnnoce) {
				  case 1:		//il annonce menteur
					  if(nbDeVal >= nbMise){
						  j1.suppDe(1);
					  }
					  
					  j2.suppDe(1);
					  
					  /*gerer le end game ou nouvelle manche
					  .
					  .
					  .
					  */
				    break;
				  case 2:		//il annnonce tout pile
					  if(nbDeVal != nbMise){
						  //System.out.println("perdu"); //pour tester
						  j1.suppDe(1);
					  }
						
					  j1.ajoutDe1();
						  
					  /*gerer le end game ou nouvelle manche
					  .
					  .
					  .
					  */
				    break;
				  case 3:		//il annonce mise
					  	int nb=0, val=0;	
					  	j1.mise(nb, val);	//un joueur a donc �a derniere mise enregistree
					  	this.nbMise = j1.getNbMise(); 	//et une partie a la derniere mise enregistree par un joueur
					  	this.valMise = j1.getValMise();
					break;
				}
				
				
			}
			
		public void passerTour(Joueur j){
			this.joueurcourant=j;
		}
		
		public void passerManche(){ // lié à annoncer
			this.nbManche =+ 1 ;
		}
			
		public void creerJ(){
			try {
				Joueur j = new Joueur();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}
