<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogue</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
        }
        .container {
            display: flex;
            height: 100%;
        }
        .category-menu {
            width: 20%;
            padding: 20px;
            border-right: 1px solid #ccc;
            box-sizing: border-box;
            background-color: #f2f2f2;
            position: fixed;
            overflow-y: auto;
            height: 100%;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
        }
        .category-menu ul {
            list-style-type: none; /* Retire les points de la liste */
            padding: 0;
        }
        .category-menu ul li a {
            margin: 5px 0;
            text-decoration: none;
        }
        .product-list {
            margin-left: 20%;
            padding: 20px;
            width: 80%;
            display: flex;
            flex-wrap: wrap;
            align-content: flex-start;
        }
        .product-container {
            width: 30%;
            margin: 10px;
            text-align: center;
        }
        .product-image {
            width: 100px;
            height: 100px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="category-menu">
            <ul>
                <c:forEach var="categorie" items="${categories}">
                    <li><h2><a href="?category=${categorie.id}">${categorie.nom}</a></h2></li>
                </c:forEach>
                <li><h2><a href="/ProjetFinalAndAnss/catalogue">Toutes les catégories</a></h2></li>            	
		        <!-- Logout link -->
		        <li><a href="logout" class="btn btn-danger">Déconnexion</a></li>
            </ul>
            <form action="CatalogueServlet" method="GET">
                <input type="text" name="search" placeholder="Chercher un produit spécifique">
                <button type="submit">Rechercher</button>
            </form>
        </div>
        
        <div class="product-list">        
            <c:forEach var="produit" items="${produits}" varStatus="loop">
                <div class="product-container">
                    <img src="${produit.image_url}" class="product-image">
                    <p><b>${produit.nom}</b></p>
                    <p>${produit.description}</p>
                    <p><i><fmt:formatNumber value="${produit.prix}" type="number" pattern="#,##0.00" /> $</i>
                    &emsp;<button type="button" onclick="">Ajouter</button></p>
                </div>
            </c:forEach>
        </div>
    </div>
</body>
</html>s