package filsrouge;

public class T implements Piece{

    public Boolean[][] tab = new Boolean[4][3];

    public T(Boolean[][] tab){
        this.tab = tab;
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

    public static void main(String[] args){
            Boolean[][] tab = 
            {{true,true,true},
            {false,true,false},
            {false,true,false},
            {false,true,false}};
            Piece p = new T(tab);
            System.out.println(p);
        }
}