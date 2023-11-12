using System;
using System.ComponentModel.DataAnnotations;

namespace ServeurBibliobazar.Models
{
	public class RequeteConnexion
	{
        [Required(ErrorMessage = "Le nom d'utilisateur est requis.")]
        public string Login { get; set; }

        [Required(ErrorMessage = "Le mot de passe est requis.")]
        public string MotDePasse { get; set; }

        public RequeteConnexion()
        {
        }

    }
}

