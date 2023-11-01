using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_4_ManipArrayList
    {
        public Question1_4_ManipArrayList() 
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficher;
            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Manipuler un ArrayList", "-", false);
            afficher.afficher_message();


            //-----------------------------------------------------//
            //                  Les ArrayList                      //
            //-----------------------------------------------------//
            // Création d'un nouveau ArrayList vide
            ArrayList arrayList = new ArrayList();

            // Création d'une instance de la classe ArrayList et y stocke une collection de chaînes de caractères.
            string[] tableau = { "Red", "Green", "Orange", "White", "Black" };
            object donnees = tableau;

            //crée une instance de la classe ColArrayList en passant
            //en paramètre une référence à une instance de la classe ArrayList.
            ColArrayList colArrayList = new ColArrayList(arrayList);
            
            //ajoutera chaque élément de la liste donnees à l'objet arrayList de la classe ColArrayList.
            colArrayList.ajouterElements(donnees);
            //récupérer l'élement à l'index tableau.taille - 2
            int index = tableau.Length - 2;
                        
            Console.WriteLine("La valeur de l'élement du ArrayList à l'index {0} : {1}", index, colArrayList.Element(index));
            Console.WriteLine();                                

            // afficher la liste
            colArrayList.afficherElementCollection(false);









        }
    }
}
