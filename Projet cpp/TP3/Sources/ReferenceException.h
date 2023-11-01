/**
 * \file ReferenceException.h
 * \class ReferenceException
 *
 * \brief Cette classe permet de gérer l’exception liée aux références.
 * Le fichier contient la déclaration de la classe ReferenceException et ses héritiers.
 *
 *
 *
 *
 *\author Florence Lariviere et Aleck Desrochers
 * \date 2023-07-13
 * \version 1.0
 *
 */

#ifndef REFERENCEEXCEPTION_H
#define REFERENCEEXCEPTION_H

#include <string>
#include <stdexcept>

/**
 * \class ReferenceException
 * \brief Classe de base des exceptions de référence
 */

class ReferenceException : public std::runtime_error
{
public:
  ReferenceException (const std::string& p_raison); // le constructeur de la classe parent

};

/**
 * \class ReferenceDejaPresenteException
 * \brief Classe pour la gestion des erreurs lorsque la référence est déjà présente.
 */
class ReferenceDejaPresenteException : public ReferenceException
{
public:
  ReferenceDejaPresenteException (const std::string& p_raison);
};

/**
 * \classe ReferenceAbsenteException
 * \brief Classe pour la gestion des erreurs lorsque la référence est manquante
 */
class ReferenceAbsenteException : public ReferenceException
{
public:
  ReferenceAbsenteException (const std::string& p_raison);
};




#endif /* REFERENCEEXCEPTION_H */

