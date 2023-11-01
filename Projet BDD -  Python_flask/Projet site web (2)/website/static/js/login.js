import * as patterneValide from "../js/pckg_utilitaire/matchPatterne";
import * as longChamp from "../js/pckg_utilitaire/matchLongChamp";
import * as postData from "../js/pckg_utilitaire/postData";


function connecter(event) {
    // vérifier que les informations
    // du formulaire sont valide avant de les envoyer au serveur

    if (form_valide()) {
        // récupérer le formulaire et les éléments de formulaire
        let formulaire = document.querySelector('#login');
        // empêcher le formulaire de se soumettre
        // et rafraîchir la page
        event.preventDefault();

        // récupérer les valeurs des champs de formulaire
        let donneesForm = new FormData(formulaire);

        // envoyé les données au serveur
        post_inscription(donneesForm, '/login');
    } else {
        return null;

    }
}

/**
 * S'assurer de la validité du formulaire de connection
 * @returns {boolean} vrai si le formulaire est valide. Faux sinon
 */
function form_valide() {
    let courriel = document.getElementById('email');
    let pd = document.getElementById('password');

    // initialisation de la variable de validation pour tracer les erreurs
    let estValide = true;

    // Valide le champ Courriel ou le champ username
    let usernameRegex = /^[a-zA-Z]{6}(_?[a-zA-Z0-9]{3})$/;
    let emailPattern = /^[\w-.]+@([\w-]+\.)+[\w-]{2,}$/;
    if ((!longChamp.match_longeur_champ(courriel.value, 50) &&
            !patterneValide.match_patterne(emailPattern, courriel.value.trim())) ||
        (!longChamp.match_longeur_champ(courriel.value, 30) &&
            !patterneValide.match_patterne(usernameRegex, courriel.value.trim()))) {
        courriel.classList.add('invalid');
        estValide = false;
    } else {
        courriel.classList.remove('invalid');
    }

    // Valide le champ Mot de passe
    let mdpPattern = /^[^\+*\/\\\-=]+$/; // excepter ces caractères
    if (!longChamp.match_longeur_champ(pd.value, 30) &&
        !patterneValide.match_patterne(mdpPattern, pd.value)) {
        pd.classList.add('invalid');
        estValide = false;
    } else {
        pd.classList.remove('invalid');
    }

    return estValide;
}