// Fonction pour effectuer la requête XMLHttpRequest
function loadXMLDoc() {
    let xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            // Le fichier XML a été chargé avec succès
            afficherClients(this);
        }
    };
    xhttp.open("GET", "clients.xml", true);
    xhttp.send();
}

// Fonction pour afficher les informations du premier client
function afficherClients(xml) {
    let xmlDoc = xml.responseXML;
    let clients = xmlDoc.getElementsByTagName("client");
    let premierClient = clients[0];
    let id = premierClient.getElementsByTagName("id")[0].childNodes[0].nodeValue;
    let nom = premierClient.getElementsByTagName("nom")[0].childNodes[0].nodeValue;
    let adresse = premierClient.getElementsByTagName("adresse")[0].childNodes[0].nodeValue;


    let clientInfo = "<td>" + id + "</td>";
    clientInfo += "<td>" + nom + "</td>";
    clientInfo += "<td>" + adresse + "</td>";
    document.getElementById("infos-client").innerHTML = clientInfo;
}

// Charge le fichier clients.xml lors du chargement de la page
//window.onload = loadXMLDoc;