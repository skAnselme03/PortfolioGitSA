using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;

namespace Labo5Question3
{
	internal class Program
	{
		static void Main(string[] args)
		{
			try
			{
				// Charger le fichier XML
				XmlDocument xmlDoc = new XmlDocument();
				string cheminFicXml = @"client.xml";
				xmlDoc.Load(cheminFicXml);

				// Sélectionner tous les éléments "Livre" dans le fichier XML
				XmlNodeList clients = xmlDoc.SelectNodes("//client");

				// Parcourir et afficher les détails de chaque livre
				foreach (XmlNode livre in clients)
				{
					string ID = livre.SelectSingleNode("id").InnerText;
					string nom = livre.SelectSingleNode("nom").InnerText;
					int adresse = int.Parse(livre.SelectSingleNode("adresse").InnerText);

					Console.WriteLine($"ID: {ID}");
					Console.WriteLine($"Nom: {nom}");
					Console.WriteLine($"Adresse: {adresse}\n");
				}
			}
			catch (Exception ex)
			{
				Console.WriteLine("Une erreur s'est produite : " + ex.Message);
			}
		}
	}
	}

