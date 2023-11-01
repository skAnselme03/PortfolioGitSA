/*
 * \file gestionBibliographie.cpp
 * \brief C`est un programme qui utilise la classe Bibliographie avec les differents types de références
 * \Author Florence Lariviere
 * \date 2023-07-01
 */

#include "validationFormat.h"
#include "Reference.h"
#include "Journal.h"
#include "Ouvrage.h"
#include "Bibliographie.h"

#include <iostream>
#include <string>

using namespace biblio;
using namespace util;
using namespace std;

//Les questions spécifiques à ouvrage
void InfosOuvrage (Bibliographie& p_bibliographie);
//ISBN
string demanderInfoEtValiderIdentifiantIsbn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant);

//Les questions spécifiques à journal
void InfosJournal (Bibliographie& p_bibliographie);
//ISSN
string demanderInfoEtValiderIdentifiantIssn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant);

//Les questions demandées à l'utilisateur:
string demanderInfoEtValiderTitre (const string& p_reponseUtilisateur); // Valider le titre de l'ouvrage et du journal
string demanderInfoEtValiderFormatNom (const string& p_reponseUtilisateur); // Valider auteur (ouvrage et journal), ville (ouvrage et journal), édition (ouvrage et journal), éditeur (ouvrage et journal), nom  (journal)
unsigned int demanderInfoEtValiderAnnee (const string& p_reponseUtilisateur); // Valider année (ouvrage et journal)
unsigned int demanderInfoEtValiderPositif (const string& p_reponseUtilisateur); // Valider volume (journal) , numéro (journal) , page (journal)


/**
 * \fn demanderSaisieOuvrage(Bibliographie& p_bibliographie)
 * \brief Saisir les informations pour créer un ouvrage et l`ajouter
 * \param[in] p_bibliographie: c`est la référence de la bibliographie du programme principal
 * \param[in] auteur: l`auteur de l`ouvrage
 * \param[in] titre: le titre de l`ouvrage
 * \param[in] annee: l`année de l`ouvrage
 * \param[in] identifiant: l`identifiant de l`ouvrage
 * \param[in] editeur: l`éditeur de l`ouvrage
 * \param[in] ville: la ville de l`ouvrage
 * \return unOuvrage avec les informations obtenues
 */

void
InfosOuvrage (Bibliographie & p_bibliographie)
{
  bool utilisateurEntreInfos = false;
  while (!utilisateurEntreInfos)
    {
      string auteur = demanderInfoEtValiderFormatNom ("Quel est le nom de l`auteur ?");
      string titre = demanderInfoEtValiderTitre  ("Quel est le titre de l`ouvrage ?");
      string ville = demanderInfoEtValiderFormatNom ("Quelle est la ville d`édition ?");
      string editeur = demanderInfoEtValiderFormatNom  ("Quel est le nom de l`éditeur ?");
      int annee = demanderInfoEtValiderAnnee ("Quelle est l`année de l`ouvrage ?");
      string identifiant = demanderInfoEtValiderIdentifiantIsbn ("Quel est le code ISBN de l`ouvrage ?", "ISBN");



      Ouvrage unOuvrage (auteur, titre, annee, identifiant, editeur, ville);
      p_bibliographie.ajouterReference (unOuvrage);

      utilisateurEntreInfos = true;
    }


}


/**
 * \fn demanderSaisieJournal(Bibliographie& p_bibliographie)
 * \brief Saisir les informations pour créer un journal et l`ajouter
 * \param[in] p_bibliographie c`est la référence de la bibliographie du programme principal
 * \param[in] auteur: l`auteur du journal
 * \param[in] titre: le titre du journal
 * \param[in] annee: l`année du journal
 * \param[in] identifiant: l`identifiant du journal
 * \param[in] volume: le volume du journal
 * \param[in] numero: le numéro du journal
 * \param[in] page: la page du journal
 * \return unJournal avec les informations obtenues
 */
void
InfosJournal (Bibliographie & p_bibliographie)
{
  bool utilisateurEntreInfos = false;
  while (!utilisateurEntreInfos)
    {
      string auteur = demanderInfoEtValiderFormatNom ("Quel est le nom de l`auteur ?");
      string titre = demanderInfoEtValiderTitre  ("Quel est le titre du journal ?");
      string nom = demanderInfoEtValiderFormatNom ("Quel est le nom de la revue dans laquelle a été publiée la référence ?");
      unsigned int volume = demanderInfoEtValiderPositif ("Entrez le volume: ");
      unsigned int numero = demanderInfoEtValiderPositif ("Entrez le numéro de la référence: ");
      unsigned int page = demanderInfoEtValiderPositif ("Entrez la page de la référence: ");
      int annee = demanderInfoEtValiderAnnee ("Entrez l`année du journal ?");

      string identifiant = demanderInfoEtValiderIdentifiantIssn ("Entrez le code ISSN: ", "ISSN");





      Journal unJournal (auteur, titre, annee, identifiant, nom, volume, numero, page);
      p_bibliographie.ajouterReference (unJournal);

      utilisateurEntreInfos = true;
    }
}


/**
 * \fn string demanderInfoEtValiderTitre (const string& p_reponseUtilisateur)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \return titre
 */


