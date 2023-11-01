/**
 * \file Bibiographie.cpp
 * \brief La classe Bibliographie permet de faire la gestion des références.
 *
 * \author Florence Lariviere
 * \version 1.0
 * \date 2023-07-01
 *
 *
 */

#include<sstream>
#include "Bibliographie.h"
#include "ContratException.h"
#include "Reference.h"
#include <string>
#include "ReferenceException.h"

using namespace std;

namespace biblio
{


  /**
   * \fn Bibliographie::Bibliographie(const string& p_nomBibliographie)
   * \brief Le constructeur, possède un paramètre permettant d`initialiser le nom des bibliographies au moment de leur construction
   * \parma[in] p_nomBibliographie c`est une chaine de caractère représentant le nom de la bibliographie
   * \pre p_nomBibliographie ne peut être vide
   * \post m_nomBibliographie qui prend la valeur de p_nomBibliographie
   * \post m_vReference qui est un vecteur vide
   *
   */

  Bibliographie::Bibliographie (const string& p_nomBibliographie) :
  m_nomBibliographie (p_nomBibliographie)
  {
    PRECONDITION (!p_nomBibliographie.empty ());

    POSTCONDITION (m_vReferences.empty ());
    POSTCONDITION (m_nomBibliographie == p_nomBibliographie);

    INVARIANTS ();
  }


  /**
   * \fn Bibliographie:: reqNomBibliographie() const
   * \brief l`accesseur pour le nom de de la bilbiographie
   * \return le nom de la bibliographie
   */
  const string
  Bibliographie::reqNomBibliographie () const
  {
    return m_nomBibliographie;
  }


  /**
   * \fn Bibliographie::Bibliographie(const Bibliographie& p_bilbiographie)
   * \brief Constructeur qui permet de créer une copie pour la classe Bibliographie
   * \param[in] p_bibliographie c`est l`objet de la bibliographie
   * \post m_nomBibliographie qui prend la valeur de p_nomBibliographie
   * \post m_vReference qui est un vecteur vide
   */
  Bibliographie::Bibliographie (const Bibliographie& p_bibliographie)
  {

    m_nomBibliographie = p_bibliographie.m_nomBibliographie;
    copierVecteur (p_bibliographie);

    POSTCONDITION (m_nomBibliographie == p_bibliographie.m_nomBibliographie);
    POSTCONDITION (!m_vReferences.empty ());

    INVARIANTS ();
  }


  /**
   * \fn Bibliographie::~Bibliographie ()
   * \brief Le destructeur, désallouer toutes les références d'une bibliographie dans le vecteur
   */
  Bibliographie::~Bibliographie () { }


  /**
   * \fn  Bibliographie& operator= (const Bibliographie& p_bibliographie);
   * \brief  copier les valeurs d'un objet existant dans un autre objet du même type
   * \param[in] p_bibliographie la bibliographie
   * \return une référence vers l`objet courant (*this) après avoir effectué l’assignation
   */


  const Bibliographie& Bibliographie::operator= (const Bibliographie& p_bibliographie)
  {
    m_nomBibliographie = p_bibliographie.m_nomBibliographie;
    viderVecteur ();
    copierVecteur (p_bibliographie);
    INVARIANTS ();
    return *this;
  }


  /**
   * \fn bool referenceEstDejaPresente (const Reference& p_reference)const;
   * \brief Cette méthode permet de vérifier si la bibliographie a déjà une référence
   * \param[in] p_reference
   * \pre p_reference, il ne doit pas être vide
   * \return un booléen indiquant si la référence est déja présente ou non
   */
  bool
  Bibliographie::referenceEstDejaPresente (const Reference& p_reference) const
  {
    bool estPresente = false;
    vector <unique_ptr < Reference >>::const_iterator iter;
    for (iter = m_vReferences.begin (); iter != m_vReferences.end (); iter++)
      {

        if ((*iter) ->reqIdentifiant () == p_reference.reqIdentifiant ())
          {
            estPresente = true;
          }
      }
    return estPresente;
  }


