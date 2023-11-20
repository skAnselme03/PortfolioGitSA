<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

            <!DOCTYPE html>
            <html>

            <head>
               <meta charset="UTF-8">
               <title>Votre commande</title>
         	   <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">
            	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/commande.css" type="text/css">
               <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/validationFormulaire.js" type="text/javascript"></script>
               <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/activerFormulaire.js" type="text/javascript"></script>
               <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/afficherDonneesClient.js" type="text/javascript"></script>
             <!--   <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/getDataClient.js" type="text/javascript"></script>
               
                 <script src="../Assets/Scripts/modules/getDataClient.js" type="text/javascript"></script>
                <script src="../Assets/Scripts/modules/afficherDonneesClient.js" type="text/javascript"></script>
                <script src="../Assets/Scripts/modules/activerFormulaire.js" type="text/javascript"></script>
                
                <script src="../Assets/Scripts/main.js" type="module"></script>-->
                 <!-- JavaScript pour initialiser les données du client -->
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

           		 	//activerDesactiverForm('modifierFormulaire', 'enregistrerFormulaire', 'profilClientForm');

           		});
            	//remplirFormulaire(clientData, adresseData, daresseLData);           	
            </script>
            </head>

            <body>
            
       			<%@ include file="../header.jsp" %>
                <section class="contact-form-container">                
                    <h2>Votre commande</h2>
                    <form action="${pageContext.request.contextPath}/panier" method="post" name="commandeClientForm" id="commandeClientForm">
                        <input type="hidden" name="action" value="COMMANDER" required>
                        <fieldset>
                            <fieldset class="infos-perso">
                                <section>
                                    <label for="prenom">Prénom </label>
                                    <input type="text" name="prenom" id="prenom" placeholder="Votre prenom" 
                                    		aria-label="Un prenom quelconque"required>
                                  </section>
                                 <section>
                                    <label for="nom">Nom</label>
                                    <input type="text" name="nom" id="nom" placeholder="Votre nom" aria-label="Un nom quelconque"required>
                                </section>
                                <section>
                                    <label for="telephone">Numéro de téléphone</label>
                                    <input type="tel" name="telephone" id="telephone" placeholder="Votre téléphone" 
                                    	aria-label="Un numéro de téléphone quelconque"required>
                                </section>
                                <section>
                                    <label for="email">Courriel</label>
                                    <input type="email" name="email" id="email" placeholder="Entrer votre email" 
                                    	aria-label="un email quelconque"required>
                                </section>
                            </fieldset>
                            <fieldset>
                                <legend>Votre adresse</legend>
                                <section>
                                    <label for="adresse">Adresse</label>
                                    <input type="text" name="adresse" id="adresse" placeholder="Rue et numéro de rue" aria-label="une adresse quelconque"required>
                                </section>
                                <section>
                                    <label for="ville">Ville</label>
                                    <input type="text" name="ville" id="ville" placeholder="Entrez la ville" aria-label="une ville quelconque"required>
                                </section>
                                <section>
                                    <label for="province">Province</label>
                                    <select name="province" id="province"required>
							          <option value="" required selected>Sélectionnez votre province</option>
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
                                    <input type="text" name="pays" id="pays" placeholder="Entrez le pays" aria-label="un pays quelconque"required>
                                </section>
                                <section>
                                    <label for="codePostal">Code postal</label>
                                    <input type="text" name="codePostal" id="codePostal" placeholder="Entrez le code postal" aria-label="un code postal quelconque"required>
                                </section>
                            </fieldset>
                            <fieldset>
                                <legend>Adresse de livraison si différent de votre adresse</legend>
                                <section>
                                    <label for="adresseLivraison">Adresse</label>
                                    <input type="text" name="adresseLivraison" id="adresseLivraison" placeholder="Rue et numéro de rue" aria-label="une adresseLivraison quelconque" required>
                                </section>
                                <section>
                                    <label for="villeLivraison">Ville</label>
                                    <input type="text" name="villeLivraison" id="villeLivraison" placeholder="Entrez la ville" aria-label="une villeLivraison quelconque" required>
                                </section>
                                <section>
                                    <label for="provinceLivraison">Province</label>
                                      <select name="provinceLivraison" id="provinceLivraison" required>
							          <option value="" required selected>Sélectionnez votre province</option>
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
                                    <input type="text" name="paysLivraison" id="paysLivraison" placeholder="Entrez le pays de Livraison" aria-label="un pays de Livraison quelconque" required>
                                </section>
                                <section>
                                    <label for="codePostalLivraison">Code postal</label>
                                    <input type="text" name="codePostalLivraison" id="codePostalLivraison" placeholder="Entrez le code postal de Livraison" aria-label="un code postal de Livraison quelconque" required>
                                </section>
                            </fieldset>
                            
                            <fieldset class="paiment">
                                <legend>Information paiment</legend>                            	
                               <section>
			                        <label for="carteCredit">Carte de crédit</label>
			                        <input type="text" name="carteCredit" id="carteCredit" placeholder="Numéro de carte de crédit"
			                            aria-label="Une carte de crédit quelconque" required>
			                    </section>                      	
                                <section>
                                    <label for="dateExp">Date exp</label>
                                    <input type="date" name="dateExp" id="dateExp" placeholder="Date d'expiration de la carte" 
                                    	aria-label="une date d'éxpiration quelconque"required>
                                </section>                      	
                                <section>
                                    <label for="cc">CC</label>
                                    <input type="text" name="cc" id="cc" placeholder="Code de sécurité" 
                                    	aria-label="un code de sécurité quelconque"required>
                                </section>
                                
                            </fieldset>
                        </fieldset>
                   
                       <section>                            
                           <button type="submit" id="commandeEnvoyer" required>Envoyer ma commande</button>
                       </section>
                    </form>
                </section>

                <%@ include file="../footer.jsp" %>
            </body>

            </html>
            