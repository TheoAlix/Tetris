package filsrouge;

public class T extends Piece{

    public Boolean[][] tab = 
            {{true,true,true},
            {false,true,false},
            {false,true,false},
            {false,true,false}};

    public T(){
        super.tab = this.tab;
    }

    public static void main(String[] args){
            Piece p = new T();
            System.out.println(p);
            System.out.println(p.largeurActuelle());
            System.out.println(p.hauteurActuelle());
            p.rotateLeft();
            System.out.println(p);
            System.out.println(p.largeurActuelle());
            System.out.println(p.hauteurActuelle());
        }
}