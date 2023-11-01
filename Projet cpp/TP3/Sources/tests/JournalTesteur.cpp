/*
 *\file JournalTesteur.cpp
 * \brief Les tests unitaires pour la classe Journal
 * \author Florence Lariviere
 * \date 2023-07-01
 */


#include "Journal.h"
#include "ContratException.h"
#include "validationFormat.h"

#include <gtest/gtest.h>
#include <string>
#include <memory>

using namespace biblio;

using namespace util;
using namespace std;


/**
 * C`est le test du constructeur Journal::Journal (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant, const string& p_nom, const unsigned int& p_volume, const unsigned int& p_numero, const unsigned int& p_page):
 * Cas valide:
 * ConstructeurValide: Le constructeur est valide lorsque tous les paramètres sont valides
 * Les cas invalides:
 * ConstructeurNomVide: Il manque le nom
 * ConstructeurNumeroInvalide: Le numéro doit être dans un format valide, invalide si pas plus grand que 0
 * ConstructeurPageInvalide: La page doit être dans un format valide, invalide si pas plus grand que 0
 * ConstructeurVolumeInvalide: Le volume doit être dans un format valide, invalide si pas plus grand que 0
 *
 */

TEST (Journal, ConstructeurValide)
{
  Journal unJournal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 24, 6, 365);


  ASSERT_EQ ("Contrat, responsabilité, exécution et extinction", unJournal.reqNom ());
  ASSERT_EQ (24, unJournal.reqVolume ());
  ASSERT_EQ (6, unJournal.reqNumero ());
  ASSERT_EQ (365, unJournal.reqPage ());


  ASSERT_TRUE (unJournal.reqVolume () > 0);
  ASSERT_TRUE (unJournal.reqNumero () > 0);
  ASSERT_TRUE (unJournal.reqPage () > 0);
  ASSERT_TRUE (validerCodeIssn (unJournal.reqIdentifiant ()));

}


TEST (Journal, ConstructeurNomVide)
{
  ASSERT_THROW (Journal unJournal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "", 24, 6, 365), PreconditionException) <<  "Le nom du journal ne peut être vide";
}


TEST (Journal, ConstructeurNumeroInvalide)
{
  ASSERT_THROW (Journal unJournal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 24, 0, 365), PreconditionException) << " Le numéro du journal doit être plus grand que 0";
}


TEST (Journal, ConstructeurPageInvalide)
{
  ASSERT_THROW (Journal unJournal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 24, 6, 0), PreconditionException) << "La page doit être plus grand que 0";
}


TEST (Journal, ConstructeurVolumeInvalide)
{
  ASSERT_THROW (Journal unJournal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 0, 6, 365), PreconditionException) << "Le volume doit être plus grand que 0 ";
}

/**
 * \class JournalValide
 * \brief Fixture JournalValide, créer un objet Journal valide pour les tests
 */

class JournalValide : public::testing::Test
{

public:


  JournalValide () : t_journal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 24, 6, 365) { };

  Journal t_journal;
} ;


/**
 * \fn string Journal:: reqNom() const
 * \brief test l`accesseur pour le nom du journal
 * reqNom: vérifie le retour pour le nom du journal
 * cas invalide: s`il n`y a pas de titre
 */

TEST_F (JournalValide, reqNom)
{
  ASSERT_EQ ("Contrat, responsabilité, exécution et extinction", t_journal.reqNom ());

}


/**
 * \fn int Journal::reqVolume () const
 * \brief test l`accesseur pour le volume du journal
 * reqVolume: vérifie le retour du volume
 * cas invalide: si le volume n`est pas plus grand que 0
 */
TEST_F (JournalValide, reqVolume)
{
  ASSERT_EQ (24, t_journal.reqVolume ());
}


/**
 * \fn int Journal::reqNumero () const
 * \brief test l`accesseur pour le numéro du journal
 * reqVolume: vérifie le retour du numéro
 * cas invalide: si le numéro n`est pas plus grand que 0
 */
TEST_F (JournalValide, reqNumero)
{
  ASSERT_EQ (6, t_journal.reqNumero ());
}


/**
 * \fn int Journal::reqPage () const
 * \brief test l`accesseur pour la page du journal
 * reqVolume: vérifie le retour de la page
 * cas invalide: si la page n`est pas plus grand que 0
 */
TEST_F (JournalValide, reqPage)
{
  ASSERT_EQ (365, t_journal.reqPage ());
}


/**
 * \fn Journal::reqReferenceFormate () const
 * \brief Tester le retour du format de la référence
 */
TEST_F (JournalValide, reqReferenceFormate)
{

  ASSERT_EQ ("Frederic Levesque. Precis de droit quebecois des obligations. Contrat, responsabilité, exécution et extinction, vol. 24, no. 6, pp. 365, 2022. ISSN 1937-4771.", t_journal.reqReferenceFormate ());
}


/**
 * \fn  virtual Reference* clone () const;
 * \brief Teste si la copie allouée sur le monceau de l'objet courant a bien été
 *  effectué,  vérifie pour auteurs, titre, nom, volume,  numéro, page, année, identifiant
 */
TEST_F (JournalValide, Clone)
{

  unique_ptr<Reference> cloneJournal = t_journal.clone ();

  // Vérifie que l'objet cloné est du type Journal
  Journal* cloneJournalPointeur = dynamic_cast<Journal*> (cloneJournal.get ()); // dynamic_cast: conversion du pointeur en classe de base en un pointeur en classe dérivée
  ASSERT_TRUE (cloneJournalPointeur != nullptr); // le pointeur ne pointe pas vers une adresse nulle

  // Vérifie que les attributs de l'objet cloné sont identiques à ceux de l'objet original
  ASSERT_EQ (t_journal.reqAuteurs (), cloneJournalPointeur->reqAuteurs ());
  ASSERT_EQ (t_journal.reqTitre (), cloneJournalPointeur->reqTitre ());
  ASSERT_EQ (t_journal.reqNom (), cloneJournalPointeur->reqNom ());
  ASSERT_EQ (t_journal.reqVolume (), cloneJournalPointeur->reqVolume ());
  ASSERT_EQ (t_journal.reqNumero (), cloneJournalPointeur->reqNumero ());
  ASSERT_EQ (t_journal.reqPage (), cloneJournalPointeur->reqPage ());
  ASSERT_EQ (t_journal.reqAnnee (), cloneJournalPointeur->reqAnnee ());
  ASSERT_EQ (t_journal.reqIdentifiant (), cloneJournalPointeur->reqIdentifiant ());
}






