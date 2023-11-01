package org.cegep.gg.controleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Servlet implementation class PokemonExpInfoServlet
 */
public class PokemonExpInfoServlet extends HttpServlet {
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

            // 2) Liste des espèces de pokémons avec expérience > 100
            XPathExpression highExperienceExpression = xPath.compile("//pokemon[experience > 100]/especes/text()");
            NodeList highExperienceNodeList = (NodeList) highExperienceExpression.evaluate(document, XPathConstants.NODESET);
            
         // Convertir NodeList en une liste Java de chaînes
            List<String> highExperienceSpecies = new ArrayList<>();
            for (int i = 0; i < highExperienceNodeList.getLength(); i++) {
                Node node = highExperienceNodeList.item(i);
                highExperienceSpecies.add(node.getNodeValue());
            }
            
            request.setAttribute("highExperienceSpecies", highExperienceSpecies);

           
            // Rediriger vers une page JSP pour afficher les résultats
            request.getRequestDispatcher("especesPokemonExp.jsp").forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
