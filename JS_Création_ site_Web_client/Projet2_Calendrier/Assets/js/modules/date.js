/*
	Fichier: date.js
	Nom: Stéphanie Anselme
	Date: La date de remise
	But: Librairie de fonctions sur les dates
*/

/*
|-----------------------------------------------------------------------------|
| er: 
| Retourne er si intJour = 1 et binExposant = false                                       
| Retourne er en exposant si intJour = 1 et binExposant = true
| Retourne la chaîne vide dans tous les autres cas                              
|-----------------------------------------------------------------------------|
*/
function er(intJour, binExposant) {
    if (intJour === 1 && binExposant === true) return mettreEnExposant('er');
    if (intJour === 1 && binExposant === false) {
        return 'er';
    } else {
        return '';
    }
}

/*
|-----------------------------------------------------------------------------|
| jourSemaineEnLitteral: 
| Retourne le jour de la semaine en littéral (1=Dimanche,...,7=Samedi)        
| Retourne la première lettre en majuscule si binMajuscule vaut true          
| Retourne la chaîne vide si le numéro du jour n'est pas entre 1 et 7         
|-----------------------------------------------------------------------------|
*/
function jourSemaineEnLitteral(intJourSem, binMajuscule) {
    // intialisation de la variable de retour. Si le jour de la semaine n'est pas comprise [1,7]
    let strJourSemaine = '';

    // Vérifier la validité du paramètre intJourSem est comprise entre 1 et 7
    if (intJourSem >= 1 && intJourSem <= 7) {
        let tabJoursSemaine = [
            'dimanche',
            'lundi',
            'mardi',
            'mercredi',
            'jeudi',
            'vendredi',
            'samedi'
        ];

        // Définir la valeur du jour de la semaine en fonction du paramètre intJourSem
        strJourSemaine = tabJoursSemaine[(intJourSem - 1)];

        // Vérifier si majuscule est vrai retourner le premier caractère du jour de la semaine en majuscule
        if (binMajuscule) {
            strJourSemaine = majuscule1Lettre(strJourSemaine);
        }
    }

    // Retourner le jour de la semaine
    return strJourSemaine;
}

/*
|-----------------------------------------------------------------------------|
| moisEnLitteral: 
| Retourne le mois en littéral (1=Janvier,...,12=Décembre)                    
| Retourne la première lettre en majuscule si binMajuscule vaut true
| Retourne la chaîne vide si le numéro du mois n'est pas entre 1 et 12
|-----------------------------------------------------------------------------|
*/
function moisEnLitteral(intMois, binMajuscule) {
    // intialisation de la variable de retour. Si le mois de l'année n'est pas comprise [1,12]
    let strMois = '';

    // Vérifier la validité du paramètre intMois est comprise entre 1 et 7
    if (intMois >= 1 && intMois <= 12) {
        let tabJoursSemaine = [
            'janvier',
            'fevrier',
            'mars',
            'avril',
            'mai',
            'juin',
            'juillet',
            'août',
            'septembre',
            'octobre',
            'novembre',
            'decembre'
        ];

        // Définir la valeur du jour de la semaine en fonction du paramètre intMois
        strMois = tabJoursSemaine[(intMois - 1)];

        // Vérifier si majuscule est vrai retourner le premier caractère du jour de la semaine en majuscule
        if (binMajuscule) {
            strMois = majuscule1Lettre(strMois);
        }
    }

    // Retourner le jour de la semaine
    return strMois;
}

/*
|-----------------------------------------------------------------------------|
| bissextile: 
| Retourne true si l'année est bissextile sinon retourne false  
|-----------------------------------------------------------------------------|
*/
function bissextile(intAnnee) {
    // intialisation de la variable de retour.à faux par defaut 
    let estBissextile = false;

    // Vérification si l'année passée en paramètre est bissextile selon ces conditions
    if ((intAnnee % 400 == 0) || ((intAnnee % 4 == 0)) && (intAnnee % 100 != 0)) {
        estBissextile = true;
    }
    return estBissextile


}
/*
|-----------------------------------------------------------------------------|
| nombreJoursAnnee: 
| Retourne le nombre de jours qu'il y a dans l'année          
|-----------------------------------------------------------------------------|
*/
function nombreJoursAnnee(intAnnee) {
    return bissextile(intAnnee) ? 366 : 365;
}

