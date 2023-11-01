/**
 * \file OuvrageInterface.cpp
 * \brief Déclaration de la classe OuvrageInterface
 * \author Florence Lariviere, Aleck Desrochers, Stéphanie Anselme, Anne-Marie Rochette
 * \Date juillet 2023
 * \version 2
 *
 */

#include "OuvrageInterface.h"
#include "validationFormat.h"
#include <QMessageBox>


/**
 * \fn OuvrageInterface::OuvrageInterface()
 * \brief Le constructeur de OuvrageInterface, il construit un objet QT
 */
OuvrageInterface::OuvrageInterface ()
{
  widget.setupUi (this);
}


OuvrageInterface::~OuvrageInterface () { }


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.auteur
 * \return QString (l`auteur de l`ouvrage)
 */
QString
OuvrageInterface::getAuteur () const
{
  return widget.auteur->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.titre
 * \return QString (le titre de l`ouvrage)
 */
QString
OuvrageInterface::getTitre () const
{
  return widget.titre->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.ville
 * \return QString (la ville de l`ouvrage)
 */
QString
OuvrageInterface::getVille () const
{
  return widget.ville->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.editeur
 * \return QString (l`éditeur de l`ouvrage)
 */
QString
OuvrageInterface::getEditeur () const
{
  return widget.editeur->text ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.annee
 * \return int (l`année de l`ouvrage)
 */
int
OuvrageInterface::getAnnee () const
{
  return widget.annee->value ();
}


/**
 * \brief La méthode d`accès en lecture à l`attribut widget.identifiant
 * \return QString (l`identifiant de l`ouvrage)
 */
QString
OuvrageInterface::getIdentifiant () const
{
  return widget.codeISBN->text ();
}


/**
 * \fn OuvrageInterface::validerEnregistrement ()
 * \brief La méthode valide les variables de l`ouvrage
 * si les critères ne sont pas respectés, il y a un message
 */

void
OuvrageInterface::validerEnregistrement ()
{
  if (!util::validerFormatNom (getAuteur ().toStdString ()))
    {
      QString message ("Le nom des auteurs doit être valide");
      QMessageBox::information (this,  "ERREUR", message);
      return;
    }
  if (getTitre ().isEmpty ())
    {
      QString message ("Le titre doit être non vide ");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  if (!util::validerFormatNom (getVille ().toStdString ()))
    {
      QString message ("Le nom de la ville doit être valide");
      QMessageBox::information (this,  "ERREUR", message);
      return;
    }
  if (!util::validerFormatNom (getEditeur ().toStdString ()))
    {
      QString message ("Le nom de l`éditeur doit être valide");
      QMessageBox::information (this,  "ERREUR", message);
      return;
    }
  if (getAnnee () <= 0)
    {
      QString message ("L`année doit être  plus grand que 0");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }

  if (!util::validerCodeIsbn (getIdentifiant ().toStdString ()))
    {
      QString message ("L`identifiant doit être un code ISBN valide");
      QMessageBox::information (this, "ERREUR", message);
      return;
    }
  accept ();
}