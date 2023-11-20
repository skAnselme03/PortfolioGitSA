<%@ include file="header.jsp" %>
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
    <!-- <link rel="stylesheet" href="assets/css/main.css"> -->
    <title>Index de test</title>
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
    
    
    
    <section class="section_accueil">
        <p>Tester toutes les pages sans BDD</p>
    </section>
</body>

</html>