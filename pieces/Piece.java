package filsrouge;

public abstract class Piece implements PiecePuzzle{
    public int x;
    public int y;
    public Boolean[][] tab = new Boolean[4][3];
    public int orientation; // 0 = 0°, 1 = 90°, 2 = 180°, 3 = 270°

    public Piece(){
        super();
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
            return this.tab.length;
        }
        else{
            return this.tab[0].length;
        }
    }

    public int hauteurActuelle(){
        if(this.orientation == 0 || this.orientation == 2){
            return this.tab[0].length;
        }
        else{
            return this.tab.length;
        }
    }

    public boolean estOccupee(int colonne, int ligne){
        return true;
    }

    public int[] centreGrille(int colonne, int ligne){
        int[] res = {1,2,3};
        
        return res;
    }


    @Override
    public String toString(){
        String res = "";
        for(int i=0; i<this.tab.length; i++){
            for(int j=0; j<this.tab[0].length; j++){
                if(this.tab[i][j]){
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