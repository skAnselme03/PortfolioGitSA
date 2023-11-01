// Permet d'afficher une valeurs dans un label selon l'id du label
function afficheValInLabel(objElements, fvalTaux, masque, unite) {
    for (let key in objElements) {
        if (key.includes(masque)) {
            afficherValDansElement(key, fvalTaux + '' + unite);
        }
    }
}

// Permet d'afficher une valeurs d'un tableau dans un label selon l'id du label
function afficheValTabInLabel(objElements, ftabValeurs, masque, unite) {
    let indexVal = 0;
    for (let key in objElements) {
        if (key.includes(masque)) {

            afficherValDansElement(key, ftabValeurs[indexVal] + '' + unite);
            indexVal++;
        }
    }
}