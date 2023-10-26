package pieces ;

import java.util.ArrayList;
import java.util.List;

public class T extends Piece{

    public T(int longueur, int orientation){
        super(longueur,orientation);
        this.tab.add(List.of(true, true, true));
        this.tab.add(List.of(false, true, false));
        if (longueur >= 2){
            List<Boolean> tmp = new ArrayList<>();
            for (int i = this.tab.size(); i < longueur; i++) {
                System.out.println(i);
                tmp = new ArrayList<>();
                tmp.add(false);
                tmp.add(true);
                tmp.add(false);
                this.tab.add(tmp);
            }
        }
    }

    public static void main(String[] args){
            Piece p = new T(4,0);
            System.out.println("----------------------------");
            System.out.println(p);
            System.out.print("largeurActu :");
            System.out.println(p.largeurActuelle());
            System.out.print("hauteurActu :");
            System.out.println(p.hauteurActuelle());
            System.out.print("largeurActu :");
            System.out.println(p.centreGrille());
            p.rotateLeft();
            System.out.println("----------------------------");
            System.out.println(p);
            System.out.print("largeurActu :");
            System.out.println(p.largeurActuelle());
            System.out.print("hauteurActu :");
            System.out.println(p.hauteurActuelle());
            System.out.print("largeurActu :");
            System.out.println(p.centreGrille());
        }
}