/*
|-----------------------------------------------------------------------------|
| nombreJoursMois: 
| Retourne le nombre de jours dans un mois pour une année donnée       
| Retourne 0 si le mois n'est pas entre 1 et 12    
|-----------------------------------------------------------------------------|
*/
function nombreJoursMois(intMois, intAnnee) {
    let nbJours;

    // Déterminer le nombre de jours en fonction du intMois
    switch (intMois) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            nbJours = 31;
            break;
        case 4:
        case 6:
        case 9:
        case 11:
            nbJours = 30;
            break;
        case 2: // Le mois de février perment de prendre en considération si l'année est bissextile
            if (bissextile(intAnnee)) {
                nbJours = 29;
            } else {
                nbJours = 28;
            }
            break;
        default:
            nbJours = 0;
            break;
    }

    // Retourner le nombre de jours
    return nbJours;
}
/*
|-----------------------------------------------------------------------------|
| dateValide: 
| Retourne true si la date est valide
| Retourne false dans le cas contraire
|-----------------------------------------------------------------------------|
*/
function dateValide(intJour, intMois, intAnnee) {
    // Valider l'année
    if (intAnnee >= 1900 && intAnnee <= 2199) {
        // Determiner le nombre de jours dans le mois passer en paramètre
        let strNbreJourMois = nombreJoursMois(intMois, intAnnee);
        // Vérifier le mois
        if (intMois >= 1 && intMois <= 12 && intJour >= 1 && intJour <= strNbreJourMois) {
            return true;
        }
    } else false;
}


/*
|-----------------------------------------------------------------------------|
| dateEnNoSerie:
| Convertis une date en numéro de série et retourne ce numéro de série
| (Numéro de série: le numéro du jour à partir du 1er janvier 1900)  
| Retourne 0 si la date n'est pas valide
|-----------------------------------------------------------------------------|
*/
function dateEnNoSerie(intJour, intMois, intAnnee) {
    // Initialiser le numéro de série
    let numeroDeSerie = 0;
    // Vérifier la validité de la date
    if (dateValide(intJour, intMois, intAnnee)) {
        // ajouter les jours des mois précedents
        for (let i = 1; i < intMois; i++) {
            numeroDeSerie += nombreJoursMois(i, intAnnee);
        }
        // ajouter les jours de l'année précedents 
        for (let i = 1900; i < intAnnee; i++) {
            numeroDeSerie += nombreJoursAnnee(i);
        }
        // ajouter les jours du mois en cours
        numeroDeSerie += intJour;

        //retourner le numéro de serie
        return numeroDeSerie
    }
    // retourne 0 si la date n'est pas valide
    return numeroDeSerie;
}

/*
|-----------------------------------------------------------------------------|
| noSerieValide: 
| Retourne true si le numéro de série est valide
| Retourne false dans le cas contraire
|-----------------------------------------------------------------------------|
*/
function noSerieValide(intNoSerie) {
    return (intNoSerie >= 1 &&
        intNoSerie <= 109573);
}

/*
|-----------------------------------------------------------------------------|
| noSerieEnJourSemaine:
| Retourne le no du jour de la semaine (entre 1 et 7) 
| qui correspond à un numéro de série
| Retourne 0 si le numéro de série n'est pas valide
|-----------------------------------------------------------------------------|
*/
function noSerieEnJourSemaine(intNoSerie) {
    // Verifier que le numero de serie est valide
    if (noSerieValide(intNoSerie)) {
        // +1 a été ajouter pour corriger le décalage du 1er janvier 
        // correspondant au jour de la semaine n°2
        return (intNoSerie % 7 + 1);
    } else return 0;
}

/*
|-----------------------------------------------------------------------------|
| dateEnJourSemaine:
| Retourne le no du jour de la semaine (entre 1 et 7) 
| qui correspond à une date
| Retourne 0 si la date n'est pas valide
|-----------------------------------------------------------------------------|
*/
function dateEnJourSemaine(intJour, intMois, intAnnee) {
    // Verifier que le jour corresponde à la date
    if (dateValide(intJour, intMois, intAnnee)) {
        let intNoDeSerie = dateEnNoSerie(intJour, intMois, intAnnee);
        return noSerieEnJourSemaine(intNoDeSerie);
    } else return 0;
}

