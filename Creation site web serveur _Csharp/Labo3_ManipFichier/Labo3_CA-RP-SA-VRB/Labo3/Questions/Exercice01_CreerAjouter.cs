using Labo3.pckgClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.Questions
{
    internal class Exercice01_CreerAjouter
    {
     public Exercice01_CreerAjouter()
        {
            AfficherMessage afficher;
            afficher = new AfficherMessage("Créer fichier, écrire et écrase contenus", "-", false);
            afficher.afficher_message();


            // Chemin relatif du dossier
            string folderPath;
            string nomDuFichier;
            string extension;
            List<string> contenus = null;
            ManipFichier manipFichier = null;
            folderPath = @"..\\..\\..\\pckgDossiers\\Exercice01";

            Console.Write("Choisir l’une de ces deux options (creer/créer/c ou ajouter/a) : ");
            string reponse = Console.ReadLine();

            switch (reponse.ToLower())
            {
                case "créer":
                case "creer":
                case "c":                        
                    // Chemin relatif du dossier
                    nomDuFichier = "ecriture";
                    extension = ".txt";
                    contenus = new List<string>{ "Allo titi.", "Voila Gros-Minet", "Il est là le gros chien" };
                    // Instanciation de la classe manip fichier
                    manipFichier = new ManipFichier(folderPath, nomDuFichier, extension);
                    manipFichier.creerOuAjouterFichier(contenus, false);
                    manipFichier.lireFichier(true);
                    break;
                case "ajouter":
                case "a":
                    afficher = new AfficherMessage("Ajouter dans un fichier, écrire et ajouter contenus", "-", false);
                    afficher.afficher_message();
                    nomDuFichier = "ajouter";
                    extension = ".txt";
                    contenus = new List<string>{ "Allo titi.", "Voila Gros-Minet", "Il est là le gros chien", "Tom et Jerry" };
                    manipFichier = new ManipFichier(folderPath, nomDuFichier, extension);
                    manipFichier.creerOuAjouterFichier(contenus, true);
                    manipFichier.lireFichier(true);
                    break;
            }

            //-------------------------//
        }   
    }
}
