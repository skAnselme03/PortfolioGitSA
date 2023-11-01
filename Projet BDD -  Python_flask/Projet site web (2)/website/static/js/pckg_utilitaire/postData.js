/**
 * Envoie des données au serveur
 * @param donneesForm : données du formulaire
 * @param url : chemin d'accès
 */
function post_donnees(donneesForm, url) {
    //let url = '/signup';
    let strUrl = url;
    //acceder aux ressources du serveur
    fetch(strUrl, {
        //paramètre du serveur
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        //objet de donnée, envoyer au serveur sous
        // forme de JSON wrapper en string
        body: JSON.stringify(Object.fromEntries(donneesForm)),
    });
}