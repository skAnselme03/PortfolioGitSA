package org.cegep.gg.modele;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.http.Cookie;

//Cette classe représente le panier stocké dans le cookie
public class PanierCookie {

    public static final String NOM_PANIER_COOKIE = "panierCookie";
	/**
	 * Crée un cookie à partir d'une liste d'éléments de panier.
	 * @param panierItems
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static Cookie creerCookiePanier(List<PanierItem> panierItems) throws UnsupportedEncodingException {
		 // Crée un objet StringBuilder pour construire la valeur du cookie.
	    StringBuilder cookieValue = new StringBuilder();

	    // Parcourt la liste des éléments du panier.
	    for (int i = 0; i < panierItems.size(); i++) {
	        // Récupère un élément du panier à l'indice i.
	        PanierItem item = panierItems.get(i);

	        // Encodez correctement les données avant de les ajouter à la valeur du cookie.
	        String produitNomEncoded = URLEncoder.encode(item.getNomProduit(), "UTF-8");

	        if (i > 0) {
	            // Si ce n'est pas le premier élément, ajoutez une virgule pour séparer chaque élement ajouter.
	            cookieValue.append("|");
	        }

	        // Ajoutez l'identifiant du produit suivi de ":", le nom du produit suivi de ":",
	        // le prix du produit suivi de "." et la quantité à la valeur du cookie.

	        cookieValue.append(item.getIdProduit()).append(":").append(produitNomEncoded).append(":")
	                .append(item.getPrixUnitaire()).append(":").append(item.getQuantite());
	    }

	    // Crée un nouveau cookie nommé "panier" avec la valeur construite.
	    Cookie panierCookie = new Cookie(NOM_PANIER_COOKIE, cookieValue.toString());

	    // Définit la durée de validité du cookie en secondes (ici, 7 jours).
	    panierCookie.setMaxAge(7 * 24 * 60 * 60);
	    panierCookie.setPath("/"); // Configurez le chemin pour que le cookie soit accessible partout

	    // Renvoie le cookie créé.
	    return panierCookie;
    }


	/**
	 * Récupérer les éléments du panier à partir d'un cookie.
	 *
	 * @param cookie Le cookie contenant les données du panier.
	 * @return Une liste d'objets PanierItem représentant les éléments du panier.
	 * @throws UnsupportedEncodingException
	 */
    public static List<PanierItem> getPanierDeCookie(Cookie cookie) throws UnsupportedEncodingException {
    	 // Crée une liste pour stocker les éléments du panier.
        List<PanierItem> panierItems = new ArrayList<>();

        // Récupère la valeur du cookie.
        String cookieValeur = cookie.getValue();

        // Vérifie si la valeur du cookie n'est pas vide.
        if (!cookieValeur.isEmpty()) {
            // Sépare les éléments du panier en utilisant une point-virgule comme séparateur.
           // String[] itemStrings = cookieValeur.split(";");
            String[] itemStrings = cookieValeur.split("\\|");

            // Parcourt chaque élément du panier.
            for (String itemString : itemStrings) {
                // Divise l'élément du panier en parties en utilisant ":" comme séparateur.
                String[] parts = itemString.split(":");
                // Vérifie si l'élément contient 4 parties, ce qui est attendu.
                if (parts.length == 4) {
                    // Récupère l'identifiant du produit (partie 0).
                    int produitId = Integer.parseInt(parts[0]);

                    // Récupère le nom du produit encodé (partie 1).
                    String produitNomEncoded = parts[1];

                    // Récupère le prix du produit (partie 2).
                    double produitPrix = Double.parseDouble(parts[2]);

                    // Récupère la quantité du produit (partie 3).
                    int quantite = Integer.parseInt(parts[3]);

                    // Décode correctement le nom du produit encodé.
                    String produitNom = URLDecoder.decode(produitNomEncoded, "UTF-8");

                    // Crée un objet PanierItem représentant l'élément du panier.
                    PanierItem item = new PanierItem(produitId, produitNom, quantite, produitPrix);

                    // Ajoute l'élément au panier.
                    panierItems.add(item);
                }
            }
        }

        // Retourne la liste des éléments du panier.
        return panierItems;
    }
}

