package perudoV3;

import java.rmi.RemoteException;

public class GameControl implements Runnable{
	
	private int state;
	private GameImpl game;
	
	private final int NB_MAX_CLIENTS = 2;
	
	public GameControl(GameImpl game, int state){
		super();
		this.state = state;
		this.game = game;
	}

	@Override
	public void run() {
		boolean test = true;
		int size;
		switch(this.state){
			case 1 :
				
				break;
				
			case 2 : 
				break;
				
			default:
				System.out.println("Etat default");
		}
	}
	
	public void setState(int state){
		this.state = state;
	}
	
}
