/**
 * \file BibliographieInterface.cpp
 * \brief Déclaration de la classe BibliographieInterface, la fenêtre de suppression de reference
 * \author Florence Lariviere, Aleck Desrochers, Stéphanie Anselme, Anne-Marie Rochette
 * \Date juillet 2023
 * \version 1
 */

#include "BibliographieInterface.h"
#include "Bibliographie.h"
#include "Ouvrage.h"
#include "Journal.h"
#include "ReferenceException.h"
#include "OuvrageInterface.h"
#include "JournalInterface.h"
#include "SupprimerUneReference.h"

#include <string>
#include <QMessageBox>

using namespace std;
using namespace biblio;


/**
 * \fn BibliographieInterface::BibliographieInterface (QWidget *parent) : QMainWindow (parent), m_biblio ("La bibliographie")
 * \brief Le constructeur qui construit un objet QT
 * \param[in] * parent Il prend une valeur par défaut 0 qui représente les dimensions de l`interface
 */
BibliographieInterface::BibliographieInterface () : m_bibliographie ("La bibliographie")
{
  widget.setupUi (this);
}


/**
 * \fn BibliographieInterface::~BibliographieInterface() {}
 * \brief Le destructeur par défaut
 */
BibliographieInterface::~BibliographieInterface () { }


/**
 * \brief C`est une méthode qui teste la présence de doublon dans un journal
 * Il y a un message qui apparait s`il y a lieu
 * \param[in] les paramètres du constructeur Journal
 */
void
BibliographieInterface::ajouterJournal (const string& p_auteurs,
                                        const string& p_titre, int p_annee,
                                        const string& p_identifiant,
                                        const string& p_nom, int p_volume,
                                        int p_numero, int p_page)
{
  try
    {

      Journal unJournal (p_auteurs, p_titre, p_annee, p_identifiant,
                         p_nom, p_volume, p_numero, p_page);
      m_bibliographie.ajouterReference (unJournal);
    }
  catch (ReferenceDejaPresenteException& e)
    {
      QString message = (e.what (), "La Référence est déjà présente");
      QMessageBox::information (this, "ERREUR", message);
    }
}


/**
 * \brief C`est une méthode qui teste la présence de doublon dans un ouvrage
 * Il y a un message qui apparait s`il y a lieu
 * \param[in] les paramètres du constructeur Ouvrage
 */
void
BibliographieInterface::ajouterOuvrage (const string& p_auteurs,
                                        const string& p_titre, int p_annee,
                                        const string& p_identifiant,
                                        const string& p_editeur,
                                        const string& p_ville)
{
  try
    {
      Ouvrage unOuvrage (p_auteurs, p_titre, p_annee, p_identifiant, p_editeur, p_ville);
      m_bibliographie.ajouterReference (unOuvrage);
    }
  catch (ReferenceDejaPresenteException& e)
    {
      QString message = (e.what (), "La Référence est déjà présente");
      QMessageBox::information (this, "ERREUR", message);
    }
}


/**
 * \brief C`est une méthode qui teste la présence de doublon dans la Bibliographie m_biblio
 * Il y a un message qui apparait s`il y a lieu
 * \param[in] p_identifiant c`est un string qui représence l`identifiant de la reference
 * Il doit être un format valide donc valider par validerCodeIssn et validerCodeIsbn
 */
void
BibliographieInterface::supprimerUneReference (const string& p_identifiant)
{
  try
    {
      m_bibliographie.supprimerReference (p_identifiant);
    }
  catch (ReferenceAbsenteException& e)
    {
      QString message = (e.what ());
      QMessageBox::information (this, "ERREUR", message);
    }
}


/**
 * \brief Cette méthode appelle le constructeur OuvrageInerface et
 *        met à jour le widget.affichage selon m_biblio
 */
