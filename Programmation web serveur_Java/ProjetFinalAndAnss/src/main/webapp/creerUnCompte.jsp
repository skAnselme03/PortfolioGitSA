<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Créer un compte</title>
    
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/profilClient.css" type="text/css">
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
    <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/validationFormulaire.js" type="text/javascript"></script>
</head>

<body>
		<%@ include file="header.jsp" %>
        <section class="creer-UnCompte contact-form-container">
	    	<%-- Afficher le message d'erreur s'il existe --%>
	        <c:if test="${not empty(msgClientExiste)}">
	            <p style="color: red;">${msgClientExiste}</p>
	        </c:if>
            <!-- <form action="signUp" method="post" onsubmit="validerCreerCompte('email', 'dateNaiss')" name="creerUnCompte">   -->
            <form action="signUp" method="post" onsubmit="return validerCreerCompte()" name="creerUnCompte">
                <input type="hidden" name="action" value="SIGNUP" />
                <fieldset>
                    <fieldset class="infos-perso">
                     <legend>Création - Compte client </legend>
                        <section>
                            <label for="prenom">Prénom </label>
                            <input type="text" name="prenom" id="prenom" placeholder="Votre prenom" aria-label="Un prenom quelconque" required>
                        </section>
                        <section>
                            <label for="nom">Nom</label>
                            <input type="text" name="nom" id="nom" placeholder="Votre nom" aria-label="Un nom quelconque" required>
                        </section>
                        <section>
                            <label for="dateNaiss" class="">Date de naissance</label>
                            <input type="date" name="dateNaiss" id="dateNaiss" required>
                        </section>
                        <section>
                            <label for="telephone">Numéro de téléphone</label>
                            <input type="tel" name="telephone" id="telephone" placeholder="Votre téléphone" aria-label="Un numéro de téléphone quelconque" required>
                        </section>
                        <section>
                            <label for="email">Courriel</label>
                            <input type="email" name="email" id="email" placeholder="Entrer votre email" aria-label="un email quelconque" required>
                        </section>
                        <section>
                            <label for="confirmerEmail">Confirmer votre courriel</label>
                            <input type="email" name="confirmerEmail" id="confirmerEmail" placeholder="Entrer votre confirmerEmail" aria-label="un confirmerEmail quelconque" required>
                        </section>
                        <section>
                            <label for="password">Mot de passe</label>
                            <input type="password" name="password" id="password" placeholder="Entrer votre password" aria-label="un password quelconque" required>
                        </section>
                    </fieldset>
                    <fieldset class="adresse-perso">
                        <legend>Votre adresse</legend>
                        <section>
                            <label for="adresse">Adresse</label>
                            <input type="text" name="adresse" id="adresse" placeholder="Rue et numéro de rue" aria-label="une adresse quelconque" required>
                        </section>
                        <section>
                            <label for="ville">Ville</label>
                            <input type="text" name="ville" id="ville" placeholder="Entrez la ville" aria-label="une ville quelconque" required>
                        </section>
                        <section>
                            <label for="province">Province</label>
                            <select name="province" id="province" required>
				                <option value="" disabled selected>Sélectionnez votre province</option>
				                <option value="AB">Alberta</option>
				                <option value="BC">Colombie-Britannique</option>
				                <option value="MB">Manitoba</option>
				                <option value="NB">Nouveau-Brunswick</option>
				                <option value="NL">Terre-Neuve-et-Labrador</option>
				                <option value="NS">Nouvelle-Écosse</option>
				                <option value="NT">Territoires du Nord-Ouest</option>
				                <option value="NU">Nunavut</option>
				                <option value="ON">Ontario</option>
				                <option value="PE">Île-du-Prince-Édouard</option>
				                <option value="QC">Québec</option>
				                <option value="SK">Saskatchewan</option>
				                <option value="YT">Yukon</option>
				            </select>
                        </section>
                        <section>
                            <label for="pays">Pays</label>
                            <input type="text" name="pays" id="pays" placeholder="Entrez le pays" aria-label="un pays quelconque" required>
                        </section>
                        <section>
                            <label for="postalCode">Code postal</label>
                            <input type="text" name="postalCode" id="postalCode" placeholder="Entrez le code postal" aria-label="un code postal quelconque" required>
                        </section>
                    </fieldset>
                    <fieldset class="adresse-livraison">
                        <legend>Adresse de livraison si différent de votre adresse</legend>
                        <section>
                            <label for="adresseLivraison">Adresse</label>
                            <input type="text" name="adresseLivraison" id="adresseLivraison" placeholder="Rue et numéro de rue" aria-label="une adresseLivraison quelconque">
                        </section>
                        <section>
                            <label for="villeLivraison">Ville</label>
                            <input type="text" name="villeLivraison" id="villeLivraison" placeholder="Entrez la ville" aria-label="une villeLivraison quelconque">
                        </section>
                        <section>
                            <label for="provinceLivraison">Province</label>
                            <select name="provinceLivraison" id="provinceLivraison">
				                <option value="" selected>Sélectionnez votre province</option>
				                <option value="AB">Alberta</option>
				                <option value="BC">Colombie-Britannique</option>
				                <option value="MB">Manitoba</option>
				                <option value="NB">Nouveau-Brunswick</option>
				                <option value="NL">Terre-Neuve-et-Labrador</option>
				                <option value="NS">Nouvelle-Écosse</option>
				                <option value="NT">Territoires du Nord-Ouest</option>
				                <option value="NU">Nunavut</option>
				                <option value="ON">Ontario</option>
				                <option value="PE">Île-du-Prince-Édouard</option>
				                <option value="QC">Québec</option>
				                <option value="SK">Saskatchewan</option>
				                <option value="YT">Yukon</option>
				            </select>
                        </section>
                        <section>
                            <label for="paysLivraison">Pays</label>
                            <input type="text" name="paysLivraison" id="paysLivraison" placeholder="Entrez le pays de Livraison" aria-label="un pays de Livraison quelconque">
                        </section>
                        <section>
                            <label for="postalCodeLivraison">Code postal</label>
                            <input type="text" name="postalCodeLivraison" id="postalCodeLivraison" placeholder="Entrez le code postal de Livraison" aria-label="un code postal de Livraison quelconque">
                        </section>
                    </fieldset>
                    <section class="creer-compte_enregistrer">
                        <button type="submit">Enregister</button>
                    </section>
                </fieldset>
            </form>
        </section>

        <%@ include file="footer.jsp" %>
</body>

</html>