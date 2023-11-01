class SuperHero {
    constructor(prenom, nom, alias, ville) {
            this.prenom = prenom;
            this.nom = nom;
            this.alias = alias;
            this.ville = ville;
        }
        // Getter
    get nom() {
        return this._nom;
    }
    get prenom() {
        return this._prenom;
    }
    get alias() {
        return this._alias;
    }
    get ville() {
        return this._ville;
    }
    set nom(value) {
        return this._nom = value;
    }
    set prenom(value) {
        return this._prenom = value;
    }
    set alias(value) {
        return this._alias = value;
    }
    set ville(value) {
        return this._ville = value;
    }
    afficher() {
        return this.nom + ',' + this.prenom + ',' + this.alias + ',' + this.ville;
    }
}