// Retourne vrai, si la valeur passer en arguments est un nombre
function estUnNombre(valeur) {
    let nonValide = true;

    if (contientSeulementUnNombre(valeur) == false ||
        estUneChaineBlanche(valeur) == true) {
        return false;
    }
    return nonValide;
}

// Focntion pour vider les valeur d'un objetde type label
function viderValObjetLabel(objElements) {

    for (let [key, value] of Object.entries(objElements)) {
        afficherValDansElement(key, '');
    }
}

// Focntion pour vider les valeur d'un objetde type input
function viderValObjetInput(objElements) {

    for (let [key, value] of Object.entries(objElements)) {
        afficherVideInput(key, '');
        miseEnFormeMsgById('sCouleurFondZoneDeTexteNormal', key)
    }
}

// Retourne une liste contenant les valeurs d'un objet
function listeValElemenObj(objElements) {
    let listeValObj = [];
    for (let [key, value] of Object.entries(objElements)) {
        listeValObj.push(value);
    }
    return listeValObj;
}

// Lire les données de tous les champs d'un groupe d'élement passer en paramètre
//par le nom de ce groupe et retourner un objet de ces elements.
// Cette objet contient le nom de l'élement ainsi que sa valeur [cle, valeur]
function lireDonneesTab(nomElement) {
    //####### Faire un test si l'élément n'as pas de nom, retourener les élements par classe alors
    let lstElements = listeElements(nomElement);
    let intIndex = 0;
    let objDonnes = new Object();

    let test = lstElements[intIndex].value;
    // Tableau des stockaqnt le nom des Id
    let tNomIdElement = tbIdGetByName(nomElement);

    for (let i = 0; i < lstElements.length; i++) {
        objDonnes[tNomIdElement[i]] = lstElements[i].value;
    }

    return objDonnes
}

// Fournir l'id  selon l'élement passer en argument
function tbIdGetByName(nomElement) {
    // Recuperer tous les elements par nom selon le nom fournis en argument
    let listenomElement = listeElements(nomElement);
    let getTabIdElement = [];

    for (let i = 0; i < listenomElement.length; i++) {
        getTabIdElement.push(listenomElement[i].id);
    }
    return getTabIdElement;
}

// Retourner sous forme de liste tous elements ayant un nom ou une
function listeElements(nom) {
    if (document.getElementsByName(nom).length != 0) {
        return document.getElementsByName(nom);
    } else {
        return document.getElementsByClassName(nom);
    }
}

// retourene la somme d'une liste
function sommeListe(liste) {
    let somme = 0;
    liste.forEach(element => {
        somme += parseFloat(element);
    });
    return somme.toFixed(2);
}

// Afficher la mise en forme en fonction du type de mise en forme 
// et de l'element de la mise en forme
function miseEnFormeMsgById(strMiseEnForme, nomElement) {
    let strElement = document.getElementById(nomElement);
    strElement.className = strMiseEnForme
}


// Fonction pour afficher une valeur dans un element
function afficherValDansElement(nomElement, valElement) {
    let element = document.getElementById(nomElement)
    element.innerHTML = valElement;
}

// Fonction pour forcer une valeur dans un element
function afficherVideInput(nomInput, valInput) {
    let element = document.getElementById(nomInput)
    element.value = valInput;
}