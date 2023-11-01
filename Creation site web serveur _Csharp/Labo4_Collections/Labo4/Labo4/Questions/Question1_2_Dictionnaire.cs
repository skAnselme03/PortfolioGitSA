using Labo4.pckgClasses;
using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.Questions
{
    internal class Question1_2_Dictionnaire
    {
        public Question1_2_Dictionnaire() 
        {
            //Création d'une instance de la classe AfficherMessage 
            AfficherMessage afficher;
            //Création d'une instance de la classe AfficherMessage 
            afficher = new AfficherMessage("Manipuler les dictionnaires", "-", false);
            afficher.afficher_message();


            //-----------------------------------------------------//
            //              Les Dictionnaires                      //
            //-----------------------------------------------------//

            // Création d'un nouveau dictionnaire vide
            Dictionary<object, object> dictionnaire = new Dictionary<object, object>();
            
            // Création des objeta clés et valeurs qui contient une collection d'éléments
            object objetCle = new ArrayList() {1,2,3,4,5};
            object objetValeur = new ArrayList() { "Player 5", "Player 1", "Player 3", "Player 2", "Player 4"};

            // Création d'une instance de la classe ColDictionnaire avec le dictionnaire vide créé précédemment
            ColDictionnaire colDictionnaire = new ColDictionnaire(dictionnaire);
            
            // Ajout des éléments de la collection objetCle en tant que clés et des éléments de
            // la collection objetValeur en tant que valeurs dans le dictionnaire de l'objet colDictionnaire
            colDictionnaire.ajouterElements(objetCle, objetValeur);
            
            // Affichage des éléments du dictionnaire de l'objet colDictionnaire en appelant la 
            colDictionnaire.afficherElementCollection(true) ;

            //Afficher le nombre total de joueurs dans la console.
            Console.WriteLine("Le nombre de joureurs total : {0}",
                               colDictionnaire.nombreTotalValeurs());

            //Interchanger deux valeur
            Console.WriteLine("\nInterchanger le Player 5 qui est à la position 1 par le Player 4 qui est à la position 5.");
            colDictionnaire.interchangerDeuxValeurs("Player 5", "Player 4");

            // Afficher tous les joueurs et leur position respective dans la console.
            colDictionnaire.afficherElementCollection(false);

            colDictionnaire.supprimerTous();

            colDictionnaire.afficherElementCollection(false);









        }
    }
}
