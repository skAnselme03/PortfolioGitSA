using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_3_InsererInArrayList
    {
        public Question1_3_InsererInArrayList() 
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
            ArrayList donnees = new ArrayList() {"Red", "Green", "Orange", "White", "Black"};

            //crée une instance de la classe ColArrayList en passant
            //en paramètre une référence à une instance de la classe ArrayList.
            ColArrayList colArrayList = new ColArrayList(arrayList);
            
            //ajoutera chaque élément de la liste donnees à l'objet arrayList de la classe ColArrayList.
            colArrayList.ajouterElements(donnees);
            //affiche tous les éléments de l'objet arrayList avec un phrase en affichage
            colArrayList.afficherElementCollection(true);
            
            Console.WriteLine("Insérer la couleur \'Pink\' dans la liste à la première position");
            int position = 0;

            //insérer le nouvel élément à la position demandé
            colArrayList.insererElement(position, "Pink");
            // afficher la liste
            colArrayList.afficherElementCollection(false);









        }
    }
}
