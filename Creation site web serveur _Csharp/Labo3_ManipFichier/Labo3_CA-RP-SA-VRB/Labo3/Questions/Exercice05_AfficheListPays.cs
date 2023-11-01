using Labo3.pckgClasses;
using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo3.Questions
{
    internal class Exercice05_AfficheListPays   
    {
        public Exercice05_AfficheListPays()
        {

            AfficherMessage afficher;
            afficher = new AfficherMessage(" Afficher liste pays et fetes nationale ", "-", false);
            afficher.afficher_message();

            // Chemin relatif du dossier
            string folderPath;
            string nomDuFichier;
            string extension;
            ManipFichier manipFichier = null;
            List<string> list = null;
            folderPath = @"..\\..\\..\\pckgDossiers\\Exercice05";

            // Chemin relatif du dossier
            nomDuFichier = "fetes-nationales";
            extension = ".txt";
            // Instanciation de la classe manip fichier
            manipFichier = new ManipFichier(folderPath, nomDuFichier, extension);
            Console.WriteLine("Voici la liste des pays :\n");
            manipFichier.afficerListInFic(';');

            //list des pays
            list = manipFichier.getListeInFic(';');

            Console.Write("\nChoisir un pays parmis la liste ci-haut : ");
            string reponse = Console.ReadLine();
            Console.WriteLine();

            Dictionary<string, List<string>> dictionnaire = manipFichier.getDataPutDict(";");
            DateTime date;
            string moisLiteral = "";
            int jour = 0, annee = 0;
            string celebration = "";


            // Parcours des clés du dictionnaire
            foreach (string cle in dictionnaire.Keys)
            {

                if (cle.ToLower() == reponse.ToLower())
                {
                    // Parcours de la liste de valeurs associée à la clé
                    foreach (string valeur in dictionnaire[cle])
                    {
                        string[] tabval = valeur.Split(' ');
                        //récupération de la date et le stocqué dans une variable
                        if (!DateTime.TryParseExact(tabval[0], "yyyy/MM/dd", CultureInfo.InvariantCulture, DateTimeStyles.None, out date))
                        {

                            throw new Exception("Le format de la date est invalide");
                           
                        }
                        //obtenir le mois sous forme littéral
                        moisLiteral = CultureInfo.CurrentCulture.DateTimeFormat.GetMonthName(date.Month);

                        //retournée le jour dans la date
                        //si le jour est inférieur à 10, et si c'est le cas, on concatène la chaîne de caractères "0" 
                        jour = date.Day;
                            
                        // récupère l'année
                        annee = date.Year;

                        for (int i = 1; i < tabval.Length; i++) {
                            if (tabval[i] != "")
                            {                                    
                                celebration = tabval[i].Trim(); 
                            }
                        }
                        
                    }
                    string lejour = jour.ToString();
                    if (jour < 10)
                    {
                        lejour = "0" + jour.ToString();   
                    }
                    //construire le message du retour
                    Console.WriteLine("Célébration : \"{0}\" depuis le {1} {2} de l'année {3}", celebration, lejour, moisLiteral, annee);
                    break;
                }
            }

        }
    }
}
