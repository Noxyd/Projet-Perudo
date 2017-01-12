package perudoV1;

import java.util.Random;

public class De {

        //variables
        int id_de;
        int valeur;
        
        //constructeur
        public De(int id, int val) {
            this.id_de = id;
            this.valeur = val;
        }


        //methodes
        public int getValeurDe() {
            return valeur;
        }
        
        public void changeValeur() {
           Random rand = new Random();
           int nombreAleatoire = rand.nextInt(7 - 1) + 1;  //attention: le 7 est exclu et le 1 inclu
           this.valeur = nombreAleatoire;
        }

        
    }

