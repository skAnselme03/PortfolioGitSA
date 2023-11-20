/**
 * Activer ou désactiver un formulaire en fonction des boutons "Modifier" et "Enregistrer".
 * @param {string} intIdModifier - L'ID du bouton "Modifier".
 * @param {string} intIdEnregistrer - L'ID du bouton "Enregistrer".
 * @param {string} intIdFormulaire - L'ID du formulaire à activer/désactiver.
 */
function activerDesactiverForm(intIdModifier, intIdEnregistrer, intIdFormulaire) {	
	let boutonModifier = document.getElementById(intIdModifier);
	let boutonEnregistrer = document.getElementById(intIdEnregistrer);
    let formulaire = document.getElementById(intIdFormulaire);
	
	if(boutonModifier != null){
		// Gestionnaire d'événement pour le bouton "Modifier"
		boutonModifier.addEventListener("click", function () {
		    // Activer tous les champs de formulaire
		    const champFormulaire  = formulaire.querySelectorAll("input, select");
		    champFormulaire.forEach((field) => {
		        field.removeAttribute("disabled");
		    });
		
		    // Activer le bouton "Enregistrer" et désactiver le bouton "Modifier"
		    boutonEnregistrer.removeAttribute("disabled");
		    boutonModifier.setAttribute("disabled", true);
		});
	}
	
	
	if(intIdEnregistrer != null){
		// Gestionnaire d'événement pour le bouton "Enregistrer" (pour le désactiver)
		boutonEnregistrer.addEventListener("click", function () {
		    // Désactiver tous les champs de formulaire
		    const champFormulaire  = formulaire.querySelectorAll("input, select");
		    champFormulaire.forEach((field) => {
		        field.setAttribute("disabled", true);
		    });
		
		    // Désactiver le bouton "Enregistrer" et activer le bouton "Modifier"
		    boutonEnregistrer.setAttribute("disabled", true);
		    boutonModifier.removeAttribute("disabled");
		});
	}
	
}