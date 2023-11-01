/**
 * \file JournalInterface.h
 * \brief Déclaration de la classe JournalInterface
 * \author Florence Lariviere, Aleck Desrochers, Anne-Marie Rochette et Stéphanie Anselme
 * \Date juillet 2023
 * \version 3
 */



#ifndef _JOURNALINTERFACE_H
#define _JOURNALINTERFACE_H

#include "ui_JournalInterface.h"

class JournalInterface : public QDialog
{
  Q_OBJECT
public:
  JournalInterface ();
  virtual ~JournalInterface ();

  /**********************/
  /*    ACCESSEURS      */
  /**********************/
  //

  QString getAuteur () const;
  QString getNom () const;
  QString getTitre () const;
  int getVolume () const;
  int getNumero () const;
  int getPage () const;
  int getAnnee () const;
  QString getIdentifiant () const;

private slots:
  void validerEnregistrement ();

private:
  Ui::JournalInterface widget;
};

#endif /* _JOURNALINTERFACE_H */
