/**
 *  récupérer toutes les valeurs des inputs
 *  du formulaire et les stocker dans un tableau et les
 *  envoyer au serveur
 *      source: Cours développement web - collège G. Godin
 */
function inscription(event) {

    // vérifier que les informations
    // du formulaire sont valide avant de les envoyer au serveur

    console.log(form_valide())
    if (form_valide()) {

        // récupérer le formulaire et les éléments de formulaire
        let formulaire = document.querySelector('#signup-form');
        // empêcher le formulaire de se soumettre
        // et rafraîchir la page
        event.preventDefault();

        // récupérer les valeurs des champs de formulaire
        let donneesForm = new FormData(formulaire);

        // envoyé les données au serveur
        post_inscription(donneesForm, '/signup');
    }
    else {
        return null;
    }
}

/**
 * Valider le formulaire d'inscription avant de le soumettre
 * @returns {boolean} true si le formulaire est valide, false sinon
 */
function form_valide() {
    // Récupère les champs de formulaire
    let prenom = document.getElementById('prenom');
    let nom = document.getElementById('nom');
    let username = document.getElementById('username');
    //let numeroCivique = document.getElementById('numeroCivique');
    //let rue = document.getElementById('rue');
    let ville = document.getElementById('ville');
    let codePostal = document.getElementById('codePostal');
    let province = document.getElementById('province');
    let pays = document.getElementById('pays');
    //let numeroTelephone = document.getElementById('numeroTelephone');
    let email = document.getElementById('email');
    let password = document.getElementById('password');
    let confirmpassword = document.getElementById('confirmpassword');

    // initialisation de la variable de validation pour tracer les erreurs
    let estValide = true;

    // Valide le champ Prénom
    if (prenom.value.trim() === '' ||
        !match_longeur_champ(prenom.value, 32)) {
        // ajouter champ invalid
        prenom.classList.add('invalid');
        estValide = false;
    } else {
        // enlever le champ invalid
        prenom.classList.remove('invalid');
    }

    // Valide le champ Nom
    if (nom.value.trim() === '' ||
        !match_longeur_champ(nom.value, 32)) {
        nom.classList.add('invalid');
        estValide = false;
    } else {
        nom.classList.remove('invalid');
    }

    // Valide le champ username
    let usernameRegex = /^[a-zA-Z]{6}(_?[a-zA-Z0-9]{3})$/;
    if (!usernameRegex.test(username.value)) {
        username.classList.add('invalid');
        estValide = false;
    } else {
        username.classList.remove('invalid');
    }

    // Valide le champ Code postal
    let codepostalRegex = /^[A-Za-z]\d[A-Za-z]\s\d[A-Za-z]\d$/;
    if (!codepostalRegex.test(codePostal.value)) {
        codePostal.classList.add('invalid');
        estValide = false;
    } else {
        codePostal.classList.remove('invalid');
    }

    // Valide le champ Ville
    if (ville.value.trim() === '' ||
        !match_longeur_champ(ville.value, 32)) {
        ville.classList.add('invalid');
        estValide = false;
    } else {
        ville.classList.remove('invalid');
    }

    // Valide le champ Province
    if (province.value === '') {
        province.classList.add('invalid');
        estValide = false;
    } else {
        province.classList.remove('invalid');
    }

    // Valide le champ Pays
    if (pays.value === '') {
        pays.classList.add('invalid');
        estValide = false;
    } else {
        pays.classList.remove('invalid');
    }

    // Valide le champ Courriel
    let emailPattern = /^[\w-.]+@([\w-]+\.)+[\w-]{2,}$/;
    if (!match_longeur_champ(email.value, 50) &&
        !match_patterne(emailPattern, email.value.trim())) {
        email.classList.add('invalid');
        estValide = false;
    } else {
        email.classList.remove('invalid');
    }

    // Valide le champ Mot de passe
    let mdpPattern = /^[^\+*\/\\\-=]+$/; // excepter ces caractères     
    if (!match_longeur_champ(password.value, 30) &&
        !match_patterne(mdpPattern, password.value)) {
        password.classList.add('invalid');
        estValide = false;
    } else {
        password.classList.remove('invalid');
    }

    // Valide le champ Confirmation du mot de passe
    if (confirmpassword.value.trim() === '' ||
        confirmpassword.value !== password.value) {
        confirmpassword.classList.add('invalid');
        estValide = false;
    } else {
        confirmpassword.classList.remove('invalid');
    }

    return estValide
}

/**
 * Fonction prend une expression régulière sous forme
 * de chaîne de caractères et une entrée quelconque à tester,
 * renvoie true si l'entrée correspond à l'expression régulière,
 * et false sinon.
 *
 * @param patterne - L'expression régulière à tester,
 *                      sous forme de chaîne de caractères
 * @param entree - entrée à tester
 * @returns {boolean} - true l'entrée correspond à l'expression régulière,
 *                      false sinon
 */
function match_patterne(patterne, entree) {
    // Crée une instance d'expression régulière à
    // partir de la chaîne de caractères du patterne
    let regex = new RegExp(patterne)
        // Utilise la méthode test() de l'objet RegExp
        // pour tester si la chaîne de caractères entree
        // correspond à l'expression régulière
    return regex.test(entree)
}

/**
 * récupère l'élément HTML du champ nom puis vérifie si la
 * longueur de la valeur saisie dans le champ nom est
 * supérieure à tailleChamp caractères
 * @param element élement du dom à vérifier
 * @param tailleChamp taille maximal pour le champ
 * @returns {boolean} Vrai si la taille de la valeur
 * de l'élement correspont, sinon faux
 */

function match_longeur_champ(element, tailleChamp) {
    if (element.value.length > tailleChamp) {
        alert(`Le champ ne doit pas dépasser ${tailleChamp} caractères.`);
        return false
    }

    return true

}

/**
 * Envoie des données au serveur
 * @param donneesForm : données du formulaire
 * @param url : chemin d'accès
 */
function post_inscription(donneesForm, url) {
    //let url = '/signup';
    let strUrl = url;
    //acceder aux ressources du serveur
    fetch(strUrl, {
        //paramètre du serveur
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        //objet de donnée, envoyer au serveur sous
        // forme de JSON wrapper en string
        body: JSON.stringify(Object.fromEntries(donneesForm)),
    });
}

