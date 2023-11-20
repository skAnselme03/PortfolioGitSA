<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Panier client</title>
    	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
 	    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
 	    <style>
	    	body {
	    		margin-left: 20px;
	    		margin-right: 20px;
	    	}
	    	
	        table {
	            width: 90%;
	            font-family: arial, sans-serif;
	            margin-left: auto; 
	  			margin-right: auto;
	  			border-collapse: collapse;
	        }
	        
	        td {
			  border: 1px solid #dddddd;
			  text-align: center;
			  padding: 8px;
			}
			
			th {
			  border: 1px solid #dddddd;
			  text-align: center;
			  padding: 8px;
			  background-color: #dddddd;
			}
			
			tr:nth-child(odd) {
			  background-color: #dddddd;
			}
			
			tr:hover {
				background-color: #fef7c8;
			}
			
			.button-container {
	            text-align: center;
	            margin-top: 20px;
	        }
	        
	        .button-container button {
	            margin: 5px;
	        }
        	/**Formattage du dernier ligne du tableau*/
        	tr:last-child, tr:last-child>th {
			    background-color: #fef7c8;
			    color: #333;
			    font-weight:bold;
			}
    	</style>
    <!-- <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/miseAJourPanier.js" type="text/javascript"></script>
    
  
    <script>
 		// Mettre à jour le panier toutes les 5 secondes (ou selon la fréquence souhaitée)
    	setInterval(mettreAJourPanier, 5000); // 5000 millisecondes (5 secondes)

    </script>-->   
    
	<script>
	    document.addEventListener("DOMContentLoaded", function() {
	        // Ajoute un événement au clic du bouton "Passer à la caisse"
	        const checkoutButton = document.querySelector("#checkoutButton");
	        const totalPriceDiv = document.querySelector("#totalPrice");
	        const totalAmountSpan = document.querySelector("#totalAmount");
	
	        
	        // Calcul le prix total
	        const priceElements = document.querySelectorAll("td:nth-child(2)");
	        let totalPrice = 0;
	
	        priceElements.forEach(function(priceElement) {
	            // Utilise une expression régulière pour extraire le nombre à virgule
	            const priceText = priceElement.textContent.trim();
	            const regex = /(\d+(?:[.,]\d+)?)/; // Recherche un nombre à virgule OU à point (problème sinon avec les systèmes anglos)
	            const match = priceText.match(regex);

	            if (match) {
	                // Remplace la virgule par un point pour convertir en nombre décimal
	                const price = parseFloat(match[0].replace(',', '.'));

	                // Vérifie que le prix est un nombre valide
	                if (!isNaN(price)) {
	                    totalPrice += price;
	                }
	            }
	        });
	        // Affiche le prix total
	        totalAmountSpan.textContent = totalPrice.toFixed(2);
	        
	    });
	</script>
</head>
<body>
    <%@ include file="header.jsp" %>
	 
     <c:if test="${!estPanier}"> 
     	<p style="color: red;">Votre panier est vide.</p>
     </c:if> 
     
     <c:if test="${estPanier}">     
	 	<h2>Panier</h2>
     	<table id="panierTable">
     		<thead>
	            <tr>
	                <th>Nom du produit</th>
	                <th>Prix</th>
	                <th>Quantité</th>
	                <th></th>
	            </tr>
            </thead>
            <tbody>
	            <c:forEach var="panierItem" items="${LISTE_PANIER}">
	                <tr> 
		               <td>${panierItem.nomProduit}</td>
		               <td><fmt:formatNumber value="${panierItem.prixTotal}" type="number" pattern="#,##0.00" groupingUsed="false"/> $</td>
		               <td>
		               	<form action="${pageContext.request.contextPath}/panier" method="post">
	           				<input type="hidden" name="action" value="MODIFIER" />                    
	           				<input type="hidden" name="produitId" value="${panierItem.idProduit}">                    
	           				<input type="number" name="produitQuantite" value="${panierItem.quantite}">
	                  		<button type="submit">Modifier</button></p>
	                    </form>
		               </td>
		               <td>	               	
	                    <form action="${pageContext.request.contextPath}/panier" method="post">
	            			<input type="hidden" name="action" value="SUPPRIMER" />                    
	            				<input type="hidden" name="produitId" value="${panierItem.idProduit}">
	                   		<button type="submit">Retirer</button></p>
	                    </form>
		               </td> 
	                </tr>
	           	</c:forEach>
	            <tr>
	            	<th>Total de la commande</th>
	            	<td id="totalPrice"><span id="totalAmount">0.00</span>$</td>    
	            	<td colspan="2"></td>
	            	        	
	            </tr>            
            </tbody>
        </table>
        <section class="button-container">      
        	<form action="${pageContext.request.contextPath}/catalogue">
				<button type="submit">Continuer à magasiner</button>        	
        	</form>  		        
	        	<form action="${pageContext.request.contextPath}/panier" method="post">
            		<input type="hidden" name="action" value="ACHETER" />
					<button type="submit">Acheter</button>
               </form>
        </section>
     
     </c:if> 
</body>
</html>
