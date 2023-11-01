package org.deuce.starwarsService;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//POJO
@WebService(serviceName = "StarwarsWS")
public class ServiceStarWars {
	@WebMethod(operationName = "PersonnageStarwars")
	public PersonnageStarWars getPersonnageStarwars(@WebParam int
	id,@WebParam String name){
	return new PersonnageStarWars(id, name, new Date());
	}
	@WebMethod(operationName = "ListePersonnagesStarwars")
	public List<PersonnageStarWars> ListePersonnagesStarWars()
	{
	List<PersonnageStarWars> liste= new
	ArrayList<PersonnageStarWars>();
	liste.add (new PersonnageStarWars(1,"Obi-One",new Date()));
	liste.add (new PersonnageStarWars(1,"Anakin",new Date()));
	liste.add (new PersonnageStarWars(1,"Padmé",new Date()));
	return liste;
	}
}