using System;
using System.Collections.Generic;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading.Tasks;

namespace Labo5.pckgClasses
{
    internal class Moovie
    {
        /*------------------------------------------------------------------*/
        /*-------------------- Attribut, Getter & Setter -------------------*/
        /*------------------------------------------------------------------*/
        public int Id { get; set; }
        public string Title { get; set; }
        public DateTime ReleaseDate { get; set; }
        public string Genre { get; set; }
        public double Price { get; set; }

        /*--------------------------------------------------------*/
        /*--------------------- Constructeur ---------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Constructeur de la classe moovie
        /// </summary>
        /// <param name="Id">id du film</param>
        /// <param name="title">le titre du film</param >
        /// <param name="releaseDate">date de sortie</param>
        /// <param name="genre">genre du film</param>
        /// <param name="price">le prix du film</param>
        public Moovie(int Id, string title, DateTime releaseDate, string genre, double price)
        {
            this.Id = Id;
            this.Title = title;
            this.ReleaseDate = releaseDate;
            this.Genre = genre;
            this.Price = price;
        }
        public Moovie() { }

        /*--------------------------------------------------------*/
        /*----------------------- Méthodes -----------------------*/
        /*--------------------------------------------------------*/
        
        /// <summary>
        ///     Afficher tous les film avec une requêtes LINQ
        /// </summary>
        /// <param name="list">liste de moovie reçu</param>
        public void afficheFilms(List<Moovie> films)
        {
            // Afficher tous les films
            var tousLesFilms = from film in films select film;
            int index = 0;
            foreach (var moovie in tousLesFilms)
            {
                index++;
                Console.WriteLine($"Film {index}: \n\t{moovie.ToString()}");
            }
        }

        /// <summary>
        ///     Afficher les films dont le prix est supérieur ou égal à un montant donné
        /// </summary>
        /// <param name="films">La liste des Moovie</param>
        /// <param name="filtrePrix">Filtre pour afficher les films selon le prix demandés</param>
        public void afficheFilmsByPrix(List<Moovie> films, double filtrePrix)
        {
            // Afficher tous les films selon le prix donné
            var filmFiltre = films.Where(film => film.Price >= filtrePrix);
            int index = 0;
            foreach (var film in filmFiltre)
            {
                index++;
                Console.WriteLine($"Film {index}: \n\t{film.ToString()}");
            }
        }


        /// <summary>
        ///  Afficher les films par genre
        /// </summary>
        /// <param name="films">La liste des Moovie</param>
        /// <param name="genre">Filtre le genre</param>
        public void afficheFilmsByGenre(List<Moovie> films, string genre)
        {
            // Afficher tous les films selon le genre donné
            var filmFiltre = films.Where(film => film.Genre == genre);
            int index = 0;
            foreach (var film in filmFiltre)
            {
                index++;
                Console.WriteLine($"Film {index}: \n\t{film.ToString()}");
            }
        }

        /// <summary>
        ///  Afficher le nombre des films d’un genre donné
        /// </summary>
        /// <param name="films">La liste des Moovie</param>
        /// <param name="genre">Filtre le genre</param>
        /// <returns>{int} le nombre de fois qu'un genre de films apparaît dans la liste</returns>
        public int afficheNbFilmsByGenre(List<Moovie> films, string genre)
        {
            int nbrFilmByGenre = films.Count(film => film.Genre == genre);

            return nbrFilmByGenre;
        }

        /// <summary>
        ///     Afficher les films triés dans un ordre demander
        ///     selon l'élement de trie
        /// </summary>
        /// <param name="films">La liste des Moovie</param>
        /// <param name="element">Élement pour le trie</param>
        /// <param name="ordreDeTrie">Croissant ou décroissant</param>
        public void afficherFilmTrier(List<Moovie> films, 
                                      object element, 
                                      string ordreDeTrie)
        {

            int index = 0;
            // Déterminer l'ordre de tri (ascendant ou descendant)
            bool ordreCroissant = true;

            switch (ordreTrieCrDecr(ordreDeTrie))
            {

                case 0:
                    ordreCroissant = false;
                    break;
                case 1:
                    ordreCroissant = true;
                    break;
                default:
                    Console.WriteLine("L'ordre de tri spécifié n'est pas valide.");
                    return;
            }

            // Convertir l'élément de tri en string
            string elementDeTri = element.ToString();

            // Trier les films en fonction de l'élément de tri et de l'ordre de tri
            switch (element)
            {
                case "id":
                    if (ordreCroissant)
                    {
                        films = films.OrderBy(f => f.Id).ToList();
                    }
                    else
                    {
                        films = films.OrderByDescending(f => f.Id).ToList();
                    }
                    break;
                case "titre":
                    if (ordreCroissant)
                    {
                        films = films.OrderBy(f => f.Title).ToList();
                    }
                    else
                    {
                        films = films.OrderByDescending(f => f.Title).ToList();
                    }
                    break;
                case "date":
                    if (ordreCroissant)
                    {
                        films = films.OrderBy(f => f.ReleaseDate).ToList();
                    }
                    else
                    {
                        films = films.OrderByDescending(f => f.ReleaseDate).ToList();
                    }
                    break;
                case "genre":
                    if (ordreCroissant)
                    {
                        films = films.OrderBy(f => f.Genre).ToList();
                    }
                    else
                    {
                        films = films.OrderByDescending(f => f.Genre).ToList();
                    }
                    break;
                case "prix":
                    if (ordreCroissant)
                    {
                        films = films.OrderBy(f => f.Price).ToList();
                    }
                    else
                    {
                        films = films.OrderByDescending(f => f.Price).ToList();
                    }
                    break;
                default:
                    Console.WriteLine("L'élément de tri spécifié n'est pas valide.");
                    return;
            }

            // Afficher les films triés
            Console.WriteLine($"Films triés par {element} en ordre ({ordreDeTrie}) :\n");


            // Afficher la liste triée

            foreach (var film in films)
            {
                index++;
                Console.WriteLine($"Film {index}: \n\t{film.ToString()}");
            }
        }

        /// <summary>
        ///     Retourne l'ordre de trie décroissant croissant
        /// </summary>
        /// <param name="ordreDeTrie">Ordre de trie fournis</param>
        /// <returns>{int} 0:décroissant, 1:croissant, 
        ///                2: ni l'un ni l'autre
        /// </returns>
        public int ordreTrieCrDecr(string ordreDeTrie)
        {
            int ordre = 2;

            switch (ordreDeTrie.ToLower()){
                case "decroissant":
                case "décroissant":
                case "d":
                case "decr":
                case "de":
                case "dsc":
                case "desc":
                case "descending":
                    return 0;
                case "croissant":
                case "c":
                case "cr":
                case "croi":
                case "asc":
                case "asce":
                case "ordreCroissant":
                    return 1;
            }

            return ordre;
        }

        /// <summary>
        ///     Retourne une chaîne de caractères représentant une instance de la classe.
        /// </summary>
        /// <returns>{string} Une description complète de l'objet avec son ID, titre, 
        ///                   date de sortie, genre et prix formaté en monnaie.
        /// </returns>
        override public string ToString()
        {
            return $"[{Id} - {Title} - {ReleaseDate.ToString("dd/mm/yyyy")} - {Genre} - {Price:C2}]";
        }

    }
}
