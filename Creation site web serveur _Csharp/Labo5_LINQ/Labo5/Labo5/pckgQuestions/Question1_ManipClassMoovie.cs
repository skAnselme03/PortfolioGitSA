using Labo5.pckgClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo5.pckgQuestions
{
    internal class Question1_ManipClassMoovie
    {
        public Question1_ManipClassMoovie()
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficherMessage;
            //Création d'une instance de la classe AfficherMessage 
            afficherMessage = new AfficherMessage("Manipulation de la classe Moovie", "-", false);
            afficherMessage.afficher_message();

            //Instancier la classe Moovie
            Moovie moovie = new Moovie();

            List<Moovie> listMoovies = new List<Moovie>()
            {
                new Moovie(1, "Hidden Figures",new DateTime(2017, 01, 06), "Biographical drama", 19.50 ),
                new Moovie(2, "Bleach",new DateTime(2018, 07, 20), "Action fantasy japonais", 17.50 ),
                new Moovie(3, "L'avare",new DateTime(1980, 05, 05), "Comédie française", 3.50 ),
                new Moovie(4, "Freda",new DateTime(2021, 07, 14), "Film dramatique", 18.75 ),
                new Moovie(5, "Hidden figures",new DateTime(2017, 01, 06), "Biographical drama", 15.50 ),
                new Moovie(6, "La couleur des sentiments",new DateTime(2011, 08, 10), "Film dramatique", 11.50 )
            };
            // afficher tous les films
            afficherMessage = new AfficherMessage("Afficher la liste de tous les films", "-", false);
            moovie.afficheFilms(listMoovies);


            //Afficher les films dont le prix est supérieur ou égal à un montant donné ;
            double prixFiltre = 15.0;
            string msg = $"Afficher les films dont le prix est supérieur ou égal à {prixFiltre}.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            moovie.afficheFilmsByPrix(listMoovies, prixFiltre);

            //Afficher les films par genre
            string genreFiltre = "Film dramatique";
            msg = $"Afficher les films par genre:  {genreFiltre}.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            moovie.afficheFilmsByGenre(listMoovies, genreFiltre);
            
            //Afficher le nombre de fois qu'un genre se retrouve dans la liste des films            
            msg = $"Afficher le nombre des films d’un genre donné:  {genreFiltre}.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            Console.WriteLine($"Le nombre de films du genre \'{genreFiltre}\' est : {moovie.afficheNbFilmsByGenre(listMoovies, genreFiltre)}");

            //Afficher les films par genre
            msg = $"Afficher les films triés dans un ordre décroissant " +
                $"de date de réalisation.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            moovie.afficherFilmTrier(listMoovies, "date", "Décroissant");

        }

    }
}
