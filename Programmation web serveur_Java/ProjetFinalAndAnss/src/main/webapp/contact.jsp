<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>

    <head>
        <meta charset="UTF-8">
        <!--<link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/main.css" type="text/css">
 	
 	    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
 	    <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">-->
        <title>Contactez-nous</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/entete.css" type="text/css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/Assets/Styles/css/footer.css" type="text/css">
        <style>
            body {
                margin-top: 120px;
                box-sizing: border-box;
            }
            
            body>*+* {
                margin-top: 1vw;
            }
            
            .contact-form-container {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;
                padding: 2vw;
                /*margin-top: 1.5vw*/
                ;
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
            
            .contact-form-container>form>fieldset section {
                display: flex;
                flex-direction: column;
                align-items: center;
                justify-content: center;
                align-content: center;
            }
            
            .contact-form-container>form>fieldset section>* {
                width: 100%
            }
            
            .contact-form-container>form>fieldset section>*+* {
                margin-top: 0.025vw;
            }
            
            .contact-form-container>form>fieldset section>label {
                margin-bottom: 10px;
                color: #333;
                display: block;
            }
            
            .contact-form-container>form>fieldset section>input,
            section>textarea {
                width: 95%;
                padding: 10px;
                border: 1px solid #ccc;
                border-radius: 3px;
                font-size: 16px;
            }
            
            .contact-form-container>form button {
                background-color: #6b5b95;
                color: #fff;
                border: none;
                padding: 20px 30px;
                border-radius: 3px;
                font-size: 18px;
                cursor: pointer;
                width: 100%;
            }
            
            .contact-form-container>form button:hover {
                background-color: #0056b3;
            }
        </style>

    </head>

    <body>
        <%@ include file="header.jsp" %>
            <section class="contact-form-container" style="margin-top:1em;">
                <!--<h1>Contactez-nous</h1>-->

                <form class="contact-form" action="contact" method="post">
                    <fieldset>
                        <legend>Contactez-nous</legend>
                        <section>
                            <label for="name">NOM COMPLET:</label>
                            <input type="text" id="name" name="name" required>
                        </section>

                        <section>
                            <label for="email">COURRIEL:</label>
                            <input type="email" id="email" name="email" required>
                        </section>

                        <section>
                            <label for="subject">OBJET:</label>
                            <input type="text" id="subject" name="subject" required>
                        </section>

                        <section>
                            <label for="message">MESSAGE:</label><br>
                            <textarea id="message" name="message" rows="4" cols="50" required></textarea>
                        </section>

                    </fieldset>
                    <button type="submit">Envoyer</button>
                </form>
            </section>
            <%@ include file="footer.jsp" %>
    </body>

    </html>