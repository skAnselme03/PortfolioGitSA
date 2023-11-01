using System;
using System.Collections.Generic;

namespace APIGestionProjetService.Entities;

public partial class Utilisateur
{
    public int IdUtilisateur { get; set; }

    public string Nom { get; set; } = null!;

    public string Prenom { get; set; } = null!;

    public string Username { get; set; } = null!;

    public string Password { get; set; } = null!;

    public string Courriel { get; set; } = null!;

    public string Titre { get; set; } = null!;

    public string Departement { get; set; } = null!;

    public virtual ICollection<Projet> Projets { get; set; } = new List<Projet>();
}
