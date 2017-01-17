package perudoV1;


import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Run {
	public static void main(String[] args) throws RemoteException{
		
		GameManagerImpl gm = new GameManagerImpl();
		
		rmi_init(gm);
			
		gm.creer_partie();
		gm.creer_partie();
		gm.creer_partie();
		
		gm.liste_parties();
		
		//uniquement pour test
		/*
		int id_partie = gm.rejoindre("Sam");
		
		System.out.println("Liste dans la partie "+id_partie+" :");
		gm.liste_joueurs(id_partie);*/
		//END
	}
	
	/* initialisation du RMI */
	public static void rmi_init(GameManager obj){
		try {
			LocateRegistry.createRegistry(1099);
			
			String url ="rmi://localhost/Perudo";
			
			Naming.rebind(url, obj);
			
			System.out.println(obj.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
