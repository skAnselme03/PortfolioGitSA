<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="Description" content="Tester les page avec des servlet sans BDD" />
    
    <!-- <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">-->
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
 	    
     <title>Accueil - Projet Final AndAnss</title>
    <style>
        body, html {
            height: 100%;
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
        }
        .hero {
            text-align: center;
            position: relative;
            background: linear-gradient(rgba(255, 255, 255, 0.2), rgba(255, 255, 255, 1)), url('https://images.unsplash.com/photo-1564141696939-9eb6e957ccfc?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2141&q=80') no-repeat center center;
            background-size: cover;
            padding: 60px;
        }
        .hero h2 {
            font-size: 36px;
            margin: 0;
            color: #000;
        }
        .hero h3 {
            font-size: 24px;
            margin: 10px 0;
            color: #000;
        }
        .hero p {
            font-size: 18px;
            color: #000;
        }
        .features {
            display: flex;
            justify-content: space-around;
            margin: 40px auto;
            max-width: 1200px;
            text-align: center;
        }
        .feature {
            flex: 0 1 calc(30% - 10px);
            padding: 20px;
            border: 1px solid #ccc;
            background-color: #fef7c8;
            border-radius: 10px;
            margin: 5px;
        }
        .feature a {
            text-decoration: none;
            color: #333;
        }
        .product-list {
            display: flex;
            flex-wrap: wrap;
            justify-content: center;
            margin: 0 auto;
            max-width: 1200px;
        }
        .product-container {
            flex: 0 1 30%;
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
    <!--TODO: À MODIFIER OU ENLEVER À LA FIN
    <header>
        <h1>TD 1: Accueil</h1>
        <nav>
            <a href="acceuil">Accueil</a>
            <a href="login">Se connecter</a>
            <a href="signUp">Créer un compte</a>
           <%-- Afficher le bouton de déconnexion si l'utilisateur est connecté --%>
        <c:if test="${not empty clientAuthentifier}">
            <a href="logout" class="btn btn-danger">Déconnexion</a>
        </c:if>
        </nav>
    </header>-->
    
    
    <%@ include file="header.jsp" %>
    <section class="hero">
        <h2>Projet Final</h2>
        <h3>Par S.Anselme & C.Andriamamonjy</h3>
        <p>Site de vente en ligne de produits capillaires en tout genre</p>
    </section>
 
     <section class="features">	        
  		<c:if test="${CONFIRMATION_COMMANDE}">
            <p style="color: green;">Votre commande a bien été reçue. Un courriel de confirmation vous a été envoyé !</p>
      	</c:if>
     </section>
    <section class="features">
        
        <div class="feature">
            <h3><a href="login">CRÉEZ UN COMPTE</a></h3>
            <p>Rejoignez notre grande famille</p>
        </div>
        <div class="feature">
            <h3><a href="catalogue">CATALOGUE</a></h3>
            <p>Découvrez une gamme variée de produits pensés pour le soin de vos cheveux</p>
        </div>
        <div class="feature">
            <h3><a href="contact">CONTACTEZ-NOUS</a></h3>
            <p>Nous sommes à votre service pour vous apporter les meilleurs produits</p>
        </div>
     </section>
    <!-- <hr color="#fef7c8"><hr color="#fef7c8"><hr color="#fef7c8">-->
     <section class="product-list">    	
        <c:forEach var="produit" items="${produits}" varStatus="loop">
            <div class="product-container">
                <img src="${produit.image_url}" class="product-image">
                <p><b>${produit.nom}</b></p>
            </div>
        </c:forEach>
    </section>
    
    <%@ include file="footer.jsp" %>
</body>

</html>