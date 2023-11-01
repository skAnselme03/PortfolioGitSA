/**
 * \file Journal.h
 * \class Journal
 *
 * \brief La classe Journal représente les références de la catégorie Journal
 *
 *
 *      Attributs: m_nom // le nom du journal dans laquelle a été publiée la référence, ne doit pas être vide
 *                 m_volume // le volume que commence la référence publiée (entier plus grand que 0)
 *                 m_numero // le numéro que commence la référence publiée (entier plus grand que 0)
 *                 m_page // la page que commence la référence publiée (entier plus grand que 0)
 *
 *
 *
 *\author Florence Lariviere
 * \date 2023-07-01
 * \version 1.0
 */

#ifndef JOURNAL_H
#define JOURNAL_H

#include "Reference.h"
#include <string>
#include <memory>

/**
 * \namespace biblio
 * \ le namespace dans lequel la bibliographique va être contenue
 */
namespace biblio
{

  /**
   * \class Journal
   * \brief La classe permet de créer un journal
   */

  class Journal : public Reference
  {
  public:
    Journal (const std::string& p_auteurs, const std::string& p_titre, const unsigned int& p_annee, const std::string& p_identifiant, const std::string& p_nom, const unsigned int& p_volume, const unsigned int& p_numero, const unsigned int& p_page);


    virtual const std::string reqReferenceFormate () const;
    virtual std::unique_ptr<Reference> clone () const;
    const std::string reqNom () const;
    int reqVolume () const;
    int reqNumero () const;
    int reqPage () const;


  private:
    std::string m_nom; // Le nom du journal , variable qui permet de stocker le nom du journal
    int m_volume; // Le volume du journal, variable qui permet de stocker le volume du journal
    int m_numero; // Le numéro du journal, variable qui permet de stocker le numéro du journal
    int m_page; // La page, variable qui permet de stocker la page du journal
    void verifieInvariant () const;

  };

}// fin du namespace biblio


#endif /* JOURNAL_H */
