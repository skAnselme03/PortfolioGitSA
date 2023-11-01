/**
 * \file Journal.cpp
 * \brief La classe Journal représente les références de la catégorie Journal
 *
 *
 *
 * \author Florence Lariviere
 * \version 1.0
 * \date 2023-07-01
 *
 *
 */

#include <string>
#include <sstream>
#include <memory>
#include "Journal.h"
#include "validationFormat.h"
#include "ContratException.h"

using namespace std;
using namespace util;

namespace biblio
{


  /**
   * \fn  Journal::Journal (const string& p_auteurs,const string& p_titre, const unsigned int& p_annee, const string& p_identifiant, const string& p_nom, const unsigned int& p_volume,         * const unsigned int& p_numero,const unsigned int& p_page) :
   * \brief Le constructeur, construit un objet Journal valide avec les données passées en paramètre
   * \parma[in] p_nom un nom de journal
   * \pre ne doit pas être vide
   * \parma[in] p_volume le volume du journal
   * \pre le volume doit être plus grand que 0
   * \parma[in] p_numero le numero du journal
   * \pre le numéro doit être plus grand que 0
   * \parma[in] p_page la page de la référence dans la journal
   * \pre la page doit être plus grande que 0
   * \parma[in] p_identifiant
   * \pre l`identifiant doit être valide selon util::validerCodeIssn
   *
   * \post m_page prend la valeur de p_page
   * \post m_volume prend la valeur de p_volume
   * \post m_nom prend la valeur de p_nom
   * \post m_numero prend la valeur de p_numero
   */

  Journal::Journal (const string& p_auteurs,
                    const string& p_titre,
                    const unsigned int& p_annee,
                    const string& p_identifiant,
                    const string& p_nom,
                    const unsigned int& p_volume,
                    const unsigned int& p_numero,
                    const unsigned int& p_page) :
  Reference (p_auteurs, p_titre, p_annee, p_identifiant), m_nom (p_nom), m_volume (p_volume), m_numero (p_numero), m_page (p_page)
  {
    PRECONDITION (p_nom.length () > 0);
    PRECONDITION (p_volume > 0);
    PRECONDITION (p_numero > 0);
    PRECONDITION (p_page > 0);
    PRECONDITION (validerCodeIssn (p_identifiant));


    POSTCONDITION (m_page == p_page);
    POSTCONDITION (m_volume ==  p_volume);
    POSTCONDITION (m_nom == p_nom);
    POSTCONDITION (m_numero == p_numero);


    INVARIANTS ();


  }


  /**
   * \fn virtual std::unique_ptr<Journal> clone () const;
   * \brief Celle-ci permet de faire une copie allouée sur le monceau de l'objet courant tout en déléguant la libération de la mémoire à un pointeur intelligent
   * \return La copie de l`objet actual encapsulé dans un pointeur
   */

  unique_ptr<Reference>
  Journal::clone () const
  {
    return make_unique<Journal> (*this);
  }


  /**
   * \fn Journal::reqNom() const
   * \brief l`accesseur pour le nom du journal
   * \return le nom du journal
   *
   */
  const string
  Journal::reqNom () const
  {
    return m_nom;
  }


  /**
   * \fn Journal::reqVolume() const
   * \brief l`accesseur pour le volume du journal
   * \return le volume du journal
   *
   */
  int
  Journal::reqVolume () const
  {
    return m_volume;
  }


  /**
   * \fn Journal::reqNumero() const
   * \brief l`accesseur pour le numéro du journal
   * \return le numéro du journal
   */
  int
  Journal::reqNumero () const
  {
    return m_numero;
  }


  /**
   * \fn Journal::reqPage() const
   * \brief l`accesseur pour la page du journal
   * \return la page du journal
   */
  int
  Journal::reqPage () const
  {
    return m_page;
  }


  /**
   * \fn Journal::reqReferenceFormate() const
   * \brief Construit un format pour une référence, ce qui permet d`augmenter la méthode de la classe de base Reference
   * \return Le format de la référence en chaine de caractère
   *
   */
  const string
  Journal::reqReferenceFormate () const
  {
    ostringstream os;
    os << Reference::reqReferenceFormate () << reqNom () << ", vol. " << reqVolume () << ", no. " << reqNumero () << ", pp. " << reqPage () << ", " << reqAnnee () << ". " << reqIdentifiant () << ".";
    return os.str ();
  }


  /**
   * \fn Journal::verifieInvariant() const
   * \brief Fait la vérification:
   * le volume, le numéro et la page sont plus grand que 0
   * le nom de la référence publiée dans le journal n`est pas vide
   * l`identifiant est valide (fonction validerCodeIssn)
   */

  void
  Journal::verifieInvariant () const
  {

    INVARIANT (m_volume > 0);
    INVARIANT (m_page > 0);
    INVARIANT (m_numero > 0);

    INVARIANT (m_nom.length () > 0);

    INVARIANT (validerCodeIssn (reqIdentifiant ())); //Le code ISSN doit être dans un format valide tel que déterminé par la fonction util::validerCodeIssn

  }


}//namespace biblio