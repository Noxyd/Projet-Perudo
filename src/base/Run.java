package base;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Run {
	public static void main(String[] args) throws RemoteException{
		
		GameManagerImpl gm = new GameManagerImpl();
		int i;
		rmi_init(gm);
		
		//Creation de 3 parties
		for(i = 0; i < 3; i++){
			gm.creer_partie();
		}
			
		System.out.println("Serveur en écoute. "+i+" parties disponibles.");
		

	}
	
	/* initialisation du RMI */
	public static void rmi_init(GameManager obj){
		try {
			System.out.println("Dï¿½marrage du registre\n");
			LocateRegistry.createRegistry(1099);
			String url = "rmi://localhost:1099/gm";
			Naming.rebind(url, obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
