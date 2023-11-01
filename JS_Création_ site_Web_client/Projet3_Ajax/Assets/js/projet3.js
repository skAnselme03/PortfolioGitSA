// Projet3.js
// Par Ronald Jean-Julien et Stéphanie Anselme
// Date de remise: 
// Librairie pour projet3.htm

/*
|----------------------------------------------------------------------------------|
| (GLOBAL; AJAX) Déclaration des variables de travail globales
|----------------------------------------------------------------------------------|
*/
/* Détection automatique du dossier où est entreposé l'application serveur */
var strNomApplication = 'http://424w.cgodin.qc.ca/rjean-julien/Kit_Ajax_projet/gestion-bd-projet3.php';

/*
|--------------------------------------------------------------------------------------------------------------|
| initialiseInterface
|--------------------------------------------------------------------------------------------------------------|
*/
function initialiseInterface(binIdentificationPresente, binOperationsSuccursalesPresente, divSuccursalesPresente) {
    masqueB('divIdentification', !binIdentificationPresente);
    masqueB('divOperationsSuccursales', !binOperationsSuccursalesPresente);
    masqueB('divSuccursales', !divSuccursalesPresente);
}

/*
|--------------------------------------------------------------------------------------------------------------|
| initialiseIdentifiant
|--------------------------------------------------------------------------------------------------------------|
*/
function initialiseIdentifiant() {
    //Récupération du cookie
    let strValIdentifiant = recupereCookie('tbMatricule');
    let strValMDP = recupereCookie('tbMotDePasse');

    if (strValIdentifiant !== null && strValMDP !== null) {
        changerTypeHtml('tbMotDePasse', 'password');
        masqueB('btnNonSouvenir', false);
        masqueB('btnSouvenir', true);
        b('tbMatricule', strValIdentifiant);
        b('tbMotDePasse', strValMDP);
        console.log(b('tbMatricule') + " : " + b('tbMotDePasse'));
    } else {
        masqueB('btnNonSouvenir', true);
        masqueB('btnSouvenir', false);
    }

}

/*
|--------------------------------------------------------------------------------------------------------------|
| enregistreIdentifiant
|--------------------------------------------------------------------------------------------------------------|
*/
function enregistreIdentifiant() {
    if (identifiantValide()) {
        enregistreCookie('tbMatricule', b('tbMatricule'), 365);
        enregistreCookie('tbMotDePasse', b('tbMotDePasse'), 365);
        masqueB('btnNonSouvenir', true);
        masqueB('btnSouvenir', false);
    }
}

/*
|--------------------------------------------------------------------------------------------------------------|
| detruitIdentifiant
|--------------------------------------------------------------------------------------------------------------|
*/
function detruitIdentifiant() {
    enregistreCookie('tbMatricule', b('tbMatricule'), -365);
    enregistreCookie('tbMotDePasse', b('tbMotDePasse'), -365);
    masqueB('btnNonSouvenir', true);
    masqueB('btnSouvenir', false);
}

/*
|--------------------------------------------------------------------------------------------------------------|
| deconnexion
|--------------------------------------------------------------------------------------------------------------|
*/
function deconnexion() {
    location.reload();
}

/*
|--------------------------------------------------------------------------------------------------------------|
| effacerAjoutModification
|--------------------------------------------------------------------------------------------------------------|
*/
function effacerAjoutModification() {
    b('tbVilleAjout', '');
    b('tbBudgetAjout', '');
    b('lblMessageAjout', '');
}

/*
|--------------------------------------------------------------------------------------------------------------|
| effacerRetrait
|--------------------------------------------------------------------------------------------------------------|
*/
function effacerRetrait() {
    b('tbVilleRetrait', '');
    b('lblMessageRetrait', '');
}

