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
    	<h2>Statistique sur les Pokemon</h2>
    	
	    <p>Nombre total de pokémons : ${totalPokemon}</p>
	    
	    <p>Nombre total de pokémons de type électrique : ${electricPokemonCount}</p>
	    
	    <p>Nombre de HP du pokémon d'espèces SQUIRTLE : ${squirtleHP}</p>
   </section>
</body>
</html>