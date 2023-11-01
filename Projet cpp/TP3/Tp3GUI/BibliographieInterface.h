/**
 * \file BibliographieInterface.h
 * \brief Déclaration de la classe BibliographieInterface, la fenêtre de suppression de reference
 * \author Florence Lariviere, Aleck Desrochers, Anne-Marie Rochette et Stéphanie Anselme
 * \Date juillet 2023
 * \version 3
 */


#ifndef _BIBLIOGRAPHIEINTERFACE_H
#define _BIBLIOGRAPHIEINTERFACE_H

#include "ui_BibliographieInterface.h"
#include "Bibliographie.h"
#include <string>

class BibliographieInterface : public QMainWindow
{
  Q_OBJECT
public:
  BibliographieInterface ();
  virtual ~BibliographieInterface ();


  void ajouterJournal (const std::string& p_auteurs, const std::string& p_titre,
                       int p_annee, const std::string& p_identifiant,
                       const std::string& p_nom,
                       int p_volume, int p_numero, int p_page);

  void ajouterOuvrage (const std::string& p_auteurs, const std::string& p_titre,
                       int p_annee, const std::string& p_identifiant,
                       const std::string& p_editeur,
                       const std::string& p_ville);
  void supprimerUneReference (const std::string& p_identifiant);


  //actions
private slots:
  //boite de dialogue pour ouvrir le formulaire d'ouvrage
  void dialogOuvrage ();
  //boite de dialogue pour ouvrir le formulaire de journal
  void dialogJournal ();
  //boite de dialogue pour ouvrir le formulaire Supprimer
  void dialogSupprimer ();

private:
  Ui::BibliographieInterface widget;
  biblio::Bibliographie m_bibliographie;
};

#endif /* _BIBLIOGRAPHIEINTERFACE_H */
