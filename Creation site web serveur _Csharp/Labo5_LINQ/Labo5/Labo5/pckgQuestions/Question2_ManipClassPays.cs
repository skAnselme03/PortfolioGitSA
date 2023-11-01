using Labo5.pckgClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using static System.Net.Mime.MediaTypeNames;

namespace Labo5.pckgQuestions
{
    internal class Question2_ManipClassPays
    {
        public Question2_ManipClassPays() {

            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficherMessage;
            //Création d'une instance de la classe AfficherMessage 
            afficherMessage = new AfficherMessage("Manipulation de la classe Pays", "-", false);
            afficherMessage.afficher_message();

            // Chemin relatif du dossier
            string folderPath = @"..\\..\\..\\pckgDonnees";
            string nomDuFichier = "pays";
            string extension = ".txt";
            string filePath = Path.Combine(folderPath, nomDuFichier + extension);



            // instanciation d'une liste de pays
            Pays pays = new Pays();

            // liste de pays contenue dans le fichier 'pays.txt'
            List<Pays> lesPays = InitListPays(filePath);


            // Afficher le nombre de pays en Asie
            string continent =  "Asie";
            string msg = $"Afficher le nombre de pays en {continent}";
            afficherMessage  = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();

            Console.WriteLine($"Le nombre de pays en \'{continent}\' est : " +
                              $"{pays.afficherNbrPaysByContinent(lesPays, continent)}\n");

            //Afficher en ordre alphabétique les noms et les superficies des pays asiatiques
            msg = $"Afficher en ordre alphabétique les noms et les superficies des pays {continent}";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            Console.WriteLine($"Liste des Pays en \'{continent}\' :");
            pays.afficherPaysParContinent(lesPays, continent);

            //Calculer et afficher la surface totale de l’Asie
            msg = $"Calculer et afficher la surface totale de {continent}";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            pays.affficherSuperficieContinent(lesPays, continent);


            //Afficher en ordre alphabétique tous les pays d’Asie dont le nom commence par A
            string filtre = "A";
            msg = $"Afficher en ordre alphabétique tous les pays d’{continent} dont le nom commence par {filtre}";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            pays.affichertPaysByContinentFiltre(lesPays, continent, filtre);


            //Calculer et afficher la surface totale des pays d’Asie dont le nom commence par A
            msg = $"Calculer et afficher la surface totale des pays d’{continent} dont le nom commence par {filtre}";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            pays.affficherSuperficieContinentFiltre(lesPays, continent, filtre);


            //Afficher le nom du pays d’Asie ayant la plus grande superficie
            msg = $"Afficher le nom du pays d’{continent} ayant la plus grande superficie.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            Console.WriteLine($"Le pays {continent} ayant le plus grand superficie est : {pays.getMaxMinSuperficieByContinent(lesPays, continent, true)}");

            //Afficher le nom du pays d’Asie ayant la plus petite superficie
            msg = $"Afficher le nom du pays d’{continent} ayant la plus petite superficie.";
            afficherMessage = new AfficherMessage(msg, "-", false);
            afficherMessage.afficher_message();
            Console.WriteLine($"Le pays {continent} ayant le plus petite superficie est : {pays.getMaxMinSuperficieByContinent(lesPays, continent, false)}");

        }

        /// <summary>
        ///     Initialiser une liste en mémoire des pays à partir du fichier texte.
        /// </summary>
        /// <param name="filePath">Le chemin du fichier texte à lire.</param>
        /// <returns>Liste des pays lus depuis le fichier.</returns>
        public static List<Pays> InitListPays(string filePath)
        {
            // Instanciation du conteneur d'objets Pays
            var tabPays = new List<Pays>();

            // Lecture du fichier et itération sur les lignes lues
            // File.ReadAllLines crée un tableau de string
            // représentant chaque ligne
            //foreach (var line in File.ReadAllLines("pays.txt"))
            foreach (var line in File.ReadAllLines(filePath))
                {
                // Certaines lignes sont vides
                // et doivent être filtrées
                if (string.IsNullOrEmpty(line))
                    continue;

                // split construit un tableau de string à partir
                // d'une ligne découpée suivant les tabulations
                var entries = line.Split('\t');

                // Les espaces dans les superficies sont filtrés
                string tmp = null;
                foreach (char c in entries[2])
                {
                    if (c == ' ') continue;
                    tmp += c;
                }
                int.TryParse(tmp, out int superficie);

                // Ajout de l'instance dans le conteneur
                tabPays.Add(new Pays
                {
                    Nom = entries[0],
                    Continent = entries[1],
                    Superficie = superficie
                });
            }

            return tabPays;
        }
    }

}
