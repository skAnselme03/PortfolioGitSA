/*
 * \file ReferenceException.cpp
 * \brief Cette classe permet de gérer l’exception liée aux références.
 *
 *
 *
 * \author Florence Lariviere et Aleck Desrochers
 * \version 1.0
 * \date 2023-07-13
 */

#include <sstream>
#include "ReferenceException.h"
using namespace std;


/**
 * \fn ReferenceException (const std::string& p_raison);
 * \brief Le constructeur de la clase de base ReferenceException
 * Ce constructeur ne fait qu’appeler le constructeur de la classe parent en lui passant la raison comme paramètre
 * \parma[in] p_raison La chaîne de caractère représentant la raison de l`exception
 */

ReferenceException::ReferenceException (const string& p_raison) : runtime_error (p_raison) { }


/**
 * \fn ReferenceDejaPresenteException(const std::string& p_raison);
 * \brief Le constructeur de la classe ReferenceDejaPresenteException qui initialise la classe de base ReferenceException
 * \parma[in] p_raison La chaîne de caractère représentant la raison de l`exception
 */

ReferenceDejaPresenteException::ReferenceDejaPresenteException (const std::string& p_raison) : ReferenceException (p_raison) { }


/**
 * \fn ReferenceAbsenteException::ReferenceAbsenteException(const std::string& p_raison): ReferebceException(p_raison) { }
 * \brief Le constructeur de la classe ReferenceAbsenteException
 * Le constructeur initialise la classe de base ReferenceException
 * \parma[in] p_raison La chaîne de caractère représentant la raison de l`exception
 */
ReferenceAbsenteException::ReferenceAbsenteException (const std::string& p_raison) : ReferenceException (p_raison) { }

