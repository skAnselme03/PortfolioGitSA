var app = new function() {

    this.lstSuperHeros = document.getElementById('listeSuperHeros');
    //Liste par défaut
    this.nom = ['Parker', 'Stark']
    this.prenom = ['Peter', 'Tony']
    this.alias = ['Spiderman', 'Ironman']
    this.ville = ['New York', 'Long Island']



    this.Count = function(data) {
        let compteurSH = document.getElementById('count');
        let name = 'heros';

        if (data) {
            if (data > 1) {
                name = 'héros';
            }
            compteurSH.innerHTML = data + ' ' + name;
        } else {
            compteurSH.innerHTML = 'Aucun ' + name;
        }
    };

    this.FetchAll = function() {
        let data = '';

        if (this.nom.length > 0 &&
            this.prenom.length > 0 &&
            this.alias.length > 0 &&
            this.ville.length > 0) {
            for (i = 0; i < this.nom.length; i++) {
                data += '<tr>';
                data += '<td>' + this.nom[i] + '</td>';
                data += '<td>' + this.prenom[i] + '</td>';
                data += '<td>' + this.alias[i] + '</td>';
                data += '<td>' + this.ville[i] + '</td>';
                data += '<td><button onclick="app.Edit(' + i + ')">Changer</button></td>';
                data += '<td><button onclick="app.Delete(' + i + ')">Supprimer</button></td>';
                data += '</tr>';
            }
        }

        this.Count(this.nom.length);
        return this.lstSuperHeros.innerHTML = data;
    };

    this.Add = function() {
        let nom = document.getElementById('add-nom');
        let prenom = document.getElementById('add-prenom');
        let alias = document.getElementById('add-alias');
        let ville = document.getElementById('add-ville');

        // Get the value
        let nomHero = nom.value;
        let prenomHero = prenom.value;
        let aliasHero = alias.value;
        let villeHero = ville.value;

        if (nomHero && prenomHero && aliasHero && villeHero) {
            //instanciation de la classe super hero
            let superHero = new SuperHero(nomHero.trim(), prenomHero.trim(),
                aliasHero.trim(), villeHero.trim());

            // Add the new value
            this.nom.push(superHero.nom.trim());
            this.prenom.push(superHero.prenom.trim());
            this.alias.push(superHero.alias.trim());
            this.ville.push(superHero.ville.trim());

            // Reset input value dans le formulaire
            nom.value = '';
            prenom.value = '';
            alias.value = '';
            ville.value = '';
            // Dislay the new list
            this.FetchAll();
        }
    };

    this.Edit = function(item) {

        let nom = document.getElementById('edit-nom');
        let prenom = document.getElementById('edit-prenom');
        let alias = document.getElementById('edit-alias');
        let ville = document.getElementById('edit-ville');

        // Display value in the field du deuxième formulaire
        nom.value = this.nom[item];
        prenom.value = this.prenom[item];
        alias.value = this.alias[item];
        ville.value = this.ville[item];

        // Display fields
        document.getElementById('spoiler').style.display = 'block';
        self = this;

        document.getElementById('saveEdit').onsubmit = function() {
            // Get value
            let nomHero = nom.value;
            let prenomHero = prenom.value;
            let aliasHero = alias.value;
            let villeHero = ville.value;

            if (nomHero && prenomHero && aliasHero && villeHero) {
                // Edit value
                self.nom.splice(item, 1, nomHero.trim());
                self.prenom.splice(item, 1, prenomHero.trim());
                self.alias.splice(item, 1, aliasHero.trim());
                self.ville.splice(item, 1, villeHero.trim());
                // Display the new list
                self.FetchAll();
                // Hide fields
                CloseInput();
            }
        }
    };

    this.Delete = function(item) {
        // Delete the current row
        this.nom.splice(item, 1);
        // Display the new list
        this.FetchAll();
    };

}

app.FetchAll();

function CloseInput() {
    document.getElementById('spoiler').style.display = 'none';
}