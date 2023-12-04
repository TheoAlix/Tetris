package pieces;

import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public abstract class Piece implements PiecePuzzle{
    public int x;
    public int y;
    List<List<Boolean>> tab = new ArrayList<>();
    public int orientation; // 0 = 0°, 1 = 90°, 2 = 180°, 3 = 270° (sens horaire)
    public int longueur;

    public Piece(int longueur, int orientation){
        super();
        this.orientation = orientation;
        this.longueur = longueur;
    }

    public void setPosition(int x,int y){
        this.x = x;
        this.y = y;
    }

    public void rotateLeft(){
        if(this.orientation<=0){
            this.orientation = 3;
        }
        else{
            this.orientation -=1; 
        }
    }

    public void rotateRight(){
        if(this.orientation>=3){
            this.orientation = 0;
        }
        else{
            this.orientation +=1; 
        }
    }

    public int largeurActuelle(){
        // Renvoie la largeur actuelle de la pièce suivant sa rotation
        if(this.orientation == 0 || this.orientation == 2){
            return this.tab.size();
        }
        else{
            return this.tab.get(0).size();
        }
    }

    public int hauteurActuelle(){
        // Renvoie la hauteur actuelle de la pièce suivant sa rotation
        if(this.orientation == 0 || this.orientation == 2){
            return this.tab.get(0).size();
        }
        else{
            return this.tab.size();
        }
    }

    public boolean estOccupee(int ligne, int colonne){
        if (this.orientation == 0){
            return this.tab.get(ligne).get(colonne);
        }
        else if (this.orientation == 1){
            return this.tab.get(Math.abs(ligne-this.largeurActuelle())).get(colonne);
        }
        else if (this.orientation == 2){
            return this.tab.get(ligne).get(Math.abs(colonne-this.hauteurActuelle()));
        }
        else{
            return this.tab.get(Math.abs(ligne-this.hauteurActuelle())).get(Math.abs(colonne-this.largeurActuelle()));
        }
    }

    public List<Integer> centreGrille(){
        List<Integer> res = new ArrayList<>();
        res.add(this.largeurActuelle()/2);
        res.add(this.hauteurActuelle()/2);
        return res;
    }

    public int[] indexColActu(){ 
        // Fonction qui renvoie une liste contenant les indexs des colonnes suivant l'orientation de la piece 
        int[] res = new int[this.largeurActuelle()];
        int tmp = 0;
        if (this.orientation == 1 || this.orientation == 2){
            tmp = this.largeurActuelle()-1;
        }
        for(int i=0; i<this.largeurActuelle(); i++){
            res[i] = Math.abs(i-tmp);

        }
        return res;
    }

    public int[] indexLigneActu(){
        // Fonction qui renvoie une liste contenant les indexs des lignes suivant l'orientation de la piece 
        int[] res = new int[this.hauteurActuelle()];
        int tmp = 0;
        if (this.orientation == 1 || this.orientation == 2){
            tmp = this.hauteurActuelle()-1;
        }
        for(int i=0; i<this.hauteurActuelle(); i++){
            res[i] = Math.abs(i-tmp);
        }
        return res;
    }

    @Override
    public String toString(){
        // A OPTI
        String res = "";
        int[] ligne = this.indexLigneActu();
        int[] col = this.indexColActu();
        if (this.orientation % 2 == 0){
            for(int i=0; i<this.tab.size(); i++){
                for(int j=0; j<this.tab.get(0).size(); j++){
                    if(this.tab.get(col[i]).get(ligne[j])){
                        res += " ■ ";
                    }
                    else{
                        res += " . ";
                    }
                }
                res += "\n";
            }
        }
        else {
            for(int i=0; i<this.tab.get(0).size(); i++){
                for(int j=0; j<this.tab.size(); j++){
                    if(this.tab.get(ligne[j]).get(col[i])){
                        res += " ■ ";
                    }
                    else{
                        res += " . ";
                    }
                }
                res += "\n";
            }
        }
        return res;
    }
}
