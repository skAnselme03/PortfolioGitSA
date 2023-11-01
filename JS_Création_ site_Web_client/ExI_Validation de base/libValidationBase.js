/* 	Nom du script: libValidationBase.js
	Auteurs: Ronald Jean-Julien
	Date: Le 11/3/2020
	But: Librairie de fonctions pour des validations de base 
    Modifiez par : Stéphanie Anselme
*/

// Retourne true si la donnée vaut null
// sinon retourne false
function estNull(donnee) {
    return (donnee == null);
}

// Retourne true si la donnée est un nombre (de type number ou de type object)
// sinon retourne false
function estUnNombre(fltDonnee) {
    return (!estNull(fltDonnee) && (typeof fltDonnee == 'number') ||
        (typeof fltDonnee == 'object' && fltDonnee instanceof Number));
}

// Retourne true si la donnée est une chaîne (de type string ou de type object)
// sinon retourne false
function estUneChaine(strDonnee) {
    return (!estNull(strDonnee) && (typeof strDonnee == 'string') ||
        (typeof strDonnee == 'object' && strDonnee instanceof String));
}

// Retourne true si la donnée est un tableau
// sinon retourne false
function estUnTableau(tabDonnee) {
    return (!estNull(tabDonnee) && (typeof tabDonnee == 'object' && tabDonnee instanceof Array));
}

// Retourne true si les deux données sont de même type
// sinon retourne false
function sontDeMemeType(donnee1, donnee2) {
    return ((!estNull(donnee1)) && (!estNull(donnee2)) && (typeof donnee1 == typeof donnee2));
}

// Retourne true si une donnée est située entre une valeur et une autre valeur
// sinon retourne false; les données doivent toutes être de même type
function estDansIntervalle(donnee, valeur1, valeur2) {
    return (sontDeMemeType(donnee, valeur1) && sontDeMemeType(valeur1, valeur2) && donnee >= valeur1 && donnee <= valeur2);
}

// Retourne true si une chaîne de caractères contient seulement un nombre
// sinon retourne false
function contientSeulementUnNombre(strDonnee) {
    return (estUneChaine(strDonnee) && !isNaN(strDonnee) && !isNaN(parseFloat(strDonnee)));
}

// Retourne true si une chaîne de caractères contient seulement un nombre entier
// sinon retourne false
function contientSeulementUnNombreEntier(strDonnee) {
    return (contientSeulementUnNombre(strDonnee) && (parseFloat(strDonnee) == parseInt(strDonnee)));
}

// Retourne true si une chaîne de caractères est vide
// sinon retourne false
function estUneChaineVide(strDonnee) {
    return (estUneChaine(strDonnee) && strDonnee == '');
}

// Retourne true si une chaîne ne contient que des caractères blancs
// sinon retourne false
function estUneChaineBlanche(strDonnee) {
    return (estUneChaine(strDonnee) && strDonnee.trim() == '');
}

// Retourne true si une chaîne de caractères contient un seul chiffre
// sinon retourne false
function estUnCaractereNumerique(strDonnee) {
    return (estUneChaine(strDonnee) && strDonnee.length == 1 && estDansIntervalle(strDonnee, '0', '9'));
}

// Retourne true si une chaîne de caractères contient une seule lettre
// sinon retourne false
function estUnCaractereAlpha(strDonnee) {
    return (estUneChaine(strDonnee) && strDonnee.length == 1 && (estDansIntervalle(strDonnee, 'A', 'Z') || estDansIntervalle(strDonnee, 'a', 'z')));
}

// Retourne true si une chaîne de caractères contient un seul chiffre ou une seule lettre
// sinon retourne false
function estUnCaractereAlphaNumerique(strDonnee) {
    return (estUnCaractereAlpha(strDonnee) || estUnCaractereNumerique(strDonnee));
}

// Retourne true si un caractère est présent parmi les choix
// sinon retourne false
function estUnCaractereValide(strCaractere, strChoixCaracteres) {
    return (estUneChaine(strCaractere) && estUneChaine(strChoixCaracteres) && strCaractere.length == 1 && strChoixCaracteres.indexOf(strCaractere) != -1);
}

// Retourne true si une donnée est dans un format valide
// sinon retourne false
// Dans le format, # représente un chiffre, @ représente une lettre
// tout autre caractère est le caractère lui-même 
function estDansUnFormatValide(strDonnee, strFormat) {
    let binValide = estUneChaine(strDonnee) && estUneChaine(strFormat) && strFormat.length == strDonnee.length;

    if (binValide)
        for (let i = 0;
            (i < strDonnee.length) && binValide; i++)
            switch (strFormat.charAt(i)) {
                case '@':
                    binValide = estUnCaractereAlpha(strDonnee.charAt(i));
                    break;

                case '#':
                    binValide = estUnCaractereNumerique(strDonnee.charAt(i));
                    break;

                default:
                    binValide = strFormat.charAt(i) == strDonnee.charAt(i);
            }

    return binValide;
}

// Retourne true si une donnée est un code postal
// sinon retourne false
function estUnCodePostal(strDonnee) {
    return (estDansUnFormatValide(strDonnee, '@#@ #@#'));
}

// Retourne true si une donnée est un numéro d'assurance sociale
// sinon retourne false
function estUnNAS(strDonnee) {
    return (estDansUnFormatValide(strDonnee, '### ### ###'));
}

// Retourne true si une donnée est un matricule
// sinon retourne false
function estUnMatricule(strDonnee) {
    return (estDansUnFormatValide(strDonnee, '#######'));
}

