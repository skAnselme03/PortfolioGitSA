using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_1_Collections
    {
        public Question1_1_Collections() 
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficher;
            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Manipuler les collections", "-", false);
            afficher.afficher_message();


            //-----------------------------------------------------//
            //                  Les ArrayList                      //
            //-----------------------------------------------------//

            afficher = new AfficherMessage("ArrayList", "-", false);
            // Création d'un nouveau ArrayList vide
            ArrayList arrayList = new ArrayList();

            // Création d'une instance de la classe ArrayList et y stocke une collection de chaînes de caractères.
            ArrayList donnees = new ArrayList() {"Nathalie", "Ahmed", "Cinthia", "Joel", "Mathilde", "Karl", "Amine"};

            //crée une instance de la classe ColArrayList en passant
            //en paramètre une référence à une instance de la classe ArrayList.
            ColArrayList colArrayList = new ColArrayList(arrayList);
            
            //ajoutera chaque élément de la liste donnees à l'objet arrayList de la classe ColArrayList.
            colArrayList.ajouterElements(donnees);
            //affiche tous les éléments de l'objet arrayList avec un phrase en affichage
            colArrayList.afficherElementCollection(true);
            Console.WriteLine("Remplacer Cithia par Martin");
            //supprimer un élément de l'objet arrayList
            colArrayList.supprimerEtRemplacer("Cinthia", "Martin");
            //affiche tous les éléments de l'objet arrayList
            colArrayList.afficherElementCollection(false);

            //-----------------------------------------------------//
            //              Les Dictionnaires                      //
            //-----------------------------------------------------//

            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Dictionnaire", "-", false);
            // Création d'un nouveau dictionnaire vide
            Dictionary<object, object> dictionnaire = new Dictionary<object, object>();
            
            // Création des objeta clés et valeurs qui contient une collection d'éléments
            object objetCle = new ArrayList() {1,2,3,4,5,6,7};
            object objetValeur = new ArrayList() { "Nathalie", "Ahmed", "Cinthia", "Joel", "Mathilde", "Karl", "Amine" };

            // Création d'une instance de la classe ColDictionnaire avec le dictionnaire vide créé précédemment
            ColDictionnaire colDictionnaire = new ColDictionnaire(dictionnaire);
            
            // Ajout des éléments de la collection objetCle en tant que clés et des éléments de
            // la collection objetValeur en tant que valeurs dans le dictionnaire de l'objet colDictionnaire
            colDictionnaire.ajouterElements(objetCle, objetValeur);
            
            // Affichage des éléments du dictionnaire de l'objet colDictionnaire en appelant la 
            colDictionnaire.afficherElementCollection(true) ;
            
            // Remplacement de l'élément "Cinthia" par "Martin" dans le dictionnaire de l'objet 
            Console.WriteLine("Remplacer Cithia par Martin");
            colDictionnaire.supprimerEtRemplacer("Cinthia", "Martin");
            colDictionnaire.afficherElementCollection(false);









        }
    }
}
