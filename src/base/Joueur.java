package base;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Joueur extends UnicastRemoteObject implements JoueurInt{
	

	
	//attributs
	private String pseudo;
	private ArrayList<Integer> de_joueur;
	private int nbMise = 0, valMise = 0;
		
	//constructeur
	public Joueur(String pseudo) throws RemoteException {
		super();
		this.pseudo = null;
		this.de_joueur = null;
	}


	//methodes	

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
	
	public void ajoutDe1(){
		int de = 0;
		de_joueur.add(de);
	}
	
	public void ajoutDe5(){
		int de1 = 0, de2 = 0, de3 = 0, de4 = 0, de5 = 0;
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
		Iterator<Integer> it = de_joueur.iterator();
			while (it.hasNext()) {
				int d = it.next();
				if (d == val){
					nbde =+ 1;   	       
				}
			}
		return nbde;
	}
	
	
	public void lancerDe(){
	Iterator<Integer> it = de_joueur.iterator();
	int d;	
		while (it.hasNext()) {
				d = it.next();
				Random rand = new Random();
		        int nombreAleatoire = rand.nextInt(7 - 1) + 1;  //attention: le 7 est exclu et le 1 inclu
		        d = nombreAleatoire;
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
