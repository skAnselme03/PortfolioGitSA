/**
 *  Récupérer la date du jour et l'affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee.
 * Si une date a été transmise à la page Web:
 *  Cette fonction doit récuperer la date transmis
 *  et l'affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee.
 */

function initialiseAvecDateCourante() {
    // Objets de date: 
    let objJour = document.getElementById('ddlJour');
    let objMois = document.getElementById('ddlMois');
    let objSiecle = document.getElementById('ddlSiecle');
    let objAnnee = document.getElementById('ddlAnnee');


    //récupérer la date du jour
    let intJourActuel = recupereJourActuel();
    let intMoisActuel = recupereMoisActuel();
    let intAnneActuel = recupereAnneeActuelle();

    //dateInitiale(objJour, objMois, objSiecle, objAnnee, intJourActuel, intMoisActuel, intAnneActuel);
    // TODO
    // A PROGRAMMER à la fin du projet:
    // Si aucune date n'a été transmise à la page Web:
    // Cette fonction doit récupérer la date du jour
    // et l'affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee.
    // Si une date a été transmise à la page Web:
    // Cette fonction doit récuperer la date transmise
    // et l'affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee.

    let strJour = recupereDonnee('intJour');
    let strMois = recupereDonnee('intMois');
    let strAnnee = recupereDonnee('intAnnee');

    if (strJour !== null && strMois !== null && strAnnee !== null) {

        //--- Modifier les valeurs des selecteurs ---//
        setValueSelection(objJour, null, strJour);

        setValueSelection(objMois, null, strMois);
        setValueSelection(objSiecle, null, strAnnee.substring(0, 2));

        setValueSelection(objAnnee, null, strAnnee.substring(2, 4));
        //--- Récupération des valeurs des selecteurs modifié ---//
        dateInitiale(objJour, objMois, objSiecle, objAnnee, parseInt(strJour), parseInt(strMois), parseInt(strAnnee));
        rechargerPageAffichageCalendrier(false);


    } else {
        //affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee la date du jour récuperer.
        dateInitiale(objJour, objMois, objSiecle, objAnnee, intJourActuel, intMoisActuel, intAnneActuel);
    }
}

/**
 * Aller chercher la date sélectionnée sur la page Web.
 * Ensuite, afficher la date sélectionnée en format littéral long 
 * si la date selectionner est valide. Sinon, elle doit masquer le bouton btnCalendrier 
 * et effacer la date sélectionnée
 */
function afficheBoutonCalendrierEtDateSelectionnee() {
    //aller chercher la date sélectionnée sur la page Web.
    let objJour = document.getElementById('ddlJour');
    let objMois = document.getElementById('ddlMois');
    /* let objSiecle = document.getElementById('ddlSiecle');
    let objAnnee = document.getElementById('ddlAnnee'); */
    let intAnnee = parseInt(b('ddlSiecle') + b('ddlAnnee'));

    // vérifier que la date selectionner est valide
    if (dateValide(parseInt(b('ddlJour')), parseInt(b('ddlMois')), intAnnee)) {
        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblDateSelectionnee', dateEnLitteralLong(objJour.selectedIndex + 1,
            objMois.selectedIndex + 1, intAnnee, false));

        // afficher le bouton btnCalendrier
        masqueB('btnCalendrier', false);
    } else {
        //Effacer la date sélectionnée
        b('lblDateSelectionnee', '');
        // masquer le bouton btnCalendrier
        masqueB('btnCalendrier', true);
    };
}
/**
 * Cette fonction doit recharger la page Web avec la date du jour.
 */
function actualiseCalendrier() {

    // Objets de date: 
    let objJour = document.getElementById('ddlJour');
    let objMois = document.getElementById('ddlMois');
    let objSiecle = document.getElementById('ddlSiecle');
    let objAnnee = document.getElementById('ddlAnnee');
    let intAnnee = parseInt(b('ddlSiecle') + b('ddlAnnee'));
    //affecter à ddlJour, ddlMois, ddlSiecle et ddlAnnee la date du jour récuperer.
    dateInitiale(objJour, objMois, objSiecle, objAnnee,
        recupereJourActuel(), recupereMoisActuel(), recupereAnneeActuelle());
    b('lblDateSelectionnee', dateEnLitteralLong(objJour.selectedIndex + 1,
        objMois.selectedIndex + 1, intAnnee, false));
    // afficher le bouton btnCalendrier
    masqueB('btnCalendrier', false);
}

/**
 * Transmettre les données du formulaire frmSaisie 
 * à la page Web affiche-calendrier.htm
 */
function chargePageAffichageCalendrier() {
    document.getElementById('frmSaisie').submit();
}

/**
 * recharger la page d'envoie de données
 * @param {boolean} boolYes choisir quel type d'envoie à faire
 */
function rechargerPageAffichageCalendrier(boolYes) {
    if (boolYes === true) {
        document.getElementById('frmSaisie').submit();
    } else {
        let objSaisieSent = {
            intJourS: b('ddlJour'),
            intMoisS: b('ddlMois'),
            intSiecleS: (b('ddlSiecle')),
            intAnneeS: (b('ddlAnnee'))
        };
        // Créer l'iFrame servant à envoyer les données
        let iframe = document.createElement("iframe");
        iframe.name = "myTarget";

        // Puis, attachez l'iFrame au document principal
        // window.addEventListener("load", function() {
        iframe.style.display = "none";
        document.body.appendChild(iframe);
        envoyerDonnees(iframe, objSent, './affiche-calendrier.htm');

    }
}