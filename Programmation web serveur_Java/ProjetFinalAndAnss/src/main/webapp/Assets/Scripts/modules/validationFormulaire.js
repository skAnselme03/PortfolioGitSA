/**
 * @brief Validation d'un mailFormat
 * @param {string} emailId : l'email Id
 * @returns vrai ou faux si l'email est valider
 */
export function ValidationEmail(emailId) {
    // Récupérer l'élément d'entrée d'e-mail en utilisant son ID
    let email = document.getElementById(emailId);

    // Expression régulière pour vérifier le format d'une adresse e-mail
    let mailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

    // Vérifier si la valeur de l'entrée d'e-mail correspond au format attendu
    if (email.value.match(mailFormat)) {
        // L'adresse e-mail est valide, renvoyer true pour permettre la soumission du formulaire
        return true;
    } else {
        // L'adresse e-mail est invalide, afficher un message d'alert
        alert("Vous avez rentrez un email invalid!");
        // Donner le focus à l'entrée d'e-mail pour permettre à l'utilisateur de corriger
        emailInput.focus();

        // Renvoyer false pour empêcher la soumission du formulaire
        return false;
    }
}
/**
 * 
 * @brief Validation de la date de naissance est >=16ans
 * @param {string} dateNaissId : l'id de la date de naissance
 * @returns vrai ou faux si la date de naissance sont valident
 */
export function validationBirthday(dateNaissId) {
    // Récupérer l'élément d'entrée de la date de naissance en utilisant son ID
    let dateNaiss = document.getElementById(dateNaissId);
    let dateNaissVal = dateNaiss.value;

    // Créer un objet Date à partir de la valeur saisie
    let dateNaissance = new Date(dateNaissVal);

    // Obtenir la date courante
    let dateCourante = new Date();

    // Calculer la différence en années entre la date courante et la date de naissance
    let differenceAge = dateCourante.getFullYear() - dateNaissance.getFullYear();

    // Vérifier si l'anniversaire n'est pas encore passé cette année
    if (dateCourante.getMonth() < dateNaissance.getMonth() ||
        (dateCourante.getMonth() === dateNaissance.getMonth() &&
            dateCourante.getDate() < dateNaissance.getDate())
    ) {
        differenceAge--;
    }

    // Vérifier si l'âge est d'au moins 16 ans
    if (differenceAge < 16) {
        alert("Vous devez avoir au moins 16 ans pour vous inscrire.");
        dateNaiss.value = ""; // Effacer la valeur de l'input
        dateNaiss.focus(); // Donner le focus à l'input
        return false;
    }

    return true;

}
/**
 * 
 * @brief Validation d'un mailFormat et de la date de naissance
 * @returns vrai ou faux si l'email et la date de naissance sont valident
 */
export function validerCreerCompte() {
  	// Valider l'email et la date de naissance
    let isEmailValid = ValidationEmail('email');
    let isConfirmEmailValid = ValidationEmail('confirmerEmail');
    let isDateOfBirthValid = validationBirthday('dateNaiss');

  // Si l'une des adresses e-mail n'est pas valide, afficher un message d'erreur
    if (!isEmailValid || !isConfirmEmailValid) {
        alert("Veuillez entrer des adresses e-mail valides.");
        return false;
    }
    
    // Valider que l'adresse email et la confirmation sont les mêmes
    let email = document.getElementById('email').value;
    let confirmEmail = document.getElementById('confirmerEmail').value;

    if (email !== confirmEmail) {
        alert("L'adresse email et la confirmation de l'adresse email ne correspondent pas.");
        return false;
    }

    return isEmailValid && isDateOfBirthValid;
}