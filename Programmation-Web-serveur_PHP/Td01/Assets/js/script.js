$(document).ready(() => {
    $('#nomServeur').hide();

    $('#afficheStatistique').click(() => {
        $('#adresseMail').hide();
        $('#nomServeur').show();
    });
});