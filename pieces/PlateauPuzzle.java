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
        if (ajouterPiece(piece, newX, newY)) {
            map.remove(piece);
            return true;
        }
        return false;
    }

    public Piece deletePiece(int x, int y){
        List<Integer> res = new ArrayList<>();
        res.add(x);
        res.add(y);
        Piece error = new T(0,0);
        for (Piece p : map){
            if(res == p.centreGrille()){
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

    @Override
    public String toString() {
        // TODO : CrÃ©er une grille de caractÃ¨res reprÃ©sentant le plateau de jeu puis retourner la chaÃ®ne de caractÃ¨res la reprÃ©sentant.
        String[][] grille = new String[hauteur+1][largeur+1];

        for (int i = 0; i < hauteur+1; i++) {
            for (int j = 0; j < largeur+1; j++) {
                grille[i][j] = " .";
            }
        }
        // Placer chaque piÃ¨ce dans la grille
        for (Piece piece : map) {

            int[] ligne = piece.indexLigneActu();
            int[] col = piece.indexColActu();
            int centreX = piece.centreGrille().get(0);
            int centreY = piece.centreGrille().get(1);
            if (piece.orientation % 2 == 0){
                for (int i = 0; i < piece.tab.size(); i++) {
                    for (int j = 0; j < piece.tab.get(0).size(); j++) {
                        if(piece.tab.get(col[i]).get(ligne[j])) {
                            if(i == centreX && j == centreY){
                                grille[piece.y + i + 1][piece.x + j + 1] = " O";
                            }else{
                                grille[piece.y + i+1][piece.x + j+1] = " X";
                            }
                        }
                    }
                }
            }
            else{
                for (int i = 0; i < piece.tab.get(0).size(); i++) {
                    for (int j = 0; j < piece.tab.size(); j++) {
                        if(piece.tab.get(ligne[j]).get(col[i])){
                            grille[piece.y + i+1][piece.x + j+1] = " X";
                        }
                    }
                }
            }
        }

        // Convertir la grille en chaÃ®ne de caractÃ¨res
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

    public static void main(String[] args){
        Piece p1 = new T(7,1);
        Piece p2 = new T(4,0);
        Piece p3 = new T(4,0);
        Piece p4 = new Rec(6,0);
        p1.setPosition(0,0);
        p2.setPosition(27,26);
        p3.setPosition(3,2);
        p4.setPosition(12,12);
        PlateauPuzzle map = new PlateauPuzzle(30,30);
        System.out.println("ajouter p1 : " + map.ajouterPiece(p1,p1.x,p1.y));
        System.out.println("ajouter p2 : " + map.ajouterPiece(p2,p2.x,p2.y));
        System.out.println("ajouter p3 : " + map.ajouterPiece(p3,p3.x,p3.y));
        System.out.println("ajouter p4 : " + map.ajouterPiece(p4,p4.x,p4.y));
        System.out.println(map.toString());
    }
}

   

