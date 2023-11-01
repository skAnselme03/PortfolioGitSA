/**
 * \file OuvrageInterface.h
 * \brief Déclaration de la classe OuvrageInteface
 * \author Florence Lariviere, Aleck Desrochers, Anne-Marie Rochette et Stéphanie Anselme
 * \Date juillet 2023
 * \version 3
 */


#ifndef _OUVRAGEINTERFACE_H
#define _OUVRAGEINTERFACE_H

#include "ui_OuvrageInterface.h"

class OuvrageInterface : public QDialog
{
  Q_OBJECT
public:
  OuvrageInterface ();
  virtual ~OuvrageInterface ();

  /**********************/
  /*    ACCESSEURS      */
  /**********************/
  //
  QString getAuteur () const;
  QString getTitre () const;
  QString getVille () const;
  QString getEditeur () const;
  int getAnnee () const;
  QString getIdentifiant () const;

private slots:
  void validerEnregistrement ();

private:
  Ui::OuvrageInterface widget;
};

#endif /* _OUVRAGEINTERFACE_H */
