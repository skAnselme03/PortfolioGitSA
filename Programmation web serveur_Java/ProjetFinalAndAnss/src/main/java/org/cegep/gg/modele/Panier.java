package org.cegep.gg.modele;

import java.util.ArrayList;
import java.util.List;

public class Panier {
	  // Liste pour stocker les éléments du panier (PanierItems)
    private List<PanierItem> panierItems = new ArrayList<>();


    /*********************/
    /*	CONSTRUCTEUR	 */
    /*********************/

    public Panier() {
    	panierItems = new ArrayList<>();
    }

    /*********************/
    /*		GETTER 		 */
    /*********************/

    /**
     * Obtenir la liste des éléments du panier
     * @return
     */
    public List<PanierItem> getListeElementsDuPanier() {
        return panierItems;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/
    /**
     * Remplace la liste des éléments du panier par une nouvelle liste fournie.
     * @param nouvelleListe La nouvelle liste d'éléments du panier.
     */
    public void setListeElementsDuPanier(List<PanierItem> nouvelleListe) {
        panierItems = nouvelleListe;
    }
    /*********************/
    /*		MÉTHODES	 */
    /*********************/

    /**
     * Ajouter un produit au panier
     * @param item produit a ajouter
     * @param quantite quantité à ajouter
     */
    public void ajouterProduitAuPanier(PanierItem item, int quantite) {
        // Vérifier si le produit est déjà dans le panier
        for (PanierItem panierItem : panierItems) {
            if (panierItem.getIdProduit() == item.getIdProduit()) {
                // Mettre à jour la quantité du produit existant
                panierItem.setQuantite(panierItem.getQuantite() + quantite);
                return;
            }
        }

        // Si le produit n'est pas déjà dans le panier, l'ajouter avec la quantité spécifiée
        item.setQuantite(quantite);
        panierItems.add(item);
    }

    /**
     * Supprimer un élément du panier
     * @param idProduit index du produit
     */
    public void supprimerElementDuPanier(int idProduit) {
        for (PanierItem item : panierItems) {
            if (item.getIdProduit() == idProduit) {
            	panierItems.remove(item);
                return;
            }
        }
    }

    /**
     * Mettre à jour la quantité d'un produit du panier
     * @param idProduit index du produit
     * @param quantite à mettre à jour
     */
    public void mettreAJourQuantite(int idProduit, int quantite) {
        if (idProduit >= 0 && idProduit < panierItems.size()) {
            PanierItem item = panierItems.get(idProduit);
            item.setQuantite(quantite);
        }
    }


    /**
     * Calculer le total du panier en additionnant le prix des produits multiplié par leur quantité.
     * @return somme des prix total du panier
     */
    public float calculerTotalPanier() {
        float total = 0;
        for (PanierItem item : panierItems) {
            total += item.getPrixTotal();
        }
        return total;
    }

    /**
     * Générer une représentation sous forme de chaîne de caractères du panier.
     */
    @Override
    public String toString() {
        return "Panier{" +
                "items=" + panierItems +
                '}';
    }

}
