/*
 * \file Reference.cpp
 * \brief La classe Reference permet de manipuler et de maintenir les références.
 *
 *      La classe Reference prend des valeurs en paramètre et créer une référence avec.
 *      Cela doit être des références valides, l'utilisateur de la classe doit vérifier.
 *
 * \author Florence Lariviere
 * \version 1.0
 * \date 2023-07-01
 */

#include <sstream>
#include "validationFormat.h"
#include "ContratException.h"
#include "Reference.h"
#include <string>
#include <memory>

using namespace std;
using namespace util;


namespace biblio
{


  /**
   * \fn Reference::Reference (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant) :
   * \brief Le constructeur, construit un objet Reference avec les pamamètres auteur, titre, année d'édition et identifiant
   * \parma[in] p_auteurs un ou des auteurs de la référence
   * \pre valider selon util::validernom
   * \parma[in] p_titre
   * \pre ne doit pas être vide
   * \parma[in] p_annee l`année d`édition
   * \pre p_annee doit être plus grand que 0
   * \parma[in] p_identifiant peut être un code ISSN ou ISBN 13
   * \pre p_identifiant ne doit pas être vide
   *
   * \post m_auteur prend la valeur de p_auteur
   * \post m_titre prend la valeur de p_titre
   * \post m_annee prend la valeur de p_annee
   * \post m_identifiant prend la valeur de p_identifiant
   */

  Reference::Reference (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant) :
  m_auteurs (p_auteurs), m_titre (p_titre), m_annee (p_annee), m_identifiant (p_identifiant)
  {
    PRECONDITION (p_annee > 0); //elle doit être strictement plus grande que 0
    PRECONDITION (validerFormatNom (p_auteurs)); // il doit être dans un format valide
    PRECONDITION (!p_titre.length () == 0); // le titre doit être non vide
    PRECONDITION (!p_identifiant.length () == 0); // l`identifiant doit etre non vide


    POSTCONDITION (m_auteurs == p_auteurs);
    POSTCONDITION (m_titre == p_titre);
    POSTCONDITION (m_annee == p_annee);
    POSTCONDITION (m_identifiant == p_identifiant);

    INVARIANTS ();

  }


  /**
   * \fn Reference::operator == (const Reference& p_reference)const
   * \brief Surchage de l`opérateur de comparaison, doit le faire sur tous les attributs
   * \param[in] p_reference la référence
   *
   * \return un booléen spécifiant si les deux références sont égales ou non
   */


  bool Reference::operator == (const Reference& p_reference) const
  {
    return (p_reference.reqAuteurs () == reqAuteurs () && p_reference.reqTitre () == reqTitre () && p_reference.reqAnnee () == reqAnnee () && p_reference.reqIdentifiant () == reqIdentifiant ());
  }


  /**
   * \fn Reference::asgAnnee(unsigned int& p_annee)
   * \brief Le mutateur de l`année d`édition
   * \param p_annee la nouvelle année d`édition obtenue
   * \pre L`année doit être plus grande que 0
   *
   * \post m_annee prend la valeur de p_annee
   */
  void
  Reference::asgAnnee (const unsigned int& p_annee)
  {
    PRECONDITION (p_annee > 0);
    m_annee = p_annee;
    POSTCONDITION (m_annee == p_annee);
  }


  /**
   * \fn Reference::reqAnnee() const
   * \brief l`accesseur pour l`année
   * \return  l'année d'édition de la référence
   */

  unsigned int
  Reference::reqAnnee () const
  {
    return m_annee;
  }


  /**
   * \fn Reference::reqAuteurs() const
   * \brief l`accesseur pour les auteurs
   * \return  le nom de l'auteur ou des auteurs de la référence
   */
  string
  Reference::reqAuteurs () const
  {
    return m_auteurs;
  }


  /**
   * \fn Reference::reqIdentifiant() const
   * \brief l`accesseur pour l`identifiant
   * \return l’identifiant de la référence
   */

  string
  Reference::reqIdentifiant () const
  {
    return m_identifiant;
  }


  /**
   * \fn Reference::reqTitre() const
   * \brief l`accesseur pour le titre
   * \return le titre de la référence
   */

  string
  Reference::reqTitre () const
  {
    return m_titre;
  }


  /**
   * \fn Reference::verifieInvariant() const
   * \brief Fait la vérification:
   * De l`auteur (valide selon validerFormatNom)
   * Du titre (doit être non vide)
   * De l`identifiant (dépend du type de la référence)
   * De l’année (doit être strictement plus grande que 0)
   *
   */
  void
  Reference::verifieInvariant () const
  {
    INVARIANT (m_annee > 0);
    INVARIANT (!m_titre.empty ());
    INVARIANT (!m_auteurs.empty ());
    INVARIANT (!m_identifiant.empty ());

  }


  /**
   * \fn Reference::reqReferenceFormate() const
   * \brief Formate les informations sur la référence
   */
  const string
  Reference::reqReferenceFormate () const
  {
    ostringstream os;
    os << reqAuteurs ()  << ". " << reqTitre () << ". "; //Homayoon Beigi. Fundamentals of Speaker Recognition.

    return os.str ();
  }
}//namespace biblio