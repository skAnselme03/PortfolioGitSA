<%@ include file="header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Contactez-nous</title>
    <style>
        .contact-form-container {
            text-align: center;
            width: 80%;
            max-width: 600px;
            margin: 0 auto;
            background-color: #f0f0f0;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        .contact-form {
            margin: 0 auto;
            text-align: left;
        }

        .contact-form label {
            margin-bottom: 10px;
            color: #333;
            display: block;
        }

        .contact-form input[type="text"],
        .contact-form input[type="email"],
        .contact-form textarea {
            width: 95%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 3px;
            font-size: 16px;
        }

        .contact-form textarea {
            resize: vertical;
        }

        .contact-form input[type="submit"] {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 3px;
            font-size: 18px;
            cursor: pointer;
        }

        .contact-form input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <h1>Contactez-nous</h1>
    <div class="contact-form-container">
        <form class="contact-form" action="contact" method="post">
            <label for="name">NOM COMPLET:</label>
            <input type="text" id="name" name="name" required><br><br>

            <label for="email">COURRIEL:</label>
            <input type="email" id="email" name="email" required><br><br>

            <label for="subject">OBJET:</label>
            <input type="text" id="subject" name="subject" required><br><br>

            <label for="message">MESSAGE:</label><br>
            <textarea id="message" name="message" rows="4" cols="50" required></textarea><br><br>

            <input type="submit" value="Envoyer">
        </form>
    </div>
</body>
</html>