/*
|--------------------------------------------------------------------------------------------------------------|
| effacerBudgetVisualisation
|--------------------------------------------------------------------------------------------------------------|
*/
function effacerBudgetVisualisation() {
    b('tbVilleBudgetVisualisation', '');
    b('lblMessageBudgetVisualisation', '');

}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_compteSuccursales
|--------------------------------------------------------------------------------------------------------------|
*/
function ajax_compteSuccursales() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] == '0') {
            masqueB('btnReinitialiser', true);
            masqueB('tabRetrait', true);
            masqueB('tabVisualisation', true);
            b('lblSuccursales', 'Nombre de succursale(s): ' + tabReponseServeur[0]);
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
        } else {
            masqueB('btnReinitialiser', false);
            masqueB('tabRetrait', false);
            masqueB('tabVisualisation', false);
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
            b('lblSuccursales', 'Nombre de succursale(s): ' + tabReponseServeur[0]);
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_compteSuccursales)
    |-----------------------------------------------------------------------------------------------------------|
    */

    let strDoneeTransmises;
    //Transmettre les données s'il sont valide
    // TODO: À ENLEVER PLUS TARD
    //if (identifiantValide()) {

    strDoneeTransmises = 'Action=Succursale-Compte&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5));

    //Envoi de la requete au serveur
    requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    //}
}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_afficheListeSuccursales
|--------------------------------------------------------------------------------------------------------------|
*/
function ajax_afficheListeSuccursales() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');


        if (tabReponseServeur[0] == 'AUCUNE') {
            b('lblSuccursales', 'Aucune succursale enregistrée...');
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
        } else {

            genererTableauDeFlux('sTableauSuccursales', ['No', 'Ville', 'Budget'], 'sEnteteTableauSuccursales',
                tabReponseServeur, 'sCorpsTableauSuccursales', 'sCelNoSuccursale', 'sCelVille', 'sCelBudget', 'lblSuccursales');

            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_afficheListeSuccursales)
    |-----------------------------------------------------------------------------------------------------------|
    */

    let strDoneeTransmises;
    //Transmettre les données s'il sont valide
    if (identifiantValide()) {

        strDoneeTransmises = 'Action=Succursale-Liste&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5));

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }
}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_tenteConnexion
|--------------------------------------------------------------------------------------------------------------|
*/
function ajax_tenteConnexion() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] == 'PASOK') {
            b('lblMessageConnexion', 'Utilisateur inconnu!');
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);

        } else {
            if (tabReponseServeur[0] === 'ERREUR;') {
                alert('Erreur dans la transmission des données!')
            } else {
                //masqué le panneau divIdentification  et Affichage du panneau divOperationsSuccursales et divSuccursales
                initialiseInterface(false, true, true);
                //Pour la trace
                b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
                b('lblNomComplet', tabReponseServeur[1] + tabReponseServeur[2]);
                b('lblReponse', tabReponseServeur[0]);
                //
                ajax_compteSuccursales();
            }
        }
    }

    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_tenteConnexion)
    |-----------------------------------------------------------------------------------------------------------|
    */
    let strDoneeTransmises;
    //Transmettre les données s'il sont valide
    if (identifiantValide()) {

        strDoneeTransmises = 'Action=Connexion&Mat=' + b('tbMatricule') + '&MDP=' + b('tbMotDePasse');

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }
}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_tenteAjoutModificationSuccursale
|--------------------------------------------------------------------------------------------------------------|
*/

