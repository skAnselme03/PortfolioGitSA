using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.pckgClasses
{
    internal class ColDictionnaire
    {
        Dictionary<object, object> dictionnaire;

        /*--------------------- Constructeur ---------------------*/
        public ColDictionnaire(Dictionary<object, object> dictionnaire)
        {
            this.dictionnaire = dictionnaire;
        }

        public ColDictionnaire()
        {
        }


        /*-------------------- Getter & Setter -------------------*/
        public Dictionary<object, object> getElement() { return this.dictionnaire; }
        public void setElement(Dictionary<object, object> dictionnaire) { this.dictionnaire = dictionnaire; }

        /*----------------------- Méthodes -----------------------*/

        /// <summary>
        ///     Interchanger deux valeurs dans un dictionnaire
        /// </summary>
        /// <param name="valeur1">1ère valeur à interchanger</param>
        /// <param name="valeur2">2ème valeur à interchanger</param>
        /// <exception cref="ArgumentException"></exception>
        public void interchangerDeuxValeurs(object valeur1, object valeur2)
        {
            if (valEstDansDictionnaire(valeur1))
            {
                // récupérer les clés des valeurs passée en paramètre
                object cleVal1 = trouverCle(valeur1);
                object cleVal2 = trouverCle(valeur2);

                this.dictionnaire[cleVal1] = valeur2; // On met la deuxième valeur à la première clé
                this.dictionnaire[cleVal2] = valeur1; // On met la première valeur à la deuxième clé
            }
            else
            {
                // Lever une exception si un des valeurs minimale n'Est pas présent.
               Console.WriteLine("La valeur n'existe pas dans le dictionnaire!");
            }
        }

        /// <summary>
        ///     Vérifier si une valeur éxiste dans un dictionnaire
        /// </summary>
        /// <param name="valeur">valeur à tester</param>
        /// <returns>vrai si présent, faux sinon</returns>
        public bool valEstDansDictionnaire(object valeur)
        {
            //retourne vrai si la vaeur existe dans le dictionnaire
            return this.dictionnaire.ContainsValue((dynamic)valeur);
        }

        /// <summary>
        ///     Vérifier si une clé éxiste dans un dictionnaire
        /// </summary>
        /// <param name="cle">clé à tester</param>
        /// <returns>vrai si présent, faux sinon</returns>
        public bool cleEstDansDictionnaire(object cle)
        {
            //retourne vrai si la vaeur existe dans le dictionnaire
            return this.dictionnaire.ContainsKey((dynamic)cle);
        }

        /// <summary>
        ///     Retourne le nombre d'éléments dans le dictionnaire
        /// </summary>
        /// <returns>La taille du dictionnaire</returns>          
        public int nombreTotalValeurs()
        {
            return this.dictionnaire.Count;
        }


        /// <summary>   
        ///     Ajouter n'importe quels pairs clé valeur d'élements enumérable dans un dictionnaire
        /// </summary>
        /// <param name="cles">la clé des éléments à ajouter</param>
        /// <param name="valeurs">les valeurs des élements à ajouter</param>
        /// <exception cref="ArgumentException">si une erreur survient</exception>
        public void ajouterElements(object cles, object valeurs)
        {

            // Vérifier si les deux paramètres sont des énumérables.
            if (cles is IEnumerable && valeurs is IEnumerable)
            {
                // Cast les deux paramètres en tant qu'objets énumérables.
                var clesEnum = (IEnumerable)cles;
                var valeursEnum = (IEnumerable)valeurs;

                // Crée des listes à partir des objets énumérables.
                var clesList = clesEnum.Cast<object>().ToList();
                var valeursList = valeursEnum.Cast<object>().ToList();

                // Vérifier si les deux listes ont la même taille.
                if (clesList.Count == valeursList.Count)
                {
                    // Parcourir les deux listes en parallèle et ajouter chaque élément dans le dictionnaire.
                    for (int i = 0; i < clesList.Count; i++)
                    {
                        this.dictionnaire[clesList[i]] = valeursList[i];
                    }
                }
                else
                {
                    // Lever une exception si les deux listes n'ont pas la même taille.
                    throw new ArgumentException("Le nombre d'éléments dans les deux listes est différent.");
                }
            }
            else
            {
                // Lever une exception si les deux paramètres ne sont pas des objets énumérables.
                throw new ArgumentException("Les deux paramètres doivent être des objets énumérables.");
            }
        }

        /// <summary>
        ///      Ajouter n'impote des élements enumérable dans un dictionnaire
        /// </summary>
        /// <param name="valeurs">valeurs des élements à ajouter</param>
        /// <exception cref="ArgumentException">si une erreur survient</exception>
        public void ajouterElements(object valeurs)
        {
            int cle = 0;

            // Vérifier si la valeur est énumérables.
            //Vérifier si la liste de valeurs est vide avant
            //de la parcourir

            if ( valeurs is IEnumerable enumerable  && enumerable.GetEnumerator().MoveNext())
            {
                // Cast les valeurs en tant qu'objets énumérables.
                var valeursEnum = (IEnumerable)valeurs;

                // Crée des listes à partir des objets énumérables.
                var valeursList = valeursEnum.Cast<object>().ToList();

                // Parcourir les deux listes en parallèle
                // et ajouter chaque élément dans le dictionnaire.
                foreach (var valeur in valeursList)
                {
                    cle++;
                    //Ajouter une vérification supplémentaire pour s'assurer que
                    //chaque élément ajouté au dictionnaire est unique.
                    if (!this.dictionnaire.ContainsKey(cle))
                    {
                        this.dictionnaire.Add(cle, valeur);
                    }
                }

            }
            else
            {
                // Lever une exception si les deux paramètres ne sont pas des objets énumérables.
                throw new ArgumentException("Le paramètre doit être un objets énumérables et non vide.");
            }
        }

        /// <summary>
        ///     Supprimer un élement dans le dictionnaire
        /// </summary>
        /// <param name="element"></param>
        public void supprimerElement(object element)
        {
            this.dictionnaire.Remove(element);
        }

        /// <summary>
        ///     Supprimer toutes les données du Dictionary.
        /// </summary>
        public void supprimerTous()
        {
            //vider le dictionnaire
            this.dictionnaire.Clear();
        }

        /// <summary>
        ///     Supprimer et remplacer un élément d'un dictionnaire
        /// </summary>
        /// <param name="valOldElement">ancien élément à supprimer</param>
        /// <param name="valNewElement">nouvel élement pour remplacement de l'ancien</param>
        public void supprimerEtRemplacer(object valOldElement, object valNewElement)
        {
            // Parcourir toutes les paires clé/valeur du dictionnaire
            foreach (KeyValuePair<object, object> pairCleValeur in this.dictionnaire.ToList())
            {
                // Vérifier si la valeur de la paire correspond à l'ancienne valeur à supprimer
                if (pairCleValeur.Value.Equals(valOldElement))
                {
                    // Supprimer la paire clé/valeur correspondante à l'ancienne valeur
                    dictionnaire.Remove(pairCleValeur.Key);

                    // Ajouter la nouvelle paire clé/valeur avec la nouvelle valeur
                    dictionnaire.Add(pairCleValeur.Key, valNewElement);
                }
            }
        }

        /// <summary>
        ///     Trouver la clé correspondant à une valeur dans un dictionnaire
        /// </summary>
        /// <param name="valElement">valeur de recherche</param>
        /// <returns>cle correspondant à la valeur</returns>
        public object trouverCle(object valElement)
        {
            // parcourt ensuite chaque paire clé/valeur dans le dictionnaire
            foreach (var pairCleValeur in dictionnaire)
            {
                //Si la valeur recherchée est égale à la valeur de la paire clé/valeur actuelle,
                //la fonction retourne la clé associée à cette paire
                if (pairCleValeur.Value.Equals(valElement))
                {
                    return pairCleValeur.Key;
                }
            }
            return null; //  Si aucune paire ne correspond à la valeur recherchée, la fonction retourne null.
        }


        /// <summary>
        ///     Afficher les éléments d'une collection
        /// </summary>
        /// <param name="afficherEntete">Si on veux un méssage d'entête lors de l'Affichage</param>
        public void afficherElementCollection(bool afficherEntete)
        {
            Console.WriteLine();
            if (this.dictionnaire.Count != 0)
            {                
                // méssage d'êtete
                if (afficherEntete)
                {
                    Console.WriteLine("Les éléments du \'dictionnaire\': \n");
                }
                //afficher les pairs d'élement clé vlaeur du dictionnaire
                foreach (KeyValuePair<object, object> pairCleValeur in this.dictionnaire)
                {
                    Console.WriteLine("Clé : {0}, Valeur : {1}", pairCleValeur.Key, pairCleValeur.Value);
                }
            }
            else
            {
                // Lever une exception si un des valeurs minimale n'Est pas présent.
                Console.WriteLine("Le dictionnaire est vide!");
            }
            Console.WriteLine();

        }
    }
}
