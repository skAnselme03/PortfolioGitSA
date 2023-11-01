/*
 *\file ReferenceTesteur.cpp
 * \brief Les tests unitaires pour la classe Reference
 * \author Stephanie Anselme
 * \date 2023-07-15
 * \version 1.0
 */

#include "Reference.h"
#include "validationFormat.h"
#include "ContratException.h"
#include <stdlib.h>
#include <iostream>


#include <gtest/gtest.h>
#include <string>

using namespace biblio;
using namespace std;
using namespace util;

/*
 *\class ReferenceTest
 * \brief Cette classe va tester la classe Reference
 */
class RefererenceTest : public Reference
{

public:


  RefererenceTest (const std::string& p_auteurs,
                   const std::string& p_titre,
                   const unsigned int& p_annee,
                   const std::string& p_identifiant) : Reference (p_auteurs, p_titre, p_annee, p_identifiant) { };


  virtual Reference*
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
  RefererenceTest referenceTest ("Frederic Levesque",
                                 "Precis de droit quebecois des obligations",
                                 2022,
                                 "ISBN 978-0-387-77591-3");

  ASSERT_EQ ("Frederic Levesque", referenceTest.getAuteurs ());
  ASSERT_EQ ("Precis de droit quebecois des obligations", referenceTest.getTitre ());

  ASSERT_EQ (2022, referenceTest.getAnnee ());

  ASSERT_EQ ("ISBN 978-0-387-77591-3", referenceTest.getIdentifiant ());

  ASSERT_TRUE (validerFormatNom (referenceTest.getAuteurs ()));
  ASSERT_TRUE (referenceTest.getAnnee () > 0);
}


TEST (Reference, ConstructeurAuteurVide)
{
  RefererenceTest referenceTest ("",
                                 "Precis de droit quebecois des obligations",
                                 2022,
                                 "ISBN 978-0-387-77591-3");

  ASSERT_THROW (referenceTest, PreconditionException) << "Le nom de l`auteur ne peut être vide";

}


TEST (Reference, ConstructeurTitreVide)
{
  RefererenceTest referenceTest ("Frederic Levesque",
                                 "Precis de droit quebecois des obligations",
                                 2022,
                                 "ISBN 978-0-387-77591-3");

  ASSERT_THROW (referenceTest, PreconditionException) << "Le nom de l`auteur ne peut être vide";

}