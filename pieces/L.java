package pieces;

import java.util.ArrayList;
import java.util.List;

public class L extends Piece{

    public L(int longueur, int orientation){
        super(longueur,orientation);
        this.tab.add(List.of(true,false, false));
        if (longueur >= 2){
            List<Boolean> tmp = new ArrayList<>();
            for (int i = this.tab.size(); i < longueur-1; i++) {
                tmp = new ArrayList<>();
                tmp.add(true);
                tmp.add(false);
                tmp.add(false);
                this.tab.add(tmp);
            }
        }
        this.tab.add(List.of(true,true, true));
    }

    public String getType(){return "L";}

    @Override
    public List<Integer> centreGrille(){
        List<Integer> res = new ArrayList<>();
        if(this.orientation %2 == 0){
            res.add(this.largeurActuelle()/2);
            res.add(this.hauteurActuelle()/2-1);
        }
        else{
            res.add(this.largeurActuelle()/2-1);
            res.add(this.hauteurActuelle()/2);
        }
        
        return res;
    }

    public static void main(String[] args){
            Piece p = new L(5,0);
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