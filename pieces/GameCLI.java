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

    public Piece trouverPiece(int x, int y){
        // fonction qui renvoie la piece qui a pour centre x,y et renvoie une pièce de longueur 0 sinon
        Piece error = new L(0,0);
        List<Integer> res = new ArrayList<>();
        res.add(x);
        res.add(y);
        for (Piece p : this.board.map){
            if(p.centreGrille() == res){
                return p;
            }
        }
        return error;
    }

    public boolean turnRight(int x, int y){
        // x et y doivent être le centre de la pièce (largeur/2,hauteur/2)
        Piece p = this.board.deletePiece(x,y);
        if (p.longueur != 0){
            // la pièce p est la pièce qui a été supprimé du board
            p.rotateRight();
            if(this.board.ajouterPiece(p,x,y)){
                // La pièce a été ajouté en etant tournée
                return true;
            }
            p.rotateLeft();
            this.board.ajouterPiece(p,x,y); // On remet la pièce à l'endroit initial
        }
        return false;
    }

    @Override
    public String toString(){
        return this.board.toString();
    }

    // public int score(){
    //     // Méthode qui calcul le score de l'état actuel de la partie (Le but étant de faire le plus petit score possible)
    //     int minXGauche;
    //     int minYGauche;

    //     int maxXDroit;
    //     int maxYDroit;
    //     for(Piece p : this.board.map){

    //     }

    //     int longueur = maxXDroit - minXGauche;
    //     int largeur = maxYDroit - maxYGauche;
    //     return longueur*largeur;
    // }
    public boolean move(Integer x, Integer y, int newX, int newY){
        // x et y doivent être le centre de la pièce (largeur/2,hauteur/2)
        Piece p = this.board.deletePiece(x,y);
        p.setPosition(newX, newY);
        if(this.board.ajouterPiece(p,newX,newY)){
            return true;
        }
        this.board.ajouterPiece(p,x,y);
        return false;
    }


  
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        // System.out.println("Entre la taille de la grille (exemple : 3 donnera une grille 3x3)");
        // int tailleGrille = Integer.valueOf(scanner.nextLine());
        // System.out.println("Entre le nombre de pièces ");
        // int nbPieces = Integer.valueOf(scanner.nextLine());
        // if(nbPieces > tailleGrille){
        //     System.out.println("Plus de pièces que de lignes/colonnes ");
        //     return;
        // }
        int tailleGrille = 30;
        int nbPieces =12;
        GameCLI game = new GameCLI(tailleGrille,tailleGrille,nbPieces);
        ViewConsole view = new ViewConsole(game.board);
        boolean running = true;
        Piece pi = new I(4,0);
        game.board.ajouterPiece(pi,1,4);
        List<Integer> res = pi.centreGrille();
        System.out.println( "Move est : " + game.move(res.get(0),res.get(1),2,4));
        for (Piece p : game.board.map){
            List<Integer> tmp = p.centreGrille();
            System.out.println(("Centre :" + String.valueOf(tmp.get(0)) + String.valueOf(tmp.get(1))));
        }
        while(running){
            
            game.board.modeleMisAJour();


            Piece tmp;
            int x = 0;
            int y = 0;
            do{ // Selection de la pièce
                System.out.println("Entrez le x de la pièce choisis (x étant celui du centre de la pièce)");
                x = Integer.valueOf(scanner.nextLine());
                System.out.println("Entrez le y de la pièce choisis (y étant celui du centre de la pièce)");
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
                if(game.turnRight(x,y)==false){
                    System.out.println("Impossible de tourner cette pièce vers la droite");
                }
            }
            if(choix == 2){
                // On essaie de tourner la piece choisis à gauche
                System.out.println("Pas implémenté");
            }
        }
    }
}