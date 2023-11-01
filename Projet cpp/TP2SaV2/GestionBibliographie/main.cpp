/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * File:   main.cpp
 * Author: etudiant
 *
 * Created on 13 juillet 2023, 15:15
 */
#include "validationFormat.h"
#include "gestionBiblio.h"
#include <cstdlib>
#include <iostream>
#include <vector>
#include <string>

using namespace std;
using namespace util;
using namespace gestion;


/*
 *
 */
int
main ()
{

  // TESTER LES MÉTHODES validationNom, validationCodeISSN, validationCodeISBN
  string nom = "John Doe";
  string issn = "1234-5678";
  string isbn = "ISBN 978-3-16-148410-0";

  vector<string> noms = {"John Doe", "Alice Bob", "1234", "John-Doe"};
  vector<string> issns = {"1234-5678", "ISSN 1234-5678", "12345678",
                          "ISSN 12345678", "ISSN 1937-4771"};
  vector<string> isbns = {"ISBN 978-3-16-148410-0", "ISBN 1234-5678-90",
                          "ISBN 978-0-387-77591-3", "978-3-16-148410-0",
                          "12345678901234567"};

  validerNomsIssnIsbn (nom, issn, isbn);
  cout << "---------------------------------" << endl;
  cout << "---------------------------------" << endl;

  validerNomsIssnIsbn (noms, issns, isbns);


  return 0;
}

