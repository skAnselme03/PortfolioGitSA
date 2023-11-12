/**
 * @brief Validation d'un mailFormat
 * @param {string} emailValue : l'email Id
 * @returns vrai ou faux si l'email est valider
 */
function ValidationEmail(emailValue) {

    const mailFormat = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;

    // Vérifier si la valeur de l'entrée d'e-mail correspond au format attendu
    if (!emailValue.match(mailFormat)) {
        // L'adresse e-mail est invalide, afficher un message d'alert
        alert("Vous avez rentrez un email invalid!");
        // Donner le focus à l'entrée d'e-mail pour permettre à l'utilisateur de corriger
        return false;
    }
    // L'adresse e-mail est valide, renvoyer true pour permettre la soumission du formulaire
    return true;
}
export default  ValidationEmail;
