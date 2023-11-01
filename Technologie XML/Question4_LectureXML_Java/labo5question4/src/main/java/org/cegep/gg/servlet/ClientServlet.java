package org.cegep.gg.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.cegep.gg.modele.Client;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            // Créez un constructeur de documents
	            //DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            //DocumentBuilder builder = factory.newDocumentBuilder();

	            // Chargez le fichier XML
	            Document document = lireDocumentXML("/clients.xml");

	    	    document.getDocumentElement().normalize();

				System.out.println("Element Racine: " + document.getDocumentElement().getNodeName());  

	            // Obtenez la liste des éléments "client"
	            NodeList clientList = document.getElementsByTagName("client");
	           
	            List<Client> clients = new ArrayList<>();
	            
	            // Parcourez la liste des clients
	            for (int i = 0; i < clientList.getLength(); i++) {
	                Element clientElement = (Element) clientList.item(i);
	                int id =  Integer.parseInt(clientElement.getElementsByTagName("id").item(0).getTextContent());
	                String nom = clientElement.getElementsByTagName("nom").item(0).getTextContent();
	                String adresse = clientElement.getElementsByTagName("adresse").item(0).getTextContent();
	                //peupler la liste de client
	                clients.add(new Client(id, nom, adresse));
	                
	                
	            }
	             // Afficher les clients
	                response.getWriter().println("<html><body><h1>Liste des clients :</h1>");
	                for (Client client : clients) {
	                    response.getWriter().println("<p>ID : " + client.getId() + "</p>");
	                    response.getWriter().println("<p>Nom : " + client.getNom() + "</p>");
	                    response.getWriter().println("<p>Adresse : " + client.getAdresse() + "</p>");
	                    response.getWriter().println("<hr>");
	                }
	                response.getWriter().println("</body></html>");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	public static Document lireDocumentXML(String nomFichier) throws Exception{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document document = builder.parse(new File(nomFichier));
	    document.getDocumentElement().normalize();
	    return document;
	}

}
