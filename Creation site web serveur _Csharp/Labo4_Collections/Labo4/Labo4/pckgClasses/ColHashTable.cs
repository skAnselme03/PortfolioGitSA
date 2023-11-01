using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.pckgClasses
{
    internal class ColHashTable
    {
        Hashtable hashTable;

        /*------------------------------------------------------------*/
        /*----------------------- CONSTRUCTEUR -----------------------*/
        /*------------------------------------------------------------*/
        public ColHashTable(Hashtable hashTable)
        {
            this.hashTable = hashTable;
        }


        /*--------------------- Constructeur ---------------------*/
        public ColHashTable()
        {
        }



        /*---------------------------------------------------------------*/
        /*----------------------- GETTERS/SETTERS -----------------------*/
        /*---------------------------------------------------------------*/

        public Hashtable getElement() { return this.hashTable; }
        public void setElement(Hashtable hashTable) { this.hashTable = hashTable; }


        /*--------------------------------------------------------*/
        /*----------------------- MÉTHODES -----------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Interchanger deux valeurs dans un hashTable
        /// </summary>
        /// <param name="valeur1">1ère valeur à interchanger</param>
        /// <param name="valeur2">2ème valeur à interchanger</param>
        /// <exception cref="ArgumentException"></exception>
        public void interchangerDeuxValeurs(object valeur1, object valeur2)
        {
            if (valEstDanshashTable(valeur1))
            {
                // récupérer les clés des valeurs passée en paramètre
                object cleVal1 = trouverCle(valeur1);
                object cleVal2 = trouverCle(valeur2);

                this.hashTable[cleVal1] = valeur2; // On met la deuxième valeur à la première clé
                this.hashTable[cleVal2] = valeur1; // On met la première valeur à la deuxième clé
            }
            else
            {
                // Lever une exception si un des valeurs minimale n'Est pas présent.
                Console.WriteLine("La valeur n'existe pas dans le hashTable!");
            }
        }

        /// <summary>
        ///     Vérifier si une valeur éxiste dans un hashTable
        /// </summary>
        /// <param name="valeur">valeur à tester</param>
        /// <returns>vrai si présent, faux sinon</returns>
        public bool valEstDanshashTable(object valeur)
        {
            //retourne vrai si la vaeur existe dans le hashTable
            return this.hashTable.ContainsValue((dynamic)valeur);
        }

        /// <summary>
        ///     Vérifier si une clé éxiste dans un hashTable
        /// </summary>
        /// <param name="cle">clé à tester</param>
        /// <returns>vrai si présent, faux sinon</returns>
        public bool cleEstDanshashTable(object cle)
        {
            //retourne vrai si la vaeur existe dans le hashTable
            return this.hashTable.ContainsKey((dynamic)cle);
        }

        /// <summary>
        ///     Retourne le nombre d'éléments dans le hashTable
        /// </summary>
        /// <returns>La taille du hashTable</returns>          
        public int nombreTotalValeurs()
        {
            return this.hashTable.Count;
        }


        /// <summary>   
        ///     Ajouter n'importe quels pairs clé valeur d'élements enumérable dans un hashTable
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
                    // Parcourir les deux listes en parallèle et ajouter chaque élément dans le hashTable.
                    for (int i = 0; i < clesList.Count; i++)
                    {
                        this.hashTable[clesList[i]] = valeursList[i];
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
        ///      Ajouter n'impote des élements enumérable dans un hashTable
        /// </summary>
        /// <param name="valeurs">valeurs des élements à ajouter</param>
        /// <exception cref="ArgumentException">si une erreur survient</exception>
        public void ajouterElements(object valeurs)
        {
            int cle = 0;

            // Vérifier si la valeur est énumérables.
            //Vérifier si la liste de valeurs est vide avant
            //de la parcourir

            if (valeurs is IEnumerable enumerable && enumerable.GetEnumerator().MoveNext())
            {
                // Cast les valeurs en tant qu'objets énumérables.
                var valeursEnum = (IEnumerable)valeurs;

                // Crée des listes à partir des objets énumérables.
                var valeursList = valeursEnum.Cast<object>().ToList();

                // Parcourir les deux listes en parallèle
                // et ajouter chaque élément dans le hashTable.
                foreach (var valeur in valeursList)
                {
                    cle++;
                    //Ajouter une vérification supplémentaire pour s'assurer que
                    //chaque élément ajouté au hashTable est unique.
                    if (!this.hashTable.ContainsKey(cle))
                    {
                        this.hashTable.Add(cle, valeur);
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
        ///     Supprimer un élement dans le hashTable
        /// </summary>
        /// <param name="element"></param>
        public void supprimerElement(object element)
        {
            this.hashTable.Remove(element);
        }

        /// <summary>
        ///     Supprimer toutes les données du Dictionary.
        /// </summary>
        public void supprimerTous()
        {
            //vider le hashTable
            this.hashTable.Clear();
        }

        /// <summary>
        ///     Supprimer et remplacer un élément d'un hashTable
        /// </summary>
        /// <param name="valOldElement">ancien élément à supprimer</param>
        /// <param name="valNewElement">nouvel élement pour remplacement de l'ancien</param>
        public void supprimerEtRemplacer(object valOldElement, object valNewElement)
        {
            // Parcourir toutes les paires clé/valeur du hashTable
            foreach (KeyValuePair<object, object> pairCleValeur in this.hashTable)
            {
                // Vérifier si la valeur de la paire correspond à l'ancienne valeur à supprimer
                if (pairCleValeur.Value.Equals(valOldElement))
                {
                    // Supprimer la paire clé/valeur correspondante à l'ancienne valeur
                    hashTable.Remove(pairCleValeur.Key);

                    // Ajouter la nouvelle paire clé/valeur avec la nouvelle valeur
                    hashTable.Add(pairCleValeur.Key, valNewElement);
                }
            }
        }

        /// <summary>
        ///     Trouver la clé correspondant à une valeur dans un hashTable
        /// </summary>
        /// <param name="valElement">valeur de recherche</param>
        /// <returns>cle correspondant à la valeur</returns>
        public object trouverCle(object valElement)
        {
            // parcourt ensuite chaque paire clé/valeur dans le hashTable
            foreach (DictionaryEntry dictionaryEntry in hashTable)
            {
                //Si la valeur recherchée est égale à la valeur de la paire clé/valeur actuelle,
                //la fonction retourne la clé associée à cette paire
                if (dictionaryEntry.Value.Equals(valElement))
                {
                    return dictionaryEntry.Key;
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
            if (this.hashTable.Count != 0)
            {
                // méssage d'êtete
                if (afficherEntete)
                {
                    Console.WriteLine("Les éléments du \'hashTable\': \n");
                }
                //afficher les pairs d'élement clé vlaeur du hashTable
                foreach (KeyValuePair<object, object> pairCleValeur in this.hashTable)
                {
                    Console.WriteLine("Clé : {0}, Valeur : {1}", pairCleValeur.Key, pairCleValeur.Value);
                }
            }
            else
            {
                // Lever une exception si un des valeurs minimale n'Est pas présent.
                Console.WriteLine("Le hashTable est vide!");
            }
            Console.WriteLine();

        }
    }


}

