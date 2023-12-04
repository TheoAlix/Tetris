package pieces;

/** 
 * Interface Ecouteur
 * Cette interface permet de creer un ecouteur pour la vue console
 */
public class ViewConsole implements Ecouteur{
    /*** L'instance de PlateauPuzzle a ecouter*/
    PlateauPuzzle plateau;
    
    /** 
     * Constructeur de la classe ViewConsole
     * @param plateau est l'instance de PlateauPuzzle a ecouter
    */
    public ViewConsole(PlateauPuzzle plateau){
        this.plateau = plateau;
        this.plateau.ajoutEcouteur(this);
    }

    
    /** 
     * Mets a jour la vue du modele
     * @param source est l'objet source de l'evenement
     */
    @Override
    public void modeleMisAJour(Object source){
        System.out.println(this.plateau);
    }

}