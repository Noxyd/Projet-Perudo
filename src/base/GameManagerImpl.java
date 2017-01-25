package base;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe GameManager.
 * Permet de g�rer des parties : cr�er, supprimer, naviguer dans la liste des parties.
 * 
 * @author Groupe Garcia, Lesaichot, Tavera - STRI
 * 
 */

@SuppressWarnings("serial")
public class GameManagerImpl extends UnicastRemoteObject implements GameManager{

	
	
	/* Attributs */
	private Map<String, Partie> liste_parties;
	
	private final int NBMAX_JOUEURS = 6;
	private final String BASE_URL = "rmi://localhost/";
	
	
	
	/* Constructeur */
	
	/**
	 * Le constructeur permet de creer une hashmap contenant l'url (key) de la partie ainsi qu'une Partie (Object).
	 * 
	 * @throws RemoteException
	 */
	public GameManagerImpl() throws RemoteException {
		super();
		liste_parties = new HashMap<String,Partie>();
	}
	
	
	
	/* M�thodes */
	
	/**
	 * Permet d'effectuer la recherche et l'attribution d'une partie.
	 * Retourne l'url de la partie et effectue un rebind entre l'url et l'objet de type Partie.
	 * 
	 * @return String url
	 * 
	 */
	public String recherche_partie() throws RemoteException{
		
		String url = "none";
		//test permet de v�rifier qu'une partie a �t� trouv� dans la Hashmap.
		boolean test = false;
		
		for(String key : liste_parties.keySet()){
			//parties.get(key) retourne la valeur de la cl� associ�.
			//true : la partie est en en cours || false : la salle d'attente est ouverte.
			Partie current = liste_parties.get(key);
			
			if(current.getState() == false && test == false){
				if(current.getNombreJoueurs() <= NBMAX_JOUEURS){
					url = BASE_URL+key;
					try {
						Naming.rebind(url, current);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					test = true;
				}
			}
		}
				
		return url;
	}
	public ArrayList recherche_partie_list() throws RemoteException {
		 ArrayList list_part = new ArrayList();
		 String url = "none";
			//test permet de v�rifier qu'une partie a �t� trouv� dans la Hashmap.
			boolean test = false;
			
			for(String key : liste_parties.keySet()){
				//parties.get(key) retourne la valeur de la cl� associ�.
				//true : la partie est en en cours || false : la salle d'attente est ouverte.
				Partie current = liste_parties.get(key);
				
				if(current.getState() == false && test == false){
					if(current.getNombreJoueurs() <= NBMAX_JOUEURS){
						url = BASE_URL+key;
						try {
							Naming.rebind(url, current);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						list_part.add(url);
		}
				}
			}
			
			return list_part;
	}

	
	/**
	 * Cette m�thode permet de cr�er un nouvel objet de type Partie � l'int�rieur de la hashmap.
	 * On g�nere un id � cette partie ("perudo-" suivie de 5 caract�res alpha-num�riques). 
	 * Cet id devient la key de l'objet.
	 * 
	 */
	public void creer_partie() throws RemoteException{
		
		String id = "perudo-";
		
		id += generer_chaine();
		
		//TODO Penser � v�rifier que la chaine n'existe pas deja
		
		liste_parties.put(id, new Partie());
		
		System.out.println("Partie "+id+" cr�e.");
	}
	
	/**
	 * G�n�re une chaine compos�e de 5 carat�res alpha-num�riques.
	 * 
	 * @return chaine
	 */
	public String generer_chaine(){
		
		    String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		    String chaine = "";
		    
		    for(int i = 0; i < 5; i++)
		    {
		       int j = (int)Math.floor(Math.random() * 62);
		       chaine += caracteres.charAt(j);
		    }
		    
		    return chaine;
	}

}
