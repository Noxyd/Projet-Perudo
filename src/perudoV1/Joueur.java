package perudoV1;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

@SuppressWarnings("serial")
public class Joueur extends UnicastRemoteObject implements JoueurInt{
	

	
	//attributs
	private int id_joueur;
	private String pseudo;
	private ArrayList<De> de_joueur;
	private int index;
	private int nbMise = 0, valMise = 0;
		
	//constructeur
	public Joueur() throws RemoteException {
		super();
		this.index = 0;
		id_joueur = genererID();
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
		this.nbMise = nb;
		this.valMise = val;
		
	}
	
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
				return tabEntier[j]; //retourn la valeur selectionnee par l'user
			}
		}
		
		return 100; //retourn 100 si erreur
		
	}


	public int getNbMise() {
		return nbMise;
	}


	public void setNbMise(int nbMise) {
		this.nbMise = nbMise;
	}


	public int getValMise() {
		return valMise;
	}


	public void setValMise(int valMise) {
		this.valMise = valMise;
	}

}
