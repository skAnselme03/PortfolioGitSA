function remplirFormulaire(dataClient, donneesAdresse, donneesCAdresseL) {
  // Remplit les champs du formulaire avec les données du client
    document.getElementById("prenom").value = dataClient.prenom;
    
    document.getElementById("nom").value = dataClient.nom;
    
    if(document.getElementById("username") != null){
	    document.getElementById("username").value = dataClient.username;		
	}
	
    if(document.getElementById("dateNaiss") != null){
	    document.getElementById("dateNaiss").value = dataClient.dateNaiss; // Utilisez "dateNaiss" au lieu de "dateNaissance"		
	}
    
    document.getElementById("telephone").value = dataClient.telephone;
    
    document.getElementById("email").value = dataClient.email; // Utilisez "email" au lieu de "telephone"
    
    if(document.getElementById("confirmerEmail") != null){
    	document.getElementById("confirmerEmail").value = dataClient.confirmerEmail;		
	}
    
    if(document.getElementById("password") != null){
	    document.getElementById("password").value = dataClient.password;		
	}

    // Remplit les champs de l'adresse principale
    document.getElementById("adresse").value = donneesAdresse.adresse;

    document.getElementById("ville").value = donneesAdresse.ville;
    // Sélectionne la province correspondante dans la liste déroulante
    var provinceSelect = document.getElementById("province");
    for (var i = 0; i < provinceSelect.options.length; i++) {
        if (provinceSelect.options[i].value === donneesAdresse.province) {
            provinceSelect.selectedIndex = i;
            break;
        }
    }
    document.getElementById("pays").value = donneesAdresse.pays;
    document.getElementById("codePostal").value = donneesAdresse.codePostal;


    // Remplit les champs de l'adresse de livraison
    document.getElementById("adresseLivraison").value = donneesCAdresseL.adresseLivraison;
    document.getElementById("villeLivraison").value = donneesCAdresseL.villeLivraison;
    // Sélectionne la province de livraison correspondante dans la liste déroulante
    var provinceLivraisonSelect = document.getElementById("provinceLivraison");
    for (var j = 0; j < provinceLivraisonSelect.options.length; j++) {
        if (provinceLivraisonSelect.options[j].value === donneesCAdresseL.provinceLivraison) {
            provinceLivraisonSelect.selectedIndex = j;
            break;
        }
    }
    document.getElementById("paysLivraison").value = donneesCAdresseL.paysLivraison;
    document.getElementById("codePostalLivraison").value = donneesCAdresseL.codePostalLivraison;
}
