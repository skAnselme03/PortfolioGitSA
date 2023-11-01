/*import * as postData from "../js/pckg_utilitaire/postData";*/

$(document).ready(() => {
    // $('input, select,button[name="enregistrer"],' +
    //     'button[name="supprimer"]').prop('disabled', true);
    $(".detail-compte, .adresse, .modif-password,.profil-photo, .modify-profil").slideToggle();

    $("#masqueInfos").click(function(e) {
        e.preventDefault();
        $(".detail-compte, .adresse, .modif-password,.profil-photo, .modify-profil").slideToggle();
    });

    $('#modifierProfil').click(function(e) {
        e.preventDefault();
        /*Enabled input*/
        $('input, select,button[name="enregistrer"], ' +
            'button[name="supprimer"]').prop('disabled', false);
    });


    $('button[name="enregistrer"]').click(function(e) {
        e.preventDefault();
        if ($('#ancienPassword').val() !== $('#nouveauPassword').val() &&
            $('#nouveauPassword').val() === $('#confirmerNewPassword').val()) {
            let donnees_formulaire = $('#compteForm').serializeArray();
            let url = "{{url_for('modif_profil', username=g.user.username)}}";
            // Désactiver les champs et les boutons
            // $('input, select, button[name="enregistrer"], ' +
            //     'button[name="supprimer"]').prop('disabled', true);
            // Envoyer la requête POST au serveur pour la modification du profil
            post_modifier(donnees_formulaire, url);
        } else {
            if ($('#ancienPassword').val() === '' ||
                ($('#nouveauPassword').val() === '' && $('#confirmerNewPassword').val() === '') ||
                ($('#nouveauPassword').val() !== $('#confirmerNewPassword').val())) {
                let donnees_formulaire = $('#compteForm').serializeArray();
                let url = "{{url_for('modif_profil', username=g.user.username)}}";
                // Désactiver les champs et les boutons
                // $('input, select, button[name="enregistrer"], ' +
                //     'button[name="supprimer"]').prop('disabled', true);
                // Envoyer la requête POST au serveur pour la modification du profil
                post_modifier(donnees_formulaire, url);
            } else {

                alert('Le nouveau mot de passe est incorrect ou est le même que l\'ancien.' +
                    '\nVeuillez recommencer!');
                $('#ancienPassword').val('');
                $('#nouveauPassword').val('');
                $('#confirmerNewPassword').val('');
            }
        }
    });
});

/**
 * Envoie des données au serveur
 * @param donneesForm : données du formulaire
 * @param url : chemin d'accès
 */
function post_modifier(donneesForm, url) {
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
        })
        .then(response => {
            if (!response.ok) {// si la réponse n'est pas "ok" on lance une erreur
                throw new Error('Erreur lors de la mise à jour du profil.');
            }
            // on retourne les données de la réponse en JSON
            return response.json();
        })
        .then(data => {
            // on affiche les données de la réponse dans la console
            console.log(data);
        })
        .catch(error => {
            // si une erreur se produit, on la récupère et on affiche un message d'erreur
            console.error(error);
            alert('Erreur lors de la mise à jour du profil.');
        });
}