package pieces;

import java.util.ArrayList;
import java.util.List;

public abstract class Piece implements PiecePuzzle{
    public int x;
    public int y;
    /*  public Boolean[][] tab = new Boolean[4][3]; */
    List<List<Boolean>> tab = new ArrayList<>();
    public int orientation; // 0 = 0°, 1 = 90°, 2 = 180°, 3 = 270°

    public Piece(int longueur, int orientation){
        super();
        this.orientation = orientation;
    }

    public void setPostion(int x,int y){
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
        if(this.orientation == 0 || this.orientation == 2){
            return this.tab.size();
        }
        else{
            return this.tab.get(0).size();
        }
    }

    public int hauteurActuelle(){
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
        /*
        else if (this.orientation == 1){
            return this.tab[][colonne];
        }
        else if (this.orientation == 2){
            return this.tab[ligne][];
        }
        else{
            return this.tab[][];
        } */
        return true;
    }

    public List<Integer> centreGrille(){
        // A FAIRE
        List<Integer> res = new ArrayList<>();
        res.add(this.largeurActuelle()/2);
        res.add(this.hauteurActuelle()/2);
        return res;
    }

    @Override
    public String toString(){
        // A FAIRE
        String res = "";
            for(int i=0; i<this.tab.size(); i++){
                for(int j=0; j<this.tab.get(0).size(); j++){
                    if(this.tab.get(i).get(j)){
                        res += " ■ ";
                    }
                    else{
                        res += " . ";
                    }
                }
                res += "\n";
            }
        return res;
    }
}
