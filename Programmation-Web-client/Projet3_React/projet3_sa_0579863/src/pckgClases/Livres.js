export class Livres{
  constructor(id, auteur,titre,genre,editeur, date_publication)
  {
     this.id = id;
     this.auteur=auteur;
     this.titre=titre;
     this.genre=genre;
     this.editeur=editeur;
     this.date_publication=date_publication;
  }

  //Méthode
  toString2(){
      return `${this.auteur} ${this.titre},
              ${this.genre}, ${this.editeur}, ${this.date_publication}`;
  }
    toString(){
      return `ID: ${this.id}, Auteur : ${this.auteur}, Titre : ${this.titre},
              Genre : ${this.genre}, Éditeur : ${this.editeur}, Publication : ${this.date_publication}`;
    }
}