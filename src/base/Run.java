package base;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Iterator;

public class Run {
	public static void main(String[] args) throws RemoteException{
		
		 Partie j = new Partie(); 
		 
	        j.rejoindre("much","toto");
	        j.rejoindre("much1","toto1");
	        j.rejoindre("much2","toto2");
	        j.rejoindre("much3","toto3");
	        j.rejoindre("much4","toto4");
	        j.rejoindre("much5","toto5");
	     
        	System.out.println("----------------------______________________-------------------");

	        for(String key : j.getJoueurs().keySet()){
            	Joueur jou = j.getJoueurs().get(key);
            	System.out.println(": " + jou.getPseudo());

            	jou.ajoutDe5();
            	
            	 ArrayList<Integer> arr = new ArrayList<Integer>();
            	 arr=jou.getDe_joueur();

            	Iterator<Integer> it = arr.iterator();
            	int i;
            	int d;
            	while (it.hasNext()) {
    				i = it.next();
    				d=arr.get(i);
            	System.out.println("De de " + jou.getPseudo() + " val: "+d);
            	}
	        }
	        
	        
	        //operation sur Des:
	        j.lancerDes();
	        
        	System.out.println("----------------------______________________-------------------");

        	//verrif des Des:
	        for(String key : j.getJoueurs().keySet()){
            	Joueur jou = j.getJoueurs().get(key);
            	System.out.println(": " + jou.getPseudo());


            	 ArrayList<Integer> arr = new ArrayList<Integer>();
            	 arr=jou.getDe_joueur();
            	
            	 int i;
            	 for (i=0;i<arr.size();i++) {
            	 int d=arr.get(i);
            	 System.out.println("De de " + jou.getPseudo() + " val: "+d);
            	 }
	        }
	       
	        
	      //operation sur Des:
	        Joueur jo = j.getJoueurs().get("much");
	        jo.suppDe(2);
	        Joueur ju = j.getJoueurs().get("much1");
	        ju.suppDe(1);
	        Joueur je = j.getJoueurs().get("much3");
	        je.ajoutDe1();
	        je.ajoutDe1();
	        Joueur jj = j.getJoueurs().get("much4");
	        jj.ajoutDe1();
        	System.out.println("----------------------______________________-------------------");

        	for(String key : j.getJoueurs().keySet()){
            	Joueur jou = j.getJoueurs().get(key);
            	System.out.println(": " + jou.getPseudo());


            	 ArrayList<Integer> arr = new ArrayList<Integer>();
            	 arr=jou.getDe_joueur();
            	
            	 int i;
            	 for (i=0;i<arr.size();i++) {
            	 int d=arr.get(i);
            	 System.out.println("De de " + jou.getPseudo() + " val: "+d);
            	 }
	        }
	}
}
