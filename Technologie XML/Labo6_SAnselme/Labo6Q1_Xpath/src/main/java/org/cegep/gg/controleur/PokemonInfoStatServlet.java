package org.cegep.gg.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class PokemonInfoStatServlet
 */
public class PokemonInfoStatServlet extends HttpServlet {
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
	
	              // 3) Nombre total de pokémons
	            XPathExpression totalPokemonExpression = xPath.compile("count(//pokemon)");
	            double totalPokemon = (Double) totalPokemonExpression.evaluate(document, XPathConstants.NUMBER);
	            request.setAttribute("totalPokemon", (int) totalPokemon);
	
	            // 4) Nombre total de pokémons de type électrique
	            XPathExpression electricPokemonExpression = xPath.compile("count(//pokemon/types/type[text()='ELECTRIC'])");
	            double electricPokemonCount = (Double) electricPokemonExpression.evaluate(document, XPathConstants.NUMBER);
	            request.setAttribute("electricPokemonCount", (int) electricPokemonCount);
	
	            // 5) Nombre de HP du pokémon d'espèces SQUIRTLE 
	            XPathExpression squirtleHPExpression = xPath.compile("//pokemon[especes='SQUIRTLE']/statBases/HP/text()");
	            String squirtleHP = (String) squirtleHPExpression.evaluate(document, XPathConstants.STRING);
	            request.setAttribute("squirtleHP", squirtleHP);
	
	            // Rediriger vers une page JSP pour afficher les résultats
	            request.getRequestDispatcher("especesPokemonStat.jsp").forward(request, response);
		            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
    }
}
