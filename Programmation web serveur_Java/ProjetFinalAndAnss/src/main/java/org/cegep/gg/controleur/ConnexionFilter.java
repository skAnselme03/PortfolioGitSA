package org.cegep.gg.controleur;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


//@WebFilter("/*") // Ce filtre s'applique à toutes les URLs
public class ConnexionFilter extends HttpFilter implements Filter {


	@Override
	public void init(FilterConfig arg0) throws ServletException {}

	@Override
	public void  doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

	      // Vérifie si l'utilisateur est connecté en utilisant la session
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpSession session = httpRequest.getSession(false);
	        boolean clientConnecte = false;


	        if (session != null) {
	            Boolean clientConnecteAttribut = (Boolean) session.getAttribute("clientConnecte");
	            if (clientConnecteAttribut != null && clientConnecteAttribut) {
	                clientConnecte = true;
	            }
	        }

	        // Placez l'état de connexion dans un attribut de la demande pour un accès facile dans les servlets
	        request.setAttribute("clientConnecte", clientConnecte);

	        // Continuez la chaîne de filtres
	        chain.doFilter(request, response);
	    }

	@Override
	public void destroy() {}

}
