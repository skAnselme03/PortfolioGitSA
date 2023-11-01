/**
 * \file Bibliographie.h
 * \class Bibliographie
 *
 * \brief La classe Bibliographie permet de faire la gestion des Références
 *
 *
 *      La classe contient un constructeur avec un paramètre qui initialise
 *      le nom des bibliographies au moment de leur construction.
 *      La classe contient aussi un accesseur pour lire l`attribut
 *
 *
 *
 *\author Florence Lariviere
 * \date 2023-07-01
 * \version 1.0
 */



#ifndef BIBLIOGRAPHIE_H
#define BIBLIOGRAPHIE_H

#include <vector>
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
   * \class Bibliographie
   * \brief Elle permet de faire une bibliographie
   */
  class Bibliographie

  {
  public:

    Bibliographie (const std::string& p_nomBibliographie);
    Bibliographie (const Bibliographie& p_bibliographie);
    ~Bibliographie ();

    const std::string reqNomBibliographie () const;
    std::string reqBibliographieFormate () const;

    void ajouterReference (const Reference& p_reference);
    void supprimerReference (const std::string& p_identifiant);

    const Bibliographie& operator= (const Bibliographie& p_bibliographie);
    bool verifiePresentIdentifiant (const std::string&p_identifiant);


  private:
    std::vector<std::unique_ptr<Reference>>m_vReferences;
    std::string m_nomBibliographie;
    void verifieInvariant () const;
    void viderVecteur ();
    void copierVecteur (const Bibliographie& p_bibliographie);
    bool referenceEstDejaPresente (const Reference& p_reference)const;




  };

}// fin du namespace biblio


#endif /* BIBLIOGRAPHIE_H */

