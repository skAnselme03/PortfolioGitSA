<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Catalogue</title>
    
 	    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
 	    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
 	    
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
            /*justify-content: center; -- Commenté pour faire fonctionner avec le header*/
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
	<%@ include file="header.jsp" %>
    <div class="container">
        <div class="category-menu">
            <ul>
                <c:forEach var="categorie" items="${categories}">
                    <li><h2><a href="?category=${categorie.id}">${categorie.nom}</a></h2></li>
                </c:forEach>
                <li><h2><a href="/ProjetFinalAndAnss/catalogue">Toutes les catégories</a></h2></li>
            </ul>
        </div>
        
        <div class="product-list">
            <c:choose>
                <c:when test="${empty produits}">
                    <p>Aucun produit trouvé</p>
                </c:when>
                <c:otherwise>
                    <c:forEach var="produit" items="${produits}" varStatus="loop">
                        <div class="product-container">
                            <img src="${produit.image_url}" class="product-image">
                            <p><b>${produit.nom}</b></p>
                            <p>${produit.description}</p>
                            <p><i><fmt:formatNumber value="${produit.prix}" type="number" pattern="#,##0.00" /> $</i>
                            &emsp;
                            <form action="${pageContext.request.contextPath}/panier" method="post">
                                <input type="hidden" name="action" value="AJOUT" />
                                <input type="hidden" name="produitId" value="${produit.id}">
                                <input type="hidden" name="produitNom" value="${produit.nom}">
                                <input type="hidden" name="produitPrix" value="${produit.prix}">
                                <button type="submit">Ajouter au panier</button>
                            </form>
                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </div>
      
    </div>
    
    
    <%--<%@ include file="footer.jsp" %> --%>
</body>
	
</html>