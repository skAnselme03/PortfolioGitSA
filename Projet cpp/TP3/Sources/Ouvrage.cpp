/*
 * \file Ouvrage.cpp
 * \brief La classe Ouvrage représente les références de la catégorie Ouvrage
 *
 * \author Florence Lariviere
 * \version 1.0
 * \date 2023-07-01
 */

#include <sstream>
#include <string>

#include "Ouvrage.h"
#include "validationFormat.h"
#include "ContratException.h"

using namespace std;
using namespace util;

namespace biblio
{


  /**
   * \fn  Ouvrage::Ouvrage (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant, const string& p_editeur, const string& p_ville) :
   * \brief Le constructeur, permet de construire un objet Ouvrage valide avec les paramètres obtenus
   * \parma[in] p_editeur un nom d`éditeur
   * \pre valide selon util::validerFormatNom
   * \parma[in] p_ville
   * \pre valider selon util::validerFormatNom
   * \pre valider selon util:: validerCodeIsbn
   *
   * \post m_editeur prend la valeur de p_editeur
   * \post m_ville prend la valeur de p_ville
   */



  Ouvrage::Ouvrage (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant, const string& p_ville, const string& p_editeur) :
  Reference (p_auteurs, p_titre, p_annee, p_identifiant), m_ville (p_ville), m_editeur (p_editeur)
  {
    PRECONDITION (validerFormatNom (p_ville));
    PRECONDITION (validerFormatNom (p_editeur));
    PRECONDITION (validerCodeIsbn (p_identifiant));

    POSTCONDITION (m_ville == p_ville);
    POSTCONDITION (m_editeur == p_editeur);


    INVARIANTS ();

  }


  /**
   * \fn virtual std::unique_ptr<Reference> clone ()const;
   * \brief Celle-ci permet de faire une copie allouée sur le monceau de l'objet courant tout en déléguant la libération de la mémoire à un pointeur intelligent
   * \return La copie de l`objet actual encapsulé dans un pointeur
   */

  unique_ptr<Reference>
  Ouvrage::clone () const
  {
    return make_unique<Ouvrage> (*this);
  }


  /**
   * \fn Ouvrage::reqEditeur() const
   * \brief l`accesseur pour le nom de l`éditeur de l`ouvrage
   * \return le nom de l`éditeur de l`ouvrage
   */
  const string
  Ouvrage::reqEditeur () const
  {
    return m_editeur;
  }


  /**
   * \fn Ouvrage::reqVille() const
   * \brief l`accesseur pour le nom de la ville d`édition
   * \return le nom de la ville d`édition de l`ouvrage
   */
  const string
  Ouvrage::reqVille () const
  {
    return m_ville;
  }


  /**
   * \fn Ouvrage::verifieInvariant() const
   * \brief Fait la vérification:
   * le nom de l`éditeur est valide
   * le nom de la ville d`édition est valide
   */

  void
  Ouvrage::verifieInvariant () const
  {
    INVARIANT (validerFormatNom (m_ville));
    INVARIANT (validerFormatNom (m_editeur));
    INVARIANT (validerCodeIsbn (reqIdentifiant ()));
  }


  /**
   * \fn Ouvrage::reqReferenceFormate() const
   * \brief Construit un format pour une référence, ce qui permet d`augmenter la méthode de la classe de base Reference
   * \return Le format de la référence en chaine de caractère
   */
  const string
  Ouvrage::reqReferenceFormate () const
  {
    ostringstream os;
    os << Reference::reqReferenceFormate () << reqVille ()  << ": " << reqEditeur () << ", " << reqAnnee () << ". " << reqIdentifiant () << ". ";
    return os.str ();
  }





}//namespace biblio

