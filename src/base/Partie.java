package base;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import local.Joueur;
import local.PartieClient;

@SuppressWarnings("serial")
public class Partie extends UnicastRemoteObject implements PartieInt, Runnable {
	
			//attributs:
	
			private Joueur joueurcourant;
			
			private HashMap<String, Joueur> joueurs;
			private HashMap<String,Boolean> joueurPret;
			private ArrayList<String> ordreTour;
			
			private boolean state; //indique l'etat de la partie - en cours (true) ou salle d'attente (false)
			private int nbMise = 0, valMise = 0;
            //on met les boolean à 0 car les joueurs ne seront pas pret de base
			
			private final int NB_MAX_JOUEURS = 6;
            

			//constructeur:
            
			public Partie() throws java.rmi.RemoteException{
				this.joueurcourant = null; //on met un joueur qui est creer plus haut car au debut on a pas vraiment de joueur avant tirage au sort.
				this.joueurs = new HashMap<String, Joueur>();
				this.joueurPret = new HashMap<String,Boolean>();
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
			joueurPret.put(url, false);
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
            for(String key : joueurPret.keySet()){
                Boolean val = joueurPret.get(key);
                System.out.println(val);
                if(val==true){i = i+1;}
            }
            
            if(i==6){res = true;}
            
            return res;
        }  

 public void transmitBet() throws RemoteException{
        	
        	ArrayList<Integer> recup = new ArrayList<Integer>();
        	
        	this.partie_client = (PartieClient) Naming.lookup("rmi://localhost:1099/");
        	recup = partie_client.bet(nbMise, valMise); 
        	
        	//int nbDeVal = nombreDePerudo(val);
			
        	int choixAnnonce = 0;
			
        	if(recup.get(1) != null )
        		{ choixAnnonce = 1; }
			else if (recup.get(0) == 0)
				{ choixAnnonce = 2; } //menteur
			else 
				{ choixAnnonce = 3; } //toutpile
        	
			switch (choixAnnonce) {
			 case 1:		//il annonce mise
				//et une partie a la derniere mise enregistree par un joueur
				 	this.nbMise = recup.get(0); 	
				  	this.valMise = recup.get(1);
				break;
				
			 case 2:		//il annonce menteur
				 int toto = nombreDePerudo(this.valMise);
				  if( this.nbMise >= toto ){
					  //le joueur qui a dit le menteur gagne
					  //local
					  Joueur jp = getJoueurprecedant();
					  jp.suppDe(1);
					  
					  //distant
					  //le joueur doit être informé
					  
				  }
				  else {
					  //local
					  //le joueur qui dit menteur a perdu
					  Joueur ja = getJoueurcourant();
					  ja.suppDe(1);
					  
					  //distant
					  //le joueur doit être informé

				  }
				  
				  /*
				  gerer le end game ou nouvelle manche
				  .
				  .
				  .
				  */
		
			    break;
			  case 3:		//il annnonce tout pile
				  int tuto = nombreDePerudo(this.valMise);
				  if( this.nbMise == tuto ){
					  //local
					  //le joueur qui dit toutpile gagne
					  Joueur ja = getJoueurcourant();
					  ja.ajoutDe1();
					  
					  //distant
					  //tous les joueurs doivent être informé
					  
				  }
				  else{
					//local
					  //le joueur qui dit toutpile perd
					  Joueur ja = getJoueurcourant();
					  ja.suppDe(1);
					  
					  //distant
					  //tous les joueurs doivent être informé
					  
				  }
				  /*
				  gerer le end game ou nouvelle manche
				  .
				  .
				  .
				   */
				   
			    break;
			 
			}
			
			
		}
        	
        
       public void annoncer(int choixAnnoce, Joueur j1, Joueur j2, int nb, int val){
			
			int nbDeVal = nombreDePerudo(val);
			
			switch (choixAnnoce) {
			  case 1:		//il annonce menteur
				  if(valMise >= nbMise){
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
				  if(nbDeVal != nbMise){
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

	@Override
	public void run() {
		//ATTENTE DES CLIENTS
		this.attenteClients();
		//ENTREE DANS LA PARTIE
		
	}
	
	public void attenteClients(){
		boolean test = false;
		int nbJoueurs = 0;
		int nbTrue = 0;
		
		while(test != true){
			if(nbJoueurs < NB_MAX_JOUEURS){
				for(String key : joueurPret.keySet()){
					if(joueurPret.get(key)==true){
						nbTrue+=1;
					}
				}
				if(nbTrue == 6){
					test = true;
				}
			}
			
		}
	}
        
        //methodes sur les Des:
            
       //connaitre le nombre total de De en jeu dans la partie
		public int getNombreDeTotal(){

				int nbde = 0;
	            for(String key : joueurs.keySet()){
	            	Joueur j = joueurs.get(key);
				    nbde = nbde + j.getDeTotal();
				}
				
			return nbde;
		}
		
		
		//ici connaitre le nombre de De total dans la valeur est val, on prend en compte les perudo
		public int nombreDePerudo(int val){
			
			int res = 0;
            for(String key : joueurs.keySet()){
            	Joueur j = joueurs.get(key);
			    res = res + j.getDeVal(val) + j.getDeVal(1);
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
