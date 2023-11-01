using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_6_ChoixCollections
    {
        public Question1_6_ChoixCollections() 
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficher;
            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Faire le choix d'une collection", "-", false);
            afficher.afficher_message();


            //-----------------------------------------------------//
            //         Manipulation des collection                 //
            //-----------------------------------------------------//
            object donnees = new string[]{ "Titi", "Tesla", "Mangue", "Tomate" };            // instanciation de la classe pour manipuler les collections            ManipCollections manipCollections = new ManipCollections();
            string typeCollection = manipCollections.MenuPrincipal();
            string nomCollection = "";
            
            Console.WriteLine();
            //selon le choix du type de collection, on choisis la collection pour afficher des données
            switch (typeCollection.ToLower())
            {
                case "collections génériques":
                    nomCollection = manipCollections.MenuCGenerique();
                    // reinstancier de la classe pour manipuler les collections
                    // avec des arguments pour afficher des données
                    manipCollections = new ManipCollections(nomCollection, typeCollection.Split(' ')[1]);
                    manipCollections.afficherCollectionGeneric(donnees);
                    break;
                case "collections non génériques":
                    nomCollection = manipCollections.MenuCNonGénérique();
                    // reinstancier de la classe pour manipuler les collections
                    // avec des arguments pour afficher des données
                    manipCollections = new ManipCollections(nomCollection, typeCollection.Split(' ')[1]);
                    manipCollections.afficherCollectionNnGeneric(donnees);
                    break;
            }

            




        }
    }
}
