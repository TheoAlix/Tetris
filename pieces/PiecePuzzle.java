package pieces;

import java.util.List;

public interface PiecePuzzle{

    public void rotateLeft();

    public void rotateRight();

    public int largeurActuelle();

    public int hauteurActuelle();

    public boolean estOccupee(int colonne, int ligne);

    public List<Integer> centreGrille();

    //public int[] centrePiece(Boolean[][] grilleJeu);
}