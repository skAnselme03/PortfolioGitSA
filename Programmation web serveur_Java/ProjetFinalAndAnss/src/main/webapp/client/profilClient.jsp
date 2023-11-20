<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ include file="../header.jsp" %>

            <!DOCTYPE html>
            <html>

            <head>
                <meta charset="UTF-8">
                <title>Gestion client</title>
                <!--<script src="../Assets/JS/validationFormulaire.js" type="text/javascript"></script>
                <script src="../Assets/Scripts/activerFomulaire.js" type="text/javascript"></script>
                <script src="../Assets/Scripts/afficherDonneesClient.js" type="text/javascript"></script>-->
                <script src="../Assets/Scripts/main.js" type="module"></script>
            </head>

            <body>

                <section>
                    <form action="profilClient" method="post" onsubmit="return validerCreerCompte()" name="profileClientForm" id="profilClientForm">
                        <input type="hidden" name="action" value="PROFIL" disabled>
                        <fieldset>
                            <legend>Création - Compte client </legend>
                            <fieldset>
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
                            <fieldset>
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
                                    <label for="postalCode">Code postal</label>
                                    <input type="text" name="postalCode" id="postalCode" placeholder="Entrez le code postal" aria-label="un code postal quelconque" required disabled>
                                </section>
                            </fieldset>
                            <fieldset>
                                <legend>Adresse de livraison si différent de votre adresse</legend>
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
                                    <input type="text" name="paysLivraison" id="paysLivraison" placeholder="Entrez le pays de Livraison" aria-label="un pays de Livraison quelconque" disabled>
                                </section>
                                <section>
                                    <label for="postalCodeLivraison">Code postal</label>
                                    <input type="text" name="postalCodeLivraison" id="postalCodeLivraison" placeholder="Entrez le code postal de Livraison" aria-label="un code postal de Livraison quelconque" disabled>
                                </section>
                            </fieldset>
                            <section>
                                <button type="button" id="modifierFormulaire" onclick="activerDesactiverForm('modifierFormulaire', 'enregistrerFormulaire', 'profilClientForm')">Modifier</button>
                                <button type="submit" id="enregistrerFormulaire" disabled>Enregister</button>
                            </section>
                        </fieldset>
                    </form>
                </section>

                <%@ include file="../footer.jsp" %>
            </body>

            </html>