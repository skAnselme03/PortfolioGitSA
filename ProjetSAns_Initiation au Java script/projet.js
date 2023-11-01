'use strict';


/* window.onload = function() {
    //récupérer l'entrée dont l'id est [nom]
    let inNom = document.getElementById("nom");
    // Relier la fonction validerNom à l'évenement onclick sur l'entrée [nom]
    inNom.addEventListener("onchange", valider_nom);
    let test = inNom.value;
    /*    //récupérer l'entrée dont l'id est [Courriel]§
     let inCourriel = document.getElementById("courriel");
     // Relier la fonction valider_adresse_mail à l'évenement onclick sur l'entrée [Courriel]
     inCourriel.addEventListener("onclick", valider_adresse_mail);
     //récupérer l'entrée dont l'id est [telephone]§
     let inTelephone = document.getElementById("telephone");
     // Relier la fonction valider_telephone à l'évenement onclick sur l'entrée [telephone]
     inTelephone.addEventListener("onclick", valider_telephone);


     //récupérer la liste de message dont l'id est [message]§
     let slctMsg = document.getElementById("message");
     // Relier la fonction choisir_titre à l'évenement onclick sur la selection du liste [message]
     slctMsg.addEventListener("onchange", choisir_titre); 

} */

/*
	Fonction pour vérifier que le nom rentrer respecte bien les caractéristiques suivantes:
		1) Si le nom saisi n’est pas valide, un message d’alerte est affiché. Le focus est donné au champ Nom.
		Le nom est valide s’il contient entre 3 et 15 lettres (majuscules ou minuscules). Il peut être composé de deux mots reliés avec un tiret ou un espace.
 */

