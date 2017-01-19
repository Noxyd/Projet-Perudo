package tmp;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class GameManagerImpl extends UnicastRemoteObject implements GameManager{

	private Map<Integer, Partie> parties;
	private int index;
	private final int NBMAX_JOUEURS = 6;
	
	
	public GameManagerImpl() throws RemoteException {
		super();
		this.index = 0;
		parties = new HashMap<Integer,Partie>();
	}

	/*
	 * Permet à un client de rejoindre une partie.
	 */
	public int rejoindre(String pseudo) throws RemoteException {
		
		int id_partie;
		
		Partie la_partie;
		
		id_partie = recherche_partie();
		
		la_partie = parties.get(id_partie);
		
		la_partie.ajouterJoueur(pseudo);
		
		return id_partie;
	}

	public int recherche_partie() throws RemoteException{
		
		//test permet de vérifier qu'une partie a été trouvé dans la Hashmap.
		boolean test = false;
		int id_partie = 0;
		
		for(int key : parties.keySet()){
			//parties.get(key) retourne la valeur de la clé associé.
			//true : la partie est en en cours || false : la salle d'attente est ouverte.
			Partie current = parties.get(key);
			System.out.println("recherche_partie OK : "+key);
			if(current.getState() == false && test == false){
				if(current.getNombreJoueurs() <= NBMAX_JOUEURS){
					id_partie = key;
					test = true;
				}
			}
		}
		
		if(test == false){
			id_partie = 0; //Pas de partie disponible
		}
		
		return id_partie;
	}
	
public void recherche_partie_list() throws RemoteException{
		
		//test permet de vérifier qu'une partie a été trouvé dans la Hashmap.
		boolean test = false;
		ArrayList<Partie> p_list = new ArrayList<Partie>();
		int i = 0;
		
		for(int key : parties.keySet()){
			//parties.get(key) retourne la valeur de la clé associé.
			//true : la partie est en en cours || false : la salle d'attente est ouverte.
			Partie current = parties.get(key);
			System.out.println("recherche_partie OK : "+key);
			if(current.getState() == false && test == false){
				if(current.getNombreJoueurs() <= NBMAX_JOUEURS){
					p_list.add(current);
					i++;
				}
			}
		}
		System.out.println (p_list);
	}
	
	public void creer_partie() throws RemoteException{
		
		int id = genererID();
			
		parties.put(id, new Partie());
		
		//uniquement pour les tests
		System.out.println("Partie crée : "+ id);
		
	}
	
	public int genererID() throws RemoteException{
		
		this.index += 1;
		
		return index;
	}
	
	public void liste_parties() throws RemoteException{
		for(int key : parties.keySet()){
			System.out.println(key);
		}
	}
	
	public void liste_joueurs(int id_partie) throws RemoteException{
		Partie la_partie = parties.get(id_partie);
		la_partie.listerJoueurs();
	}

}