// Retourne true si une donnée est un code permanent
// sinon retourne false
function estUnCodePermanent(strDonnee) {
    return (estDansUnFormatValide(strDonnee, '@@@@########'));
}

// Retourne true si une donnée est un numéro de téléphone
// sinon retourne false
function estUnNoDeTelephone(strDonnee) {
    return (estDansUnFormatValide(strDonnee, '(###) ###-####') || estDansUnFormatValide(strDonnee, '###-###-####'));
}

// Retourne true si les données représentent une date valide
// sinon retourne false
function estUneDateValide(strJour, strMois, strAnnee) {
    let binValide = contientSeulementUnNombreEntier(strJour) &&
        contientSeulementUnNombreEntier(strMois) &&
        contientSeulementUnNombreEntier(strAnnee);
    if (binValide) {
        const intMois = parseInt(strMois);
        const intJour = parseInt(strJour);
        const intAnnee = parseInt(strAnnee);
        binValide = estDansIntervalle(intMois, 1, 12);
        if (binValide) {
            var intMaxJours = 31;
            switch (intMois) {
                case 4:
                case 6:
                case 9:
                case 11:
                    intMaxJours = 30;
                    break

                case 2:
                    intMaxJours = ((intAnnee % 400 == 0) || (intAnnee % 4 == 0 && intAnnee % 100 != 0)) ? 29 : 28;
            } // fin du switch

            binValide = estDansIntervalle(intJour, 1, intMaxJours);
        }
    }

    return binValide;
}

//Retourne la valeur d'un chiffre (caractère) romain
function convertirUnCaractereRomainEnDecimal(strCaractere) {
    // Objet chiffre romain
    let objChiffreRomain = {
        'I': 1,
        'V': 5,
        'X': 10,
        'L': 50,
        'C': 100,
        'D': 500,
        'M': 1000,
    }

    if (!contientSeulementUnNombreRomain(strCaractere)) {
        return -1;
    }

    for (let key in objChiffreRomain) {
        if (key == strCaractere.toUpperCase()) {
            return objChiffreRomain[key];
        }
    }

}
// Retourne true si la donnée contient seulement des caractères romains 
// sinon retourne false
function contientSeulementUnNombreRomain(strDonnee) {
    let chiffreRomain = 'M, D, C, L, X, V, I';
    // Vérifier si strDonnee est une chaine de caractère;
    if (!estUneChaine(strDonnee)) return false;

    for (let i = 0; i < strDonnee.length; i++) {
        let caractere = strDonnee.charAt(i).toUpperCase();


        // Vérifier que la chaine de caractère contient seulement des caractères alphanumérique
        if (!estUnCaractereAlpha(caractere)) {
            return false
        }

        // Vérifier que le caractere fait partie du chiffre romain
        if (!chiffreRomain.includes(caractere)) {
            return false;
        }
        if (i < strDonnee.length) {
            let caractereSuite1 = strDonnee.charAt(i + 1).toUpperCase();
            let caractereSuite2 = strDonnee.charAt(i + 2).toUpperCase();
            let caractereSuite3 = strDonnee.charAt(i + 3).toUpperCase();

            if (caractere === caractereSuite1 && caractere === caractereSuite2 && caractere === caractereSuite3) {
                return false;
            }
        }


    }
    // Si toutes les contraintes sont validés
    return true;

}

// Retourne la valeur décimale correspondante si la donnée contient seulement des caractères romains 
// sinon retourne la chaine vide
function convertirUnNombreRomainEnDecimal(strDonnee) {
    let resultats = 0;
    // Objet chiffre romain
    let objChiffreRomain = {
        'I': 1,
        'V': 5,
        'X': 10,
        'L': 50,
        'C': 100,
        'D': 500,
        'M': 1000,
    }

    if (!contientSeulementUnNombreRomain(strDonnee)) {
        return -1;
    }

    for (let i = 0; i < strDonnee.length; i++) {
        resultats += convertirUnCaractereRomainEnDecimal(strDonnee[i]);
    }


    return resultats;
}



function main() {
    console.log(contientSeulementUnNombreRomain(439));
    console.log(contientSeulementUnNombreRomain(true));
    console.log(contientSeulementUnNombreRomain('439'));
    console.log(contientSeulementUnNombreRomain('Z$5'));
    console.log(contientSeulementUnNombreRomain('   '));
    console.log(contientSeulementUnNombreRomain(''));
    console.log(contientSeulementUnNombreRomain('MvI'));
    console.log(contientSeulementUnNombreRomain('Bc9'));
    console.log(contientSeulementUnNombreRomain('VIII'));
    console.log(contientSeulementUnNombreRomain('VIIII'));
    console.log(contientSeulementUnNombreRomain('DXXXXIII'));
    console.log(contientSeulementUnNombreRomain('Beau'));
    console.log('----');

    console.log(convertirUnNombreRomainEnDecimal('I'));
    console.log(convertirUnNombreRomainEnDecimal('V'));
    console.log(convertirUnNombreRomainEnDecimal('X'));
    console.log(convertirUnNombreRomainEnDecimal('L'));
    console.log(convertirUnNombreRomainEnDecimal('C'));
    console.log(convertirUnNombreRomainEnDecimal('D'));
    console.log(convertirUnNombreRomainEnDecimal('M'));
    console.log('----');

    console.log(convertirUnNombreRomainEnDecimal('XV'));
    console.log(convertirUnNombreRomainEnDecimal('MDCCCLXXVI'));
    console.log(convertirUnNombreRomainEnDecimal('XLVI'));

}