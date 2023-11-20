<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestion des Catégories</title>
    <style>
    	body {
    		margin-left: 20px;
    		margin-right: 20px;
    	}
    	
        table {
            width: 80%;
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
    <h1>Gestion des Catégories</h1>

    <!-- Formulaire pour une nouvelle catégorie -->
    <form action="GererCategorieServlet" method="post">
        <input type="hidden" name="action" value="create">
        <label for="nom">Nom de la catégorie:</label>
        <input type="text" id="nom" name="nom" required>
        <label for="responsable">Responsable:</label>
        <input type="text" id="responsable" name="responsable" required>
        <button type="submit">Créer</button>
    </form>
    <br><br>

    <!-- Liste des catégories existantes avec boutons Modifier et Supprimer -->
    <table border="1">
        <thead>
            <tr>
                <th>Nom</th>
                <th>Responsable</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${categories}" var="categorie">
			    <tr>
			        <td>${categorie.nom}</td>
			        <td>${categorie.responsable}</td>
			        <td>
			            <form action="GererCategorieServlet" method="post" style="display: inline;">
						    <input type="hidden" name="action" value="modify">
						    <input type="hidden" name="categorieId" value="${categorie.id}">
						    <input type="text" name="nom" value="${categorie.nom}">
						    <input type="text" name="responsable" value="${categorie.responsable}">
						    <button type="submit">Modifier</button>
						</form>
			            <form action="GererCategorieServlet" method="post" style="display: inline;">
			                <input type="hidden" name="action" value="delete">
			                <input type="hidden" name="categorieId" value="${categorie.id}">
			                <button type="submit">Supprimer</button>
			            </form>
			        </td>
			    </tr>
			</c:forEach>
        </tbody>
    </table>
</body>
</html>