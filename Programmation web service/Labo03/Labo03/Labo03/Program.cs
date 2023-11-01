using Labo5.pckgClasses;
using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Net;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using static System.Net.WebRequestMethods;

namespace Labo03
{
	internal class Program
	{
		static void Main(string[] args)
		{
			String urlService = "http://localhost:62913/GenererNombreEtChaine.asmx";
			String actionSoapGenString = "http://tempuri.org/GenererChaine";
			String actionGenIntSoap = "http://tempuri.org/GenererEntier";

			AfficherMessage afficherMsg;

			// Crée une instance de la classe Program pour accéder aux méthodes
			Program obj = new Program();
			try
			{

				afficherMsg = new AfficherMessage("LABO III", "-", true);
				afficherMsg.afficher_message();
				//Création d'une nouvelle instance de la classe AfficherMessage d'information
				afficherMsg = new AfficherMessage("Consommer service GenererChaine", "-", false);
				afficherMsg.afficher_message();
				Console.Write("Saisir un longeur (entier) : ");
				int longueurChaine =Int32.Parse(Console.ReadLine());
				// Appelle le service en passant la longueur de la chaîne
				string resultatChaine = obj.AppelServiceGenChaine(longueurChaine, urlService, actionSoapGenString);
				// Affiche le résultat dans la console
				Console.WriteLine("Chaine générée de longueur " + longueurChaine + " : " + resultatChaine);
				Console.WriteLine("\n-------------------\n");


				//Création d'une nouvelle instance de la classe AfficherMessage d'information
				afficherMsg = new AfficherMessage("Consommer service GenererEntier", "-", false);
				afficherMsg.afficher_message();


				Console.Write("Saisir un minimum (entier) : ");
				int min = Int32.Parse(Console.ReadLine());

				Console.Write("Saisir un maximum (entier) : ");
				int max = Int32.Parse(Console.ReadLine());
				// Appelle le service en passant le minimum et le maximum du nombre entier à generer
				int resultatEntier = obj.AppelServiceGenEntier(min, max, urlService, actionGenIntSoap);
				// Affiche le résultat dans la console
				Console.WriteLine("L'entier génerer entre [ " + min + ", " + max+ "] : " + resultatEntier);
				Console.WriteLine("\n-------------------\n");
			}
			catch (Exception ex)
			{
				Console.WriteLine("Une erreur s'est produite : " + ex.Message);
			}

			Console.ReadLine();
		}


		/// <summary>
		/// Appelle un service SOAP pour générer une chaîne de caractères.
		/// </summary>
		/// <param name="longueurChaine">La longueur de la chaîne à générer.</param>
		/// <param name="urlService">L'URL du service SOAP.</param>
		/// <param name="actionSoap">La valeur de l'en-tête SOAPAction.</param>
		/// <returns>La chaîne générée.</returns>
		public int AppelServiceGenEntier(int minimum, int maximum, string urlService, string actionSoap)
		{
			// Crée une requête HTTP configurée pour l'appel SOAP
			HttpWebRequest requete = CreerRequeteSoap(urlService, actionSoap);

			// Crée un document XML pour le corps de la requête SOAP
			XmlDocument SOAPReqBody = new XmlDocument();

			// Définit le contenu XML du corps de la requête SOAP
			SOAPReqBody.LoadXml(enveloppeGenEntier(minimum, maximum));

			// Envoie le contenu XML du corps de la requête SOAP à la requête HTTP
			using (Stream stream = requete.GetRequestStream())
			{
				SOAPReqBody.Save(stream);
			}

			// Obtient la réponse de la requête
			using (WebResponse serviceResponse = requete.GetResponse())
			{
				using (StreamReader rd = new StreamReader(serviceResponse.GetResponseStream()))
				{
					// Lit le contenu de la réponse
					var serviceResult = rd.ReadToEnd();

					// Appelle la méthode ExtraireResultatSOAP en spécifiant l'action SOAP
					int resultatEntier =Int32.Parse(ExtraireResultatSOAP(serviceResult, actionSoap));
					// Retourne la chaîne générée
					return resultatEntier;
				}
			}
		}

		/// <summary>
		/// Appelle un service SOAP pour générer une chaîne de caractères.
		/// </summary>
		/// <param name="longueurChaine">La longueur de la chaîne à générer.</param>
		/// <param name="urlService">L'URL du service SOAP.</param>
		/// <param name="actionSoap">La valeur de l'en-tête SOAPAction.</param>
		/// <returns>La chaîne générée.</returns>
		public string AppelServiceGenChaine(int longChaine, string urlService, string actionSoap)
		{
			// Crée une requête HTTP configurée pour l'appel SOAP
			HttpWebRequest requete = CreerRequeteSoap(urlService, actionSoap);

			// Crée un document XML pour le corps de la requête SOAP
			XmlDocument SOAPReqBody = new XmlDocument();

			// Définit le contenu XML du corps de la requête SOAP
			SOAPReqBody.LoadXml(enveloppeGenChaine(longChaine));

			// Envoie le contenu XML du corps de la requête SOAP à la requête HTTP
			using (Stream stream = requete.GetRequestStream())
			{
				SOAPReqBody.Save(stream);
			}

			// Obtient la réponse de la requête
			using (WebResponse serviceResponse = requete.GetResponse())
			{
				using (StreamReader rd = new StreamReader(serviceResponse.GetResponseStream()))
				{
					// Lit le contenu de la réponse
					var serviceResult = rd.ReadToEnd();
 
					// Appelle la méthode ExtraireResultatSOAP en spécifiant l'action SOAP
					string resultatChaine = ExtraireResultatSOAP(serviceResult, actionSoap);


					// Retourne la chaîne générée
					return resultatChaine;
				}
			}
		}

