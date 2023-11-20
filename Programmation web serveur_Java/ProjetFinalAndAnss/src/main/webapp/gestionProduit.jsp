<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Produits</title>
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
		
		tr:nth-child(even) {
		  background-color: #dddddd;
		}
		
		tr:hover {
			background-color: #fef7c8;
		}
    </style>
</head>
<body>
	<h1>Gestion des Produits</h1>

    <!-- Formulaire pour un nouveau produit -->
    <form action="GererProduitServlet" method="post">
	    <input type="hidden" name="action" value="create">
	    <label for="categorie">Catégorie:</label>
	    <select name="categorie" id="categorie" required>
	        <option value="" disabled selected>Choisissez une catégorie</option>
	        <c:forEach items="${categories}" var="categorie">
	            <option value="${categorie.id}">${categorie.nom}</option>
	        </c:forEach>
	    </select>
	    <label for="nom">Nom du produit:</label>
	    <input type="text" id="nom" name="nom" required>
	    <label for="description">Description:</label>
	    <input type="text" id="description" name="description" required>
	    <label for="prix">Prix du produit:</label>
	    <input type="text" id="prix" name="prix" required>
	    <label for="image">Lien vers l'image du produit:</label>
	    <input type="text" id="image" name="image" required>
	    <label for="responsable">Responsable:</label>
	    <input type="text" id="responsable" name="responsable" required>
	    <button type="submit">Créer</button>
	</form>
	<br><br>

    <!-- Liste des produits existants avec boutons Modifier et Supprimer -->
    <table border="1">
        <thead>
            <tr>
                <th>Catégorie</th>
                <th>Nom</th>
                <th>Description</th>
                <th>Prix</th>
                <th>Lien image</th>
                <th>Responsable</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${produits}" var="produit">
			    <tr>
			        <td>${produit.categorieNom}</td>
			        <td>${produit.nom}</td>
			        <td>${produit.description}</td>
			        <td>${produit.prix}</td>
			        <td>${produit.image_url}</td>
			        <td>${produit.responsable}</td>
			        <td>
			            <form action="GererProduitServlet" method="post" style="display: inline;">
						    <input type="hidden" name="action" value="modify">
						    <input type="hidden" name="produitId" value="${produit.id}">
						    <select name="categorie" id="categorie" required>
							    <c:forEach items="${categories}" var="categorie">
							        <option value="${categorie.id}" ${produit.categorie == categorie.id ? 'selected' : ''}>${categorie.nom}</option>
							    </c:forEach>
							</select>
						    <input type="text" name="nom" value="${produit.nom}">
						    <input type="text" name="description" value="${produit.description}">
						    <input type="text" name="prix" value="${produit.prix}">
						    <input type="text" name="image" value="${produit.image_url}">
						    <input type="text" name="responsable" value="${produit.responsable}">
						    <button type="submit">Modifier</button>
						</form>
			            <form action="GererProduitServlet" method="post" style="display: inline;">
			                <input type="hidden" name="action" value="delete">
			                <input type="hidden" name="produitId" value="${produit.id}">
			                <button type="submit">Supprimer</button>
			            </form>
			        </td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>

</body>
</html>