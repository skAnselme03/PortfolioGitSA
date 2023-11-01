using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Lab02_b
{
	internal class Program
	{
		static void Main(string[] args)
		{
			ServiceGenNbreEntDbl.GenererNombreEtChaineSoapClient serviceGenNombre = new ServiceGenNbreEntDbl.GenererNombreEtChaineSoapClient();
			Console.WriteLine("************************************");
			Console.WriteLine("*** Génerer un nombre aléatoire  ***");
			Console.WriteLine("************************************\n");
			Console.Write("Saisir un nombre minimum entier ou décimal : ");
			string minText = Console.ReadLine();
			Console.Write("Saisir un nombre maximale entier ou décimal : ");
			string maxText = Console.ReadLine();


			try
			{
				int intMin;
				int intMax;
				double dblMin;
				double dblMax;

				if (int.TryParse(minText, out intMin) && int.TryParse(maxText, out intMax))
				{
					// Utilisez intMin et intMax pour générer un nombre entier
					Console.WriteLine("Nombre généré : " + serviceGenNombre.GenererEntier(intMin, intMax).ToString());
				}
				else if (double.TryParse(minText, out dblMin) && double.TryParse(maxText, out dblMax))
				{
					// Utilisez dblMin et dblMax pour générer un nombre décimal
					Console.WriteLine("Nombre généré : " + serviceGenNombre.GenererDouble(dblMin, dblMax).ToString());
				}
				else
				{
					Console.WriteLine("Veuillez saisir des nombres entiers ou réels !");
				}

			}
			catch (Exception ex)
			{
				// Gérez l'exception ici, par exemple, en l'affichant dans un journal
				Console.WriteLine("Erreur lors de l'appel du service Web : " + ex.Message);
			}

			
		}
	}
}
