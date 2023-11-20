<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Erreur de connection</title>
</head>
<body>
	<h3>Erreur de connexion</h3>   
    <a href="${pageContext.request.contextPath}/signUp">Créer un compte</a>    
 
 
	<c:if test="${not CONFIRMATION_COMMANDE}">
         <p style="color: red;">Une erreur est survenue lors de l'envoi du courriel de confirmation de la commande.</p>
    	 <p style="color: red;">Vous recevrez un courriel de confirmation après avoir regler ce problème.</p>
    	 	
    </c:if>
        
</body>
</html>