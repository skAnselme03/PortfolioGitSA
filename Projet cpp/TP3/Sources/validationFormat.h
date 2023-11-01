#include <string>

#ifndef VALIDATIONFORMAT_H
#define VALIDATIONFORMAT_H
/**
 * \namespace util
 * \brief Ce sont les codes de validation
 */
namespace util
{

  bool validerFormatNom (const std::string& p_nom);
  bool validerCodeIssn (const std::string& p_issn);
  bool validerCodeIsbn (const std::string& p_isbn);

}// fin de namespace util


#endif /* VALIDATIONFORMAT_H */

