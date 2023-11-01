/*
 *\file BibliographieTesteur.cpp
 * \brief Les tests unitaires pour la classe Bibliographie
 * \author Florence Lariviere
 * \date 2023-07-01
 */


#include <gtest/gtest.h>
#include "Bibliographie.h"
#include "Ouvrage.h"
#include "Journal.h"
#include "ContratException.h"


#include <sstream>

using namespace biblio;
using namespace std;


/**
 * \brief C`est le test du constructeur Bibliographie::Bibliographie (const string& p_nomBibliographie) :
 * Cas valide:
 * ConstructeurValide: Le constructeur est valide lorsque le paramètre est valide
 * Cas invalide:
 * ConstructeurInvalide: Le nom de la bilbiographie est invalide
 */


TEST (Bibliographie, ConstructeurValide)
{
  Bibliographie uneBibliographie ("La Bibliographie");

  ASSERT_EQ ("La Bibliographie", uneBibliographie.reqNomBibliographie ());
}

/**
 * \class BibliographieValide
 * \brief Fixture BibliographieValide, créer un objet Bibliographie valide pour les tests
 */

class BibliographieValide : public ::testing::Test
{

public:


  BibliographieValide () : t_bibliographie ("La Bibliographie"), t_ouvrage ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISBN 978-0-387-77591-3", "New York", "SGM"), t_journal ("Frederic Levesque", "Precis de droit quebecois des obligations", 2022, "ISSN 1937-4771", "Contrat, responsabilité, exécution et extinction", 24, 6, 365)
  {
    t_bibliographie.ajouterReference (t_ouvrage);
    t_bibliographie.ajouterReference (t_journal);
  } ;
  Bibliographie t_bibliographie;
  Ouvrage t_ouvrage;
  Journal t_journal;
} ;


/**
 * \fn Bibliographie::Bibliographie (const Bibliographie& p_bibliographie)
 * \brief Test du constructeur copie
 * ConstructeurValideCopie: la copie du constructeur est valide
 */

TEST_F (BibliographieValide, ConstructeurCopieValide)
{
  Bibliographie laCopie (t_bibliographie);


  ASSERT_EQ ("La Bibliographie", laCopie.reqNomBibliographie ());
  ASSERT_EQ (t_bibliographie.reqBibliographieFormate (), laCopie.reqBibliographieFormate ());
}


/**
 * \fn Bibiographie::reqNomBibliographie() const
 * \brief vérifie l`accesseur pour le nom de de la bilbiographie
 */

TEST_F (BibliographieValide, reqNomBibliographie)
{
  ASSERT_EQ ("La Bibliographie", t_bibliographie.reqNomBibliographie ());
}


/**
 * \fn Bibliographie:: reqBibliographieFormate() const
 * \brief vérifie que retourne les informations formatées de toutes les références de la bibliographie sous forme de chaine de caractère
 */

TEST_F (BibliographieValide, reqBibliographieFormate)
{
  ASSERT_EQ (t_bibliographie.reqBibliographieFormate (), "Bibliographie\n=================================================================\n[1] Frederic Levesque. Precis de droit quebecois des obligations. New York: SGM, 2022. ISBN 978-0-387-77591-3. \n[2] Frederic Levesque. Precis de droit quebecois des obligations. Contrat, responsabilité, exécution et extinction, vol. 24, no. 6, pp. 365, 2022. ISSN 1937-4771.\n");
}


/**
 * \fn Bibliographie& operator= (const Bibliographie& p_bibliographie);
 * \brief vérifie que  les valeurs d'un objet existant dans un autre objet du même type sont correctement copiées pour l`ouvrage
 */

TEST_F (BibliographieValide, OperateurEgal)
{
  Bibliographie bibliographie1 ("Bibliographie 1");


  // Ajouter les références dans la bibliographie
  bibliographie1.ajouterReference (t_ouvrage);
  bibliographie1.ajouterReference (t_journal );

  // Copier la bibliographie 1 dans la bibliographie 2
  Bibliographie bibliographie2 (bibliographie1);

  // Vérifier que les références  sont correctement copiés
  ASSERT_EQ ("Bibliographie 1", bibliographie2.reqNomBibliographie ());
  ASSERT_EQ (bibliographie1.reqBibliographieFormate (), bibliographie2.reqBibliographieFormate ());
}


/**
 * \fn void Bibliographie::ajouterReference (const Reference& p_nouvelleReference)
 * \brief vérifie que ça ajoute une référence lorsqu`elle n`est pas déjà présente
 */
TEST_F (BibliographieValide, ajouterReference)
{
  ASSERT_FALSE (t_ouvrage == t_journal);
}


/**
 * \fn void supprimerReference (const std::string& p_identifiant);
 * \brief vérifie que cette méthode supprime la référence dont l’identifiant est reçue en paramètre
 */
TEST_F (BibliographieValide, supprimerReference)
{
  t_bibliographie.supprimerReference (t_ouvrage.reqIdentifiant ());
  ASSERT_FALSE (t_bibliographie.verifiePresentIdentifiant (t_ouvrage.reqIdentifiant ()));
}


/**
 * \fn bool verifiePresentIdentifiant (const std::string&p_identifiant);
 * \brief vérifie que cette méthode valide si le vecteur contient la référence qui a comme identifiant p_identifiant
 */
TEST_F (BibliographieValide, verifiePresentIdentifiant)
{
  Bibliographie biblioTest ("BibliographieTest");
  // Ajoutez la référence à la bibliographie
  biblioTest.ajouterReference (t_ouvrage);

  // Maintenant, vérifiez si l'identifiant est présent.
  EXPECT_TRUE (biblioTest.verifiePresentIdentifiant ("ISBN 978-0-387-77591-3"));
  // Ajoutez la 2e référence à la bibliographie
  biblioTest.ajouterReference (t_journal);
  EXPECT_TRUE (biblioTest.verifiePresentIdentifiant ("ISSN 1937-4771"));
}