/*
|-----------------------------------------------------------------------------|
| noSerieEnJourDansDate:
| Retourne le no du jour dans une date
| qui correspond à un numéro de série
| Retourne 0 si le numéro de série n'est pas valide
|-----------------------------------------------------------------------------|
*/
function noSerieEnJourDansDate(intNoSerie) {
    // Vérifier que le numero de serie est valide
    if (!noSerieValide(intNoSerie)) {
        return 0;
    }
    return noSerieEnDate(intNoSerie)[0];
}
/*
|-----------------------------------------------------------------------------|
| noSerieEnMoisDansDate:
| Retourne le no du du mois dans une date
| qui correspond à un numéro de série
| Retourne 0 si le numéro de série n'est pas valide
|-----------------------------------------------------------------------------|
*/
function noSerieEnMoisDansDate(intNoSerie) {
    // Vérifier que le numero de serie est valide
    if (!noSerieValide(intNoSerie)) {
        return 0;
    }
    return noSerieEnDate(intNoSerie)[1];
}

/*
|-----------------------------------------------------------------------------|
| noSerieEnAnneeDansDate:
| Retourne l'année dans une date
| qui correspond à un numéro de série
| Retourne 0 si le numéro de série n'est pas valide
|-----------------------------------------------------------------------------|
*/
function noSerieEnAnneeDansDate(intNoSerie) {
    // Vérifier que le numero de serie est valide
    if (!noSerieValide(intNoSerie)) {
        return 0;
    }
    return noSerieEnDate(intNoSerie)[2];

}

/*
|------------------------------------------------------------------------------|
| recupereJourActuel:
| Retourne le jour actuel
|------------------------------------------------------------------------------|
*/
function recupereJourActuel() {
    return getNumDateActuelle()[0];
}

/*
|------------------------------------------------------------------------------|
| recupereMoisActuel:
| Retourne le mois actuel
|------------------------------------------------------------------------------|
*/
function recupereMoisActuel() {
    return getNumDateActuelle()[1];
}

/*
|------------------------------------------------------------------------------|
| recupereAnneeActuelle:
| Retourne l'année actuelle
|------------------------------------------------------------------------------|
*/
function recupereAnneeActuelle() {
    return getNumDateActuelle()[2];
}

/*
|-----------------------------------------------------------------------------|
| dateEnFormatCourt: 
| Retourne la date dans un format court: jj/mm/aaaa
| Par exemple: '01/06/2012'
|-----------------------------------------------------------------------------|
*/
function dateEnFormatCourt(intJour, intMois, intAnnee) {
    if (!dateValide(intJour, intMois, intAnnee)) {
        return '';
    }
    //Si le jour est plus petit que 10, celui-ci doit être précédé par un 0.
    if (intJour < 10) {
        intJour = '0' + intJour;
    }
    //Si le mois est plus petit que 10, celui-ci doit être précédé par un 0.
    if (intMois < 10) {
        intMois = '0' + intMois;
    }
    return intJour + '/' + intMois + '/' + intAnnee;
}
/*
|-----------------------------------------------------------------------------|
| dateEnLitteralCourt: 
| Retourne la date dans un format littéral court
| Si le jour est 1, er est en exposant
| Par exemple: '2 juin 2012'
| Offre la possibilité d'afficher la 1ère lettre du mois en majuscule 
|-----------------------------------------------------------------------------|
*/
function dateEnLitteralCourt(intJour, intMois, intAnnee, binMoisMajuscule) {
    // Vérifier si la date est valide
    if (!dateValide(intJour, intMois, intAnnee)) {
        return '';
    }
    // Ajouter le jour au format exposant s'il est 1
    if (intJour == 1) {
        intJour = intJour + er(intJour, true);
    }

    // Ajouter le mois sous forme de littérale 
    intMois = moisEnLitteral(intMois, binMoisMajuscule);

    // retour de la date
    return (intJour + ' ' + intMois + ' ' + intAnnee);
}

/*
|-----------------------------------------------------------------------------|
| dateEnLitteralLong: 
| Retourne la date dans un format littéral long
| Si le jour est 1, er est en exposant
| Par exemple: 'Mercredi, le 21 mars 2012'
| Offre la possibilité d'afficher la 1ère lettre du mois en majuscule 
|-----------------------------------------------------------------------------|
*/
function dateEnLitteralLong(intJour, intMois, intAnnee, binMoisMajuscule) {

    // Vérifier si la date est valide
    if (!dateValide(intJour, intMois, intAnnee)) {
        return '';
    }
    return jourSemaineEnLitteral(dateEnJourSemaine(intJour, intMois, intAnnee), true) +
        ', ' +
        dateEnLitteralCourt(intJour, intMois, intAnnee, binMoisMajuscule);
}