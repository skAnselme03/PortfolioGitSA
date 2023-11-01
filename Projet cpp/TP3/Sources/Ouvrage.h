/**
 * \file Ouvrage.h
 * \class Ouvrage
 *
 * \brief La classe Ouvrage représente les références de la catégorie Ouvrage
 *
 *
 *
 *      Attributs: m_editeur // le nom de l`éditeur de l`ouvrage, doit être un format valide
 *                 m_ville // la ville d`édition de l`ouvrage, doit être un format valide
 *
 *
 *
 *\author Florence Lariviere
 * \date 2023-07-01
 * \version 1.0
 */

#ifndef OUVRAGE_H
#define OUVRAGE_H

#include "Reference.h"
#include <string>
#include <memory>
#include <iostream>

/**
 * \namespace biblio
 * \ le namespace dans lequel la bibliographique va être contenue
 */

namespace biblio
{

  /**
   * \class Ouvrage
   * \brief Classe Ouvrage représente les références de la catégorie Ouvrage
   */
  class Ouvrage : public Reference

  {
  public:
    Ouvrage (const std::string& p_auteurs, const std::string& p_titre, const unsigned int& p_annee, const std::string& p_identifiant, const std::string& p_editeur, const std::string& p_ville);


    const std::string reqEditeur () const;
    const std::string reqVille () const;
    virtual const std::string reqReferenceFormate () const;
    virtual std::unique_ptr<Reference> clone ()const;


  private:
    void verifieInvariant () const;
    std::string m_ville;
    std::string m_editeur;


  };

}// fin du namespace biblio

#endif /* OUVRAGE_H */

