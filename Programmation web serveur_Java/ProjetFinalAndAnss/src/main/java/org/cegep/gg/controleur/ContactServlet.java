package org.cegep.gg.controleur;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/contact")
public class ContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
     * Traite la requête GET.
     */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
			contactezNous(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }


    @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupère les informations du formulaire
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String subject = request.getParameter("subject");
        String message = request.getParameter("message");

        // Configure email properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "mail.cam-andria.ca");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.starttls.required", "true");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");


        // Set up the email session with authentication
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            @Override
			protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ecole@cam-andria.ca", "Etudes2023*");
            }
        });

        try {
            // Create a new email message
            Message emailMessage = new MimeMessage(session);
            emailMessage.setFrom(new InternetAddress("ecole@cam-andria.ca")); // Replace with your email
            emailMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("camandriaecole@gmail.com")); // Replace with your recipient email
            emailMessage.setSubject(subject);
            emailMessage.setText("Nom: " + name + "\nCourriel: " + email + "\nMessage: " + message);

            // Send the email
            Transport.send(emailMessage);

            // Set a success message attribute
            request.setAttribute("confirmationMessage", "Votre message a bien été envoyé! Nous vous contacterons bientôt.");
        } catch (MessagingException e) {
            e.printStackTrace();
            // Set an error message attribute
            request.setAttribute("errorMessage", "Echec dans l'envoi du message. Veuillez réessayer plus tard.");
        }

        // Forward the request to confirmation.jsp
        request.getRequestDispatcher("confirmation.jsp").forward(request, response);
    }

	/**
	 * Gère la tentative du réeacheminement vers la page contactez-nous
	 *
	 * @param request La requête HTTP contenant les données de connexion.
	 * @param response La réponse HTTP à renvoyer à l'utilisateur.
	 * @throws Exception Si une erreur survient pendant le processus de connexion.
	 */
    private void contactezNous(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

    	//TODO: PEUT-ÊTRE IMPLÉMENTER UN COOKIE

    	// Redirige vers la page d'accueil
        request.getRequestDispatcher("/contact.jsp").forward(request, response);

    }
}