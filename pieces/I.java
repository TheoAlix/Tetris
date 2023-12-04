package pieces;

import java.util.ArrayList;
import java.util.List;

public class I extends Piece{

    public I(int longueur, int orientation){
        super(longueur,orientation);
        this.tab.add(List.of(true));
        if (longueur >= 2){
            List<Boolean> tmp = new ArrayList<>();
            for (int i = this.tab.size(); i < longueur; i++) {
                tmp = new ArrayList<>();
                tmp.add(true);
                this.tab.add(tmp);
            }
        }
    }

    public String getType(){return "I";}

    public static void main(String[] args){
            Piece p = new I(8,0);
            System.out.println("----------------------------");
            System.out.println(p);
            System.out.print("largeurActu :");
            System.out.println(p.largeurActuelle());
            System.out.print("hauteurActu :");
            System.out.println(p.hauteurActuelle());
            System.out.print("centre Grille :");
            System.out.println(p.centreGrille());
            p.rotateLeft();
            
            System.out.println("----------------------------");
            System.out.println(p);
            System.out.print("largeurActu :");
            System.out.println(p.largeurActuelle());
            System.out.print("hauteurActu :");
            System.out.println(p.hauteurActuelle());
            System.out.print("centre Grille :");
            System.out.println(p.centreGrille());
        }
}