using Labo3.pckgClasses;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.Questions
{
    internal class Exercice04_LectureCaracteres
    {
        public Exercice04_LectureCaracteres()
        {

            AfficherMessage afficher;
            afficher = new AfficherMessage("Lecture caractères et afficher son code ASCII et le nombre d'occurence", "-", false);
            afficher.afficher_message();

            // Chemin relatif du dossier
            string folderPath;
            string nomDuFichier;
            string extension;
            ManipFichier manipFichier = null;
            folderPath = @"..\\..\\..\\pckgDossiers\\Exercice04";

            // Chemin relatif du dossier
            nomDuFichier = "aLire";
            extension = ".txt";
            // Instanciation de la classe manip fichier
            manipFichier = new ManipFichier(folderPath, nomDuFichier, extension);
            manipFichier.afficheCharFichier();
        }
    }
}
