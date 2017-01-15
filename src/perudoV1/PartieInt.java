package perudoV1;

public interface PartieInt extends java.rmi.Remote{
	
	public Joueur getJoueurcourant() throws java.rmi.RemoteException;

	public void setJoueurcourant(Joueur joueurcourant) throws java.rmi.RemoteException; 

	public void ajouterJoueur(Joueur e) throws java.rmi.RemoteException;
	
	public int getNombreJoueur() throws java.rmi.RemoteException;
	
	public String printHello()throws java.rmi.RemoteException;

}
