package pieces;

import java.util.ArrayList;
/*** Cette classe est une classe abstraite qui permet creer une classe ecoutable*/
public abstract class AbstractModeleEcoutable{
    /*** Liste des ecouteurs*/
    ArrayList<Ecouteur> ecouteurs = new ArrayList<>();
    
    /** 
     * Ajoute un ecouteur a la liste des ecouteur
     * @param e L'ecouteur a ajouter à la liste des ecouteurs
     */
    public void ajoutEcouteur(Ecouteur e){
        this.ecouteurs.add(e);
    }
    
    /** 
     * Retire un ecouteur de la liste des ecouteurs
     * @param e l'écouté à retirer de la liste des ecouteurs
     */
    public void retraitEcouteur(Ecouteur e){
        this.ecouteurs.remove(e);
    }

    /** 
     * Appelle la methode modeleMisAJour a tout les ecouteurs qui sont dans la liste des ecouteurs
     */
    public void plateauChangement(){
        for(Ecouteur e:ecouteurs){
            e.modeleMisAJour(e);
        }
    }
    /** 
     * Appelle la methode modeleMisAJour a tout les ecouteurs qui sont dans la liste des ecouteurs
     * Meme fonction mais nom different afin de differencier les deux methodes dans le code
     */
    public void guiChangement(){
        for(Ecouteur e:ecouteurs){
            e.modeleMisAJour(e);
        }
    }
}