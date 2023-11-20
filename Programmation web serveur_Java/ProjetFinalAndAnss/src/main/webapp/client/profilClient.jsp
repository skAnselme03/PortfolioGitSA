<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html>

        <head>
            <meta charset="UTF-8">
            <title>Gestion client</title>
            <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">
            <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/profilClient.css" type="text/css">
            <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/validationFormulaire.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/activerFormulaire.js" type="text/javascript"></script>
            <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/afficherDonneesClient.js" type="text/javascript"></script>
           

            <script type="text/javascript">
                var clientData = {
                    prenom: '${clientDonnees.prenom}',
                    nom: '${clientDonnees.nom}',
                    username: '${clientDonnees.username}',
                    dateNaiss: '${clientDonnees.dateNaissance}',
                    telephone: '${clientDonnees.telephone}',
                    email: '${clientDonnees.courriel}',
                    confirmerEmail: '${clientDonnees.confirmCourriel}',
                    password: '${clientDonnees.motDePasse}'
                };

                var adresseData = {
                    adresse: '${clientAdresse.adresse}',
                    ville: '${clientAdresse.ville}',
                    province: '${clientAdresse.province}',
                    pays: '${clientAdresse.pays}',
                    codePostal: '${clientAdresse.codePostal}'
                };

                var daresseLData = {
                    adresseLivraison: '${clientAdresseL.adresse}',
                    villeLivraison: '${clientAdresseL.ville}',
                    provinceLivraison: '${clientAdresseL.province}',
                    paysLivraison: '${clientAdresseL.pays}',
                    codePostalLivraison: '${clientAdresseL.codePostal}'
                };

                //exectution du dom au chargement de la page
                document.addEventListener("DOMContentLoaded", function() {

                    console.log("DOM chargé");
                    remplirFormulaire(clientData, adresseData, daresseLData);

                    //const boutonModifier = document.getElementById("modifierFormulaire");
                    //const boutonEnregistrer = document.getElementById("enregistrerFormulaire");
                    //const champFormulaire = document.querySelectorAll("input, select");

                    activerDesactiverForm('modifierFormulaire', 'enregistrerFormulaire', 'profilClientForm');

                });
                //remplirFormulaire(clientData, adresseData, daresseLData);
            </script>
        </head>

        <body>

            <%@ include file="../header.jsp" %>
                <section class="contact-form-container">
					<h2>Création - Compte client</h2>
                    <%-- <form action="${pageContext.request.contextPath}/profilClient" method="get" onsubmit="return validerCreerCompte()" name="profileClientForm" id="profilClientForm">--%>
                   
	            	<%-- Afficher le message d'erreur s'il existe --%>
	                <c:if test="${not empty(msgClientModifier)}">
	                    <p style="color: red;">${msgClientModifier}</p>
	                </c:if>
                    <form action="${pageContext.request.contextPath}/signUp" method="post" onsubmit="return validerCreerCompte()" 
                    			name="profileClientForm" id="profilClientForm">
                        <input type="hidden" name="action" value="PROFIL" disabled>
                        <fieldset>
                            <fieldset class="infos-perso">
                                <legend></legend>
                                <section>
                                    <label for="prenom">Prénom </label>
                                    <input type="text" name="prenom" id="prenom" placeholder="Votre prenom" aria-label="Un prenom quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="nom">Nom</label>
                                    <input type="text" name="nom" id="nom" placeholder="Votre nom" aria-label="Un nom quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="username">Username</label>
                                    <input type="text" name="username" id="username" placeholder="Votre username" aria-label="Un nom username" required disabled>
                                </section>
                                <section>
                                    <label for="dateNaiss" class="">Date de naissance</label>
                                    <input type="date" name="dateNaiss" id="dateNaiss" required disabled>
                                </section>
                                <section>
                                    <label for="telephone">Numéro de téléphone</label>
                                    <input type="tel" name="telephone" id="telephone" placeholder="Votre téléphone" aria-label="Un numéro de téléphone quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="email">Courriel</label>
                                    <input type="email" name="email" id="email" placeholder="Entrer votre email" aria-label="un email quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="confirmerEmail">Confirmer votre courriel</label>
                                    <input type="email" name="confirmerEmail" id="confirmerEmail" placeholder="Entrer votre confirmerEmail" aria-label="un confirmerEmail quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="password">Mot de passe</label>
                                    <input type="password" name="password" id="password" placeholder="Entrer votre password" aria-label="un password quelconque" required disabled>
                                </section>
                            </fieldset>
                            <fieldset class="adresse-perso">
                                <legend>Votre adresse</legend>
                                <section>
                                    <label for="adresse">Adresse</label>
                                    <input type="text" name="adresse" id="adresse" placeholder="Rue et numéro de rue" aria-label="une adresse quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="ville">Ville</label>
                                    <input type="text" name="ville" id="ville" placeholder="Entrez la ville" aria-label="une ville quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="province">Province</label>
                                    <select name="province" id="province" required disabled>
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
                                    <input type="text" name="pays" id="pays" placeholder="Entrez le pays" aria-label="un pays quelconque" required disabled>
                                </section>
                                <section>
                                    <label for="codePostal">Code postal</label>
                                    <input type="text" name="codePostal" id="codePostal" placeholder="Entrez le code postal" aria-label="un code postal quelconque" required disabled>
                                </section>
                            </fieldset>
                            <fieldset class="adresse-livraison">
                                <legend>Adresse de livraison</legend>
                                <section>
                                    <label for="adresseLivraison">Adresse</label>
                                    <input type="text" name="adresseLivraison" id="adresseLivraison" placeholder="Rue et numéro de rue" aria-label="une adresseLivraison quelconque" disabled>
                                </section>
                                <section>
                                    <label for="villeLivraison">Ville</label>
                                    <input type="text" name="villeLivraison" id="villeLivraison" placeholder="Entrez la ville" aria-label="une villeLivraison quelconque" disabled>
                                </section>
                                <section>
                                    <label for="provinceLivraison">Province</label>
                                    <select name="provinceLivraison" id="provinceLivraison" disabled>
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
                                    <label for="paysLivraison">Pays</label>
                                    <input type="text" name="paysLivraison" id="paysLivraison" placeholder="Entrez le pays de Livraison" aria-label="un pays de Livraison quelconque" disabled>
                                </section>
                                <section>
                                    <label for="codePostalLivraison">Code postal</label>
                                    <input type="text" name="codePostalLivraison" id="codePostalLivraison" placeholder="Entrez le code postal de Livraison" aria-label="un code postal de Livraison quelconque" disabled>
                                </section>
                            </fieldset>
                        </fieldset>
                        <section>
                            <button type="button" id="modifierFormulaire">Modifier</button>
                            <button type="submit" id="enregistrerFormulaire" disabled>Enregister</button>
                        </section>
                    </form>
                </section>

                <%@ include file="../footer.jsp" %>
        </body>

        </html>