package perudoV1;

import java.util.ArrayList;

public class Partie {
	
			//attributs
			int id_partie;
			Joueur joueurcourant;
			ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
			
			//constructeur
			public Partie(int id, ArrayList map){
				this.id_partie = id;
				this.joueurcourant = null; //on met un joueur qui est creer plus haut 
										//car au débbut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = map; //se remplie lors de l'arrivé des joueurs donc ici vide.
			}	
			
			//methodes
			public Joueur getJoueurcourant() {
				return joueurcourant;
			}

			public void setJoueurcourant(Joueur joueurcourant) {
				this.joueurcourant = joueurcourant;
			}

			public void ajouterJoueur(Joueur e){
				joueurs.add(e);				
			}
			
			public int getNombreJoueur() {
				int nbjoueur = joueurs.size();
				return nbjoueur;
			}
			
	
			
				

}
