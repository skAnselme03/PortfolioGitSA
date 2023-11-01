/*
 *\file ReferenceTesteur.cpp
 * \brief Les tests unitaires pour la classe Reference
 * \author Florence Lariviere
 * \date 2023-07-01
 */

#include "Reference.h"
#include "ContratException.h"
#include "validationFormat.h"

#include <gtest/gtest.h>
#include <string>

using namespace biblio;
using namespace std;
using namespace util;

/*
 *\class ReferenceTest
 * \brief Cette classe va tester la classe Reference
 */
class ReferenceTest : public Reference
{

public:


  ReferenceTest (const string& p_auteurs, const string& p_titre, const unsigned int& p_annee, const string& p_identifiant) :
  Reference (p_auteurs, p_titre, p_annee, p_identifiant) { };


  unique_ptr<Reference>
  clone () const
  {
    return nullptr;
  };


} ;


/**
 * \brief C`est le test du constructeur Reference::Reference (std::string& p_auteurs,  std::string& p_titre, unsigned int& p_annee, std::string& p_identifiant) :
 * Cas valide:
 * Constructeur valide: Le constructeur est valide lorsque tous les paramètres sont valides
 * Les cas invalides:
 * ConstructeurAuteurVide: Il manque le nom de l`auteur
 * ConstructeurTitreVide: Il manque le titre
 * ConstructeurAnneeInvalide: Il faut que l`année soit plus grande que 0
 * ConstructeurIdentifiantVide: Il manque l`identifiant
 */


TEST (Reference, ConstructeurValide)
{
  ReferenceTest referenceTest ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3");

  ASSERT_EQ ("Frederic Levesque", referenceTest.reqAuteurs ());
  ASSERT_EQ ("Precis de droit quebecois des obligations", referenceTest.reqTitre ());
  ASSERT_EQ (2022, referenceTest.reqAnnee ());
  ASSERT_EQ ("ISBN 978-0-387-77591-3", referenceTest.reqIdentifiant ());


  ASSERT_TRUE (validerFormatNom (referenceTest.reqAuteurs ()));
  ASSERT_TRUE (referenceTest.reqAnnee () > 0);

}


TEST (Reference, ConstructeurAuteurVide)
{
  ASSERT_THROW (ReferenceTest referenceTest ("", "Precis de droit quebecois des obligations", 2022, "ISBM 978-0-387-77591-3"), PreconditionException) << "Le nom de l`auteur ne peut être vide";
}


TEST (Reference, ConstructeurTitreVide)
{
  ASSERT_THROW (ReferenceTest referenceTest ("Frederic Levesque", "", 2022, "ISBN 978-0-387-77591-3"), PreconditionException) << "Le titre ne peut être  vide";
}


TEST (Reference, ConstructeurAnneeInvalide)
{
  ASSERT_THROW (ReferenceTest referenceTest ("Frederic Levesque", "Precis de droit quebecois des obligations", 0, "ISBN 978-0-387-77591-3"), PreconditionException) << "L`année ne peut pas être 0";
}


TEST (Reference, ConstructeurIdendifiantVide)
{
  ASSERT_THROW (ReferenceTest referenceTest ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, ""), PreconditionException) << "L`identifiant ne peut pas être vide";
}

/**
 * \class ReferenceValide
 * \brief Fixture ReferenceValide, créer un objet Reference valide pour les tests
 */
class ReferenceValide : public ::testing::Test
{

public:


  ReferenceValide () : t_reference ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3") { };
  ReferenceTest t_reference;
} ;


/**
 * \brief Tester Reference::reqReferenceFormate() const
 * Cela vérifie le retour du format de la référence
 */
TEST_F (ReferenceValide, reqReferenceFormate)
{

  ASSERT_EQ ("Frederic Levesque. Precis de droit quebecois des obligations. ", t_reference.reqReferenceFormate ());
}


