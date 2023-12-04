package bataille_navale.jeu;

import bataille_navale.joueur.*;

/**
 * Classe qui permet de lancer le jeu
 */
public class Play {

    /**
     * Fonction main qui permet de lancer le jeu
     * @param args arguments de la fonction main
     */
    public static void main(String args[]){

        GenericJoueur j1 = new Humain("aa",1,1,2,1);
        GenericJoueur j2 = new RandomPlayer(1,1,2,1);

        Game game = new Game(j1,j2);
        game.jouer();
    }    
    
}
