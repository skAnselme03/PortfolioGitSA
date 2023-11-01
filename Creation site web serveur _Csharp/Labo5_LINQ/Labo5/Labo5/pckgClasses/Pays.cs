using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo5.pckgClasses
{
    internal class Pays
    {
        /*------------------------------------------------------------------*/
        /*-------------------- Attribut, Getter & Setter -------------------*/
        /*------------------------------------------------------------------*/
        public string Nom { get; set; }
        public string Continent { get; set; }
        public int Superficie { get; set; }

        /*--------------------------------------------------------*/
        /*--------------------- Constructeur ---------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Constructeur de la classe Pays
        /// </summary>
        /// <param name="Nom">Nom du pays</param> 
        /// <param name="Continent">Continent ou se trouve le pays</param>
        /// <param name="Superficie">Superfucie du pays</param>
        public Pays(string Nom, string Continent, int Superficie)
        {
            this.Nom = Nom;
            this.Continent = Continent;
            this.Superficie = Superficie;
        }
        public Pays() { }

        /*--------------------------------------------------------*/
        /*----------------------- Méthodes -----------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Retourne une chaîne de caractères représentant une instance de la classe.
        /// </summary>
        /// <returns>{string} Une description complète de l'objet avec son nom, continent, 
        ///                   et sa superficie.
        /// </returns>
        override public string ToString()
        {
            return $"[Pays: {Nom} - Continent: {Continent} - Superficie: {Superficie.ToString()}]";
        }

        /// <summary>
        ///     Afficher le nombre de pays selon le continent
        /// </summary>
        /// <param name="pays">liste des pays</param>
        /// <param name="continent">continent</param>
        /// <returns>{int} nombre de pays dans le continent</returns>
        public int afficherNbrPaysByContinent(List<Pays> pays, string continent)
        {
            int nbrPaysByContinent = pays.Count(pays => pays.Continent == continent);

            return nbrPaysByContinent;
        }

        /// <summary>
        ///     Afficher en ordre alphabétique les noms et les superficies des pays asiatiques ;
        /// </summary>
        /// <param name="pays"></param>
        /// <param name="continent"></param>
        public void afficherPaysParContinent(List<Pays> pays, string continent)
        {
            try
            {
                // Récupérer les pays appartenant au continent demandé, triés par ordre alphabétique

                List<(string, int)> listPaysFiltrer = getPaysParContinent(pays, continent);

                foreach (var tuple in listPaysFiltrer)
                {
                    Console.WriteLine($"{tuple.Item1} à une superficie de : {tuple.Item2}");
                }
            }
            catch (Exception e) {
                // Si une exception est levée, on affiche le message d'erreur à l'utilisateur
                Console.WriteLine(e.Message);
            }
        }

        /// <summary>
        ///     Retourne en ordre alphabétique les noms et les superficies des pays asiatiques
        /// </summary>
        /// <param name="pays">listes des pays</param>
        /// <param name="continent">le continent</param>
        /// <returns>{(string, int)} la liste de nom et de superficie des pays </returns>
        public List<(string, int)> getPaysParContinent(List<Pays> pays, string continent)
        {
            List<(string, int)> list = new List<(string, int)>();

            list = pays.Where(p => p.Continent == continent)
                       .OrderBy(p => p.Nom)
                       .Select(p => (p.Nom, p.Superficie)).ToList();
            return list;
        }

        /// <summary>
        ///     Afficher la superficie totale d'un continent
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        public void affficherSuperficieContinent(List<Pays> pays, string continent)
        {
            try
            {
                Console.WriteLine($"La surface totale du continent {continent} est de {getSuperficieContinent(pays, continent)} km²");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        /// <summary>
        ///     Calculer et afficher la surface totale d'un continent
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        /// <returns>{double} superficie totale d'un continent</returns>
        public double getSuperficieContinent(List<Pays> pays, string continent)
        {
            // vérifier que la liste et le continent n'est pas vide
            if (pays == null || continent == "")
            {
                return 0;
            }

            return pays.Where(pays => pays.Continent == continent).Sum(pays => pays.Superficie);
        }

        /// <summary>
        ///     Afficher en ordre alphabétique tous les pays d’un continent dont le nom commence par le filtre fourni en paramètre
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        /// <param name="filtre">filtre</param>
        public void affichertPaysByContinentFiltre(List<Pays> pays, string continent, string filtre)
        {
            try
            {
                List<Pays> liste = getPaysByContinentFiltrer(pays, continent, filtre);
                Console.WriteLine($"Liste des pays en {continent} dont le nom commence par {filtre} :\n");

                foreach (var lepays in liste)
                {
                    Console.WriteLine(lepays.ToString());
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }

        }

        /// <summary>
        ///    Retourne en ordre alphabétique tous les pays d’un continent dont le nom commence par 'filtre'
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        /// <param name="filtre">filtre</param>
        /// <returns>{List<Pays>} la liste des pays d'un continent filtrer</returns>
        public List<Pays> getPaysByContinentFiltrer(List<Pays> pays, string continent, string filtre)
        {
            return pays.Where(pays => pays.Continent == continent && pays.Nom.StartsWith(filtre)).OrderBy(pays => pays.Nom).ToList();
        }

        /// <summary>
        ///     Afficher la superficie totale d'un continent
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        public void affficherSuperficieContinentFiltre(List<Pays> pays, string continent, string filtre)
        {
            try
            {
                Console.WriteLine($"La surface totale des pays du continent {continent} qui commence par {filtre} est de {getSuperficieContinentFiltre(pays, continent, filtre)} km²");
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
            }
        }

        /// <summary>
        ///     Calculer et afficher la surface totale d'un continent
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        /// <returns>{double} superficie totale d'un continent</returns>
        public double getSuperficieContinentFiltre(List<Pays> pays, string continent, string filtre)
        {
            // vérifier que la liste et le continent n'est pas vide
            if (pays == null || continent == "")
            {
                return 0;
            }

            return pays.Where(pays => pays.Continent == continent && pays.Nom.StartsWith(filtre)).Sum(pays => pays.Superficie);
        }


              
        /// <summary>
        ///     Retourne le pays dont la superficie est le plus grand ou plus petit selon le continent
        /// </summary>
        /// <param name="pays">Liste des pays</param>
        /// <param name="continent">Continent dans la liste des pays</param>
        /// <param name="superficieEstPlusGrand"> vrai si la superficie est le plus grand, faux sinon</param>
        /// <returns>{strinf} nom du pays</returns>
        public string getMaxMinSuperficieByContinent(List<Pays> pays, string continent, bool superficieEstPlusGrand)
        {
            try
            {

                string paysPGSuperficie =  "";

                if (superficieEstPlusGrand)
                {
                    paysPGSuperficie = pays.Where(p => p.Continent == continent).OrderByDescending(p => p.Superficie).FirstOrDefault()?.Nom;
                }
                else
                {
                    paysPGSuperficie = pays.Where(p => p.Continent == continent).OrderByDescending(p => p.Superficie).LastOrDefault()?.Nom;
                }

                return paysPGSuperficie;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.Message);
                return null;
            }

        }

    }
}
