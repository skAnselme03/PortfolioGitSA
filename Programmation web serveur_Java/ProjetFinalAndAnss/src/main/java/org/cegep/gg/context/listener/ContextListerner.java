package org.cegep.gg.context.listener;

import java.io.File;

import org.apache.log4j.PropertyConfigurator;

import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;


@WebListener("context listener")
public class ContextListerner implements jakarta.servlet.ServletContextListener {


    @Override
	public void contextDestroyed(jakarta.servlet.ServletContextEvent event)  {
         // ne fait rien  du tout
    }

    @Override
	public void contextInitialized(jakarta.servlet.ServletContextEvent event)  {
    	/*
    	 * Si je change la path par défaut, je suis obliger d'instancier mon Log4J
    	 * alors le fichier log4j.properties doit etres dans le dossier demo.
    	 */
    	ServletContext context = event.getServletContext();
        String log4jFichier = context.getInitParameter("log4j-fichier");
        String chemin = context.getRealPath("") + File.separator + log4jFichier;
        PropertyConfigurator.configure(chemin);
    }

}
