/*
 * \file Reference.cpp
 * \brief Implémentation de la classe de base abstraite Reference.
 *        Note: aucune définition de la méthode virtuelle pure.
 * \author Stéphanie Anselme
 * \date 2023-07-14
 * \version 1.0
 */

#include "Reference.h"
#include "validationFormat.h"
#include "ContratException.h"
#include <iostream>
#include <string>
#include <sstream>

using namespace std;
using namespace util;

namespace biblio
{


  Reference::Reference (const std::string& p_auteurs,
                        const std::string& p_titre,
                        const unsigned int& p_annee,
                        const std::string& p_identifiant) : m_auteurs (p_auteurs), m_titre (p_titre), m_annee (p_annee), m_identifiant (p_identifiant)
  {
    PRECONDITION (validerFormatNom (p_auteurs));
    //PRECONDITION (!p_auteurs.length () == 0);
    PRECONDITION (!p_titre.length () == 0);
    PRECONDITION (p_annee > 0);
    PRECONDITION (!p_identifiant.length () == 0);

    POSTCONDITION (m_auteurs == p_auteurs);
    POSTCONDITION (m_titre == p_titre);
    POSTCONDITION (m_annee == p_annee);
    POSTCONDITION (m_identifiant == p_identifiant);

    INVARIANTS ();
  }


  Reference::~Reference () {
    //cout << "Destruction de l'objet bien effectué." << endl;
  }

  /**************************/
  /*       ACCESSEURS       */
  /**************************/
  //


  string
  Reference::getAuteurs () const
  {
    return m_auteurs;
  }


  string
  Reference::getTitre () const
  {
    return m_titre;
  }


  unsigned int
  Reference::getAnnee () const
  {
    return m_annee;
  }


  string
  Reference::getIdentifiant () const
  {
    return m_identifiant;
  }
  /**************************/
  /*       MUTATEURS        */
  /**************************/
  //


  void
  Reference::setAnnee (const unsigned int& p_annee)
  {
    PRECONDITION (p_annee > 0);
    m_annee = p_annee;
    POSTCONDITION (m_annee == p_annee);
    INVARIANTS ();
  }

  /**************************/
  /*       MÉTHODES         */
  /**************************/
  //


  bool Reference::operator== (const Reference& p_reference) const
  {
    return (m_auteurs == p_reference.m_auteurs &&
            m_titre == p_reference.m_titre &&
            m_annee == p_reference.m_annee &&
            m_identifiant == p_reference.m_identifiant);
  }


  const string
  Reference::regReferenceFormate () const
  {
    ostringstream os;
    os << m_auteurs << ". " << m_titre << ". ";
    return os.str ();
  }


  void
  Reference::verifieInvariant () const
  {
    INVARIANT (!m_auteurs.empty ());
    INVARIANT (!m_titre.empty ());
    INVARIANT (m_annee > 0);
    INVARIANT (!m_identifiant.empty ());
  }

}