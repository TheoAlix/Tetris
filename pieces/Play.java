package pieces;

/**
 * Classe qui permet de lancer le jeu
 */
public class Play {

    /**
     * Fonction main qui permet de lancer le jeu
     * @param args arguments de la fonction main
     */
    public static void main(String args[]){


        int tailleGrille = 30;
        int nbPieces =12;
        GameCLI game = new GameCLI(tailleGrille,tailleGrille,nbPieces);
        game.jouer();
    }    
    
}
