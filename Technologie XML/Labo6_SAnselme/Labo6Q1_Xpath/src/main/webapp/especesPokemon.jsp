<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Afficher les informations des Pokemons</title>
		<link rel="stylesheet" href="Assets/css/main.css" type="text/css">
</head>
<body>
	<%@ include file="entete.jsp" %>
	
    <section class="section_accueil">
	    <h2>Liste des espèces de tous les pokémons</h2>
	    <ul>
	        <c:forEach items="${allSpecies}" var="species">
	            <li>${species}</li>
	        </c:forEach>
	    </ul>
    </section>
   
</body>
</html>