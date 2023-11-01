/**
 * Populer une liste déroulante avec des nombre entier
 * 
 * @param {string} strIdElement (String) Id de l'élement select
 * @param {int} intValListeDebut  valeur de début de l'option dans la liste
 * @param {int} intValListeFin  valeur de fin de l'option dans la liste
 * @param {boolean} estAnne  si c'est vrai on remplit la liste de l'année
 */
function populateListeDeroulante(strIdElement, intValListeDebut, intValListeFin, estAnne) {
    let selection = document.getElementById(strIdElement);

    // boucle pour populer des options dans la liste déroulante selon les bornes
    for (let i = intValListeDebut; i <= intValListeFin; i++) {
        // création de l'éiement option
        let option = document.createElement('option');
        if (i < 10 && estAnne === true) {
            // initialiser l'option
            option.value = '0' + i;
            // afficher la valeur de l'option
            option.innerHTML = '0' + i;

        } else {
            option.value = i;
            option.innerHTML = i;
        }
        // ajout de l'option dans la liste
        selection.appendChild(option);
    }
}

/**
 * Populer une liste déroulante élement de date
 * 
 * @param {string} strIdElement Id de l'élement select
 * @param {int} intValListe valeur de chaque option dans la liste
 * @param {string} elementDate l'élement de la date {jour, mois}
 * @param {boolean} binMajuscule vrai si on veut la première lettre en majuscule
 */
function poupulateListeDateLiteral(strIdElement, intValListe, elementDate, binMajuscule) {
    let selection = document.getElementById(strIdElement);
    // boucle pour populer des options dans la liste déroulante
    for (let i = 1; i <= intValListe; i++) {
        // création de l'éiement option
        let option = document.createElement('option');
        // initialiser l'option
        option.value = i;
        // afficher la valeur de l'option
        if (elementDate == 'jour') {
            option.innerHTML = jourSemaineEnLitteral(i, binMajuscule);
        }
        if (elementDate == 'mois') {
            option.innerHTML = moisEnLitteral(i, binMajuscule);
        }
        // ajout de l'option dans la liste
        selection.appendChild(option);
    }


}