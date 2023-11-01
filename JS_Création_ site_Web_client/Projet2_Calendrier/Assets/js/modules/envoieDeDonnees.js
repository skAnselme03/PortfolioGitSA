/**
 * Envoyer les donnée à la page de selection
 * @param {*} objSent 
 * Ref: https://developer.mozilla.org/fr/docs/Learn/Forms/Sending_forms_through_JavaScript
 */

function envoyerDonnees(iframe, objSent, urlDestination) {
    // Voici la fonction réellement utilisée pour expédier les données
    // Elle prend un paramètre, qui est un objet chargé des paires clé/valeurs.
    let name,
        form = document.createElement('form'),
        node = document.createElement('input');

    // Définir ce qui se passe quand la réponse est chargée
    iframe.addEventListener('load', function() {
        console.log('Ouais ! Données envoyés.');
        //window.location.replace(urlDestination);
    });
    // Definissez ce qui se passe en cas d'erreur
    iframe.addEventListener('error', function(event) {
        alert('Oups! Quelque chose s\'est mal passé.');
    });
    form.action = urlDestination;
    form.target = iframe.name;

    for (name in objSent) {
        node.name = name;
        node.value = objSent[name].toString();
        form.appendChild(node.cloneNode());
    }

    // Pour être envoyé, le formulaire nécessite d'être attaché au document principal.
    form.style.display = 'none';
    document.body.appendChild(form);

    form.submit();

    // Une fois le formulaire envoyé, le supprimer.
    document.body.removeChild(form);
}

/**
 * Envoie des donnée de calendrier
 * @param {String} strUrlDestination 
 * @param {int} intJour 
 * @param {int} intMois 
 * @param {int} intSiecle 
 * @param {int} intAnnee 
 */
function sendData(strUrlDestination, intJour, intMois, intSiecle, intAnnee) {
    window.location.assign(strUrlDestination + '?intJour=' + intJour + '&intMois=' + intMois + '&intSiecle=' + intSiecle + '&intAnnee=' + intAnnee);
}