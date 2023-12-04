package pieces;

/** 
 * Interface Ecouteur
 * Cette interface permet de creer un écouteur
 */
public interface Ecouteur{
    
    /** 
     * Mets a jour la vue du modele
     * @param Source est l'objet source de l'evenement
     */
    public void modeleMisAJour(Object Source);
}