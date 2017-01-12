package perudoV1;

import java.util.ArrayList;

public class Joueur {
	
	//attributs
		private int id_joueur;
		private String pseudo;
		private ArrayList<De> de_joueur;
	
		
	//constructeur
	public Joueur(int id_joueur, String pseudo, ArrayList<De> de_joueur) {
		super();
		this.id_joueur = id_joueur;
		this.pseudo = pseudo;
		this.de_joueur = de_joueur;
	}

	//méthodes
	public int getId_joueur() {
		return id_joueur;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}


	public ArrayList<De> getDe_joueur() {
		return de_joueur;
	}
	

}
