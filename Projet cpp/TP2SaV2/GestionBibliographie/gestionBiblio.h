/**
 * \file gestionBiblio.h
 * \brief implentation des méthodes pour tester les méthodes de la librairie sources
 *
 * \author Stéphanie Anselme
 * \date 13/07/2023
 * \version 1.0
 */

#ifndef GESTIONBIBLIO_H
#define GESTIONBIBLIO_H

#include <cstdlib>
#include <string>
#include <vector>

namespace gestion
{
  void validerNomsIssnIsbn (const std::string& nom,
                            const std::string& issn,
                            const std::string& isbn);
  void validerNomsIssnIsbn (const std::vector<std::string>& noms,
                            const std::vector<std::string>& issns,
                            const std::vector<std::string>& isbns);
}
#endif /* GESTIONBIBLIO_H */

