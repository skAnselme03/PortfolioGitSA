<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Administration</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            flex-direction: column;
        }

        h1 {
            text-align: left;
            color: #333;
            margin-left: 20px;
        }

        .button-container {
            display: flex;
		    flex-direction: column;
		    align-items: center;
		    justify-content: center;
		    height: 100%;
        }

        .custom-button {
            padding: 10px 20px;
            font-size: 18px;
            background-color: #007BFF;
            color: #fff;
            border: none;
            border-radius: 5px;
            margin: 10px;
            cursor: pointer;
            width: 250px;
        }

        .custom-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
	<%@ include file="../header.jsp" %>
	<section>
	    <h1>Module Administrateur</h1>
	    <div class="button-container">
	        <form action="${pageContext.request.contextPath}/GererCategorieServlet" method="post">
	            <button class="custom-button" type="submit" name="category">Gestion de catégorie</button>
	        </form>
	        <form action="${pageContext.request.contextPath}/GererProduitServlet" method="post">
	            <button class="custom-button" type="submit" name="product">Gestion de produit</button>
	        </form>
	    </div>
    </section>
</body>
</html>