string
demanderInfoEtValiderTitre (const string& p_reponseUtilisateur)
{
  string titre;
  bool titreValide = false;
  while (!titreValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, titre); // la réponse de l`utilisateur

      if (!titre.empty ()) //si l`utilisateur a mis quelque chose retourne vrai
        {
          titreValide = true;
        }
    }
  return titre;
}


/**
 * \fn unsigned int demanderInfoEtValiderAnnee (const string& p_reponseUtilisateur)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \return annee
 */

unsigned int
demanderInfoEtValiderAnnee (const string& p_reponseUtilisateur)
{
  string anneeStr;
  unsigned int annee;
  bool anneeValide = false;
  while (!anneeValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, anneeStr); // la réponse de l`utilisateur

      if (!anneeStr.empty () && anneeStr.length () >= 4)//si l`utilisateur a mis quelque chose et bien 4 chiffres
        {
          anneeValide = true;

          for (int i = 0; i < anneeStr.length (); i++)
            {
              if (!isdigit (anneeStr[i]) || isalpha (anneeStr[i] - '0')) // si ce n`est pas des chiffres ou ce sont des lettres, retourne faux
                {
                  anneeValide = false;

                }
            }

          if (anneeValide)
            {
              annee = stoi (anneeStr); // convertit la chaîne de caractères en entier
              if (annee <= 0) // vérifie que l`année est positive sinon retourne faux
                {
                  anneeValide = false;
                }
            }
        }

    }
  return annee;
}


/**
 * \fn string demanderInfoEtValiderFormatNom (const string& p_reponseUtilisateur)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \return nom
 */

string
demanderInfoEtValiderFormatNom (const string& p_reponseUtilisateur)
{
  string nom;
  bool nomValide = false;
  while (!nomValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, nom); // la réponse de l`utilisateur

      if (!nom.empty () && validerFormatNom (nom)) // si l`utilisateur a écrit quelque chose et c`est valide, retourne vrai
        {
          nomValide = true;
        }
    }
  return nom;
}


/**
 * \fn string demanderInfoEtValiderPositif(const string& p_reponseUtilisateur)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \return positif (un nombre qui est positif)
 */

unsigned int
demanderInfoEtValiderPositif (const string& p_reponseUtilisateur)
{
  string nombre;
  bool entierValide = false;
  while (!entierValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, nombre); // la réponse de l`utilisateur

      if (!nombre.empty ())
        {
          entierValide = true;
        }
      if (entierValide)
        {
          for (int i = 0; i < nombre.length (); i++)
            {
              if (!isdigit (nombre[i]) || isalpha (nombre[i] - '0') )// si ce n`est pas des chiffres ou ce sont des lettres, retourne faux
                {
                  entierValide = false;
                }
            }
        }
      if (entierValide)
        {
          if (stoi (nombre) <= 0)// convertit la chaîne de caractères en entier et si plus petit que 0, retourne faux
            {
              entierValide = false;
            }
        }
    }
  return stoi (nombre);
}


/**
 * \fn string demanderInfoEtValiderIdentifiantIsbn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \param[in] p_typeIdentifiant: si c`est bien un ISBN
 * \return identifiant
 */
string
demanderInfoEtValiderIdentifiantIsbn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant)
{
  string identifiant;
  bool identifiantValide = false;
  while (!identifiantValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, identifiant); // la réponse de l`utilisateur


      if (!identifiant.empty () && p_typeIdentifiant == "ISBN" && validerCodeIsbn (identifiant)) // si l`utilisateur a écrit quelque chose et que c`est un ISBN et valider, retourne vrai
        {
          identifiantValide = true;

        }
    }
  return identifiant;
}


/**
 * \fn string demanderInfoEtValiderIdentifiantIssn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant)
 * \brief vérifier que les infos obtenues sont valides
 * \param[in] p_reponseUtilisateur: l`info que l`utilisateur entre
 * \param[in] p_typeIdentifiant: si c`est bien un ISSN
 * \return identifiant
 */

string
demanderInfoEtValiderIdentifiantIssn (const string& p_reponseUtilisateur, const string& p_typeIdentifiant)
{
  string identifiant;
  bool identifiantValide = false;
  while (!identifiantValide)
    {
      cout << p_reponseUtilisateur; // demander la question
      getline (cin, identifiant); // la réponse de l`utilisateur


      if (!identifiant.empty () && p_typeIdentifiant == "ISSN" && validerCodeIssn (identifiant))// si l`utilisateur a écrit quelque chose et que c`est un ISBN et valider, retourne vrai
        {
          identifiantValide = true;

        }
    }
  return identifiant;
}


int
main ()
{
  {
    //Commencer par construire une bibliographie (dont le nom n`est pas saisi)
    Bibliographie laBibliographie ("Bibiographie ");

    cout << "Bienvenue dans la section gestion de références bibliographique !" << endl;
    cout << "=================================================================" << endl;

    //Demander d`ajouter un ouvrage
    cout << "Entrez l'ouvrage désiré : " << endl;
    InfosOuvrage (laBibliographie); // ajout de l`ouvrage dans la bibliographie


    //Demander d`ajouter un journal
    cout << "=================================================================" << endl;
    cout << "Entrez le journal désiré : " << endl;
    InfosJournal (laBibliographie); // ajout du journal dans la bibliographie


    // Affichage de la bibliographie
    cout << "=================================================================" << endl;
    cout << laBibliographie.reqBibliographieFormate () << endl;



  }
  return 0;

}