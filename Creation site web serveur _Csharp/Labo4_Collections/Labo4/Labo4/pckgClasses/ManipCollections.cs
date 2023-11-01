using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Labo4.pckgClasses
{
    internal class ManipCollections
    {
        string nomCollection;
        string typeCollection;
        string nomTypeCollection;
        char delimiteur;


        /*------------------------------------------------------------*/
        /*----------------------- CONSTRUCTEUR -----------------------*/
        /*------------------------------------------------------------*/
        public ManipCollections(string nomCollection, string typeCollection)
        {
            this.nomCollection = nomCollection;
            this.typeCollection = typeCollection;
        }
        public ManipCollections(string nomTypeCollection,
                                char delimiteur,
                                char separateur)
        {

            this.nomTypeCollection = nomTypeCollection;
            this.delimiteur = delimiteur;
            this.getNomEtType(separateur);


        }
        public ManipCollections() { }

        /*---------------------------------------------------------------*/
        /*----------------------- GETTERS/SETTERS -----------------------*/
        /*---------------------------------------------------------------*/
        public string getNomCollection() { return nomCollection; }
        public string getTypeCollection() { return typeCollection; }

        public void setNomCollection(string nomCollection) { this.nomCollection = nomCollection; }
        public void setTypeCollection(string typeCollection) { this.typeCollection = typeCollection; }

        /*--------------------------------------------------------*/
        /*----------------------- MÉTHODES -----------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Choisir le type de la collection qu’on désire manipuler : 
        ///     collections génériques ou collection non génériques
        /// </summary>
        /// <returns>la sélection de l’utilisateur</returns>
        public string MenuPrincipal()
        {
            // Afficher la liste des collections non générique
            Console.Write("Choisir le type de collection désirer" +
                          " (g/generic/générique, ng/nogeneric/nongénérique : ");
            string choix = Console.ReadLine();
            string reponse = "";

            switch (choix.ToLower())
            {
                case "g":
                case "generic":
                case "générique":
                    return "collections génériques";
                case "ng":
                case "nogeneric":
                case "nongénérique":
                    return "collections non génériques";
                default:
                    return reponse;
            };
        }

        /// <summary>
        ///     Choisir le type de la collection générique qu’on désire manipuler
        /// </summary>
        /// <returns>la sélection de l’utilisateur</returns>
        public string MenuCGenerique() {
            // Afficher la liste des collections non générique
            Console.WriteLine("*** Liste des collections générique ***");
            ArrayList Arraylist = new ArrayList();
            object donnees = new ArrayList() {"ArrayList", "HashTable",
                                              "SortedList", "Stack", "Queue"};

            ColArrayList colArrayList = new ColArrayList(Arraylist);
            colArrayList.ajouterElements(donnees);
            colArrayList.afficherElementCollection(false);

            Console.Write("Choisir le type de collection générique désirer parmis les choix ci-dessous : ");
            string choix = Console.ReadLine();
            string reponse = "";

            switch (choix.ToLower())
            {
                case "arraylist":
                case "alist":
                case "tableauliste":
                case "tblliste":
                    return "ArrayList";

                case "hashtable":
                case "htable": 
                    return "HashTable";

                case "sortedlist":
                case "listetrie":
                case "slist":
                    return "SortedList";

                case "stack":
                case "pile":
                    return "Stack";

                case "queue":
                case "file":
                    return "Queue";
                default:
                    return reponse;

            }
        }

        /// <summary>
        ///     Choisir le type de la collection non générique qu’on désire manipuler
        /// </summary>
        /// <returns>la sélection de l’utilisateur</returns>
        public string MenuCNonGénérique()
        {
            // Afficher la liste des collections non générique
           Console.WriteLine("*** Liste des collections non générique ***"); 
           ArrayList Arraylist = new ArrayList();
           object donnees = new ArrayList() {"List<T>", "Dictionary<TKey, TValue>",
                                              "SortedList<T>", "Stack<T>", "Queue<T>"};

            ColArrayList colArrayList = new ColArrayList(Arraylist);
            colArrayList.ajouterElements(donnees);
            colArrayList.afficherElementCollection(false);

            Console.Write("Choisir un type de collection non générique désirer parmis les choix ci-dessous: ");
            string choix = Console.ReadLine();

                                
            //retourner le choix sélectionner
            switch (choix.ToLower())
            {
                case "list":
                case "liste":
                case "list<t>":                    
                    return "List";
                case "dictionary":
                case "dictionaire":
                case "dictionary<tkey, tvalue>":
                    return "Dictionary";
                case "sortedlistt":
                case "listetriet":
                case "slistt":
                case "SortedList<t>":
                    return "SortedlistT";
                case "stackt":
                case "pilet":
                case "stack<t>":
                    return "StackT";
                case "queuet":
                case "filet":
                case "queue<t>":
                    return "queueT";
                default:
                    return "";
            }
            
        }


        /// <summary>
        ///     Afficher les valeurs d'une collection selon le choix de collection et de son type
        /// </summary>
        /// <param name="donnees">données de la collection à afficher</param>
        public void afficherCollection(object donnees)
        {
            object collection = null;
            
            switch(this.typeCollection.ToLower())
            {
                case "générique":
                case "génériques":
                case "generique":
                case "generiques":
                case "generic":
                case "gc":
                    this.afficherCollectionGeneric(donnees);
                    break;
                case "non-générique":
                case "non-generique":
                case "no-generic":
                case "nongénérique":
                case "nongénériques":
                case "nongenerique":
                case "nongeneriques":
                case "nogeneric":
                case "ngénérique":
                case "ngenerique":
                case "ngeneric":
                case "ngc":
                    this.afficherCollectionNnGeneric(donnees);
                    break;
            }
        }

        /// <summary>
        ///     Afficher une collecion généric
        /// </summary>
        /// <param name="donnees">donnees de la collection</param>
        public void afficherCollectionGeneric(object donnees)
        {
            switch (this.nomCollection.ToLower())
            {
                case "arraylist":
                case "alist":
                case "tableauliste":
                case "tblliste":
                    ArrayList arrayList = new ArrayList(); ;
                    ColArrayList colArrayList = new ColArrayList(arrayList);
                    colArrayList.ajouterElements(donnees);
                    colArrayList.afficherElementCollection(true);
                    colArrayList.supprimerTous();// liberer la mémoire
                    break;
                case "hashtable":
                case "htable":
                    break;
                case "sortedlist":
                case "listetrie":
                case "slist":
                    break;
                case "stack":
                case "pile":
                    break;
                case "queue":
                case "file":
                    break;
            }
        }

        /// <summary>
        ///     Afficher une collecion généric
        /// </summary>
        /// <param name="donnees">donnees de la collection</param>
        public void afficherCollectionNnGeneric(object donnees)
        {
            switch (this.nomCollection.ToLower())
            {
                case "list":
                case "listet":
                    break;
                case "dictionary":
                case "dictionaire":
                    Dictionary<object, object> dictionnaire = new Dictionary<object, object>();
                    ColDictionnaire colDictionnaire = new ColDictionnaire(dictionnaire);
                    colDictionnaire.ajouterElements(donnees);
                    colDictionnaire.afficherElementCollection(true);
                    colDictionnaire.supprimerTous();
                    break;
                case "sortedlistT":
                case "listetrieT":
                case "slistT":
                    break;
                case "stackT":
                case "pileT":
                    break;
                case "queueT":
                case "fileT":
                    break;
            }
        }

        /// <summary>
        ///     Récuperer le nom et le type
        /// </summary>
        private void getNomEtType(char separateur) 
        {
            // Effectuer un Split sur la chaîne en utilisant le caractère underscore comme délimiteur.
            string[] mots = this.nomTypeCollection.Split(this.delimiteur);

            // Ajouter les deux premiers éléments à leur valeurs
            // respective en vérifiant d'abord 
            if (mots.Length >= 2)
            {
                this.nomCollection = mots[0];

                //extraire la première partie du deuxième élément de "mots"
                //qui contient le type de la collection et l'affecte à la
                //variable typeCollection.
                this.typeCollection = mots[1].Split(separateur)[0];
            }


        }

    }
}
