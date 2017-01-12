package perudoV1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Run {
	public static void main(String[] args) throws Exception{
		try {
			LocateRegistry.createRegistry(1099);
			String url ="//"+InetAddress.getLocalHost().getHostAddress()+"/Perudo";
			
			Partie partie1 = new Partie(0, null);
			Naming.rebind(url, (Remote) partie1);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
