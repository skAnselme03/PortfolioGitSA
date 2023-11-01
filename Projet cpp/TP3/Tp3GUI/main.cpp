/**
 * \file main.cpp
 * \brief programme principale main
 * \author Florence Lariviere, Aleck Desrochers, Anne-Marie Rochette et Stéphanie Anselme
 * \Date juillet 2023
 * \version 1
 */


#include <QApplication>
#include"BibliographieInterface.h"


/**
 * \brief c`est le programme principale qui créér l`objet QT BibliographieInterface et l`affiche
 */

int
main (int argc, char *argv[])
{
  // initialise les ressources
  QApplication app (argc, argv);

  // créé l`objet BibliographieInterface QT
  BibliographieInterface fenetrePrincipale;
  //affiche l`objet
  fenetrePrincipale.show ();

  return app.exec ();
}
