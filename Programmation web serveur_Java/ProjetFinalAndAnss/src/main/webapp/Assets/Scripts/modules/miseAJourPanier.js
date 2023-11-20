function mettreAJourPanier() {
    // Effectuer une requête AJAX vers la servlet pour récupérer les données du panier
    var xhr = new XMLHttpRequest();
    xhr.open('GET', '${pageContext.request.contextPath}/panier', true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            var data = JSON.parse(xhr.responseText);
            // Mettre à jour la table du panier avec les données reçues du serveur
            var table = document.getElementById('tablePanier');
            table.innerHTML = ''; // Effacer le contenu actuel de la table
            
            // Parcourir les données et ajouter les lignes à la table
            for (var i = 0; i < data.length; i++) {
                var row = table.insertRow(table.rows.length);
                var cell1 = row.insertCell(0);
                var cell2 = row.insertCell(1);
                var cell3 = row.insertCell(2);
                
                cell1.innerHTML = data[i].nomProduit;
                cell2.innerHTML = data[i].prixTotal + '$';
                cell3.innerHTML = '<form action="${pageContext.request.contextPath}/panier" method="post">' +
                    '<input type="hidden" name="action" value="MODIFIER" />' +
                    '<input type="hidden" name="produitId" value="' + data[i].idProduit + '" />' +
                    '<input type="number" name="produitQuantite" value="' + data[i].quantite + '" />' +
                    '<button type="submit">Modifier</button>' +
                    '</form>';
            }
        }
    };
    xhr.send();
}