  /**
   * \fn Bibliographie::ajouterReference (const Reference& p_reference)
   * \brief Ajoute une référence au vecteur de référence lorsqu`elle n`est pas déjà présente
   * \param[in] p_reference  Une référence constante à cloner et ajouter
   */
  void
  Bibliographie::ajouterReference (const Reference& p_reference)
  {
    if (referenceEstDejaPresente (p_reference))
      {
        throw ReferenceDejaPresenteException (p_reference.reqReferenceFormate ());
      }
    else
      {
        m_vReferences.push_back (p_reference.clone ());
      }
    POSTCONDITION (referenceEstDejaPresente (p_reference));
    INVARIANTS ();
  }


  /**
   * \fn void Bibliographie::copierVecteur(const Bibliographie& p_bibliographie)
   * \brief Cette méthode copie le contenu et ajoute chaque élément référence dans le         * vecteur
   * \param[in] p_bibliographie la bibliographie source
   * \pre m_VReferences ne doit pas être vide
   */
  void
  Bibliographie::copierVecteur (const Bibliographie & p_bibliographie)
  {
    PRECONDITION (m_vReferences.empty ());
    vector <unique_ptr < Reference >>::const_iterator iter;
    for (iter = p_bibliographie.m_vReferences.begin (); iter != p_bibliographie.m_vReferences.end (); iter++ )
      {
        ajouterReference (*(*iter));
      }
    INVARIANTS ();
  }


  /**
   * \fn Bibliographie::viderVecteur()
   * \brief Cette méthode supprimer tous les éléments du vecteur m_vReferences en utilisant des itérateurs et vide le vecteur, pour libérer de la mémoire occupe par les    *objets références
   */
  void
  Bibliographie::viderVecteur ()
  {

    m_vReferences.clear ();

    INVARIANTS ();
  }


  /**
   * \fn Bibliographie::verifiePresentIdentifiant (const std::string& p_identifiant)
   * \brief Cette méthode valide si le vecteur contient la référence qui a comme identifiant p_identifiant
   * \return Retourne vrai si la référence existe et non si ce n`est pas le cas
   *
   */
  bool
  Bibliographie::verifiePresentIdentifiant (const std::string& p_identifiant)
  {
    bool referenceExiste = false;
    vector <unique_ptr < Reference >>::const_iterator iter;
    for (iter = m_vReferences.begin (); iter != m_vReferences.end (); iter++)
      {
        if ((*iter) -> reqIdentifiant () == p_identifiant)
          {
            referenceExiste = true;
          }
      }
    return referenceExiste;
  }


  /**
   * \fn void suprimerReference (const std::string& p_identifiant);
   * \brief Cette méthode supprime la référence dont l’identifiant est reçue en paramètre
   * \return Retourne une referenceAbsenteException s’il n’y a pas de référence qui possède cet identifiant dans la bibliographie
   *
   */
  void
  Bibliographie::supprimerReference (const string& p_identifiant)
  {
    if (!verifiePresentIdentifiant (p_identifiant))
      {
        throw ReferenceAbsenteException ("Impossible de supprimer la référence, car elle n`existe pas !");
      }


    vector <unique_ptr < Reference >>::const_iterator iter;
    for (iter = m_vReferences.begin (); iter != m_vReferences.end (); iter++)
      {
        if ((*iter) -> reqIdentifiant () == p_identifiant)
          {
            iter = m_vReferences.erase (iter);
            iter--;
          }
      }
    POSTCONDITION (!verifiePresentIdentifiant (p_identifiant));
    INVARIANTS ();
  }


  /**
   * \fn Bibliographie::reqBibliographieFormate() const
   * \brief Construit un format contenant les informations des références de la bibliographie
   * \return Retourne les informations formatées de toutes les références de la bibliographie sous forme de chaine de caractère
   *
   */
  string
  Bibliographie::reqBibliographieFormate () const
  {
    ostringstream os;
    os << "Bibliographie" << endl;
    os << "=================================================================" << endl;
    int ajoutPourChaqueReference = 1;
    for (const unique_ptr<Reference>& reference : m_vReferences)
      {


        os << "[" << ajoutPourChaqueReference++ << "] " << reference -> reqReferenceFormate () << endl; // [1] pour la première référence et ainsi de suite
      }
    return os.str ();
  }


  /**
   * \fn Bibliographie::verifieInvariant() const
   * \brief Fait la vérification du nom de la bibliographie
   */
  void
  Bibliographie::verifieInvariant () const
  {
    INVARIANT (!m_nomBibliographie.empty ());
  }
}