package org.cegep.gg.modele;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvoieEmail {
	private String emailDestinateur = "ecole@cam-andria.ca";
	private String emailMDPDestinateur = "Etudes2023*";
    private String emailDestinataire;
    private Object donneesMessage;
    private String sujetDuMessage;

    // Constructeur avec les trois champs en paramètres
    public EnvoieEmail(String emailDestinataire, Object objetMessage, String sujetDuMessage) {
        this.emailDestinataire = emailDestinataire;
        this.emailDestinataire = emailDestinataire;
        this.donneesMessage = objetMessage;
        this.sujetDuMessage = sujetDuMessage;
    }


    public EnvoieEmail() {
	}


	/*********************/
    /*		GETTER 		 */
    /*********************/
    public String emailDestinateur() {
        return emailDestinateur;
    }

    public String getEmail() {
        return emailDestinataire;
    }

    public Object getObjetMessage() {
        return donneesMessage;
    }

    public String getSujetDuMessage() {
        return sujetDuMessage;
    }

    /*********************/
    /*		SETTER 		 */
    /*********************/
    public void setSujetDuMessage(String sujetDuMessage) {
        this.sujetDuMessage = sujetDuMessage;
    }

    public void setObjetMessage(Object objetMessage) {
        this.donneesMessage = objetMessage;
    }

    public void setEmailDestinataire (String email) {
        this.emailDestinataire = email;
    }
    public void setEmailDestinateur(String email) {
        this.emailDestinateur = email;
    }

    /*********************/
    /*		MÉTHODES	 */
    /*********************/
    /**
     * Envoie un email confirme une commande
     * @param panierItems liste de produit de la commande
     * @param carteDAchat carte bancaire pour passer la commande
     * @throws Exception
     */
    public boolean envoyerEmailConfirmation(List<PanierItem> panierItems, String carteDAchat)
    		throws Exception {
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
                return new PasswordAuthentication(emailDestinateur, emailMDPDestinateur);
            }
        });

        try {
            // Créez un message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ecole@cam-andria.ca")); // Remplacez par votre adresse e-mail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(this.emailDestinataire));
            message.setSubject(this.sujetDuMessage);

            // Construisez le contenu de l'e-mail avec les détails de la commande
            StringBuilder contenuEmail = new StringBuilder();
            double prixTotalCommande = 0;
            contenuEmail.append("<p>Merci d'avoir effectué votre achat chez nous. Voici les détails de votre commande :</p>");
            contenuEmail.append("<table border=\"1\" cellpadding=\"5\" cellspacing=\"0\">");
            contenuEmail.append("<thead>");
            contenuEmail.append("<tr>");
            contenuEmail.append("<th style=\"text-align:center;\">Nom du produit</th>");
            contenuEmail.append("<th style=\"text-align:center;\">Quantité</th>");
            contenuEmail.append("<th style=\"text-align:center;\">Prix unitaire</th>");
            contenuEmail.append("<th style=\"text-align:center;\">Prix total</th>");
            contenuEmail.append("</tr>");
            contenuEmail.append("</thead>");
            contenuEmail.append("<tbody>");
            //Créez un objet DecimalFormat avec le format "0.00" (deux chiffres après la virgule)
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            for (PanierItem item : panierItems) {
                contenuEmail.append("<tr>");
                contenuEmail.append("<td style=\"text-align:center;\">" + item.getNomProduit() + "</td>");
                contenuEmail.append("<td style=\"text-align:center;\">" + item.getQuantite() + "</td>");
                contenuEmail.append("<td style=\"text-align:center;\">"  + item.getPrixUnitaire() + "$</td>");
                contenuEmail.append("<td style=\"text-align:center;\">"  + decimalFormat.format(item.getPrixTotal()) + "$</td>");
                contenuEmail.append("</tr>");
                prixTotalCommande += item.getPrixTotal();
            }
            contenuEmail.append("<tr>");
            contenuEmail.append("<th colspan=\"3\">Total de la commande</th>");
            contenuEmail.append("<td style=\"text-align:center;\">"  + decimalFormat.format(prixTotalCommande) + "$</td>");
            contenuEmail.append("</tr>");
            contenuEmail.append("</tbody>");
            contenuEmail.append("</table>");
            // Ajoutez les 4 derniers chiffres de la carte

         // Ajoutez les 4 derniers chiffres de la carte en les extrayant de la variable derniersChiffresCarte
            String derniers4Caracteres = carteDAchat.trim();
            derniers4Caracteres = derniers4Caracteres.substring(derniers4Caracteres.length() - 4);

            contenuEmail.append("<p>Vous avez commandé avec la carte se terminant par : ");
            contenuEmail.append("XXXX..." + derniers4Caracteres + "</p>");


            //message.setText(contenuEmail.toString());
            message.setContent(contenuEmail.toString(), "text/html; charset=utf-8"); // Définissez le contenu HTML

            // Envoyez le message
            Transport.send(message);
        } catch (MessagingException e) {
        	e.printStackTrace();
        	return false;// message fail.
        }
        return true;//message envoyer
    }

    // Méthode toString pour afficher l'objet sous forme de chaîne de caractères
    @Override
    public String toString() {
        return "EnvoieEmail{" +
                "email='" + emailDestinataire + '\'' +
                ", sujetDuMessage='" + sujetDuMessage + '\'' +
                ", objetMessage=" + donneesMessage +
                '}';
    }
}
