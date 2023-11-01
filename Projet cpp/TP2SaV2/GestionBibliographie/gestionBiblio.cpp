/**
 * \file gestionBiblio.cpp
 * \brief gestionBiblio a pour but de tester les méthodes de la librairie sources
 *
 * \author Stéphanie Anselme
 * \date 13/07/2023
 * \version 1.0
 */

#include "gestionBiblio.h"
#include "validationFormat.h"
#include <iostream>
#include <string>
#include <vector>


using namespace std;
using namespace util;

namespace gestion
{


  /**
   * \brief Valider des méthodes validations nom, issn, isbn
   * \param nom[in] un nom quelconque
   * \param issns[in] un code issn
   * \param isbns[in] un code isbn
   */
  void
  validerNomsIssnIsbn (const string& nom,
                       const string& issn,
                       const string& isbn)
  {

    if (validerFormatNom (nom))
      {
        cout << "Le nom " + nom + " est valide." << endl;
      }
    else
      {
        cout << "Le nom " + nom + " n'est pas valide." << endl;
      }

    if (validerCodeIssn (issn))
      {
        cout << "Le code " + issn + " est valide." << endl;
      }
    else
      {
        cout << "Le code " + issn + " n'est pas valide." << endl;
      }

    if (validerCodeIsbn (isbn))
      {
        cout << "Le code " + isbn + " est valide." << endl;
      }
    else
      {
        cout << "Le code " + isbn + " n'est pas valide." << endl;
      }
  }


  /**
   * \brief Valider des méthodes validations nom, issn, isbn
   * \param noms [in] un vecteur de nom valide et invalide
   * \param issns [in] un vecteur de code issn valide et invalide
   * \param isbns [in] un vecteur de code isbn valide et invalide
   */
  void
  validerNomsIssnIsbn (const vector<string>& noms,
                       const vector<string>& issns,
                       const vector<string>& isbns)
  {
    cout << "Noms :" << endl;
    for (const string& nom : noms)
      {
        if (validerFormatNom (nom))
          {
            cout << nom << " : valide" << endl;
          }
        else
          {
            cout << nom << " : non valide" << endl;
          }
      }
    cout << "----------------------------" << endl;
    cout << "----------------------------" << endl;

    cout << endl << "ISSN :" << endl;
    for (const string& issn : issns)
      {
        if (validerCodeIssn (issn))
          {
            cout << issn << " : valide" << endl;
          }
        else
          {
            cout << issn << " : non valide" << endl;
          }
      }

    cout << "----------------------------" << endl;
    cout << "----------------------------" << endl;
    cout << endl << "ISBN :" << endl;
    for (const string& isbn : isbns)
      {
        if (validerCodeIsbn (isbn))
          {
            cout << isbn << " : valide" << endl;
          }
        else
          {
            cout << isbn << " : non valide" << endl;
          }
      }
  }

}