package pieces;
import java.util.ArrayList;

public class PlateauPuzzle{

    public ArrayList<Piece> map;
    public int largeur;
    public int hauteur;

    public PlateauPuzzle(int largeur, int hauteur){
        this.largeur = largeur;
        this.hauteur = hauteur;
        map = new ArrayList<Piece>();
    }

    public boolean ajouterPiece(Piece piece, int x, int y) {
        for (Piece p : map) {
            if (intersect(piece, x, y, p)) {
                return false; 
            }
        }
        piece.setPosition(x, y);
        map.add(piece);
        return true;
    }

    public boolean deplacerPiece(Piece piece, int newX, int newY) {
        if (ajouterPiece(piece, newX, newY)) {
            map.remove(piece);
            return true;
        }
        return false;
    }

    // Méthode pour vérifier l'intersection de deux pièces
public boolean intersect(Piece piece1, int x1, int y1, Piece piece2) {
    
    int x1Max = x1 + piece1.largeurActuelle() - 1;
    int y1Max = y1 + piece1.hauteurActuelle() - 1;
    int x2Max = piece2.x + piece2.largeurActuelle() - 1;
    int y2Max = piece2.y + piece2.hauteurActuelle() - 1;

    if (x1 <= x2Max && x1Max >= piece2.x && y1 <= y2Max && y1Max >= piece2.y) {
        for (int i = Math.max(x1, piece2.x); i <= Math.min(x1Max, x2Max); i++) {
            for (int j = Math.max(y1, piece2.y); j <= Math.min(y1Max, y2Max); j++) {
                if (piece1.estOccupee(i - x1, j - y1) && piece2.estOccupee(i - piece2.x, j - piece2.y)) {
                    return true;
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

    @Override
    public String toString() {
        // TODO : Créer une grille de caractères représentant le plateau de jeu puis retourner la chaîne de caractères la représentant.
        String[][] grille = new String[hauteur][largeur];

        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                grille[i][j] = "□ ";
            }
        }

        // Placer chaque pièce dans la grille
        for (Piece piece : map) {

            int[] ligne = piece.indexLigneActu();
            int[] col = piece.indexColActu();
            if (piece.orientation % 2 == 0){
                for (int i = 0; i < piece.tab.size(); i++) {
                    for (int j = 0; j < piece.tab.get(0).size(); j++) {
                        if(piece.tab.get(col[i]).get(ligne[j])) {
                            grille[piece.y + i][piece.x + j] = "■ ";
                        }
                    }
                }
            }
            else{
                for (int i = 0; i < piece.tab.get(0).size(); i++) {
                    for (int j = 0; j < piece.tab.size(); j++) {
                        if(piece.tab.get(ligne[j]).get(col[i])){
                            grille[piece.y + i][piece.x + j] = "■ ";
                        }
                    }
                }
            }
        }

        // Convertir la grille en chaîne de caractères
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                result.append(grille[i][j]);
            }
            result.append('\n');
        }

        return result.toString();
    
    }
    public static void main(String[] args){
        Piece p1 = new T(7,1);
        Piece p2 = new T(4,0);
        Piece p3 = new T(4,0);
        System.out.println(p1);
        System.out.println(p2);
        p1.setPosition(0,0);
        p2.setPosition(27,26);
        p3.setPosition(5,3);
        PlateauPuzzle map = new PlateauPuzzle(30,30);
        System.out.println("ajouter p1 : " + map.ajouterPiece(p1,p1.x,p1.y));
        System.out.println("ajouter p2 : " + map.ajouterPiece(p2,p2.x,p2.y));
        System.out.println("ajouter p1 : " + map.ajouterPiece(p3,p3.x,p3.y));
        System.out.println(map.intersect(p1,0,0,p2));
        System.out.println(map.toString());
    }
}

   

