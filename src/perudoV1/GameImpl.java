package perudoV1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameImpl extends UnicastRemoteObject implements Game {

	private ArrayList<Clients> clients_list;

	public GameImpl() throws RemoteException{
		super();
		clients_list = new ArrayList<Clients>();
	}

	public int getSize(){
		return clients_list.size();
	}

	public int connexion(Clients cli)throws RemoteException{

		clients_list.add(cli);
		System.out.println(cli.getName()+" s'est connecté.");

		return 100;

	}

	public void ready()throws RemoteException{
		synchronized(this){
			for(Clients cli : this.clients_list){
				cli.printString("[INFO] La partie va commencer.");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notify();
		}
	}


	public void print_clients(){
		for(Clients cli : this.clients_list){
			try {
				System.out.println(cli.getName()+" : "+cli.getURL());
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void round(){

		ArrayList<Integer> client_choice;
		boolean test = false;
		boolean end_game = false;
		int round = 1;
		boolean first_player;
		Clients client_precedent = null;
		ArrayList<Integer> mise_precedente = new ArrayList<Integer>();


		synchronized(this){
			try {
				//Waiting for clients
				wait();
				//Begining of the game
				
				for(Clients cli : this.clients_list){
					cli.ajoutDe5();
					System.out.println(cli.getDes());
				}
				while(!end_game){
					//Begening of the round
					first_player = true;
					for(Clients cli : this.clients_list){
						cli.lancerDe();
						System.out.println(cli.getDes());
					}
					
					do{
						for(Clients cli : this.clients_list){	
							//Test the end of the round
							if(test == false){
								//Inform clients of the current player
								for(Clients client : this.clients_list){
									client.printString("[INFO] C'est au tour de "+cli.getName()+".");
									if(!first_player){
										client.printString("Le joueur "+client_precedent.getName()+" a misé "+(int)mise_precedente.get(0)+" dés de "+(int)mise_precedente.get(1)+".");
									}
								}

								//Let the client choose
								if(first_player){
									client_choice = cli.choice(round,1,1);
									first_player = false;
								} else {
									client_choice = cli.choice(round,(int)mise_precedente.get(0),(int)mise_precedente.get(1));
								}

								System.out.println(client_choice);
								
								int choixAnnonce = 0;

								
								if (client_choice.get(0) == 0){
									System.out.println("choix MENTEUR");
									choixAnnonce = 2; 
									for(Clients client_message : this.clients_list){
										client_message.printString(cli.getName()+" dit MENTEUR !");
									}
									System.out.println("choix MENTEUR2");
								}else if(client_choice.get(0) == 1) {
									choixAnnonce = 3; 
									for(Clients client_message : this.clients_list){
										client_message.printString(cli.getName()+" dit TOUT PILE !");
									}
								} //toutpile 
								else {
									choixAnnonce = 1; 
									System.out.println("choix MISE");
									for(Clients client_message : this.clients_list){
										client_message.printString(cli.getName()+" a misé "+client_choice.get(0)+"-"+client_choice.get(1)+".");
									}
								}

								switch (choixAnnonce) {
								case 1:		//il annonce mise
									mise_precedente = client_choice;
									break;

								case 2:		//il annonce menteur

									int[][] tab_resultat = total_des();

									if(tab_resultat[1][mise_precedente.get(1)-1] < mise_precedente.get(0) ){
										//Joueur courant gagne
										for(Clients cli_annonce : this.clients_list){
											cli_annonce.printString("Le joueur "+cli.getName()+" a gagné.");
											cli_annonce.printString("Le joueur "+client_precedent.getName()+" perd 1 dé.");
										}

										client_precedent.suppDe(1);

										test = true;

									} else {
										//Joueur courant perd
										for(Clients cli_annonce : this.clients_list){
											cli_annonce.printString("PERDU : Le joueur "+cli.getName()+" perd 1 dé.");
										}

										cli.suppDe(1);

										test = true;
									}


									break;
								case 3:		//il annnonce tout pile

									int[][] tab_resultat2 = total_des();

									if(tab_resultat2[1][mise_precedente.get(1)-1] == mise_precedente.get(0) ){
										//Joueur courant gagne
										for(Clients cli_annonce : this.clients_list){
											cli_annonce.printString("Le joueur "+cli.getName()+" a gagné, il gagne 1 dé !");
										}

										client_precedent.ajoutDe1();

										test = true;

									} else {
										//Joueur courant perd
										for(Clients cli_annonce : this.clients_list){
											cli_annonce.printString("PERDU : Le joueur "+cli.getName()+" perd 1 dé.");
										}

										cli.suppDe(1);

										test = true;
									}

								}	  

								client_precedent = cli;

							}

						}
						//Test the end of the round 
					}while(test != true);

					for(Clients cli : this.clients_list){
						System.out.println(cli.getName()+" : "+cli.getDes());
					}

					//Increment the round : new round
					round++;
					test = false;
					//Inform the clients of the new round
					System.out.println("Nouvelle manche : "+round);
					for(Clients client : this.clients_list){
						client.printString("[INFO] Fin de la manche "+(round-1)+".");
					}
					Thread.sleep(2000);
					if(round == 3){
						end_game = true;
					}else{
						for(Clients client : this.clients_list){
							client.printString("[INFO] Début de la manche "+round+".");
						}
					}


					//End of game
					System.out.println("Fin de la partie.");
					for(Clients client : this.clients_list){
						client.printString("[INFO] Fin de la partie.");
					}
				}
			}catch(Exception e){

			}
		}

	}

	public int[][] total_des(){

		int[][] tab_res = new int [2][6];

		for(int i = 0; i < 2;i++){
			tab_res[0][i] = i+1;
		}

		for(int j = 0; j < 6;j++){
			tab_res[1][j] = 0;
		}

		try {
			for(Clients cli : this.clients_list){

				for(int i = 0; i < cli.getNbDes();i++){
					for(int j = 1; j < 7;j++){
						if(tab_res[0][j-1] == cli.getVal(i)){
							tab_res[1][j-1]++; 
						}
					}
				}

			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		return tab_res;

	}


}
