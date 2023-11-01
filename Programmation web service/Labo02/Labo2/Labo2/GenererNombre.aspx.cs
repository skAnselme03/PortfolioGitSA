using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace Labo2
{
	public partial class GenererNombre : System.Web.UI.Page
	{
		protected void Page_Load(object sender, EventArgs e)
		{

		}

        protected void BtnGenerer_Click(object sender, EventArgs e)
        {
			ServiceGenNombreChaine.GenererNombreEtChaineSoapClient serviceGenNbre = new ServiceGenNombreChaine.GenererNombreEtChaineSoapClient();

			try
			{
				// Récupérer le contenu des zones de texte
				string minText = MinTxtB.Text.ToString();
				string maxText = MaxTxtB.Text.ToString();

				// Vérifier si le contenu des zones de texte est un nombre entier
				if (int.TryParse(minText, out int min) && int.TryParse(maxText, out int max))
				{
					int intMin = Int32.Parse(minText);
					int intMax = Int32.Parse(maxText);
					this.Reslbl.Text = serviceGenNbre.GenererEntier(intMin, intMax).ToString();

				}
				// Vérifier si le contenu des zones de texte est un nombre décimal
				else if (double.TryParse(minText, out double minDouble) && double.TryParse(maxText, out double maxDouble))
				{
					double dblMin = double.Parse(minText);
					double dblMax = double.Parse(maxText);
					this.Reslbl.Text = serviceGenNbre.GenererDouble(dblMin, dblMax).ToString();
				}
				else
				{

					this.Reslbl.Text = "Veuillez saisir des nombres entiers ou réel!";
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