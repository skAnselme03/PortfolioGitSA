using Labo3.pckgClasses;
using Labo3.Questions;

namespace Labo3
{
    internal class Program
    {
        /*
         *  Équipe 1:
         *          Stéphanie Anselme
         *          Andriamamonjy Camille
         *          Pareja Romain, 
         *          Richer-Brossoit Vincent
         *          
         *   14/03/2023
         *
         */
        static void Main(string[] args)
        {
            AfficherMessage afficher;
            afficher = new AfficherMessage("LABO III", "-", true);
            afficher.afficher_message();

            /*-----------------------------------------------------------------------------------------*/
            /* Écrire dans un fichier texte, ajouter du contenu tout en écrasant le contenue existant. */
            /*-----------------------------------------------------------------------------------------*/
            Exercice01_CreerAjouter exercice01_CreerAjouter = new Exercice01_CreerAjouter();

            /*-----------------------------------------------*/
            /* Recherche présence de chaine dans un fichier. */
            /*-----------------------------------------------*/
            Exercice02_RechercheChaine exercice02_RechercheChaine = new Exercice02_RechercheChaine();

            /*-----------------------*/
            /* Lecture d'un fichier. */
            /*-----------------------*/
            Exercice03_LectureFichier exercice03_LectureFichier = new Exercice03_LectureFichier();

            /*-------------------------------------------------------------*/
            /*Afficher caractère: son code ASCII et le nombre d'occurence. */
            /*-------------------------------------------------------------*/
            Exercice04_LectureCaracteres exercice04_LectureCaracteres = new Exercice04_LectureCaracteres();

            /*-------------------------------------------------------------*/
            /*          Afficher liste pays et fetes nationale             */
            /*-------------------------------------------------------------*/
            Exercice05_AfficheListPays  exercice05_AfficheListPays = new Exercice05_AfficheListPays();






            Console.WriteLine("_____________________________________________");


        }
    }
}