using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Web;
using System.Web.Services;

namespace Labo01
{
	/// <summary>
	/// Description résumée de GenererNombreEtChaine
	/// </summary>
	[WebService(Namespace = "http://tempuri.org/")]
	[WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
	[System.ComponentModel.ToolboxItem(false)]
	// Pour autoriser l'appel de ce service Web depuis un script à l'aide d'ASP.NET AJAX, supprimez les marques de commentaire de la ligne suivante. 
	// [System.Web.Script.Services.ScriptService]
	public class GenererNombreEtChaine : System.Web.Services.WebService
	{

		[WebMethod]
		public string HelloWorld()
		{
			return "Hello World";
		}

		/// <summary>
		/// Générer un entier entre 2 valeurs
		/// </summary>
		/// <param name="min">Borne inférieur</param>
		/// <param name="max">Bornes supérieur</param>
		/// <returns>Nombre entier générer</returns>
		[WebMethod]
		public int GenererEntier(int min, int max)
		{
			// Créez une instance de la classe Random pour générer des nombres aléatoires.
			Random random = new Random();
			return random.Next(min, max + 1);
		}

		/// <summary>
		/// Générer un double entre 2 valeurs
		/// </summary>
		/// <param name="a">Borne inférieur</param>
		/// <param name="b">Borne supérieur</param>
		/// <returns>Nombre décimal générer</returns>
		[WebMethod]
		public double GenererDouble(double a, double b)
		{
			// Créez une instance de la classe Random pour générer des nombres aléatoires.
			Random random = new Random();
			// Utilisez NextDouble pour générer un nombre aléatoire entre 0(inclus) et 1(exclus),
			//étirez-le pour qu'il soit dans la plage [a, b] en le multipliant par la différence entre b et a,
			// ajouter a pour décaler la plage vers la droite.
			double resultat = a + random.NextDouble() * (b - a);
			return Math.Round(resultat,2); //limiter à deux chiffres après la virgule
		}

		/// <summary>
		/// Générer une chaine de caracteres de longueur n
		/// </summary>
		/// <param name="longueur">Longeur de la chaine (nbre. entier)</param>
		/// <returns>Chaine générer</returns>
		[WebMethod]
		public string GenererChaine(int longueur)
		{
			// Liste des caractères possibles qui peuvent être inclus dans la chaîne générée.
			const string caracteres = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

			// Créez une instance de la classe Random pour générer des nombres aléatoires.
			Random random = new Random();
			
			// Utilisez un StringBuilder pour construire la chaîne de caractères.
			StringBuilder builder = new StringBuilder(longueur);

			// Générez un caractère aléatoire à partir de caracteres pour chaque position de la chaîne.
			for (int i = 0; i < longueur; i++)
			{
				int index = random.Next(caracteres.Length);
				builder.Append(caracteres[index]);
			}
			return builder.ToString();
		}
	}
}
