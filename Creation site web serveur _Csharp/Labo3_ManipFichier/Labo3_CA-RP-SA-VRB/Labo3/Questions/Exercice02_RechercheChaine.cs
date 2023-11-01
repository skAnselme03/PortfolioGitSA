using Labo3.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.Questions
{
    internal class Exercice02_RechercheChaine
    {
        public Exercice02_RechercheChaine()
        {

            AfficherMessage afficher;
            afficher = new AfficherMessage("Recherche contenus dans un fichier", "-", false);
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
            
            Dictionary<string, string> dictionnaire =  manipFichier.getDataPutHasT(" ");

            foreach (KeyValuePair<string,string> keyValue in dictionnaire)
            {
                    Console.WriteLine("Le nom : {0}\t\t et la note est {1}", keyValue.Key, keyValue.Value);
            }




            Console.Write("\nInsérer le mot à cherché : ");
            string chaine = Console.ReadLine();

            int nbOccurence = manipFichier.rechercheMotDsFichier(chaine);

            if (nbOccurence > 0)
            {
                Console.WriteLine("Ce mot est dans le fichier, le nombre d'occurence est : " + nbOccurence.ToString());
            }
            else
            {
                Console.WriteLine("Ce mot est absent dans ce fichier");
            }


        }
    }
}