		/// <summary>
		/// Extrait la valeur de la balise de résultat dans la réponse SOAP en fonction de l'action SOAP spécifiée.
		/// </summary>
		/// <param name="response">La réponse SOAP complète.</param>
		/// <param name="actionSoap">L'action SOAP pour laquelle le résultat doit être extrait.</param>
		/// <returns>La valeur de la balise de résultat.</returns>
		public string ExtraireResultatSOAP(string response, string actionSoap)
		{
			// Crée un nouvel objet XmlDocument et charge la réponse SOAP.
			XmlDocument doc = new XmlDocument();
			doc.LoadXml(response);

			// Crée un gestionnaire de l'espace de noms XML pour les préfixes SOAP et tempuri.
			XmlNamespaceManager manager = new XmlNamespaceManager(doc.NameTable);
			manager.AddNamespace("soap", "http://schemas.xmlsoap.org/soap/envelope/");
			manager.AddNamespace("tempuri", "http://tempuri.org/");

			// Initialise le nom de la balise de résultat en fonction de l'action SOAP.
			string resultatTagName = null;
			string responseTagName = null;

			if (actionSoap == "http://tempuri.org/GenererChaine")
			{
				responseTagName = "GenererChaineResponse";
				resultatTagName = "GenererChaineResult";
			}
			else if (actionSoap == "http://tempuri.org/GenererEntier")
			{
				responseTagName = "GenererEntierResponse";
				resultatTagName = "GenererEntierResult";
			}

			// Vérifie si le nom de la balise de résultat a été initialisé.
			if (resultatTagName != null)
			{
				// Recherche la balise correspondant au résultat et extrait sa valeur.
				XmlNode resultatNode = doc.SelectSingleNode($"//soap:Body/tempuri:{responseTagName}/tempuri:{resultatTagName}", manager);

				if (resultatNode != null)
				{
					return resultatNode.InnerText; // Retourne la valeur de la balise de résultat.
				}
				else
				{
					return $"Erreur : Balise {resultatTagName} non trouvée.";
				}
			}
			else
			{
				return "Action SOAP non reconnue.";
			}
		}

		/// <summary>
		///  Retourne l'enveloppe SOAP de la méthode GenererEntier.
		/// </summary>
		/// <param name="minimum">La borne minimum de l'entier à génerer </param>
		/// <param name="maximum">La borne maximum de l'entier à génerer</param>
		/// <returns>L'enveloppe SOAP de la méthode.</returns>
		public string enveloppeGenEntier(int minimum, int maximum)
		{   // Définit le contenu XML du corps de la requête SOAP
			return $@"<?xml version=""1.0"" encoding=""utf-8""?>
                    <soap:Envelope xmlns:soap=""http://schemas.xmlsoap.org/soap/envelope/""
                    xmlns:xsi=""http://www.w3.org/2001/XMLSchema-instance""
                    xmlns:xsd=""http://www.w3.org/2001/XMLSchema"">
                        <soap:Body>
                            <GenererEntier xmlns=""http://tempuri.org/"">
								<min>{minimum}</min>
								<max>{maximum}</max>
                            </GenererEntier>
                        </soap:Body>
                    </soap:Envelope>";
		}

		/// <summary>
		/// Retourne l'enveloppe SOAP de la méthode GenererChaine.
		/// </summary>
		/// <param name="longueurChaine">La longueur de la chaîne à générer.</param>
		/// <returns>L'enveloppe SOAP de la méthode.</returns>
		public string enveloppeGenChaine(int longueurChaine)
		{	// Définit le contenu XML du corps de la requête SOAP
			return $@"<?xml version=""1.0"" encoding=""utf-8""?>
                    <soap:Envelope xmlns:soap=""http://schemas.xmlsoap.org/soap/envelope/""
                    xmlns:xsi=""http://www.w3.org/2001/XMLSchema-instance""
                    xmlns:xsd=""http://www.w3.org/2001/XMLSchema"">
                        <soap:Body>
                            <GenererChaine xmlns=""http://tempuri.org/"">
                                <longueur>{longueurChaine}</longueur>
                            </GenererChaine>
                        </soap:Body>
                    </soap:Envelope>";
		}

		/// <summary>
		/// Crée une requête HTTP pour appeler un service SOAP.
		/// </summary>
		/// <param name="urlService">L'URL du service SOAP.</param>
		/// <param name="actionSoap">La valeur de l'en-tête SOAPAction.</param>
		/// <returns>Un objet HttpWebRequest configuré pour l'appel SOAP.</returns>
		public HttpWebRequest CreerRequeteSoap(String urlService, String actionSoap)
		{
			// Crée une instance de HttpWebRequest pour effectuer la requête.
			HttpWebRequest Req = (HttpWebRequest)WebRequest.Create(urlService);

			// Ajoute l'en-tête SOAPAction à la requête. Cela indique quelle opération SOAP doit être appelée.
			Req.Headers.Add(actionSoap);

			// Définit le type de contenu de la requête comme du texte XML avec l'encodage UTF-8.
			Req.ContentType = "text/xml;charset=\"utf-8\"";

			// Spécifie que la requête accepte du texte XML en réponse.
			Req.Accept = "text/xml";

			// Définit la méthode HTTP à POST, car les appels SOAP utilisent généralement POST pour envoyer des données.
			Req.Method = "POST";

			// Retourne l'objet HttpWebRequest configuré.
			return Req;
		}

	}
}
