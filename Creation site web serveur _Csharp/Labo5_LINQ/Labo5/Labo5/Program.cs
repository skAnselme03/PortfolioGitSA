using Labo5.pckgClasses;
using Labo5.pckgQuestions;

namespace Labo5
{
    internal class Program
    {
        static void Main(string[] args)
        {

            AfficherMessage afficher;
            afficher = new AfficherMessage("LABO V", "-", true);
            afficher.afficher_message();

            /*------------------------------------*/
            /*  Manipulation de la classe Moovie. */
            /*------------------------------------*/
            Question1_ManipClassMoovie question1_ManipClassMoovie = new Question1_ManipClassMoovie();
            Console.WriteLine("\n-------------------");


            /*------------------------------------*/
            /*  Manipulation de la classe Pays.   */
            /*------------------------------------*/
            Question2_ManipClassPays question2_ManipClassPays = new Question2_ManipClassPays();

            Console.WriteLine("\n-------------------");
            Console.WriteLine("-------------------\n");
        }
    }
}