package pieces;
import java.util.ArrayList;
import java.util.List;
public class PlateauPuzzle extends AbstractModeleEcoutable{

    public ArrayList<Piece> map;
    public int largeur;
    public int hauteur;

    public PlateauPuzzle(int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        map = new ArrayList<Piece>();
    }

    public boolean ajouterPiece(Piece piece, int x, int y) {
        /*
         * Fonction qui ajoute une pièce à la map
         * @param piece est la pièce à ajouter
         * @param x est la position x de la pièce
         * @param y est la position y de la pièce
         */
        if (x < 0 || y < 0 || x + piece.largeurActuelle() > this.largeur || x + piece.hauteurActuelle() > this.largeur || y + piece.hauteurActuelle() + piece.longueur > this.hauteur || y + piece.largeurActuelle() + piece.longueur > this.hauteur ||x + 1 > this.largeur || y + 1 > this.hauteur) {
            return false;
        }
        for (Piece p : map) {
            if (intersect(piece, x, y, p) || p.x == x || p.y == y) {
                return false; 
            }
        }
        piece.setPosition(x, y);
        map.add(piece);
        return true;
    }

    public boolean deplacerPiece(Piece piece, int newX, int newY) {
        // Fonction qui déplace la pièce qui a pour centre x,y
        if (ajouterPiece(piece, newX, newY)) {
            map.remove(piece);
            return true;
        }
        return false;
    }

    public Piece deletePiece(int x, int y){
        // Fonction qui supprime la pièce qui a pour centre x,y
        // Avec x et y la case supérieur gauche de leur grille
        Piece error = new T(0,0);
        for (Piece p : map){
            if(p.x == x && p.y == y){
                map.remove(p);
                return p;
            }
        }
        return error;
    }
    

    // Méthode pour vérifier l'intersection de deux pièces
    public boolean intersect(Piece piece1, int x1, int y1, Piece piece2) {
        
        int x1Max = x1 + piece1.largeurActuelle() - 1;
        int y1Max = y1 + piece1.hauteurActuelle() - 1;
        int x2Max = piece2.x + piece2.largeurActuelle() - 1;
        int y2Max = piece2.y + piece2.hauteurActuelle() - 1;

        if (x1 <= x2Max && x1Max >= piece2.x && y1 <= y2Max && y1Max >= piece2.y) {
            if (this.largeur < x2Max + 1 && this.largeur < x1Max + 1 && this.hauteur < y2Max + 1 && this.hauteur < y1Max + 1) {
                for (int i = Math.max(x1, piece2.x); i <= Math.min(x1Max, x2Max); i++) {
                    for (int j = Math.max(y1, piece2.y); j <= Math.min(y1Max, y2Max); j++) {
                        if (piece1.estOccupee(i - x1, j - y1) && piece2.estOccupee(i - piece2.x, j - piece2.y)){
                            return true;
                        }
                    }
                }
            }
        }

    return false; 
}


    public void tournerPiece(Piece piece, boolean sens){
        if(sens){
            piece.rotateRight();
        }
        else{
            piece.rotateLeft();
        }
    }

    private String[][] initGrille(String[][] grille){
        /*
         * Fonction qui initialise la grille
         * @param grille est la grille a initialiser
         */
        for (int i = 0; i < this.hauteur+1; i++) {
            for (int j = 0; j < this.largeur+1; j++) {
                grille[i][j] = " .";
            }
        }
        return grille;
    }

    @Override
    public String toString() {
        /*
         * Fonction qui permet d'afficher la grille
         */
        String[][] grille = new String[this.hauteur+1][this.largeur+1];
        
        grille = initGrille(grille);

        // Placer chaque pièce dans la grille
        for (Piece piece : map) {

            int[] ligne = piece.indexLigneActu();
            int[] col = piece.indexColActu();
            int centreX = piece.centreGrille().get(0);
            int centreY = piece.centreGrille().get(1);
            if (piece.orientation % 2 == 0){
                for (int i = 0; i < piece.tab.size(); i++) {
                    for (int j = 0; j < piece.tab.get(0).size(); j++) {
                        if(piece.tab.get(col[i]).get(ligne[j])) {
                            grille[piece.y + i+1][piece.x + j+1] = " X";
                        }
                        if(i == centreX && j == centreY){
                                grille[piece.y + i + 1][piece.x + j + 1] = " O";
                        }
                    }
                }
            }
            else{
                for (int i = 0; i < piece.tab.get(0).size(); i++) {
                    for (int j = 0; j < piece.tab.size(); j++) {
                        if(piece.tab.get(ligne[j]).get(col[i])){
                            grille[piece.y + i + 1][piece.x + j + 1] = " X";
                        }
                        if(i == centreX && j == centreY){
                                grille[piece.y + i + 1][piece.x + j + 1] = " O";
                        }
                    }
                }
            }
        }

        // Convertir la grille en chaîne de caractères
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hauteur+1; i++) {
            for (int j = 0; j < largeur+1; j++) {
                if(i == 0){
                    if(j>0 && j<=10){
                        result.append(String.valueOf(j-1));
                        result.append("  ");
                    }else if (j>0){
                        result.append(String.valueOf(j-1));
                        result.append(" ");
                    }
                    if (j==0){
                        result.append("__| ");
                    }
                }
                else if(j == 0){
                    if(i>0){
                        result.append(String.valueOf(i-1));
                    }else{
                        result.append(" ");
                    }
                    if(i <= 10){
                        result.append(" ");
                    }
                    result.append("|");
                }
                else{
                    result.append(grille[i][j]);
                    result.append(" ");
                }
            }
            result.append('\n');
        }

        return result.toString();
    }

    public void modeleMisAJour(){
        this.plateauChangement();
    }

   
}

   

