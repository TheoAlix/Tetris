package pieces;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;


public class GameCLI{

    PlateauPuzzle board;
    int largeur;
    int hauteur;
    String[] listPiece = {"L","T","I","Rec"};

    public GameCLI(int largeur, int hauteur, int nbPieces){
        this.largeur = largeur;
        this.hauteur = hauteur;
        this.board = new PlateauPuzzle(largeur,hauteur);
        this.initPieces(nbPieces);
    }

    public void initPieces(int nbPieces){
        for(int i=0; i<nbPieces; i++){
            int longueur = new Random().nextInt(7); int orientation = new Random().nextInt(4);
            Piece tmp = this.makeRandomPiece(longueur,orientation);
            
            while (this.board.ajouterPiece(tmp,new Random().nextInt(this.largeur),new Random().nextInt(this.hauteur)) == false){
                // On ajoute la piece à un emplacement aléatoire
                this.board.ajouterPiece(tmp,new Random().nextInt(this.largeur),new Random().nextInt(this.hauteur));
            } 
        }
    }

    public Piece makeRandomPiece(int longueur, int orientation){
        /**
         * Fonction qui permet de créer une pièce aléatoire
         * @param longueur est la longueur de la pièce
         * @param orientation est l'orientation de la pièce
         */
        int indexP = new Random().nextInt(this.listPiece.length);
        if (indexP == 1){
            return new L(longueur,orientation);
        }
        if (indexP == 2){
            return new T(longueur,orientation);
        }
        if (indexP == 3){
            return new I(longueur,orientation);
        }
        return new Rec(longueur,orientation);
    }

    @Override
    public String toString(){
        return this.board.toString();
    }

    public int score(){
        // Méthode qui calcul le score de l'état actuel de la partie (Le but étant de faire le plus petit score possible)
        int minXGauche = 10000000;
        int minYGauche = 10000000;

        int maxXDroit = 0;
        int maxYDroit = 0;
        for(Piece p : this.board.map){
            // On parcours toutes les pièces 
            int pX = p.x;
            int pY = p.y;

            if(pX < minXGauche){
                // On cherche la pièce la plus en haut à gauche possible
                minXGauche = pX;
            }

            if(pY < minYGauche){
                minYGauche = pY;
            }

            if(pX > maxXDroit){
                // On cherche la pièce la plus en bas à droite possible
                maxXDroit = pX;
            }

            if(pY > maxYDroit){
                maxYDroit = pY;
            }
        }

        int longueur = maxXDroit - minXGauche;
        int largeur = maxYDroit - minYGauche;


        System.out.println(minXGauche);
        System.out.println(minYGauche);

        System.out.println();

        System.out.println(maxXDroit);
        System.out.println(maxYDroit);

        System.out.println();

        System.out.println(longueur);
        System.out.println(largeur);

        return longueur*largeur;
    }

    public Piece trouverPiece(int x, int y){
        /**
         * Fonction qui permet de trouver une pièce à partir de son centre
         * @param x est l'abscisse du centre de la pièce
         * @param y est l'ordonnée du centre de la pièce
         */
        // fonction qui renvoie la piece qui a pour centre x,y et renvoie une pièce de longueur 0 sinon
        Piece error = new L(0,0); 
        for (Piece p : this.board.map){
            System.out.println(p.x);
            System.out.println(p.y); 
            System.out.println(p.x+ p.centreGrille().get(0));
            System.out.println(p.y+ p.centreGrille().get(1));
            if(p.x == x && p.y== y){
                return p;
            }
        }
        return error;
    }

    public boolean turn(int x, int y, String sens){
        /**
         * Fonction qui permet de tourner une pièce
         * @param x est l'abscisse du centre de la pièce
         * @param y est l'ordonnée du centre de la pièce
         * @param sens est le sens de rotation de la pièce (R = droite, L = gauche)
         */

        // x et y doivent être le centre de la pièce (largeur/2,hauteur/2)
        Piece p = this.board.deletePiece(x,y);
        if (p.longueur != 0){
            if(sens == "R"){
                // la pièce p est la pièce qui a été supprimé du board
                p.rotateRight();
                
            }else{
                // la pièce p est la pièce qui a été supprimé du board
                p.rotateLeft();
            }

            if(this.board.ajouterPiece(p,x,y)){
                // La pièce a été ajouté en etant tournée
                return true;
            }
            // La pièce n'a pas pu être ajoutée
            if(sens == "R"){p.rotateLeft();}
            else{p.rotateRight();}
            this.board.ajouterPiece(p,x,y); // On remet la pièce à l'endroit initial
        }
        return false;
    }


    public void jouer(){
        /**
         * Fonction qui permet de jouer au jeu
         * Ne prends rien en entrée et ne renvoie rien, uniquement des affichages et des inputs de l'utilisateur
         */
        Scanner scanner = new Scanner(System.in);
        int tailleGrille = 10;
        int nbPieces = 1;
        GameCLI game = new GameCLI(tailleGrille,tailleGrille,nbPieces);
        ViewConsole view = new ViewConsole(game.board);
        boolean running = true;
        while(running){

            Piece tmp;
            int x = 0;
            int y = 0;
            do{ // Selection de la pièce
                game.board.modeleMisAJour();
                System.out.println("Entrez le x (de la case la plus à gauche) de la pièce choisis (x étant l'abscisse)");
                x = Integer.valueOf(scanner.nextLine());
                System.out.println("Entrez le y (de la case la plus à gauche) de la pièce choisis (y étant l'ordonnée)");
                y = Integer.valueOf(scanner.nextLine());
                tmp = game.trouverPiece(x,y);
            }while(tmp.longueur == 0);
                
            int choix = 300;
            while (choix > 3 || choix <= 0){
                // Selection de l'action sur la pièce
                System.out.println("Voulez vous déplacer la pièce(1), la tourner à droite(2), la tourner à gauche(3) (tapez le num correspondant à l'action)");
                choix = Integer.valueOf(scanner.nextLine());
            }
            if(choix == 1){
                // On essaie de déplacer la piece choisis dans l'endroit choisi
                int newX = 0;
                int newY = 0;
                do{
                    System.out.println("Entrez le x de la case choisis (x étant celui du nouveau centre de la pièce)");
                    newX = Integer.valueOf(scanner.nextLine());
                    System.out.println("Entrez le y de la case choisis (y étant celui du nouveau centre de la pièce)");
                    newY = Integer.valueOf(scanner.nextLine());
                }while(game.board.deplacerPiece(tmp,newX,newY)==false);
            }
            if(choix == 2){
                // On essaie de tourner la piece choisis à droite
                if(game.turn(x,y,"R")==false){
                    System.out.println("Impossible de tourner cette pièce vers la droite");
                }
            }
            if(choix == 3){
                // On essaie de tourner la piece choisis à gauche
                if(game.turn(x,y,"L")==false){
                    System.out.println("Impossible de tourner cette pièce vers la Gauche");
                }
            }
        }
    }
}
