/**
 * Récupérer la date qui a été transmise à la page Web
 */
function recupereDateEtConstruitCalendrier() {
    // A PROGRAMMER:
    // Cette fonction doit:// 1- Récupérer la date qui a été transmise à la page Web
    let strJour = recupereDonnee('ddlJour');
    let strMois = recupereDonnee('ddlMois');
    let intJour = parseInt(strJour);
    let intMois = parseInt(strMois);
    let strSiecle = recupereDonnee('ddlSiecle');
    let strAnnee = recupereDonnee('ddlAnnee');
    let intAnnee = parseInt(strSiecle + strAnnee);
    //2. Si aucune date n'a été transmise, charger la page selectionne-date.htm
    if (strJour === null || strMois === null || strSiecle === null || strAnnee === null) chargePageSelectionneDate();

    // 3- Afficher sur la page Web, le mois (en littéral) ainsi que l'année
    b('lblMois', moisEnLitteral(intMois, true));
    b('lblAnnee', intAnnee);
    //TODO: A REVERIFIER
    // 4- Masquer le bouton btnAnneePrecedente si le calendrier est celui de 1900
    (intAnnee === 1900) ? masqueB('btnAnneePrecedente', true): masqueB('btnAnneePrecedente', false);
    // 5- Masquer le bouton btnAnneeSuivante si le calendrier est celui de 2199
    (intAnnee === 2199) ? masqueB('btnAnneeSuivante', true): masqueB('btnAnneeSuivante', false);
    // 6- Masquer le bouton btnMoisPrecedent
    //    si le calendrier est celui de Janvier 1900
    (intAnnee === 1900 && moisEnLitteral(intMois, false) === 'janvier') ?
    masqueB('btnMoisPrecedent', true): masqueB('btnMoisPrecedent', false);
    // 7- Masquer le bouton btnMoisSuivant
    //    si le calendrier est celui de Décembre 2199
    (intAnnee === 2199 && moisEnLitteral(intMois, false) === 'decembre') ?
    masqueB('btnMoisSuivant', true): masqueB('btnMoisSuivant', false);

    // 8- Construire et afficher le calendrier du mois 
    //    dans un flux de données
    // Tableau jour de la semaine avec les jours de la semaine
    let tabJoursSemaine = tblJourSemaineEnLitteral();
    genererTabFluxCalendrier(tabJoursSemaine, intJour, intMois, intAnnee, 'lblCalendrierCourant');
}
let objSent = {
    intJour: 0,
    intMois: 0,
    intAnnee: 0
};

let intJourSent = objSent.intJour;
let intMoisSent = objSent.intMois;
let strAnneeSent = objSent.intAnnee;

/**
 * charger la page selectionne-date.htm (en lui transmettant la date courante) 
 * Ref: https://developer.mozilla.org/en-US/docs/Learn/Forms/Sending_forms_through_JavaScript
 */
function chargePageSelectionneDate() {
    //location.href = './selectionne-date.htm?name=objSent.intJour&name=objSent.intMois&name=objSent.intAnnee';
    location.href = './selectionne-date.htm?name=strMoisSent&name=strAnneeSent';
}

/**
 * Afficher un nouveau calendrier
 * @param {boolean} binMois 
 * @param {*} intSens 
 */
function nouveauCalendrier(binMois, intSens) {
    // 1- Récupérer la date qui a été transmise à la page Web
    /*let intJour = intJour;
    let intMois = intMois
    let intAnnee = intAnnee;*/
    let strJour = objSent.intJour;
    let strMois = objSent.intMois;
    let intAnnee = objSent.intAnnee;

    let strddlMois = recupereDonnee('ddlMois');

    if (strJour === 0 && strMois === 0 && intAnnee === 0) {
        strJour = recupereDonnee('ddlJour');
        strMois = recupereDonnee('ddlMois');
        strSiecle = recupereDonnee('ddlSiecle');
        strAnnee = recupereDonnee('ddlAnnee');
        intAnnee = parseInt(strSiecle + strAnnee);
    } else {
        intAnnee = objSent.intAnnee;
    }

    let intJour = parseInt(strJour);
    let intMois = parseInt(strMois);
    // Tableau jour de la semaine avec les jours de la semaine
    let tabJoursSemaine = tblJourSemaineEnLitteral();

    // Créer l'iFrame servant à envoyer les données
    let iframe = document.createElement("iframe");
    iframe.name = "myTarget";

    // Puis, attachez l'iFrame au document principal
    iframe.style.display = "none";
    document.body.appendChild(iframe);

    // Cette fonction doit :
    // le calendrier du mois suivant (binMois=true, intSens=1) ou
    if (binMois === true && intSens === 1) {
        if (intMois === 12) {
            intMois = 1;
            intAnnee++;
        } else {
            intMois += 1;
        }
        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblMois', moisEnLitteral(intMois, true));
        b('lblAnnee', intAnnee);
    }
    // le calendrier du mois précédent (binMois=true, intSens=-1) ou
    if (binMois === true && intSens === -1) {
        if (intMois === 1) {
            intMois = 12;
            intAnnee--;
        } else {
            intMois--;
        }

        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblMois', moisEnLitteral(intMois, true));
        b('lblAnnee', intAnnee);
    }

    // le calendrier de l'année suivante (binMois=false, intSens=1) ou
    if (binMois === false && intSens === 1) {

        intAnnee++;

        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblMois', moisEnLitteral(intMois, true));
        b('lblAnnee', intAnnee);
    }

    // le calendrier de l'année précédente (binMois=false, intSens=-1) ou
    if (binMois === false && intSens === -1) {

        intAnnee--;

        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblMois', moisEnLitteral(intMois, true));
        b('lblAnnee', intAnnee);
    }
    // le calendrier de la date d'aujourd'hui (intSens=0)
    if (intSens === 0) {
        intJour = recupereJourActuel();
        intMois = recupereMoisActuel();
        intAnnee = recupereAnneeActuelle();

        //afficher la date sélectionnée et l'afficher dans le label en format littéral long 
        b('lblMois', moisEnLitteral(intMois, true));
        b('lblAnnee', intAnnee);
    }

    if (intJour > nombreJoursMois(intJour, intAnnee)) {
        intJour = 1
    } else intJour = parseInt(recupereDonnee('ddlJour'));

    //Masquer les boutons mois suivantes et année suivantes si lon est au mois de Décembre 2199
    if (intMois === 12 && intAnnee === 2199) {
        masqueB('btnMoisSuivant', true);
        masqueB('btnAnneeSuivante', true);
    }
    //Masquer les boutons mois precédent et année precédent si lon est au mois de janvier 1900
    if (intMois === 1 && intAnnee === 1900) {
        masqueB('btnMoisPrecedent', true);
        masqueB('btnAnneePrecedente', true);
    }

    // Les données doivent être transmises manuellement à la même page Web
    genererTabFluxCalendrier(tabJoursSemaine, intJour, intMois, intAnnee, 'lblCalendrierCourant');
    objSent.intJour = intJour;
    objSent.intMois = intMois;
    objSent.intAnnee = intAnnee;
    envoyerDonnees(iframe, objSent, './selectionne-date.htm');
}