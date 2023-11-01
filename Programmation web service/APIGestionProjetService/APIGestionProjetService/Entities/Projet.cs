using System;
using System.Collections.Generic;

namespace APIGestionProjetService.Entities;

public partial class Projet
{
    public int IdProjet { get; set; }

    public string? CodeProjet { get; set; }

    public string Nom { get; set; } = null!;

    public string Description { get; set; } = null!;

    public DateTime DateDebut { get; set; }

    public DateTime DateFin { get; set; }

    public DateTime? DateTermine { get; set; }

    public string Client { get; set; } = null!;

    public string Statut { get; set; } = null!;

    public int? CodeUtilisateur { get; set; }

    public virtual Utilisateur? CodeUtilisateurNavigation { get; set; }

    public virtual ICollection<Tache> Taches { get; set; } = new List<Tache>();
}
