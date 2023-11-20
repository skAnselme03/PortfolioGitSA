<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <link rel="stylesheet" href="./Assets/Styles/main.css" type="text/css">
        </head>

        <body>
            <header class="main-header">
                <div class="logo">
                    <!-- TODO: UTILISATION D'UN IMAGE EN RESSOURCE QUE D'UN SITE, CAR NE S'AFFICHE PAS PAR MOMENT DE MON COTÉ 
    <img src="https://imageupload.io/ib/3Ng0jPdVjaVBEl7_1693459444.png" alt="Logo de notre site sans nom">-->
                    <img src="./Assets/Img/logo.png" alt="Logo de notre site sans nom">
                </div>
                <nav class="nav-links">
                    <ul>
                        <li>
                            <form action="CatalogueServlet" method="GET">
                                <input type="text" name="search" placeholder="Chercher un produit spécifique">
                                <button type="submit">Rechercher</button>
                            </form>
                        </li>
                        <li><a href="acceuil">Accueil</a></li>
                        <li><a href="contact.jsp">Contactez-nous</a></li>
                        <li><a href="catalogue">Produits</a></li>

                        <!-- Définition d'une variable isClient en fonction du rôle de l'utilisateur -->
                        <% request.setAttribute("isClient", request.isUserInRole("client")); %>
                            <!-- TODO: À CONTINUER VOIR EXEMPLE CRUD3 -->

                            <%-- Masque le boutton de login si le client est connecter --%>
                                <!-- <li><a href="login">Se connecter</a></li>-->

                                <c:if test="${not clientConnecte}">
                                    <li><a href="login">Se connecter</a></li>
                                </c:if>
                                <c:if test="${clientConnecte}">
                                    <a href="profilClient" class="btn btn-danger">${clientDonnees.nom}, ${clientDonnees.prenom}</a>
                                </c:if>
                                <%-- Afficher le boutton sigUp si l'utilisateur n'existe pas --%>
                                    <c:if test="${not empty(errorMessageLogin) or not clientConnecte}">
                                        <a href="signUp">Créer un compte</a>
                                    </c:if>
                                    <%-- Afficher le boutton de déconnection si le client est connecter --%>
                                        <c:if test="${clientConnecte}">
                                            <li><a href="logout" class="btn btn-danger">Déconnexion</a></li>
                                        </c:if>
                                        <li><a href="#">Panier</a></li>
                    </ul>
                </nav>
            </header>

            <div class="main-container">
                <!-- Le contenu des JSP se trouve là -->
            </div>

            <!-- TODO: TEST Afficher la valeur de la variable clientConnecte
    <c:out value="${not clientConnecte}" />  -->

        </body>

        </html>