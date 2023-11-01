using Labo4.pckgClasses;
using Labo4.Questions;

namespace Labo4
{
    internal class Program
    {
        static void Main(string[] args)
        {
            AfficherMessage afficher;
            afficher = new AfficherMessage("LABO IV", "-", true);
            afficher.afficher_message();

            /*--------------------------------*/
            /*  Manipulation des collections. */
            /*--------------------------------*/
            Question1_1_Collections question1_1_Collections = new Question1_1_Collections();

            /*-------------------------------------*/
            /*  Manipulation sur un dictionnaires. */
            /*-------------------------------------*/
            Question1_2_Dictionnaire question1_2_Dictionnaire = new Question1_2_Dictionnaire();

            /*---------------------------------*/
            /*  Manipulation sur un ArrayList. */
            /*---------------------------------*/
            Question1_3_InsererInArrayList question1_3_InsererInArrayList = new Question1_3_InsererInArrayList();

            /*------------------------------------*/
            /*  Get un élement dans un ArrayList. */
            /*------------------------------------*/
            Question1_4_ManipArrayList question1_4_ManipArrayList = new Question1_4_ManipArrayList();

            /*--------------------------*/
            /*  Choix de la collection. */
            /*--------------------------*/
            Question1_5_ManipCollections question1_5_ManipCollections = new Question1_5_ManipCollections();

            /*---------------------------------*/
            /*  Manipulation sur un ArrayList. */
            /*---------------------------------*/
            //Question1_6_ChoixCollections question1_6_ChoixCollections = new Question1_6_ChoixCollections();




            Console.WriteLine("_____________________________________________");

        }
    }
}