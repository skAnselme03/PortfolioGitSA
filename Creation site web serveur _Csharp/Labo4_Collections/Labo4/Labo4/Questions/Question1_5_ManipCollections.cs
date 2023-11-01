using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_5_ManipCollections
    {
        public Question1_5_ManipCollections() 
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficher;
            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Faire le choix d'une collection", "-", false);
            afficher.afficher_message();


            //-----------------------------------------------------//
            //         Manipulation des collection                 //
            //-----------------------------------------------------//
            object donnees = new string[]{ "Titi", "Tesla", "Mangue", "Tomate" };
            string[] nomCollectionGeneric = { "Arraylist_Générique ()", "Hashtable_Générique ()", 
                                             "Sortedlist_Générique ()", "Stack_Générique ()", 
                                             "Queue_Générique ()" };
            string[] nomCollectionNoGeneric = { "List_NonGénérique ()", "Dictionary_NonGénérique ()",
                                                "SortedlistT_NonGénérique ()", "StackT_NonGénérique ()",
                                                "QueueT_NonGénérique ()" };            ManipCollections manipCollections = new ManipCollections();

            // Affichage des données selon les collection de type générique

            foreach (string nomCollectionG in nomCollectionGeneric)
            {
                switch (nomCollectionG)
                {
                    case "Arraylist_Générique ()":
                        manipCollections = new ManipCollections(nomCollectionG, '_', ' ');
                        manipCollections.afficherCollection(donnees);
                        break;

                   case "Hashtable_Générique ()":
                        manipCollections = new ManipCollections(nomCollectionG, '_', ' ');
                        manipCollections.afficherCollection(donnees);

                        break;

                        /*  case "Sortedlist_Générique ()":
                             manipCollections = new ManipCollections(nomCollectionG, '_', ' ');
                             manipCollections.afficherCollection(donnees);
                             break;

                         case "Stack_Générique ()":
                        manipCollections = new ManipCollections(nomCollectionG, '_', ' ');
                        manipCollections.afficherCollection(donnees);
                             break;

                         case "Queue_Générique ()":
                        manipCollections = new ManipCollections(nomCollectionG, '_', ' ');
                        manipCollections.afficherCollection(donnees);
                             break;*/
                }
            }



            // Affichage des données selon les collection de type non générique
            foreach (string nomCollectionNG in nomCollectionNoGeneric)
            {
                switch (nomCollectionNG)
                {
                    case "List_NonGénérique ()":
                         //manipCollections = new ManipCollections(nomCollectionNG);
                         //manipCollections.List_Générique(donnees);
                         break;

                    case "Dictionary_NonGénérique ()":
                        manipCollections = new ManipCollections(nomCollectionNG, '_', ' ');
                        manipCollections.afficherCollectionNnGeneric(donnees);
                        
                        break;

                        /*  case "SortedlistT_NonGénérique ()":
                              manipCollections = new ManipCollections(nomCollectionNG);
                              manipCollections.SortedlistT_Générique(donnees);
                              break;

                          case "StackT_NonGénérique ()":
                              manipCollections = new ManipCollections(nomCollectionNG);
                              manipCollections.StackT_Générique(donnees);
                              break;

                          case "QueueT_NonGénérique ()":
                              manipCollections = new ManipCollections(nomCollectionNG);
                              manipCollections.QueueT_Générique(donnees);
                              break;*/
                }
            }








        }
    }
}
