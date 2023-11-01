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

    let tableBody = document.getElementById("infos-clients");

    for (let i = 0; i < clients.length; i++) {

        let id = clients[i].getElementsByTagName("id")[0].childNodes[0].nodeValue;
        let nom = clients[i].getElementsByTagName("nom")[0].childNodes[0].nodeValue;
        let adresse = clients[i].getElementsByTagName("adresse")[0].childNodes[0].nodeValue;

        //créer la ligne du tableay
        let ligneTableau = document.createElement("tr");
        ligneTableau.innerHTML = "<td><p> Id : " + id + "</p>" +
            "<p>Nom : " + nom + "</p>" +
            "<p>Adresse : " + adresse + "</p>" + "</td>";
        //inserer la ligne du tableau dans le corps du tableau
        tableBody.appendChild(ligneTableau);
    }
}

// Charge le fichier clients.xml lors du chargement de la page
//window.onload = loadXMLDoc;