function valider_nom() {
    // Récupérer la chaine entrés dans l'entrée [Nom]
    let element = document.getElementById("nom");
    let valElement = element.value;
    let masqueNom = /^[A-Z][a-z]{3,15}|^[A-Z][a-z]{3,15}[-|" "][A-Z][a-z]{3,15}/

    // Exclure les caractères blanc en début de chaine et fin de chaine
    valElement = exclude_Blank_DFChaine(valElement);
    // Convertir 2 espaces ou plus en 1 seul espace " "
    valElement = convert_2SpaceMore_To_One(valElement);

    // Comparer le nom rentrer avec l'expression reg du nom
    let nomEstValide = masqueNom.test(valElement);

    if (nomEstValide == false) {
        alert("Le nom rentrez n'est pas valide.\nVeuillez resaisir le nom.\n- Il doit contenir entre 4 à 16 caractères,\- Il peux être en m ajuscule ou minuscule,\n- Il peut-être un nom composée,\n- Il sera sous ce format:\n [xxxx]\n[xxxx-xxxx]");
        element.focus();
        empase_labels("nom")
    }
}
/*
    2) Si l’adresse courriel n’est pas valide, un message d’alerte informe l’usager. Le focus est donné au champ Courriel. Une adresse courriel est valide: 
        si elle contient une suite d’au moins un caractère alphanumérique (une lettre, un chiffre ou le caractère _) 
        suivie obligatoirement du caractère "@", 
        suivi d’une suite d’au moins un caractère alphanumérique (une lettre, un chiffre ou le caractère _), 
        suivie obligatoirement du caractère ".", 
        suivi de 3 lettres. Comme suit : 
            xxxxxxxx@xxxxxxxx.xxx
*/
function valider_adresse_mail() {
    let courriel = document.getElementById("courriel").value;
    let masqueCourriel = /\w+@\w+\.[a-zA-Z]{2,3}/;
    let estValide = masqueCourriel.test(courriel);

    if (estValide == false) {
        alert("Attention: Adresse courriel invalide.\nIl doit avoir ce format:\n[xxxxxxxx@xxxxxxxx.xxx]");
        courriel.focus();
        empase_labels("courriel")

    }
}
/*
    3) Le numéro de téléphone doit être valide. Si ce n’est pas le cas, l’usager en est informé. Il a lors le choix de corriger son numéro en redonnant le focus à l’option ou d’envoyer le formulaire tel quel (puisque de toute façon le numéro de téléphone est facultatif).
    Un numéro de téléphone est valide s’il a la forme suivante : + indicatif national xxxxxxxxxx.
    
    Il contient obligatoirement + suivi de l’indicatif international entre 1 et 3 chiffres suivi de 10 chiffres.
        Exemple : +15141234567
*/
function valider_telephone() {
    let strNumTelephone = document.getElementById("telephone");
    let masqueTelephone = /\+[1]{1,3}\d{10}/;
    let estValide;

    if (strNumTelephone.value != "") {
        estValide = masqueTelephone.test(strNumTelephone);

        if (estValide) {
            strNumTelephone.focus();
            empase_labels("telephone")

        }
    }
}
/*
    4) Si la première ligne de la liste « Choisissez un titre » est sélectionné, cela signifie que l’utilisateur n’a pas choisi de thème à son message. Un message d’alerte est signalé. Redonner le focus.
*/
function choisir_titre_msg() {
    let selectMsg = document.getElementById("message");
    let valSelectMsg = selectMsg.value;
    let afficherTitreMsg = document.getElementById("afficheMsg");
    // Récupérer le texte afficher des options. 
    //Source: https://www.javascripttutorial.net/javascript-dom/javascript-select-box/#:~:text=The%20element%20allows%20you,value%20of%20the%20selected%20option.
    let valMsg = [].filter
        .call(selectMsg.options, option => option.selected)
        .map(option => option.text);

    if (valSelectMsg == "label") {
        alert("Veuillez selectionner un message");
        selectMsg.focus();
        empase_labels("message")

    } else {
        /*
            7) Si l’option « Autre » est sélectionnée par l’utilisateur la valeur du champs de précision sera Préciser ici » et le focus sera donné à ce champs, de cette façon :
        */
        if (valSelectMsg == "autre") {
            afficherTitreMsg.value = "";
            afficherTitreMsg.disabled = false;
            afficherTitreMsg.placeholder = "Préciser ici";
            afficherTitreMsg.focus();
            empase_labels("nom")

        } else {
            afficherTitreMsg.disabled = true;
            afficherTitreMsg.value = valMsg;
        }
    }
}
/*
    5) Si aucune note n’est cochée, le visiteur a le choix via la méthode confirm() entre valider sa réponse ou la modifier en redonnant le focus. L’absence de note n’empêche pas la soumission du formulaire.
*/
function cocher_note() {
    let listeNote = document.getElementsByName("noteSite");
    let estCocher = false;

    // Vérifier si un element est cocher
    for (let element of listeNote) {
        if (element.checked == true) {
            estCocher = true;
            break;
        }
    }
    if (estCocher == false) {
        if (confirm("Voulez-vous notés le site?")) {
            listeNote[0].focus;
            return false;
        }
    } else
        return true;
}
/*
    6) Le message saisi dans la zone de texte « Message » ne doit pas contenir les caractères spéciaux suivants : - ! # $ % ? & + ; ( ) [ ]><. De plus, Le message doit contenir un seul espace entre deux mots. Si le message n’est pas conforme, le focus devra être redonné à la zone de texte et l’utilisateur en doit être informé.
*/
function valider_messages() {
    let chaineMsg = document.getElementById("messageEnv");
    let valChaineMsg = chaineMsg.value;
    let masque = /\-|\!|\#|\$|\%|\?|\&|\+|\;|\(|\)|\[|\]|\>|\<|[ ][ ]/;
    let testMasque = masque.test(valChaineMsg);

    if (testMasque) {
        alert("Vous avez rentrer des caractères spéciaux non comforme. Veuillez corriger votre message");
        chaineMsg.focus();
        empase_labels("messageEnv");

    }
}
/*
    9) À chaque fois qu’une zone de saisie a le focus, l’emphase est mise sur le libellé de la zone comme mettre le texte en gras, augmenter la taille et changer de police. 
    Source: https://openclassrooms.com/forum/sujet/js-modifier-couleur-du-label-40017
*/
function empase_labels(nomChamp) {
    let element = document.getElementById(nomChamp);
    let estvrai = false;
    let nomElement;
    for (let i = 0; i < element.length; i++) {
        nomElement = (element[i].name)
        nomElement = nomElement.toLowerCase();
        if (nomElement = nomChamp.toLowerCase()) {
            estvrai = true
            element[i].parentNode.style.fontWeight = "bold";
        }

    }
}
/*
    10) Le formulaire ne doit être soumis que si les informations qu’il contient sont valides. La validité des informations doit se faire à l’aide des expressions régulières.
*/
function validation_form() {
    // VALIDER UNE DERNIÈRES FOIS LES CHAMPS AVANT DE SOUMETTRE
    valider_nom();
    valider_adresse_mail();
    valider_telephone();
    choisir_titre_msg();
    /*
        8) Si l’option Autre est choisie un texte doit être saisi dans le champ <Précisez ici>.
        Si le message est vide ou inchangé, une alerte invite à préciser le message en redonnant le focus.
        L’absence de texte dans ce champs empêchera la soumission du formulaire.
    */
    let options = document.getElementById("message");
    let afficherOptions = document.getElementById("afficheMsg");
    if (options.value == "autre" && afficherOptions.value == "") {
        alert("Préciser la nature de votre message");
        afficherOptions.focus()
        empase_labels("messageEnv");
    }
    valider_messages();
    cocher_note();

    return false;
}
//----------------------------
//----------------------------
//1) transformer les caractères de fin de lignes en un caractère espace " "

function transforme_EndLine_to_space(chaine) {
    let strChaine = chaine.replace(/\n/g, " ");
    return strChaine;
}
// Fonction qui retourne une chaine après avoir enlever les caracère blanc au début et à la fin de la chaine
function exclude_Blank_DFChaine(chaine) {
    let strChaine = chaine.replace(/^\s*|\s*$/g, "");
    return strChaine;
}
// Fonction qui retourne une chaine après avoir convertit 2 espaces ou plus en 1 seul espace " " 
function convert_2SpaceMore_To_One(chaine) {
    let strChaine = chaine.replace(/[ ]{2,}/g, " ");
    return strChaine;
}