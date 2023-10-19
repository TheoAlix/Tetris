package filsrouge;

public interface Piece{

    public Boolean[][] tab = new Boolean[4][3];

    public void rotateLeft();

    public void rotateRight();

    public int largeurActuelle();

    public int hauteurActuelle();

    public boolean estOccupee(int colonne, int ligne);

    public int[] centreGrille(int colonne, int ligne);

    //public int[] centrePiece(Boolean[][] grilleJeu);
}