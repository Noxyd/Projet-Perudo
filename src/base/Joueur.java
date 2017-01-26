package base;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Joueur extends UnicastRemoteObject implements JoueurInt{
	

	
	//attributs:
	
	private String pseudo;
	private ArrayList<Integer> de_joueur;
	private int nbMise = 0, valMise = 0;
		
	//constructeur
	public Joueur(String pseudo) throws RemoteException {
		this.pseudo = pseudo;
		this.de_joueur = new ArrayList<Integer>();;
	}


	//methodes:	

	public String getPseudo() throws RemoteException{
		return pseudo;
	}

	public void setPseudo(String pseudo)  throws RemoteException{
		this.pseudo = pseudo;
	}

	public void mise(int nb, int val){
		this.nbMise = nb;
		this.valMise = val;
		
	}
	
	
	//methodes sur les DES

	//get de l'arraylist de Des
	public ArrayList<Integer> getDe_joueur() {
		return de_joueur;
	}
	
	//ajout d'1 seul De
	public void ajoutDe1(){
		int de = 0;
		de_joueur.add(de);
	}

	//ajout de 5 Des
	public void ajoutDe5(){
		int de1 = 0, de2 = 0, de3 = 0, de4 = 0, de5 = 0;
		de_joueur.add(de1);
		de_joueur.add(de2);
		de_joueur.add(de3);
		de_joueur.add(de4);
		de_joueur.add(de5);
	}
	
	//sup un nobre nbe de De
	public void suppDe(int nbe){
		int nbede = de_joueur.size();
		int endsize = nbede - nbe;
			while(nbede != endsize){
			de_joueur.remove(nbede-1);	
			nbede = de_joueur.size();
			}
	}
	
	//connaitre le nombre de De total du joueur
	public int getDeTotal(){
		int nbde = de_joueur.size();
		return nbde;
	}
	
	//connaitre le nombre de De qui ont la valeur val du joueur
	//sans le nombre de perudo car géré dans partie.
	public int getDeVal(int val){
			 int nbde = 0;
			 int i = 0;

			   for (i=0;i<de_joueur.size();i++) {
				   if(val == de_joueur.get(i)){
					   nbde = nbde + 1; 
				   }
			   } 
		return nbde;
	}
	
	//lancer les De du joueur
	public void lancerDe(){
		 int i;
		   for (i=0;i<de_joueur.size();i++) {
			   Random rand = new Random();
		       int nombreAleatoire = rand.nextInt(7 - 1) + 1;  //attention: le 7 est exclu et le 1 inclu
		       this.de_joueur.set(i, nombreAleatoire);
		   }		
	}
	
	
	//Methode pour saisir une valeur
	
	//faire une boucle tant que sur le 100 pour assurer la victoire ;)
	//faire un choix en chiffre, ex: 1 ou 2 ou 3 (ici on adapte � l'aide de l'entier i)
	public int saisir_valeur(int i){
		//i nous permet de connaitre le nombre de choix, 
		//si i a la valeur 3 alors le joueur ne peux entrer que 1 ou 2 ou 3.
		
		int tabEntier[] = new int[i-1];
		int j = 0;
				
		Scanner sc = new Scanner(System.in);
		System.out.println("Choix :");
		int str = sc.nextInt();
		System.out.println("Vous avez saisi : " + str);
		
		while (j!=(i-1)){
			if(str != tabEntier[j]){
				j++;
			}
			if(str == tabEntier[j]){
				sc.close();
				return tabEntier[j]; //retourn la valeur selectionnee par l'user
			}
		}
		sc.close();
		return 100; //retourn 100 si erreur
		
	}
	

}
