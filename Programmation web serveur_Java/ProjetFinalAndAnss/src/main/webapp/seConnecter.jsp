<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Se connecter</title>
    <link rel="stylesheet" href="./Assets/CSS/main.css">
    <!-- <script src="./Assets/JS/validationFormulaire.js" type="text/javascript"></script>-->
</head>

<body>
<body>
 <header>
        <h1>TD 1: Accueil</h1>
        <nav>
            <a href="./index.jsp">Accueil</a>
            <a href="./seConnecter.jsp">Se Connecter</a> 
        </nav>
    </header>
    <section>
    
        <%-- Afficher le message d'erreur s'il existe --%>
	    <c:if test="${not empty(errorMessage)}">
	        <p style="color: red;">${errorMessage}</p>
            <a href="./creerCompte.html">Créer un compte</a> <!--TODO: A MODIFIER LE href -->
	    </c:if>
	    
        <form action="login" method="POST" class="">
			<input type="hidden" name="action" value="LOGIN"/>
        	
            <fieldset>
                <Legend>Connection</Legend>
                <section>
                    <label for="login">Nom d'utilisateur ou Adresse e-mail:</label>
                    <input type="text" name="login" id="login" placeholder="Entrer votre username ou votre email" aria-label="un email ou un userna quelconque" required>
                </section>
                <section>
                    <label for="password">Mot de passe</label>
                    <input type="password" name="password" id="password" aria-label="un password quelconque" placeholder="Entrer votre password" required>
                </section>
                <section>
                    <button type="submit" class="">Se connecter</button>
                </section>
            </fieldset>
        </form>
    </section>
</body>
</html>