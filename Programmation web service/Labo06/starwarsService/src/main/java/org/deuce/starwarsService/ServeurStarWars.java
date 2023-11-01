package org.deuce.starwarsService;

import jakarta.xml.ws.Endpoint;

public class ServeurStarWars {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url="http://0.0.0.0:8081/";
		Endpoint.publish(url, new ServiceStarWars());
		System.out.println("Serveur demaree a l'adresse : "+url);
		}
}