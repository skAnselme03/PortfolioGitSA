/**
 * Générer un tableau xhtml de type flux de données
 * 
 * @param {string} selecteurMEFTab : (peux-être présent ou non) selecteur de mise en forme de la table
 * @param {string} tabValEntete : valeur de l'entête du tableau
 * @param {string} selecteurMEFEntete : (peux-être présent ou non) selecteur de mise en forme de l'entête
 * @param {string} tabValeurTab : valeur du corps du tableau
 * @param {string} selecteurMEFCorps : (peux-être présent ou non) selecteur de mise en forme du corps du tableau
 * @param {string} selecteurMEFCellNoS : (peux-être présent ou non) selecteur de mise en forme Pour la cellule qui contient le numéro 
 * @param {string} selecteurMEFCelV : (peux-être présent ou non) selecteur de mise en forme Pour la cellule qui contient la ville
 * @param {string} selecteurMEFB : (peux-être présent ou non) selecteur de mise en forme Pour la cellule qui contient le budget
 * @param {string} strIdRecepteur : Emplacement du tableau
 * 
 */
function genererTableauDeFlux(selecteurMEFTab,
    tabValEntete, selecteurMEFEntete,
    tabValeurTab, selecteurMEFCorps,
    selecteurMEFCellNoS,
    selecteurMEFCelV,
    selecteurMEFB,
    strIdRecepteur) {

    let strFlux;
    // création du tableau de flux
    (selecteurMEFTab !== null || selecteurMEFTab !== '') ?
    strFlux = '\n<table class=' + selecteurMEFTab + '>\n': strFlux = '\n<table>\n';

    // Création de l'entête
    (selecteurMEFEntete !== null || selecteurMEFEntete !== '') ?
    strFlux += '<thead class=' + selecteurMEFEntete + '>\n <tr>\n': strFlux += '<thead>\n<tr>\n';

    for (let valEntete of tabValEntete) {
        strFlux += '<th> ' + valEntete + '</th>\n';
    }
    strFlux += '</tr>\n</thead>\n';

    //Création du corps du tableau
    (selecteurMEFCorps !== null || selecteurMEFCorps !== '') ?
    strFlux += '<tbody class=' + selecteurMEFCorps + '>\n': strFlux += '<tbody>\n';

    if (tabValEntete[0] === 'No' && tabValEntete[1] === 'Ville' && tabValEntete[2] === 'Budget') {

        for (let i = 0; i < tabValeurTab.length - 1; i++) {
            strFlux += '<tr>\n';
            if ((selecteurMEFCellNoS !== null || selecteurMEFCellNoS !== '') &&
                (selecteurMEFCelV !== null || selecteurMEFCelV !== '') &&
                (selecteurMEFB !== null || selecteurMEFB !== '')) {
                // traitement des colonnes
                let colonnes = tabValeurTab[i].split(',');
                strFlux += '<td class=' + selecteurMEFCellNoS + '>' + (i + 1) + '</td>\n';
                strFlux += '<td class=' + selecteurMEFCelV + '>' + colonnes[0] + '</td>\n';
                strFlux += '<td class=' + selecteurMEFB + '>' + colonnes[1] + ' $</td>\n';
            } else {
                for (let j = 0; j < tabValEntete[i].length; j++) {
                    strFlux += '<td>' + tabValEntete[i][j] + '</td>\n';
                }
            }

            strFlux += '</tr>\n';

        }

    } else {
        strFlux += '<tr>\n';

        if (tabValeurTab.length == 1) {
            // si affichage d'une liggne dans un tableau
            strFlux += '<td>' + tabValEntete + '</td>\n';
        } else {
            for (let i = 0; i < tabValeurTab.length; i++) {
                for (let j = 0; j < tabValEntete[i].length; j++) {
                    strFlux += '<td>' + tabValEntete[i][j] + '</td>\n';
                }
            }
        }
        strFlux += '</tr>\n';

    }
    strFlux += '</tbody>\n</table>\n';

    //Affichage du tableau
    b(strIdRecepteur, strFlux);


}