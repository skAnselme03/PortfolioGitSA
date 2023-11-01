using Labo3.pckgClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.Questions
{
    internal class Exercice03_LectureFichier
    {
        public Exercice03_LectureFichier()
        {

            AfficherMessage afficher;
            afficher = new AfficherMessage("Lecture d'un fichier", "-", false);
            afficher.afficher_message();

            // Chemin relatif du dossier
            string folderPath;
            string nomDuFichier;
            string extension;
            ManipFichier manipFichier = null;
            folderPath = @"..\\..\\..\\pckgDossiers\\Exercice02";

            // Chemin relatif du dossier
            nomDuFichier = "aLireDeuxColonnes";
            extension = ".txt";
            // Instanciation de la classe manip fichier
            manipFichier = new ManipFichier(folderPath, nomDuFichier, extension);
            manipFichier.lireFichier(true);       
        }
    }
}