function ajax_tenteAjoutModificationSuccursale() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] === 'PASOK') {
            b('lblMessageAjout', 'Succursale existante!');

            //Pour la trace
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);

        } else {
            if (tabReponseServeur[0] === 'OKM') {
                b('lblMessageAjout', 'Succursale modifié!');

                //Pour la trace
                b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
                b('lblReponse', tabReponseServeur[0]);
            }
            if (tabReponseServeur[0] === 'OKI') {
                b('lblMessageAjout', 'Succursale ajoutée!');

                //Pour la trace
                b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
                b('lblReponse', tabReponseServeur[0]);
                ajax_compteSuccursales();
            }
        }
    }
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_tenteAjoutModificationSuccursale)
    |-----------------------------------------------------------------------------------------------------------|
    */
    let strDoneeTransmises;;
    //Transmettre les données s'il sont valide
    if (villeBudgetValide()) {

        strDoneeTransmises = 'Action=Succursale-Ajout&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5)) + '&Ville=' + b('tbVilleAjout') + '&Budget=' + b('tbBudgetAjout');

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }

}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_tenteRetraitSuccursale
|--------------------------------------------------------------------------------------------------------------|
*/
function ajax_tenteRetraitSuccursale() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] === 'PASOK') {
            b('lblMessageRetrait', 'Succursale inconnue!');

            //Pour la trace
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);

        } else {
            if (tabReponseServeur[0] === 'OK') {
                b('lblMessageRetrait', 'Succursale retirée!');
                ajax_compteSuccursales();

                //Pour la trace
                b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
                b('lblReponse', tabReponseServeur[0]);
            }
            if (tabReponseServeur[0] === 'ERREUR') {
                alert('La requête est invalide!')
                    //Pour la trace
                b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
                b('lblReponse', tabReponseServeur[0]);
            }
        }
    }
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_tenteRetraitSuccursale)
    |-----------------------------------------------------------------------------------------------------------|
    */
    let strDoneeTransmises;;
    //Transmettre les données s'il sont valide
    if (villeValide('tbVilleRetrait', 'lblMessageRetrait')) {

        strDoneeTransmises = 'Action=Succursale-Retrait&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5)) + '&Ville=' + b('tbVilleRetrait');

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }
}


/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_tenteVisualisationBudgetSuccursale
|--------------------------------------------------------------------------------------------------------------|
*/

function ajax_tenteVisualisationBudgetSuccursale() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] === 'PASOK') {
            b('lblMessageBudgetVisualisation', 'Succursale inconnue!');
            b('lblBudgetVisualisation', '');

            //Pour la trace
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);

        } else {
            b('lblMessageBudgetVisualisation', 'Budget affiché');
            b('lblBudgetVisualisation', tabReponseServeur[0]);

            //Pour la trace
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
        }
    }
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_tenteVisualisationBudgetSuccursale)
    |-----------------------------------------------------------------------------------------------------------|
    */
    let strDoneeTransmises;;
    //Transmettre les données s'il sont valide
    if (villeValide('tbVilleBudgetVisualisation', 'lblMessageBudgetVisualisation')) {

        strDoneeTransmises = 'Action=Succursale-Budget&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5)) + '&Ville=' + b('tbVilleBudgetVisualisation');

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }
}

/*
|--------------------------------------------------------------------------------------------------------------|
| Module ajax_reinitialiseSuccursales
|--------------------------------------------------------------------------------------------------------------|
*/
function ajax_reinitialiseSuccursales() {
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | recupereReponseServeur
    |-----------------------------------------------------------------------------------------------------------|
    */
    function recupereReponseServeur(strVerdict) {
        //* Extraction des données reçues *//
        let tabReponseServeur = strVerdict.split(';');

        if (tabReponseServeur[0] === 'OK') {
            effacerRetrait();
            effacerAjoutModification();
            effacerBudgetVisualisation();
            b('lblRequete', strNomApplication + '?' + strDoneeTransmises);
            b('lblReponse', tabReponseServeur[0]);
            ajax_compteSuccursales();
        }
    }
    /*
    |-----------------------------------------------------------------------------------------------------------|
    | Module directeur (ajax_reinitialiseSuccursales)
    |-----------------------------------------------------------------------------------------------------------|
    */

    let strDoneeTransmises;;
    //Transmettre les données s'il sont valide
    if (window.confirm('Cliquer sur OK pour confirmer la suppression de toutes les succursales enregistrées') == true) {

        strDoneeTransmises = 'Action=Succursale-Suppression&Aut=' + b('tbMatricule') + strExtraireSousChaine(b('tbMotDePasse'), (b('tbMotDePasse').length - 5));

        //Envoi de la requete au serveur
        requeteServeur(strNomApplication, strDoneeTransmises, recupereReponseServeur, true);
    }
}