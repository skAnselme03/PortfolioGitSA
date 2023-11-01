/**
 * \file SupprimerUneReference.h
 * \brief Déclaration de la classe SupprimerReference
 * \author Florence Lariviere, Aleck Desrochers, Anne-Marie Rochette et Stéphanie Anselme
 * \Date juillet 2023
 * \version 3
 */

#ifndef _SUPPRIMERUNEREFERENCE_H
#define _SUPPRIMERUNEREFERENCE_H

#include "ui_SupprimerUneReference.h"

class SupprimerUneReference : public QDialog
{
  Q_OBJECT
public:
  SupprimerUneReference ();
  virtual ~SupprimerUneReference ();
  //Accesseur
  QString reqIdentifiant ()const;
private slots:
  void validerSupprimer ();

private:
  Ui::SupprimerUneReference widget;
};

#endif /* _SUPPRIMERUNEREFERENCE_H */
