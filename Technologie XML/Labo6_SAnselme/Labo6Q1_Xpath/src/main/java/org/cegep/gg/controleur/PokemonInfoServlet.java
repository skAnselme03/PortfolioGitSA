package org.cegep.gg.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.*;

/**
 * Servlet implementation class PokemonInfoServlet
 */
public class PokemonInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            // Charger le fichier XML
	            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	            DocumentBuilder builder = factory.newDocumentBuilder();
	            Document document = builder.parse(getServletContext().getResourceAsStream("/WEB-INF/pokedex.xml"));

	            // Créer un objet XPath
	            XPathFactory xPathFactory = XPathFactory.newInstance();
	            XPath xPath = xPathFactory.newXPath();

	            // 1) Liste des espèces de tous les pokémons
	            XPathExpression speciesExpression = xPath.compile("//pokemon/especes/text()");
	            NodeList speciesNodeList = (NodeList) speciesExpression.evaluate(document, XPathConstants.NODESET);
	            
	            // Convertir NodeList en une liste Java de chaînes
	            List<String> allSpecies = new ArrayList<>();
	            for (int i = 0; i < speciesNodeList.getLength(); i++) {
	                Node node = speciesNodeList.item(i);
	                allSpecies.add(node.getNodeValue());
	            }
	            
	            request.setAttribute("allSpecies", allSpecies);          

	            // Rediriger vers une page JSP pour afficher les résultats
	            request.getRequestDispatcher("especesPokemon.jsp").forward(request, response);
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	}
