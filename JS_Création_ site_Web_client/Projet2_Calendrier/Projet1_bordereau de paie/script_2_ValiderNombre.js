// Retourne tableau de validation [vrai ou faux] pour chaque élement de l'objet des élements d'entrées saisi
// Et mise en forme des donnée à valider
function validerNombre(objDonneSaisi) {
    let bEstValide = true;
    let btabValidationSaisi = [];
    let iPartieDecimal = 0;

    for (let [key, value] of Object.entries(objDonneSaisi)) {
        // Valider que les données saisi sont des nombres
        if (!estUnNombre(value)) {
            miseEnFormeMsgById('sNonNumerique', key);
            bEstValide = false;
        } else {
            value = parseFloat(value);
            if (key == 'tbTauxHor') {
                //Validation fu taux horaire dans l'intervalle [10,18.80]
                if (!estDansIntervalle(value, 10, 18.80)) {
                    miseEnFormeMsgById('sHorsLimite', key);
                    bEstValide = false;
                } else { miseEnFormeMsgById('sValide', key); }
            } else {
                //Validation des heures travaillée dans l'intervalle [0,16]
                if (!estDansIntervalle(value, 0, 16)) {
                    miseEnFormeMsgById('sHorsLimite', key);
                    bEstValide = false;
                } else {
                    iPartieDecimal = (value % parseInt(value)).toFixed(2) * 100;
                    // Validation des nombres heures réglés au quart d’heure
                    let test = iPartieDecimal % 25;
                    if (((iPartieDecimal % 25) != 0) && (value != 0)) {
                        miseEnFormeMsgById('sNon-QuartHeure', key);

                    } else {
                        miseEnFormeMsgById('sValide', key);
                    }
                }
            }
        }
        btabValidationSaisi.push(bEstValide);
        bEstValide = true

    }
    return btabValidationSaisi;
}