package base;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@SuppressWarnings("serial")
public class Partie extends UnicastRemoteObject implements PartieInt {
	
			//attributs:
	
			private Joueur joueurcourant;
			
			private HashMap<String, Joueur> joueurs;
			private HashMap<Joueur,Boolean> joueurPret;
			private ArrayList<String> ordreTour;
			
			private boolean state; //indique l'etat de la partie - en cours (true) ou salle d'attente (false)
			private int nbMise = 0, valMise = 0;
            //on met les boolean à 0 car les joueurs ne seront pas pret de base
            

			//constructeur:
            
			public Partie() throws java.rmi.RemoteException{
				this.joueurcourant = null; //on met un joueur qui est creer plus haut car au debut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = new HashMap<String, Joueur>();
				this.joueurPret = new HashMap<Joueur,Boolean>();
				this.ordreTour = new ArrayList<String>();
			}	
			
			//methodes:
			
		public Joueur getJoueurcourant() {
			return joueurcourant;
		}

		public HashMap<String, Joueur> getJoueurs() {
			return joueurs;
		}

		public void setOrdreTour(Joueur j) {	
			try {
				this.ordreTour.add(j.getPseudo());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		public void setJoueurcourant(Joueur joueurcourant) {
			this.joueurcourant = joueurcourant;
		}
		
		public int getNombreJoueurs() throws java.rmi.RemoteException{
			return joueurs.size();
		}

		public boolean getState() throws java.rmi.RemoteException{
			return state;
		}

		public void setState(boolean value) throws java.rmi.RemoteException{
			state = value;
		}
		
        public void setJoueurPret(HashMap<Joueur, Boolean> joueurPret) {
            this.joueurPret = joueurPret;
        }

		public void listerJoueurs() throws RemoteException{
			for(int i = 0; i<joueurs.size();i++){
				System.out.println(joueurs.get(i).getPseudo());
			}
		}
		
		public void rejoindre(String url, String pseudo)throws java.rmi.RemoteException{
			joueurs.put(url,new Joueur(pseudo));
			ordreTour.add(url);
			System.out.println(pseudo+" a rejoins la partie avec l'url : "+url);
		}
	
			
		public void passerTour(Joueur j){
			this.joueurcourant=j;
		}
			
		public void gameOrder(){			
			Collections.shuffle(this.ordreTour);
		}
		
		
		//retourne true si tous les joueurs sont ok
        public Boolean waitingFoClient(){
            Boolean res = false;
            int i=0;
            for(Joueur key : joueurPret.keySet()){
                Boolean val = joueurPret.get(key);
                System.out.println(val);
                if(val==true){i = i+1;}
            }
            
            if(i==6){res = true;}
            
            return res;
        }  

        
       public void annoncer(int choixAnnoce, Joueur j1, Joueur j2, int nb, int val){
			
			int nbDeVal = nombreDePerudo(val);
			
			switch (choixAnnoce) {
			  case 1:		//il annonce menteur
				  if(nbDeVal >= nb){
					  j1.suppDe(1);
				  }
				  
				  j2.suppDe(1);
				  
				  /*
				  gerer le end game ou nouvelle manche
				  .
				  .
				  .
				  */
		
			    break;
			  case 2:		//il annnonce tout pile
				  if(nbDeVal != nb){
					  //System.out.println("perdu"); //pour tester
					  j1.suppDe(1);
				  }
					
				  j1.ajoutDe1();
				  /*
				  gerer le end game ou nouvelle manche
				  .
				  .
				  .
				   */
				   
			    break;
			  case 3:		//il annonce mise
				  	j1.mise(nb, val);	//un joueur a donc sa derniere mise enregistree
				  	this.nbMise = nb; 	//et une partie a la derniere mise enregistree par un joueur
				  	this.valMise = val;
				break;
			}
			
			
		}

       //on donne l'url et on retrouve le pseudo
       public String urlPseudo(String url){
    	   String str = "";
    	   
    	   Joueur j = joueurs.get(url);
    	   try {
			str = j.getPseudo();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   return str;
       }
       
       
       
       //methodes sur les Des:
            
       //connaitre le nombre total de De en jeu dans la partie
		public int getNombreDeTotal(){

				int nbde = 0;
	            for(String key : joueurs.keySet()){
	            	Joueur j = joueurs.get(key);
				    nbde =+ j.getDeTotal();
				}
				
			return nbde;
		}
		
		
		//ici connaitre le nombre de De total dans la valeur est val, on prend en compte les perudo
		public int nombreDePerudo(int val){
			
			int res = 0;
            for(String key : joueurs.keySet()){
            	Joueur j = joueurs.get(key);
			    res =+ j.getDeVal(val) + j.getDeVal(1);
			}
	            
			return res;
		}
		
		
		//lancer les Des de tout les joueur en game.
		public void lancerDes(){			
			
			Iterator<String> ordreTourI = ordreTour.iterator();
			while (ordreTourI.hasNext()) {
				String urlJ = ordreTourI.next();
				Joueur j = joueurs.get(urlJ);
				j.lancerDe();
				System.out.println(urlJ);
			}			
		}
		
		//avec un url donné, on recupere ses Des
	   public ArrayList<Integer> recupDe(String url){
		  
		   ArrayList<Integer> arr = new ArrayList<Integer>();
		   Joueur j = joueurs.get(url);
		   arr = j.getDe_joueur();

  	   	return arr;
	   }
  	   
		
}
