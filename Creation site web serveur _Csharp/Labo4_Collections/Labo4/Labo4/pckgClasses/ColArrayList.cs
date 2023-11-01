using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.pckgClasses
{
    internal class ColArrayList
    {
        ArrayList arrayList;

        /*--------------------- Constructeur ---------------------*/
        public ColArrayList(ArrayList arrayList)
        {
            this.arrayList = arrayList;
        }

        public ColArrayList()
        {
        }


        /*-------------------- Getter & Setter -------------------*/
        public ArrayList getElement() { return this.arrayList; }
        public void setElement(ArrayList arrayList) { this.arrayList = arrayList; }

        /*----------------------- Méthodes -----------------------*/
        
        /// <summary>
        ///     Retourne la valeur d'un élement selon son index
        /// </summary>
        /// <param name="index">index de l'élement</param>
        /// <returns>l'élement recherché</returns>
        public object Element(int index)
        {
            // Vérifier si l'index est valide.
            if (index >= 0 && index < this.arrayList.Count)
            {
                // Retourner l'élément selon l'index de l'élément.
                return this.arrayList[index];
            }
            else
            {
                // Retourner une valeur nulle si l'index est invalide.
                return null;
            }
        }

        /// <summary>
        ///    Insérer un élément à l'index spécifier
        /// </summary>
        /// <param name="index">index du nouvel élement</param>
        /// <param name="element">nouvelle élement à insérer</param>
        public void insererElement(int index, object element)
        {
            this.arrayList.Insert(index, element);
        }

        /// <summary>
        ///     Ajouter n'impote quels élements enumérable dans la liste
        /// </summary>
        /// <param name="elements">les élements à ajouter</param>
        public void ajouterElements(ArrayList arrayList)
        {
            foreach (object element in arrayList)
            {
                this.arrayList.Add(element);
            }
        }

        /// <summary>
        ///     Supprimer un élement dans le arraylist
        /// </summary>
        /// <param name="element"></param>
        public void supprimerElement(object element)
        {
            this.arrayList.Remove(element);
        }

        /// <summary>
        ///     Supprimer et remplacer un élément d'une liste
        /// </summary>
        /// <param name="oldElement">ancien élément à supprimer</param>
        /// <param name="newElement">nouvel élement pour remplacement de l'ancien</param>
        public void supprimerEtRemplacer(object oldElement,  object newElement)
        {
            // recherche l'index de l'élément à enlever
            int indexOldElement = this.arrayList.IndexOf(oldElement);

            // si l'élwmwnr n'existe pas dans la liste
            if (indexOldElement != -1)
            {
                this.arrayList.RemoveAt(indexOldElement);
                this.arrayList.Insert(indexOldElement, newElement);  
            }
        }


        /// <summary>
        ///     Supprimer toutes les données du ArrayList.
        /// </summary>
        public void supprimerTous()
        {
            //vider le ArrayList
            this.arrayList.Clear();
        }

        /// <summary>
        ///     Ajouter un éléments quelconque dans un arrayList
        /// </summary>
        /// <param name="donees">donnee(s) à ajouter</param>
        public void ajouterElements(object donees)
        {
            // Vérifier si les données des énumérables.
            if(donees is IEnumerable)
            {
                // Cast donees en IEnumerable.
                var lesDonnes = (IEnumerable)donees;

                //itérer à travers chaque élément de donees.
                foreach (object element in lesDonnes)
                {
                    // Pour chaque élément, on l'ajoute à l'ArrayList en utilisant la méthode Add.
                    // L'ArrayList est stockée dans une variable d'instance appelée arrayList.
                    this.arrayList.Add(element);
                }

            }
            else
            {
                // ajouter la donné dans le ArrayList si non énumérable
                this.arrayList.Add(donees);
            }
        }

        /// <summary>
        ///     Afficher les éléments d'une collection
        /// </summary>
        /// <param name="afficherEntete">Si on veux un méssage d'entête lors de l'Affichage</param>
        public void afficherElementCollection(bool afficherEntete)
        {
            Console.WriteLine();
            if (afficherEntete)
            {
                Console.WriteLine("Les éléments du \'ArrayList\': \n");
            }
            foreach (string element in this.arrayList)
            {
                    Console.WriteLine("{0}", element);                    
            }
            Console.WriteLine();

        }
    }
}
