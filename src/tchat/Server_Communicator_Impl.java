package tchat;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Server_Communicator_Impl extends UnicastRemoteObject implements Server_Communicator{
	
	//1° Il faut une liste qui pourra recevoir les références vers les objets clients.
	private ArrayList<Client_Communicator> liste_clients;
	
	//2° On initialise cette liste de référence dans le constructeur
	public Server_Communicator_Impl() throws RemoteException {
		super();
		this.liste_clients = new ArrayList<Client_Communicator>();
	}
	
	//3° Une méthode "Remote" permet au client de s'inscrire dans la liste
	public void inscription(Client_Communicator client)throws RemoteException{
		liste_clients.add(client);
	}
	
	//4° Une méthode permet de recevoir un message client et de le renvoyer à tout le monde
	public void envoyer_a_tous(String pseudo, String message)throws RemoteException{
		
		for(Client_Communicator cli : this.liste_clients){
			cli.envoyer_message(pseudo+" dit : "+message);
		}
	}
}
