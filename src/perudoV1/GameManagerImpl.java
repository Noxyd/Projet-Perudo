package perudoV1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("serial")
public class GameManagerImpl extends UnicastRemoteObject implements GameManager{

	private Map<String, Partie> parties;
	private final int NBMAX_JOUEURS = 6;
	
	
	public GameManagerImpl() throws RemoteException {
		super();
		parties = new HashMap<String,Partie>();
	}

	/*
	 * Permet à un client de rejoindre une partie.
	 */
	public String rejoindre() throws RemoteException {
		
		String id_partie;
		
		id_partie = recherche_partie();
		
		return id_partie;
	}

	public String recherche_partie() {
		
		//test permet de vérifier qu'une partie a été trouvé dans la Hashmap.
		boolean test = false;
		String id_partie = "0";
		
		for(String key : parties.keySet()){
			//parties.get(key) retourne la valeur de la clé associé.
			//true : la partie est en en cours || false : la salle d'attente est ouverte.
			Partie current = parties.get(key);
			if(current.getState() == false && test == false){
				if(current.getNombreJoueurs() < NBMAX_JOUEURS){
					id_partie = key;
					test = true;
				}
			}
		}
		
		return id_partie;
	}
	
	public void creer_partie(){
		
	}

}
