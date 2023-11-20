<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Se connecter</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
 	<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
	
        <style>
            body {
                margin-top: 120px;
                box-sizing: border-box;
            }
            
            body>*+*{
            	margin-top:1vw;
            }
            
            .contact-form-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;
                padding: 2vw;
                /*margin-top: 1.5vw*/;
	            width: 100%;
	            max-width: 600px;
            	margin: 0 auto;
            }
            
            .contact-form-container form {
                /*width:100;*/
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;
                margin: 0 auto;
                background-color: #f0f0f0;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }
            
            .contact-form-container form>*+* {
                margin-top: 1vw;
            }
            
            .contact-form-container>form fieldset {
                border: none;
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;
                border-bottom: 1px solid  #6b5b95;
                
            }
            
            .contact-form-container>form fieldset>*+* {
                margin-top: 1.5vw;
            }
            
            .contact-form-container>form fieldset>* {
                flex-basis: 100%;
                width: 100%;
            }
            
            .contact-form-container>form legend {
                font-size: 36px;
                margin: 0;
                color: #333;
                font-weight: bold;
                text-align: center;
            }
            
             .contact-form-container>form>fieldset section{
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;             	
             }
             
             .contact-form-container>form>fieldset section>*{
             	width:100%
             }
             .contact-form-container>form>fieldset section>*+*{
             	margin-top:0.025vw;
             }             
             
             .contact-form-container>form>fieldset section>label{             
		            margin-bottom: 10px;
		            color: #333;
		            display: block;
             }     
                     
             .contact-form-container>form>fieldset section>input, section>textarea{    
	            width: 95%;
	            padding: 10px;
	            border: 1px solid #ccc;
	            border-radius: 3px;
	            font-size: 16px;
             }
                        
            .contact-form-container>form button {
                background-color:  #6b5b95;
                color: #fff;
                border: none;
                padding: 20px 30px;
                border-radius: 3px;
                font-size: 18px;
                cursor: pointer;
                width: 100%;
            }
            
            
            .contact-form-container>form button:hover{
            	background-color: #0056b3;            
            }
        </style>
   

   <script src="${pageContext.request.contextPath}/Assets/Scripts/modules/validationFormulaire.js" type="text/javascript"></script>
</head>
    <body>
    
		<%@ include file="../header.jsp" %>
        <section  class="contact-form-container">
            <%-- Afficher le message d'erreur s'il existe --%>
                <c:if test="${not empty(errorMessageLogin)}">
                    <p style="color: red;">${errorMessageLogin}</p>
                </c:if>

                <!-- <form action="login" method="POST" class="">
                <form action="j_security_check" method="POST" class="" name="loginForm">-->
                <form action="${pageContext.request.contextPath}/login" method="POST" class="" name="loginForm">
                    <input type="hidden" name="action" value="LOGIN" />
                    <fieldset>
                        <Legend>Connexion</Legend>
                        <section>
                            <label for="login">Nom d'utilisateur ou Adresse e-mail:</label>
                            <input type="text" name="login" id="login" placeholder="Entrer votre username ou votre email" aria-label="un email ou un userna quelconque" required>
                        </section>
                        <section>
                            <label for="password">Mot de passe</label>
                            <input type="password" name="password" id="password" aria-label="un password quelconque" placeholder="Entrer votre password" required>
                        </section>
                        <section>
                        </section>
                    </fieldset>
                   <button type="submit" class="">Se connecter</button>
                </form>
        </section>
        
        <%@ include file="../footer.jsp" %>
    </body>
</html>