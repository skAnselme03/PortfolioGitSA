let ftotHNormal = 0;
let ftotHTempsEtDemi = 0;
let ftotHTempsDouble = 0
let fNbHTravailleesTot = 0
let ftotHeures = [];
let fSalaireBrut = [];
let fTotSalaireBrut = 0;
let fDeductionsTots = 0;
let fSalaireNet = 0;
let fDeductionsPaie = [];
let TAUX_ASS_PERSONNELLES = 0.015;
let TAUX_COT_SYNDICALE = 0.0175;
let TAUX_ASS_EMPLOI = 0.0275;
let TAUX_RRQ = 0.0265;
let TAUX_IMP_FED = [0.1 * 450, 0.15 * 350, 0.2];
let TAUX_IMP_PROV = [0.15 * 350, 0.2 * 250, 0.25];
let objDatSaisiPaie = {};
let objResultat = {};
let objResultatC = {};
let SIGLE_DEVISE = '$'


function calculePaie() {
    let NB_TOT_HEURES_NORMAL_SEM = nbTotalNormalSemaine();

    let ftotHNormExcedentaire = 0;

    // Lire les données de tous les champs de saisi et retourner un objet des 
    // élement saisi
    objDatSaisiPaie = lireDonneesTab('tbSaisie');

    // Valider les élement saisi et retourener une liste que chaque élément est bien valide
    let bSaisiValide = validerNombre(objDatSaisiPaie);

    // Label d'affichage
    objResultat = lireDonneesTab('sResultat');
    objResultatC = lireDonneesTab('sResultatC');
    // Nom des ids des élements label
    let idElementssRes = tbIdGetByName('sResultat');
    let idElementssResC = tbIdGetByName('sResultatC');

    let fTauxSaisie = parseFloat(listeValElemenObj(objDatSaisiPaie)[0]).toFixed(2);

    // Si les données saisi sont valide, calcul du paie
    if (saisieValide(bSaisiValide, objResultat, objResultatC)) {
        ftotHNormal = nbHTotHeuresSem(objDatSaisiPaie);
        let ftotHWeekEnd = nbHWeekEnd(objDatSaisiPaie)

        // Traitement heures normales
        if (ftotHNormal > NB_TOT_HEURES_NORMAL_SEM) {
            // Traitement heures temps et demi
            ftotHTempsEtDemi = ftotHNormal - NB_TOT_HEURES_NORMAL_SEM;
            ftotHNormal = NB_TOT_HEURES_NORMAL_SEM;
        }
        // Traitement heures temps et demi
        if (ftotHWeekEnd[0] > 0) {
            ftotHTempsEtDemi += ftotHWeekEnd[0];
        }
        if (ftotHWeekEnd[1] > 0) {
            if (bAtravaillerTsJrsLaSemaine(objDatSaisiPaie)) {
                ftotHTempsEtDemi += ftotHWeekEnd[1];
            } else {
                ftotHTempsDouble = ftotHWeekEnd[1];
            }
        }
        // liste des heures en fonction du types d'heures travaillée
        ftotHeures = [ftotHNormal.toFixed(2), ftotHTempsEtDemi.toFixed(2), ftotHTempsDouble.toFixed(2)];
        // nombre d'heures totales travaillées
        /* fNbHTravailleesTot = sommeListe(ftotHeures).toFixed(2); */
        fNbHTravailleesTot = sommeListe(ftotHeures);
        // liste des salairees brut en fonction du types d'heures travaillée
        fSalaireBrut = [ftotHNormal * fTauxSaisie, ftotHTempsEtDemi * fTauxSaisie, ftotHTempsDouble * fTauxSaisie];
        // Salaire brut total travaillées
        fTotSalaireBrut = sommeListe(fSalaireBrut).toFixed(2);
        // Calculer les déductions
        fDeductionsPaie = calculDeduction(fTotSalaireBrut);
        // Afficcher les résultats
        afficheValTabInLabel(objResultat, ftotHeures, 'lblHeures', '');
        afficheValInLabel(objResultat, fTauxSaisie, 'TauxHoraire', SIGLE_DEVISE);
        afficheValTabInLabel(objResultat, fSalaireBrut, 'lblSalaire', SIGLE_DEVISE);
        // Afficher les déductions
        let index = 0;
        for (let i = idElementssRes.length - 6; i < idElementssRes.length; i++) {
            let test = idElementssRes[i];
            afficheValInLabel(objResultat, fDeductionsPaie[index], idElementssRes[i], SIGLE_DEVISE);
            index++;
        }
        //Afficher les resultats totaux
        afficheValInLabel(objResultatC, fNbHTravailleesTot, 'lblHeuresTotales', '');
        afficheValInLabel(objResultatC, fTotSalaireBrut, 'lblSalaireBrut', SIGLE_DEVISE);
        afficheValInLabel(objResultatC, fDeductionsTots, 'lblDeductionsTotales', SIGLE_DEVISE);
        afficheValInLabel(objResultatC, fSalaireNet, 'lblSalaireNet', SIGLE_DEVISE);
    } else {
        // Vider les resultats si <bSaisiValide> est faux
        viderValObjet(objResultat);
        viderValObjet(objResultatC);
    }
}
// Fonction retourne une liste des deductions salariale
function calculDeduction(fSalaireBrutTot) {
    let fcalcImpotsFed = 0;
    let fcalcImpotsProv = 0;
    let fBrutReajuste = 0;

    let fAssCPerso = TAUX_ASS_PERSONNELLES * fSalaireBrutTot;
    let fCotSyndic = TAUX_COT_SYNDICALE * fSalaireBrutTot;
    let fAssEmploi = TAUX_ASS_EMPLOI * fSalaireBrutTot;
    let fRRQ = TAUX_RRQ * fSalaireBrutTot;

    fBrutReajuste = fSalaireBrutTot - (fCotSyndic + fAssEmploi + fRRQ);
    //Calcul impots fédérale
    if (fSalaireBrutTot >= 450) {
        fcalcImpotsFed = TAUX_IMP_FED[0] + TAUX_IMP_FED[1] +
            TAUX_IMP_FED[2] * (fBrutReajuste - 450 - 350)
    } else {
        fcalcImpotsFed = (TAUX_IMP_FED[2] * fBrutReajuste);
    }

    //calcul impots provincial
    if (fSalaireBrutTot >= 350) {
        fcalcImpotsProv = TAUX_IMP_PROV[0] + TAUX_IMP_PROV[1] +
            TAUX_IMP_PROV[2] * (fBrutReajuste - 350 - 250)
    } else {
        fcalcImpotsProv = (TAUX_IMP_PROV[2] * fBrutReajuste);
    }

    // deductions totales
    fDeductionsTots = (fAssCPerso + fCotSyndic + fAssEmploi + fRRQ +
        fcalcImpotsFed + fcalcImpotsProv).toFixed(2);
    // salaire net
    fSalaireNet = (fSalaireBrutTot - fDeductionsTots).toFixed(2);

    return [fAssCPerso.toFixed(2), fCotSyndic.toFixed(2), fAssEmploi.toFixed(2),
        fRRQ.toFixed(2), fcalcImpotsFed.toFixed(2), fcalcImpotsProv.toFixed(2)
    ]
}
// Fonction pour savoir si un employer à travaillées tous les jours de la semaine incluant Samedi
function bAtravaillerTsJrsLaSemaine(objElements) {
    let listeTotHSemaie = listeValElemenObj(objElements)
    let compteur = 6;
    for (let i = 1; i < listeTotHSemaie.length - 1; i++) {
        if (listeTotHSemaie[i] == 0) {
            compteur--;
        }
    }
    if (compteur == 0) {
        return false;
    }
    return true
}
// Vérifier que tous les élements Saisie sont valide. Si faux vider les resultats
function saisieValide(bSaisiValide, objRes, objResC) {
    for (let i = 0; i < bSaisiValide.length; i++) {
        // Si un des élements de saisi est invalide, vider le tableau des résultats
        if (bSaisiValide[i] == false) {
            viderValObjetLabel(objRes);
            viderValObjetLabel(objResC);
            return false
        }
    }
    return true;
}
// Nombre d'heures totales normales par semaine du Lundi au Vendredi
function nbTotalNormalSemaine() {
    return 40;
}
// Retourne nombre d'heures totales de la semaine travaillées, lundi au vendredi
function nbHTotHeuresSem(objElements) {
    let fSommeNbHTotSem = 0;
    let sListeNbHeures = listeValElemenObj(objElements);

    for (let i = 1; i < sListeNbHeures.length - 2; i++) {
        fSommeNbHTotSem += parseFloat(sListeNbHeures[i]);
    }
    return fSommeNbHTotSem;
}
//retourne la liste des heures travaillées le week-end
function nbHWeekEnd(objElements) {
    let sListeNbHeures = listeValElemenObj(objElements);
    let fSamedi = parseFloat(sListeNbHeures[sListeNbHeures.length - 2])
    let fDimanche = parseFloat(sListeNbHeures[sListeNbHeures.length - 1])
    return [fSamedi, fDimanche];
}