void
BibliographieInterface::dialogOuvrage ()
{
  OuvrageInterface ouvrage; //recupérer les données de l'interface Ouvrage
  //ouvrir la boite de dialoge d'ouvrage et l'exécuter
  if (ouvrage.exec ())
    {

      //conversion Qstring en string
      std::string auteur = ouvrage.getAuteur ().toStdString ();
      std::string titre = ouvrage.getTitre ().toStdString ();
      std::string identifiant = ouvrage.getIdentifiant ().toStdString ();
      std::string editeur = ouvrage.getEditeur ().toStdString ();
      std::string ville = ouvrage.getVille ().toStdString ();
      int annee = ouvrage.getAnnee ();

      //ajouter un nouvel ouvrage saisie
      ajouterOuvrage (auteur, titre, annee, identifiant, editeur, ville);
      widget.affichageBiblio->setText (m_bibliographie.reqBibliographieFormate ().c_str ());

    }
}


/**
 * \brief Cette méthode appelle le constructeur JoutnalInterface et
 *        met à jour le widget.affichage selon m_biblio
 */
void
BibliographieInterface::dialogJournal ()
{
  JournalInterface journal;
  //ouvrir la boite de dialoge d'ouvrage et l'exécuter
  if (journal.exec ())
    {
      //conversion Qstring en string
      std::string auteur = journal.getAuteur ().toStdString ();
      std::string titre = journal.getTitre ().toStdString ();
      std::string nom = journal.getNom ().toStdString ();
      std::string identifiant = journal.getIdentifiant ().toStdString ();
      int volume = journal.getVolume ();
      int numero = journal.getNumero ();
      int page = journal.getPage ();
      int annee = journal.getAnnee ();


      //ajouter un nouvel ouvrage saisie
      ajouterJournal (auteur, titre, annee, identifiant, nom,
                      volume, numero, page);

      //afficher le vecteur des journaux dans l'interface bibliographie
      widget.affichageBiblio->setText (m_bibliographie.reqBibliographieFormate ().c_str ());
    }
}


/**
 * \brief Cette méthode supprime une reférence selon le
 *        code de réfence soit un ISBN soit un ISSN
 */
void
BibliographieInterface::dialogSupprimer ()
{
  //Bibliographie b1 (m_bibliographie);
  SupprimerUneReference supRef;
  QMessageBox msgBoxConfirm;

  if (supRef.exec ())
    {
      try
        {
          std::string identifiant = supRef.reqIdentifiant ().toStdString ();

          //création d'une boite de dialogue pour
          //confirmer la supprésion d'une référence
          msgBoxConfirm.setText (QString ("La réferenc ") +
                                 QString::fromStdString (identifiant) +
                                 QString (" sera supprimer."));
          msgBoxConfirm.setInformativeText ("Voulez-vous vraiment la supprimer?");
          //msgBoxConfirm.setStandardButtons (QMessageBox::Save |QMessageBox::Cancel);
          //msgBoxConfirm.setDefaultButton (QMessageBox::Save);
          QPushButton* btnConfirmer = msgBoxConfirm.addButton ("Confirmer", QMessageBox::AcceptRole);
          msgBoxConfirm.addButton ("Annuler", QMessageBox::RejectRole);
          msgBoxConfirm.setDefaultButton (btnConfirmer);
          msgBoxConfirm.setIcon (QMessageBox::Question);
          //int ret = msgBoxConfirm.exec ();
          msgBoxConfirm.exec ();

          /*switch (ret)
            {
                //confirmation de suppression
              case QMessageBox::Save:
                m_bibliographie.supprimerReference (identifiant);
                widget.affichageBiblio->setText (m_bibliographie.reqBibliographieFormate ().c_str ());
                break;
              case QMessageBox::Cancel:
                break;
            }*/

          if (msgBoxConfirm.clickedButton () == btnConfirmer)
            {
              m_bibliographie.supprimerReference (identifiant);
              widget.affichageBiblio->setText (m_bibliographie.reqBibliographieFormate ().c_str ());
            }
        }
      catch (ReferenceAbsenteException& e)
        {
          QString message = (e.what ());
          QMessageBox::information (this, "ERREUR", message);
        }
    }
}

