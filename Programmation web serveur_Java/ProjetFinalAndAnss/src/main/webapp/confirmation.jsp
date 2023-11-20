<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Confirmation</title>
    <style>
        .center-container {
            display: flex;
            justify-content: center;
        }
    </style>
</head>
<body>
    <div class="center-container">
        <h1>Confirmation</h1>
        <% if (request.getAttribute("confirmationMessage") != null) { %>
            <p><strong>Message envoyé:</strong> <%= request.getAttribute("confirmationMessage") %></p>
        <% } else if (request.getAttribute("errorMessage") != null) { %>
            <p><strong>Erreur:</strong> <%= request.getAttribute("errorMessage") %></p>
        <% } %>
        <a href="contact.jsp">Retour au formulaire de contact</a>
    </div>
</body>
</html>