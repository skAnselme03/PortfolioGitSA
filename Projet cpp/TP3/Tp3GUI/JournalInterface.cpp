/**
 * \file JournalInterface.h
 * \brief Déclaration de la classe JournalInterface
 * \author Florence Lariviere, Aleck Desrochers, Stéphanie Anselme, Anne-Marie Rochette
 * \Date juillet 2023
 * \version 2
 */
#include "JournalInterface.h"
#include "validationFormat.h"
#include "QMessageBox"
#include "ctype.h"


/**
 * \fn JournalInterface::JournalInterface()
 * \brief Le constructeur de JournalInterface, il construit un objet QT
 */
JournalInterface::JournalInterface ()
{
  widget.setupUi (this);
}


JournalInterface::~JournalInterface () { }


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.auteurs
 * \return QString (l`auteur du journal)
 */
QString
JournalInterface::getAuteur () const
{
  return widget.auteur->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.nom
 * \return QString (le nom du journal)
 */
QString
JournalInterface::getNom () const
{

  return widget.nom->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.titre
 * \return QString (le titre du journal)
 */
QString
JournalInterface::getTitre () const
{
  return widget.titre->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.volume
 * \return int (le volume du journal)
 */
int
JournalInterface::getVolume () const
{
  return widget.volume->value ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.numero
 * \return int (le numéro du journal)
 */
int
JournalInterface::getNumero () const
{
  return widget.numero->value ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.page
 * \return int (la page du journal)
 */
int
JournalInterface::getPage () const
{
  return widget.page->value ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.annee
 * \return int (l`année du journal)
 */
int
JournalInterface::getAnnee () const
{
  return widget.annee->value ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.identifiant
 * \return QString (l`identifiant du journal)
 */
QString
JournalInterface::getIdentifiant () const
{
  return widget.codeISSN->text ();
}


/**
 * \fn JournalInterface::validerEnregistrement ()
 * \brief La méthode valide les variables de journal
 * si les critères ne sont pas respectés, il y a un message
 */

void
JournalInterface::validerEnregistrement ()
{
  if (!util::validerFormatNom (getAuteur ().toStdString ()))
    {
      QString message ("Le nom des auteurs doit être valide");
      QMessageBox::information (this,  "ERREUR", message);
      return;
    }
  if (!util::validerCodeIssn (getIdentifiant ().toStdString ()))
    {
      QString message ("L`identifiant doit être un code ISSN valide");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  if (getTitre ().isEmpty ())
    {
      QString message ("Le titre doit être non vide ");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  if (getAnnee () <= 0)
    {
      QString message ("L`année doit être  plus grand que 0");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }

  if (getNom ().isEmpty ())
    {
      QString message ("Le nom doit être non vide");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  if (getVolume () < 0)
    {
      QString message ("Le volume doit être  plus grand que 0");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  if (getNumero () < 0)
    {
      QString message ("Le numéro doit être  plus grand que 0");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }

  if (getPage () < 0)
    {
      QString message ("La page doit être  plus grande que 0");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }

  accept ();

}

