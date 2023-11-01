/*
 * \file validationFormat.cpp
 * \brief La classe validerFormatNom permet de vérifier si le nom obtenu en paramètre est valide
 * \brief La classe validerCodeIssn permet de vérifier si le code ISSN est valide
 * \brief La classe validerCodeIsbn permet de vérifier si le code ISBN est valide
 *
 * \author Florence Lariviere
 * \version 1.0
 * \date 2023-07-01
 */


#include<string>
#include <cstdlib>
#include <valarray>

using namespace std;


namespace util
{


  /**
   * \fn validerFormatNom (const string& p_nom)
   * \brief Elle permet de valider le nom obtenu en parametre
   * \param[in] p_nom c`est une chaine de caractere
   * \return retourne vrai si le nom est valide ou non si ce n`est pas le cas
   */

  bool
  validerFormatNom (const string& p_nom)
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
  validerCodeIsbn (const string & p_isbn)
  {
    // Vérification de la clé de controle avec la somme pondérée
    // ISBN 978-2-212-67895-2 est supposee donner faux
    int cleVerifier = 0;
    int sommePonderee = 1;
    for (int n = 5; n < 20; n++)// 978-2-212-67895 (les 15 chiffres avant tiret dernier chiffre)
      {
        if ('-' == p_isbn[n] ) //si cest un tiret continue
          continue;
        int entier;
        entier = p_isbn[n] - '0'; // convertir un caractere numerique en valeur entiere equivalente
        cleVerifier += sommePonderee * entier; // inverse la pondération de 1 ou 3 à chaque chiffre
        if (sommePonderee == 1) // Multiplier le premier chiffre par 1
          sommePonderee = 3; // Multiplier le deuxieme chiffre par 3
        else
          sommePonderee = 1; // Multiplier le troisieme chiffre par 1 et 3 pour le quatrieme et ainsi de suite
      }

    int cleControle;
    cleControle = 10 - (cleVerifier % 10); // 10 - modulo 10 pour obtenir le reste de la division par 10
    cleControle = (cleControle == 10) ? 0 : cleControle; // si le resultat est egal a 10, la cle de controle est 0

    if (cleControle != p_isbn[21] - '0') // si la cle de controle = dernier chiffre, la cle est valide si pas le cas, la cle est invalide
      return false;

    // identifiant du titre

    string identifiantTitreLongueur;
    int i = 9; // comme au premier chiffre apres ISBN 978-

    while (isdigit (p_isbn[i]) && i <= 14 && '-' != p_isbn[i]) // verifier si le caractere index i est un chiffre, ne pas depasser 14 (chiffre et tiret apres ISBN 978-)et caractere index pas un tiret
      {
        identifiantTitreLongueur += p_isbn[i]; //construit la chaine de caractere qui est mis dans identifiantTitreLongueur
        i++; // incremente la valeur de i
      }


    int identifiantTitre;
    identifiantTitre = stoi (identifiantTitreLongueur); //convertir la chaine de caractere identifiantTitreLongueur en entier pour la comparer par la suite

    //doit retourner faux, donc prendre les chiffres que retourneraient faux et non vrai. Faire inverse de 0-5 ou 7 ou 80-94 ou 950-989 ou 9900-9989 ou 99900-99999

    if ((identifiantTitre > 989 && identifiantTitre < 9920) || (identifiantTitre > 9989 && identifiantTitre < 99901) || (identifiantTitre > 99982))
      return false;
    if (identifiantTitre == 6 || (identifiantTitre > 65 && identifiantTitre < 79) || (identifiantTitre > 7 && identifiantTitre < 65)  || (identifiantTitre > 94 && identifiantTitre < 600) || (identifiantTitre > 624 && identifiantTitre < 950))
      return false;

    while (p_isbn[i] != '-') // tant que le caractere index i nest pas un tiret (continuer la boucle)
      {
        if (!isdigit (p_isbn[i])) // si le caractere index i nest pas un chiffre, retourner faux
          return false;
        i++; // incremente la valeur i
      }

    // identifiant de l'éditeur (longueur de 1 à 7 chiffres)
    i++;
    string identifiantEditeurLongueur;
    while (p_isbn[i] != '-') // tant que le caractere index i nest pas un tiret (continue la boucle)
      {
        if (!isdigit (p_isbn[i])) // si le caractere index i nest pas un chiffre, retourne faux
          return false;
        if (p_isbn[i] == '0' && identifiantEditeurLongueur.empty ())
          {
            i++; // incremente la valeur i
            continue; // continue la boucle
          }
        identifiantEditeurLongueur += p_isbn[i]; //construit la chaine de caractere qui est mis dans identifiantEditeurLongueur
        i++; // incremente la valeur i
      }

    int segmentEditeur = stoi (identifiantEditeurLongueur); //convertir la chaine de caractere identifiantEditeurLongueur en entier pour la comparer par la suite

    if (identifiantEditeurLongueur.length () == 0) // si la longueur de la chaine de caractere identifiantEditeurLongueur est 0, retourne faux
      return false;

    //doit retourner faux, donc prendre les chiffres que retourneraient faux et non vrai. Faire inverse de 00-19 ou 200-699 ou 7000-8499 ou 85000-89999 ou 900000-949999 ou 9500000-9999999
    if ((segmentEditeur > 949999 && segmentEditeur < 9500000) || (segmentEditeur > 9999999))
      return false;

    if ((segmentEditeur > 19 && segmentEditeur < 200) || (segmentEditeur > 699 && segmentEditeur < 7000) || (segmentEditeur > 8499 && segmentEditeur < 85000) || (segmentEditeur > 89999 && segmentEditeur < 900000))
      return false;

    // maintenant il faut juste verifier la base du format ISBN
    if ('-' != p_isbn[i]  || p_isbn.length () != 22 || !isdigit (p_isbn[21]) || (p_isbn.substr (0, 8) != "ISBN 978") && (p_isbn.substr (0, 8) != "ISBN 979") ||  '-' != p_isbn[8]   || p_isbn[20] != '-' )
      return false; // la forme ISBN 978 -              -chiffre et la longueur totale qui est 22 (en commencant par 1 et non 0)

    i++; // incremente i
    return true; // fin quand rien na donnee faux (le ISBN est valide), retourne vrai
  }

} // fin du namespace util


