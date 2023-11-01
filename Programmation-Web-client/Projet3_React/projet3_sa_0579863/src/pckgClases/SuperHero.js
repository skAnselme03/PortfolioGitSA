export class SuperHero{
  constructor(id, prenom,nom,alias,ville)
  {
     this.id = id;
     this.prenom=prenom;
     this.nom=nom;
     this.alias=alias;
     this.ville=ville;
  }

  //Méthode
  toString2(){
      return `${this.prenom} ${this.nom},
              ${this.alias}, ${this.ville}`;
  }
  toString(){
      return `ID: ${this.id}, Prénom : ${this.prenom}, Nom : ${this.nom},
              Alias : ${this.alias}, Ville : ${this.ville}`;
}
}