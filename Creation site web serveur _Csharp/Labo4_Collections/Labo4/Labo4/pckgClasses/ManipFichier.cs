using System;
using System.Collections;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace Labo4.pckgClasses
{
    internal class ManipFichier
    {
        string nomFichier;
        string extension;
        string cheminAcces;
        string filePath;
        bool ecrireALaRacine; //Écrire à la racine du dossier ou donner son emplacement

        /*------------------------------------------------------------*/
        /*----------------------- CONSTRUCTEUR -----------------------*/
        /*------------------------------------------------------------*/
        public ManipFichier(string cheminAcces, string nomFichier, string extenstion, bool ecrireALaRacine = false)
        {
            this.nomFichier = nomFichier;
            this.cheminAcces = cheminAcces;
            this.extension = extenstion;
            this.filePath = Path.Combine(this.cheminAcces, this.nomFichier + this.extension);
            this.ecrireALaRacine = ecrireALaRacine;
        }
        public ManipFichier(string nomFichier, string extenstion, bool ecrireALaRacine = true)
        {
            this.nomFichier = nomFichier;
            this.extension = extenstion;
            this.ecrireALaRacine = ecrireALaRacine;
        }
        public ManipFichier() { }

        /*---------------------------------------------------------------*/
        /*----------------------- GETTERS/SETTERS -----------------------*/
        /*---------------------------------------------------------------*/
        public string getNomFichier() { return nomFichier; }
        public string getExtenstion() { return extension; }
        public string getPath() { return cheminAcces; }
        public string getFilePath() { return filePath; }
        public bool getecrireALaRacine() { return ecrireALaRacine; }

        public void setNomFichier(string nomFichier) { this.nomFichier = nomFichier; }
        public void setPath(string path) { this.cheminAcces = path; }
        public void setExtension(string extension) { this.extension = extension; }
        public void setFilePath(string filePath) { this.filePath = filePath; }
        public void setecrireALaRacine(bool ecrireALaRacine) { this.ecrireALaRacine = ecrireALaRacine; }
        /*--------------------------------------------------------*/
        /*----------------------- MÉTHODES -----------------------*/
        /*--------------------------------------------------------*/

        /// <summary>
        ///     Affiche une liste de chaînes de caractères contenue dans un fichier.
        ///     Les éléments de la liste sont séparés par le caractère spécifié.
        /// </summary>
        /// <param name="separateur">Le caractère utilisé pour séparer les éléments de la liste.</param>
        public void afficerListInFic(char separateur) 
        {
            // Récupération de la liste de chaînes de caractères dans le fichier
            List<string> list = getListeInFic(separateur);

            // Parcours de la liste et affichage de chaque élément
            foreach (var item in list)
            {
                Console.WriteLine(item);
            }

        }

        /// <summary>
        ///     Lire un fichier et rcupérer le contenu
        /// </summary>
        /// <param name="separateur">séparateur pour récupérer la liste</param>
        /// <returns></returns>
        public List<string> getListeInFic(char separateur)
        {
            StreamReader lectureFic = null;
            List<string> list = new List<string>();
            try
            {
                //ouvrir le fichier en lecture
                lectureFic = new StreamReader(this.filePath);

                // lire le contenu du fichier ligne par ligne pour récupérer chaque mot et l'ajouter dans une liste
                string ligne;
                while ((ligne = lectureFic.ReadLine()) != null)
                {
                    string[] elements0 = ligne.Split(separateur);
                    list.Add(elements0[0]);
                }

                return list;
            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                lectureFic.Close();
            }

            return list;

        }

        /// <summary>
        ///     Afficher les caractères, son code ascii ainsi que le nombre d'occurence du caractère lus dans un fichier 
        /// </summary>
        public void afficheCharFichier() 
        {
            StreamReader lectureFic = null;
            try
            {
                //ouvrir le fichier en lecture
                lectureFic = new StreamReader(this.filePath);
                int nbreOccurenceChar = 0;
                char caractere;
                int codeCaractere;

                //lire le fichier caractère par caractère
                while (!lectureFic.EndOfStream)
                {

                    // code ascii du caractère
                    codeCaractere = lectureFic.Read();
                    //lecture d'un caractère
                    caractere = (char)codeCaractere;

                    //remplacer les caractères de fin de ligne CR (13) ou LF (10) par le caractère ayant le code ascii 128.
                    if(codeCaractere == 13 ||  codeCaractere == 10 ) 
                    {
                        codeCaractere = 128;
                        caractere = (char)codeCaractere;

                    }

                    //incrémenter le nombre d'occurence du caractère dans le fichier
                    nbreOccurenceChar++;


                    //Affichage du caractère avec son code ascii et le  nombre d'occurence dans le fichier
                    Console.WriteLine("Caractère : {0},\tCode : {1}\tnombre de caractères lus : {2}", 
                                        caractere, codeCaractere, nbreOccurenceChar);
                }
            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                lectureFic.Close();
            }
        }


        /// <summary>
        ///     Nombre d'occurence d'un caractère dans un fichier
        /// </summary>
        /// <param name="caractere">caractère à recherché</param>
        /// <returns></returns>
        public int nbrOccurencesCaractere(char caractere)
        {
            int nbrOccurences = 0;

            StreamReader lectureFic = null;
            try
            {
                //ouvrir le fichier en lecture
                lectureFic = new StreamReader(this.filePath);

                int codeCar;

                //lecture du fichier caractère par caractère
                while (!lectureFic.EndOfStream)
                {
                    //lit un caractère
                    codeCar = lectureFic.Read();

                    //incrémenter le caractère si le caractère existe plus d'une fois
                    if ((char)codeCar == caractere)
                    {
                        nbrOccurences++;
                    }
                }

                return nbrOccurences;

            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                lectureFic.Close();
            }


            return nbrOccurences;
        }

        /// <summary>
        ///     Recherce la présence d'une chaine de caractère dans un fichier
        /// </summary>
        /// <param name="chaine">Chaine à rechercher</param>
        /// <returns></returns>
        public int rechercheMotDsFichier(string chaine)
        {
            int nbOccurence = 0;

            StreamReader lectureFic = null;
            try
            {
                // Ouvrir le fichier en mode lecture
                lectureFic = new StreamReader(this.filePath);

                // lire le contenu du fichier ligne par ligne tant
                string ligne;
                while ((ligne = lectureFic.ReadLine()) != null)
                {
                    // recherche le mot dans la ligne
                    int indexMot = ligne.IndexOf(chaine, StringComparison.OrdinalIgnoreCase);

                    // si le mot est trouvé dans la ligne, incrémenter lenombre d'occurence
                    // et continue de rechercher d'autre occurence dans la ligne.
                    while (indexMot >= 0)
                    {
                        nbOccurence++;
                        indexMot = ligne.IndexOf(chaine, indexMot+chaine.Length,  StringComparison.OrdinalIgnoreCase);
                    }

                }
                return nbOccurence;

            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                if (lectureFic != null)
                {
                    lectureFic.Close();
                }
            }
            return nbOccurence;


        }


        /// <summary>
        /// Crée un fichier et y insère les élements du tableau en paramète et écrase le contenu 
        /// du fichier à chaque fois que cette option est choisie
        /// </summary>
        /// <param name="lignes">Lignes à ajouter dans le fichier</param> 
        /// <param name="estCreer">Créer ou ajouter des lignes dans un fichier. False: pour crée, True pour ajouter</param>
        public void creerOuAjouterFichier(List<string> lignes, bool estCreer)
        {
            StreamWriter monFichier = null;
            try
            {

                string nomDuFichier = this.nomFichier + this.extension;
                //creation de l'éditeur du fichier et ouverture du fichier en mode écriture
                // si le fichier n’existe pas on crée un nouveau fichier selon l'enmplacement fournis
                monFichier = editeOuCreate(monFichier, nomDuFichier, estCreer);

                //ouvrir le fichier en mode écriture.
                //écraser le contenue existant.
                //garantir que le flux de sortie est correctement fermé lorsque l'opération d'écriture est terminée.
                //Écrire les élément du tableau dans le fichier
                foreach (string element in lignes)
                {
                    //Écrire chaque élément dans le fichier, en sautant une ligne entre chaque élément.
                    monFichier.WriteLine(element);

                }

                            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                if (monFichier != null)
                {
                    monFichier.Close();
                }
            }

        }
       
        
        /// <summary>
        /// Permettre de créer un fichier à la racine ou dans un endroit spécifié
        /// </summary>
        /// <param name="monFichier">Le fichier en écriture</param>
        /// <param name="nomDuFichier">Nom du fichier</param>
        /// <param name="estCreer">Créer ou ajouter des lignes dans un fichier. False: pour crée, True pour ajouter</param>
        private StreamWriter editeOuCreate(StreamWriter monFichier, string nomDuFichier, bool estCreer)
        {
            //creation de l'éditeur du fichier et ouverture du fichier en mode écriture
            // si le fichier n’existe pas on crée un nouveau fichier
            if (this.ecrireALaRacine)
            {
                monFichier = new StreamWriter(nomDuFichier, estCreer);
            }
            else
            {
                if (this.cheminAcces != null)
                {
                    monFichier = new StreamWriter(this.filePath, estCreer);

                }
                else
                {
                    monFichier = new StreamWriter(nomDuFichier, estCreer);

                }
            }
            return monFichier;
        }

        /// <summary>
        ///     Écrire les nombres de intervalDroite à intervalDroite un par ligne, dans un fichier
        /// </summary>
        /// <param name="intervalDroite">nombre borne gauche</param>
        /// <param name="intervalDroite">nombre borne droite</param>
        public void ecrireNombres(int intervalGauche, int intervalDroite)
        {
            StreamWriter monFichier = null;
            string nomDuFichier = this.nomFichier + this.extension;

            try
            {
                //creation e l'éditeur du fichier et ouverture du fichier en mode écriture
                // si le fichier n’existe pas on crée un nouveau fichier
                if (this.ecrireALaRacine)
                {
                    monFichier = new StreamWriter(nomDuFichier);
                }
                else
                {
                    if (this.cheminAcces != null)
                    {
                        monFichier = new StreamWriter(this.filePath);

                    }
                    else
                    {
                        monFichier = new StreamWriter(nomDuFichier);

                    }
                }

                // Écrire les nombres de intervalGauche à intervalDroite en utilisant
                // la méthode WriteLine() de l'objet StreamWriter.
                for (int i = intervalGauche; i <= intervalDroite; i++)
                {
                    //écriture sur le fichier
                    monFichier.WriteLine(i);
                }
                // fermeture du fichier et libérer l’utilisation du fichier aux prochains
                monFichier.Close();

            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de l'écriture dans le fichier : " + e.Message);

            }

        }


        /// <summary>
        /// Somme des nombres lues dans un fichier
        /// </summary>
        /// <returns>Sommes des nombres (Entier)</returns>
        public int sommeCalculeInt()
        {
            int somme = 0; //somme totale des nombres lus dans le fichier.
            string ligne; // ligne lue dans le fichier

            StreamReader lecture = new StreamReader(this.filePath); // lire le fichier

            // lire chaque ligne du fichier tant qu'il y'a du texte dans le fichier
            while ((ligne = lecture.ReadLine()) != null)
            {
                int nombre;
                // convertir la chaine de caractères lus en nombre
                if (int.TryParse(ligne, out nombre))
                {
                    // conversion en nombre réussi, donc sommation
                    somme += nombre;
                }
            }
            // fermeture du fichier
            lecture.Close();

            return somme; //retour la somme
        }

        /// <summary>
        ///     Calculer la somme des nombres dans un fichier
        /// </summary>
        /// <returns>somme [double]</returns> 
        public double sommeCalculeDouble()
        {
            double somme = 0; //somme totale des nombres lus dans le fichier.
            string ligne; // ligne lue dans le fichier

            StreamReader lecture = new StreamReader(this.filePath); // lire le fichier

            // lire chaque ligne du fichier tant qu'il y'a du texte dans le fichier
            while ((ligne = lecture.ReadLine()) != null)
            {
                double nombre;
                // convertir la chaine de caractères lus en nombre
                if (double.TryParse(ligne, out nombre))
                {
                    // conversion en nombre réussi, donc sommation
                    somme += nombre;
                }
            }
            // fermeture du fichier
            lecture.Close();

            return somme; //retour la somme
        }

        /// <summary>
        ///     Lire un fichier et stocké des valeurs pour un même clé dans un dictionnaire
        /// </summary>
        /// <param name="separateurData">Séparateurs de la ligne de données. Séparant la clé et la valeur</param>
        /// <returns>Dictionnaire de données.</returns>
        public Dictionary<string, List<string>> getDataPutDict(string separateurData)
        {
            // dictionnaire pour stoquer les données
            Dictionary<string, List<string>> donnees = new Dictionary<string, List<string>>();

            try
            {
                StreamReader lecture = new StreamReader(this.filePath); // lire le fichier
                string ligne; // ligne lue dans le fichier

                // Lire chaque ligne du fichier
                while ((ligne = lecture.ReadLine()) != null)
                {
                    // Suppression des tabulations présentes dans la ligne
                    string ligneSansTabulations = ligne.Replace("\t", "");
                    // Diviser la ligne en deux parties en utilisant
                    // le caractère passée en paramètre [separateurData]
                    string[] ligneParts = ligneSansTabulations.Split(separateurData);

                    // Vérifier que la ligne contient exactement deux parties minimalement
                    if (ligneParts.Length == 2)
                    {
                        // Ajouter la clé et la valeur au dictionnaire,
                        // en enlevant les espaces avant et après chaque partie
                        string cle = ligneParts[0].Trim();
                        string valeur = ligneParts[1].Trim();

                        // Vérifier si la clé existe déjà dans le dictionnaire

                        if (donnees.ContainsKey(cle))
                        {
                            donnees[cle].Add(valeur);
                        }
                        else
                        {
                            donnees[cle] = new List<string>() { valeur };
                        }

                    }
                }

                lecture.Close();
            }
            catch (IOException e)
            {
                // Capturer toute exception d'entrée-sortie qui pourrait survenir lors de la lecture du fichier
                Console.WriteLine("Une erreur c'est produit lors de la lecture du fichier: " + e.Message);
            }

            // Retourner le dictionnaire rempli de données
            // (ou un dictionnaire vide si une erreur s'est produite)
            return donnees;
        }



        /// <summary>
        ///     Lire un fichier et stocké des valeurs pour un même clé dans un haschode
        /// </summary>
        /// <param name="separateurData">Séparateurs de la ligne de données. Séparant la clé et la valeur</param>
        /// <returns>HashTanble de données.</returns>
        public Dictionary<string, string> getDataPutHasT(string separateurData)
        {
            // dictionnaire pour stoquer les données
            Dictionary<string, string> donnees = new Dictionary<string, string>();  

            try
            {
                StreamReader lecture = new StreamReader(this.filePath); // lire le fichier
                string ligne; // ligne lue dans le fichier

                string valeur = "";

                // Lire chaque ligne du fichier
                while ((ligne = lecture.ReadLine()) != null)
                {
                    // Suppression des tabulations présentes dans la ligne
                    string ligneSansTabulations = ligne.Replace("\t", "");
                    // Diviser la ligne en deux parties en utilisant
                    // le caractère passée en paramètre [separateurData]
                    string[] ligneParts = ligneSansTabulations.Split(separateurData);

                    // Vérifier que la ligne contient exactement deux parties minimalement
                    if (ligneParts.Length >= 2)
                    {
                        // Ajouter la clé et la valeur au dictionnaire,
                        // en enlevant les espaces avant et après chaque partie
                        string cle = ligneParts[0].Trim();

                        for (int i = 1; i < ligneParts.Length; i++)
                        {
                            if (ligneParts[i] != "")
                            {
                                valeur = ligneParts[i].Trim();
                            }
                        }
                        donnees[cle] = valeur;  
                       
                       

                    }
                }

                lecture.Close();
            }
            catch (IOException e)
            {
                // Capturer toute exception d'entrée-sortie qui pourrait survenir lors de la lecture du fichier
                Console.WriteLine("Une erreur c'est produit lors de la lecture du fichier: " + e.Message);
            }

            // Retourner le dictionnaire rempli de données
            // (ou un dictionnaire vide si une erreur s'est produite)
            return donnees;
        }




        /// <summary>
        ///     Calculer la mmoyenne d'un données lu
        /// </summary>
        /// <param name="separateurData"></param>
        /// <returns>dictionnaire de moyenne selon la clé</returns>
        public Dictionary<string, double> calcMoyenneDonnees(string separateurData)
        {
            // Dictionnaires des données lus
            Dictionary<string, List<string>> donnees = getDataPutDict(separateurData);

            // Créer un dictionnaire pour stocker les moyennes selon la clé
            Dictionary<string, double> moyenne = new Dictionary<string, double>();

            double dblMoyenne = 0;

            // parcourir le dictionnaires de données pour en extraire sa valeur
            foreach (KeyValuePair<string, List<string>> entrer in donnees)
            {
                // récupération de la clé et la liste de valeurs associées à cette clé
                string cle = entrer.Key;
                List<string> valeurs = entrer.Value;

                double somme = 0;

                // parcourir chaque valeur de la liste selon la clé pour sommer les valeurs de cet clé
                foreach (string valeur in valeurs)
                {
                    // convertir la valeur en double
                    if (double.TryParse(valeur, out double valeurConvertie))
                    {
                        // si la conversion a réussi faire la somme des valeurs
                        somme += valeurConvertie;

                    }
                }

                // calculer la moyenne des valeurs pour la clé courante
                dblMoyenne = somme / valeurs.Count;

                // Stocker la moyenne dans le dictionnaire de retour
                moyenne[cle] = Math.Round(dblMoyenne, 2);
            }
            return moyenne;

        }


        /// <summary>
        ///     Lire et afficher le contenu d'un fichier
        /// </summary>
        /// <param name="afficherEntete">Afficher une entête ou non</param>
        public void lireFichier(bool afficherEntete)
        {
            StreamReader monFichier = null;

            try
            {
                monFichier = new StreamReader(this.filePath);
                // Lit le contenu du fichier et l'affiche dans la console
                if (afficherEntete)
                {
                    Console.WriteLine("Le contenu du fichier est : \n");                    
                }

                Console.WriteLine(monFichier.ReadToEnd());
            }
            catch (Exception e)
            {
                // Gère l'exception en affichant le message d'erreur
                Console.WriteLine("Une erreur s'est produite lors de la lecture du fichier : " + e.Message);
            }
            finally
            {
                // Ferme le fichier même si une exception est levée
                if (monFichier != null)
                {
                    monFichier.Close();
                }
            }
        }
    }
}
