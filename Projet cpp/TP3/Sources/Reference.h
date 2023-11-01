

/*
 * \file Reference.h
 * \class Reference
 *
 * \brief La classe Reference permet de manipuler et de maintenir les références.
 *
 *      La classe Reference prend des valeurs en paramètre et construit une référence avec.
 *      Cela doit être des références valides, l'utilisateur de la classe doit vérifier.
 *
 *      Attributs: m_cote // la cote de la reference (non nulle)
 *                 m_auteurs // string, le nom de l`auteur ou des auteurs de la référence, doit être dans un format valide
 *                 m_titre // Le titre de la référence (string) et doit être non vide
 *                 m_annee // L`année d`édition de la référence (un entier), doit être strictement plus grande que 0
 *                 m_identifiant // L`identifiant de la référence (un string), dépend du type de la référence, peut être un code ISBN, ISSN
 *
 *
 *\author Florence Lariviere
 * \date 2023-07-01
 * \version 1.0
 */

#ifndef REFERENCE_H
#define REFERENCE_H

#include <string>
#include <memory>

/**
 * \namespace biblio
 * \ le namespace dans lequel la bibliographique va être contenue
 */

namespace biblio
{

  /**
   * \class Reference
   * \brief La classe Reference permet de modéliser les références bibliographiques
   */

  class Reference
  {
  public:
    Reference (const std::string& p_auteurs, const std::string& p_titre, const unsigned int& p_annee, const std::string& p_identifiant);

    virtual
    ~Reference () { }; // le destructeur virtuel

    bool operator== (const Reference& p_reference) const;

    void asgAnnee (const unsigned int& p_annee);


    virtual const std::string reqReferenceFormate () const;
    unsigned int reqAnnee () const;
    std::string reqAuteurs () const;
    std::string reqIdentifiant () const;
    std::string reqTitre () const;
    virtual std::unique_ptr< Reference > clone () const = 0;



  private:
    void verifieInvariant () const;
    std::string m_auteurs; // Auteur(s) de la référence, variable qui permet de stocker les noms des auteurs de la référence
    std::string m_titre; // Titre de la référence, variable qui permet de stocker le titre de la référence bibliographique
    unsigned int m_annee; // Année d'édition de la référence, variable qui permet de stocker l'année de publication de la référence
    std::string m_identifiant; // soit ISSN ou ISBN, variable pour stocker l'identifiant de la référence

  };

}// fin du namespace biblio

#endif /* REFERENCE_H */

