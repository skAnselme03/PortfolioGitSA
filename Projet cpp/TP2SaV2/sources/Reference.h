/*
 * \file Reference.h
 * \brief Classe de base abstraite Reference
 * \author Stéphanie Anselme
 * \date 2023-07-14
 * \version 1.0
 */

#ifndef REFERENCE_H
#define REFERENCE_H
#include <string>
namespace biblio
{

  class Reference
  {
    /**************************/
    /*       ATTRIBUTS        */
    /**************************/
  private:
    std::string m_auteurs; //Auteur(s) de la  référence
    std::string m_titre; //Titre de la référence
    unsigned int m_annee; // Année d'édition de la référence
    std::string m_identifiant; // Identifiant de la référence (ISSN OU ISBN)
    void verifieInvariant () const;

  public:
    /**************************/
    /*      CONSTRUCTEUR      */
    /**************************/
    Reference (const std::string& p_auteurs,
               const std::string& p_titre,
               const unsigned int& p_annee,
               const std::string& p_identifiant);
    virtual ~Reference ();


    /**************************/
    /*       ACCESSEURS       */
    /**************************/
    std::string getAuteurs () const;
    std::string getTitre () const;
    unsigned int getAnnee () const;
    std::string getIdentifiant () const;

    /**************************/
    /*       MUTATEURS        */
    /**************************/
    void setAnnee (const unsigned int& p_annee);

    /**************************/
    /*       MÉTHODES         */
    /**************************/
    bool operator== (const Reference& p_reference) const;
    virtual const std::string regReferenceFormate () const;
    virtual Reference* clone () const = 0;



  };
}
#endif /* REFERENCE_H */

