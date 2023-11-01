/**
 * \file SupprimerUneReference.cpp
 * \brief Déclaration de la classe OuvrageInterface
 * \author Florence Lariviere, Aleck Desrochers, Stéphanie Anselme, Anne-Marie Rochette
 * \Date juillet 2023
 * \version
 *
 */



#include "SupprimerUneReference.h"
#include "validationFormat.h"
#include "BibliographieInterface.h"
#include <QMessageBox>


/**
 * \fn SupprimerUneReference::SupprimerUneReference ()
 * \brief C`est un constructeur qui est appelé automatiquement pour initialiser l`objet
 * Il est utilisé pour intialiser l`interface utilisateur (UI) du widget
 */
SupprimerUneReference::SupprimerUneReference ()
{
  widget.setupUi (this);
}


/**
 * \fn SupprimerUneReference::~SupprimerUneReference () { }
 * \brief C`est le destructeur qui est appelé automatiquement lorsque l`objet est détruit
 *
 */

SupprimerUneReference::~SupprimerUneReference () { }


/**
 * \fn SupprimerUneReference::reqIdentifiant ()const
 * \brief Lorsque la fonction reqIdentifiant() est appelée sur un objet de la classe "SupprimerUneReference", elle renvoie le texte contenu dans widget.idASupprimer
 * \return Une valeur de type string
 */

QString
SupprimerUneReference::reqIdentifiant ()const
{
  return widget.idASupprimer->text ();
}


/**
 * \fn void SupprimerUneReference::validerSupprimer ()
 * \brief la fonction "validerSupprimer" vérifie si le code ISBN et le code ISSN sont valides. Si l'un des codes est invalide, elle affiche un message d'erreur. Sinon, elle signale que la    * suppression a été validée avec succès
 * \return Elle ne renvoie aucune valeur
 */

void
SupprimerUneReference::validerSupprimer ()
{
  std::string id = reqIdentifiant ().toStdString ();
  if (!util::validerCodeIsbn (id) && !util::validerCodeIssn (id))
    {
      QString message ("Le code est invalide");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  else
    accept ();
}