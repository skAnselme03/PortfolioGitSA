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

namespace util
{


  /**
   * Valide le format d’un nom
   * @param p_nom : le nom a valider format
   * @return: vrai ou faux
   */
  bool
  validerFormatNom (const std::string& p_nom)
  {

    if (!isalpha (p_nom[0])) // si le premier caractere nest pas une lettre, retourne faux
      return false;

    int i = 1; // commencer la boucle au premier caractere
    while (i < p_nom.length ()) // tant que lindice du caractere est plus petit que la longueur du nom continuer
      {
        if (!isalpha (p_nom[i])) // si le caractere index i nest pas une lettre
          {
            if (p_nom[i] == '-' || p_nom[i] == ' ')// si le caractere index i est un tiret ou un espace
              {
                if (i == p_nom.length () - 1 || p_nom[i + 1] == '-' || p_nom[i + 1] == ' ' ) // si le dernier caractere index i (si i est lindex du dernier caractere de la chaine) ou le caractere index i suivant est un tiret ou le caractere index i suivant est un espace
                  return false; // retourne faux
              }
            else // autre
              {
                return false; // retourne faux
              }
          }
        i++; // increment i (verification si le caractere index i nest pas une lettre)
      }
    return true; //na pas retournee faux donc vrai
  }


  /**
   * \fn validerCodeIssn(const string& p_issn)
   * \brief Elle permet de valider le code Issn
   * \param[in] p_issn c`est une chaine de caractere
   * \return retourne vrai si le code Issn est valide ou non si ce n`est pas le cas
   */
  bool
  validerCodeIssn (const string& p_issn)
  {
    // valider clef
    int sommePonderee = 0;
    int chiffreAvantDernierTiret = 8;
    int cleControle;
    cleControle = (p_issn[13] == 'X') ? 10 : p_issn[13] - '0'; // si le dernier caractere est un X retourne 10, sinon prendre le caractere et le meme en entier

    for (int i = 5; i < 13; i++) // commence a partir du premier chiffre jusqua lavant dernier chiffre
      {
        if (i != 9 && !isdigit (p_issn[i])) // si i ne vaut pas 9 et que les caracteres ne sont pas des chiffres, retourne faux
          {
            return false;
          }
        if (i != 9) // si i nest pas le dernier chiffre
          {
            sommePonderee += (p_issn[i] -  '0') * chiffreAvantDernierTiret; // chaine de caractere mis en entier multiplier par le chiffre avant le dernier tiret
            chiffreAvantDernierTiret--; //decremente la variable chiffreAvantDernierTiret de 1 pour chaque iteration de la boucle
          }
      }

    if ((11 - sommePonderee % 11) % 11 != cleControle) //si 11- modulo 11 sur la somme ponderee ne donne pas cleControle, la cle est invalide
      {
        return false;
      }

    // Valider le format du ISSN
    if ((p_issn.substr (0, 5) != "ISSN ") || (!isdigit (p_issn[13]) && p_issn[13] != 'X') || (p_issn.length () != 14  || p_issn[9] != '-' ))
      {
        return false; // format ISSN    -      et la longueur total doit etre de 14 (commence par 1 et non 0)
      }
    // fin quand rien n'a donné faux, retourne vrai
    return true;
  }


  /**
   * \fn validerCodeIsbn(const string& p_isbn)
   * \brief Elle permet de valider le code Isbn
   * \param[in] p_isbn c`est une chaine de caractere
   * \return retourne vrai si le code Isbn est valide ou non si ce n`est pas le cas
   */
  bool
  validerCodeIsbn (const string& p_isbn)
  {
    // Vérification de la clé de contrôle avec la somme pondérée
    int cleVerifier = 0;
    int sommePonderee = 1;
    for (int n = 5; n < 20; n++)
      {
        if (p_isbn[n] == '-') continue;

        int entier = p_isbn[n] - '0';
        cleVerifier += sommePonderee * entier;
        sommePonderee = (sommePonderee == 1) ? 3 : 1;
      }

    int cleControle = 10 - (cleVerifier % 10);
    cleControle = (cleControle == 10) ? 0 : cleControle;

    if (cleControle != p_isbn[21] - '0')
      return false;

    // Identifiant du titre
    string identifiantTitreLongueur;
    int i = 9;
    while (isdigit (p_isbn[i]) && i <= 14 && p_isbn[i] != '-')
      {
        identifiantTitreLongueur += p_isbn[i];
        i++;
      }

    int identifiantTitre = stoi (identifiantTitreLongueur);

    // Vérification des identifiants de titre invalides
    if ((identifiantTitre > 989 && identifiantTitre < 9920) || (identifiantTitre > 9989 && identifiantTitre < 99901) || (identifiantTitre > 99982))
      return false;
    if (identifiantTitre == 6 || (identifiantTitre > 65 && identifiantTitre < 79) || (identifiantTitre > 7 && identifiantTitre < 65) || (identifiantTitre > 94 && identifiantTitre < 600) || (identifiantTitre > 624 && identifiantTitre < 950))
      return false;

    while (p_isbn[i] != '-')
      {
        if (!isdigit (p_isbn[i]))
          return false;
        i++;
      }

    // Identifiant de l'éditeur (longueur de 1 à 7 chiffres)
    i++;
    string identifiantEditeurLongueur;
    while (p_isbn[i] != '-')
      {
        if (!isdigit (p_isbn[i]))
          return false;
        if (p_isbn[i] == '0' && identifiantEditeurLongueur.empty ())
          {
            i++;
            continue;
          }
        identifiantEditeurLongueur += p_isbn[i];
        i++;
      }

    int segmentEditeur = stoi (identifiantEditeurLongueur);

    if (identifiantEditeurLongueur.length () == 0)
      return false;

    // Vérification des segments d'éditeur invalides
    if ((segmentEditeur > 949999 && segmentEditeur < 9500000) || (segmentEditeur > 9999999))
      return false;

    if ((segmentEditeur > 19 && segmentEditeur < 200) || (segmentEditeur > 699 && segmentEditeur < 7000) || (segmentEditeur > 8499 && segmentEditeur < 85000) || (segmentEditeur > 89999 && segmentEditeur < 900000))
      return false;

    // Vérification de la base du format ISBN
    if (p_isbn[i] != '-' || p_isbn.length () != 22 || !isdigit (p_isbn[21]) || (p_isbn.substr (0, 8) != "ISBN 978" && p_isbn.substr (0, 8) != "ISBN 979") || p_isbn[8] != '-' || p_isbn[20] != '-')
      return false;

    return true; // Le code ISBN est valide
  }

}