/**
 * \fn void Reference::asgAnnee(const unsigned int& p_annee)
 * \brief test Le mutateur de l`année d`édition
 * cas valide:
 * asgAnneeValide: l'année est plus grande que 0
 * cas invalide:
 * asgAnneeInvalide: l'année n'est pas plus grande que 0
 *
 */
TEST_F (ReferenceValide, asgAnneeValide)
{
  t_reference.asgAnnee (2022);
  ASSERT_EQ (2022, t_reference.reqAnnee ());
  ASSERT_TRUE (t_reference.reqAnnee () > 0);
}


TEST_F (ReferenceValide, asgAnneeInvalide)
{
  ASSERT_THROW (t_reference.asgAnnee (0), PreconditionException) << "L`année doit être plus grande que 0";
}


/**
 * \brief Tester bool Reference::operator == (const Reference& p_reference) const
 * cas valide:
 * operatorEgal : C`est valide si les deux références ont les mêmes noms d`auteurs, titres, années, identifiants
 * cas invalides:
 * operatorAuteursNonEgaux: les deux références n`ont pas le même nom d`auteur
 * operatorTitreNonEgal: les deux références n`ont pas le même titre
 * operatorAnneeNonEgal: les deux références n`ont pas la même année
 * operatorIdentifiantNonEgal: les deux références n`ont pas le même identifiant
 */
TEST_F (ReferenceValide, operatorEgal)
{
  ReferenceTest autreReference ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3");
  ASSERT_TRUE (t_reference == autreReference);

}


TEST_F (ReferenceValide, operatorAuteursNonEgaux)
{
  ReferenceTest autreReference ("Florence Lariviere", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3");
  ASSERT_FALSE (t_reference == autreReference);

}


TEST_F (ReferenceValide, operatorTitreNonEgal)
{
  ReferenceTest autreReference ("Frederic Levesque", "Valeurs mobilières et gestion de portefeuille", 2022, "ISBN 978-0-387-77591-3");
  ASSERT_FALSE (t_reference == autreReference);

}


TEST_F (ReferenceValide, operatorAnneeNonEgal)
{
  ReferenceTest autreReference ("Frederic Levesque", "Precis de droit quebecois des obligations", 2002, "ISBN 978-0-387-77591-3");
  ASSERT_FALSE (t_reference == autreReference);

}


TEST_F (ReferenceValide, operatorIdentifiantNonEgal)
{
  ReferenceTest autreReference ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-287-77561-4");
  ASSERT_FALSE (t_reference == autreReference);
}


/**
 * \fn string Reference::reqAuteurs() const
 * \brief test l`accesseur pour les auteurs
 * reqAuteurs: vérifie le retour de l`auteur
 * cas invalide: s`il n`y a pas d`auteur(s)
 */
TEST_F (ReferenceValide, reqAuteurs)
{
  ASSERT_EQ ("Frederic Levesque", t_reference.reqAuteurs ());

}


/**
 * \fn string Reference::reqIdentifiant () const
 * \brief test l`accesseur pour l`identifiant
 * reqIdentifiant: vérifie le retour de l`identifiant
 * cas invalide: s`il n`y a pas d`identifiant
 */
TEST_F (ReferenceValide, reqIdentifiant)
{
  ASSERT_EQ ("ISBN 978-0-387-77591-3", t_reference.reqIdentifiant ());
}


/**
 * \fn string Reference::reqTitre () const
 * \brief test l`accesseur pour le titre
 * reqTitre: vérifie le retour du titre
 * cas invalide: s`il n`y a pas de titre
 */
TEST_F (ReferenceValide, reqTitre)
{
  ASSERT_EQ ("Precis de droit quebecois des obligations", t_reference.reqTitre ());
}


/**
 * \fn unsigned int Reference::reqAnnee () const
 * \brief test l`accesseur pour l`année
 * reqAnnee: vérifie le retour de l`année
 * cas invalide: s`il n`y a pas d`année
 */
TEST_F (ReferenceValide, reqAnnee)
{
  ASSERT_EQ (2022, t_reference.reqAnnee ());
}