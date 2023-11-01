/*
 *     Author: Stéphanie Anselme
 *  Matricule: 537025715
 *      Date : 29/05/2023
 *
 */
#include <cstdlib>
#include <string>
#include "validationFormat.h"
using namespace std;


/**
 * Valide le format d’un nom
 * @param p_nom : le nom a valider format
 * @return: vrai ou faux
 */
bool
validerFormatNom (const std::string& p_nom)
{
  //Variable de suivi pour l'état de validité du nom
  bool estValide = true;
  //variable pour vérifier la validité d'une lette est présent avec un espace ou un tiret
  bool lettrePrecedente = false;

  //si la premmire caracteres nom passée en parametre n'est pas composé
  //de caracteres de lettre  sort de la fonction
  if (!isalpha (p_nom[0]))
    {
      return !estValide;
    }

  // Parcours de chaque caractère du nom
  for (int i = 1; i < p_nom.length (); i++)
    {
      //si c'est des lettres
      if (isalpha (p_nom[i]))
        {
          // Mettre lettrePrecedente à vrai si une lettre valide est présente
          lettrePrecedente = true;
        }
        // Vérifie si le caractère est un tiret ou un espace
      else if (p_nom[i] == '-' || p_nom[i] == ' ')
        {
          //valider que les caracteres '-' ou ' ' ne sont pas doublés
          if (!lettrePrecedente)
            {
              return !estValide;
            }

          // Réinitialise lettrePrecedente pour la prochaine itération
          lettrePrecedente = false;
        }
    }

  // Retourne valide && lettrePrecedente, assure que le nom est valide seulement s'il respecte toutes les règles et se termine par une lettre

  return estValide && lettrePrecedente;
}


bool
validerCodeIssn (const std::string& p_issn)
{

  bool estValide = true;
  // Vérifier la longueur du code ISSN
  if (p_issn.length () != 9)
    {
      return !estValide;
    }

  // Vérifier le format du code ISSN
  if (p_issn[4] != '-')
    {
      return !estValide;
    }

  // Vérifier les chiffres du code ISSN
  for (int i = 0; i < p_issn.length (); i++)
    {
      // Vérifier la position du tiret
      if (i == 4)
        {
          continue;
        }

      // Vérifier si le caractère est un chiffre
      if (!isdigit (p_issn[i]))
        {
          return estValide;
        }
    }

  // Vérifier la clé du code ISSN
  int cleISNN = 0;
  for (int i = 0; i < 8; i++)
    {
      cleISNN += (p_issn[i] - '0') * (8 - i);
    }
  cleISNN %= 11;
  cleISNN = (cleISNN == 0) ? 0 : (11 - cleISNN);

  // Vérifier si la clé correspond au dernier chiffre du code ISSN
  if (cleISNN != (p_issn[8] - '0'))
    {
      return !estValide;
    }

  return true;
}


/**
 * * Valide le code ISBN d'un livre
 * @param p_isbn : le code ISBN à valider
 * @return : vrai ou faux
 */
bool
validerCodeIsbn (const std::string& p_isbn)
{
  // Vérifier la longueur du code ISBN
  if (p_isbn.length () != 17)
    {
      return false;
    }

  // Vérifier le format du code ISBN
  if (p_isbn.substr (0, 5) != "ISBN " || p_isbn[6] != '-' || p_isbn[8] != '-' || p_isbn[15] != '-')
    {
      return false;
    }

  // Vérifier les chiffres du code ISBN
  for (int i = 5; i < p_isbn.length (); i++)
    {
      // Vérifier la position des tirets
      if (i == 6 || i == 8 || i == 15)
        {
          continue;
        }

      // Vérifier si le caractère est un chiffre
      if (!isdigit (p_isbn[i]))
        {
          return false;
        }
    }

  // Vérifier la clé du code ISBN
  int cle = 0;
  for (int i = 0; i < 17; i++)
    {
      if (isdigit (p_isbn[i]))
        {
          cle += (p_isbn[i] - '0') * ((i % 2 == 0) ? 1 : 3);
        }
    }
  cle %= 10;
  cle = (cle == 0) ? 0 : (10 - cle);

  // Vérifier si la clé correspond au dernier chiffre du code ISBN
  if (cle != (p_isbn[16] - '0'))
    {
      return false;
    }

  return true;
}
