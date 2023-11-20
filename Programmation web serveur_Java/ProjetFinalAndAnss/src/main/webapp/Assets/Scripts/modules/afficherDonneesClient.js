export function remplirFormulaire(clientDonnees, clientAdresse, clientAdresseL) {
    if (typeof clientDonnees !== 'undefined') {
        // Remplit les champs du formulaire avec les données du client
        document.getElementById("prenom").value = clientDonnees.prenom;
        document.getElementById("nom").value = clientDonnees.nom;
        document.getElementById("username").value = clientDonnees.username;
        document.getElementById("dateNaiss").value = clientDonnees.dateNaissance;
        document.getElementById("telephone").value = clientDonnees.telephone;
        document.getElementById("email").value = clientDonnees.courriel;
        document.getElementById("confirmerEmail").value = clientDonnees.confirmCourriel;
        document.getElementById("password").value = clientDonnees.password;

        document.getElementById("adresse").value = clientAdresse.adresse;
        document.getElementById("ville").value = clientAdresse.ville;
        // Sélectionne la province correspondante dans la liste déroulante
        var provinceSelect = document.getElementById("province");
        for (var i = 0; i < provinceSelect.options.length; i++) {
            if (provinceSelect.options[i].value === clientAdresse.province) {
                provinceSelect.selectedIndex = i;
                break;
            }
        }
        document.getElementById("pays").value = clientAdresse.pays;
        document.getElementById("postalCode").value = clientAdresse.codePostal;

        document.getElementById("adresseLivraison").value = clientAdresseL.adresseLivraison;
        document.getElementById("villeLivraison").value = clientAdresseL.villeLivraison;
        // Sélectionne la province de livraison correspondante dans la liste déroulante
        var provinceLivraisonSelect = document.getElementById("provinceLivraison");
        for (var j = 0; j < provinceLivraisonSelect.options.length; j++) {
            if (provinceLivraisonSelect.options[j].value === clientAdresseL.provinceLivraison) {
                provinceLivraisonSelect.selectedIndex = j;
                break;
            }
        }

        document.getElementById("paysLivraison").value = clientAdresseL.paysLivraison;
        document.getElementById("postalCodeLivraison").value = clientAdresseL.codePostal;
    }
}
