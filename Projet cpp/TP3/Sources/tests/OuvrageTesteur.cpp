/*
 *\file OuvrageTesteur.cpp
 * \brief Les tests unitaires pour la classe Ouvrage
 * \author Florence Lariviere
 * \date 2023-07-01
 */



#include "Ouvrage.h"
#include "ContratException.h"
#include "validationFormat.h"

#include <gtest/gtest.h>
#include <string>
#include <memory>

using namespace biblio;

using namespace util;

using namespace std;


/**
 * \brief C`est le test du constructeur Ouvrage::Ouvrage (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant, const string& p_editeur, const string& p_ville) :
 * cas valide:
 * ConstructeurValide: le constructeur est valide lorsque tous les paramètres sont valides
 * Les cas invalides:
 * ConstructeurEditeurVide: Il manque le nom de l’éditeur
 * ConstructeurVilleVide: Il manque le nom de la ville
 */



TEST (Ouvrage, ConstructeurValide)
{
  Ouvrage unOuvrage ("Frederic Levesque", "Precis de droit quebecois des obligations",  2022, "ISBN 978-0-387-77591-3", "New York", "SGM");

  ASSERT_EQ (2022, unOuvrage.reqAnnee ());
  ASSERT_EQ ("ISBN 978-0-387-77591-3", unOuvrage.reqIdentifiant ());
  ASSERT_EQ ("New York", unOuvrage.reqVille ());
  ASSERT_EQ ("SGM", unOuvrage.reqEditeur ());

  ASSERT_TRUE (validerCodeIsbn (unOuvrage.reqIdentifiant ()));
  ASSERT_TRUE (validerFormatNom (unOuvrage.reqVille ()));
  ASSERT_TRUE (validerFormatNom (unOuvrage.reqEditeur ()));
}


TEST (Ouvrage, ConstructeurVilleVide)
{
  ASSERT_THROW (Ouvrage UnOuvrage ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3", "", "SGM"), PreconditionException) << "Le nom de la ville ne doit pas être vide";
}


TEST (Ouvrage, ConstructeurEditeurVide)
{
  ASSERT_THROW (Ouvrage UnOuvrage ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3", "New York", ""), PreconditionException) << "L`éditeur ne doit pas être vide";
}

/**
 * \class OuvrageValide
 * \brief Fixture OuvrageValide,  créer un objet Ouvrage valide pour les tests
 */
class OuvrageValide : public::testing::Test
{

public:


  OuvrageValide () : t_ouvrage ("Frederic Levesque", "Precis de droit quebecois des obligations",  2022, "ISBN 978-0-387-77591-3", "New York", "SGM") { };

  Ouvrage t_ouvrage;
} ;


/**
 * \fn string Ouvrage::reqVille () const
 * \brief test l`accesseur pour le nom de la ville d`édition
 * reqVille: vérifie le retour du format de la ville
 * cas invalide: s`il n`y a pas de nom de ville
 */

TEST_F (OuvrageValide, reqVille)
{
  ASSERT_EQ ("New York", t_ouvrage.reqVille ());
}


/**
 * \fn string Ouvrage::reqEditeur () const
 * \brief test l`accesseur pour le nom de l`éditeur de l`ouvrage
 * reqEditeur: vérifie le retour du format de l`éditeur
 * cas invalide: s`il n`y a pas d`éditeur
 */
TEST_F (OuvrageValide, reqEditeur)
{
  ASSERT_EQ ("SGM", t_ouvrage.reqEditeur ());
}


/**
 * \fn string Ouvrage::reqReferenceFormate() const
 * \brief Tester le retour du format de la référence
 */
TEST_F (OuvrageValide, reqReferenceFormate)
{
  ASSERT_EQ ("Frederic Levesque. Precis de droit quebecois des obligations. New York: SGM, 2022. ISBN 978-0-387-77591-3. ", t_ouvrage.reqReferenceFormate ());
}


/**
 * \fn virtual Reference*clone () const;
 * \brief Test si la copie allouée sur le monceau de l'objet courant a bien été
 *  effectué,  vérifie pour auteurs, titre, nom, volume,  numéro, page, année, identifiant
 */
TEST_F (OuvrageValide, Clone)
{
  unique_ptr<Reference> cloneOuvrage = t_ouvrage.clone ();

  // Vérifie que l'objet cloné est du type Journal
  Ouvrage* OuvragePointeur = dynamic_cast<Ouvrage*> (cloneOuvrage.get ()); // dynamic_cast: conversion du pointeur en classe de base en un pointeur en classe dérivée
  ASSERT_TRUE (OuvragePointeur != nullptr); // le pointeur ne pointe pas vers une adresse nulle

  // Vérifie que les attributs de l'objet cloné sont identiques à ceux de l'objet original
  ASSERT_EQ (t_ouvrage.reqAuteurs (), OuvragePointeur->reqAuteurs ());
  ASSERT_EQ (t_ouvrage.reqTitre (), OuvragePointeur->reqTitre ());
  ASSERT_EQ (t_ouvrage.reqAnnee (), OuvragePointeur->reqAnnee ());
  ASSERT_EQ (t_ouvrage.reqIdentifiant (), OuvragePointeur->reqIdentifiant ());
  ASSERT_EQ (t_ouvrage.reqVille (), OuvragePointeur -> reqVille ());
  ASSERT_EQ (t_ouvrage.reqEditeur (), OuvragePointeur -> reqEditeur ());
}
