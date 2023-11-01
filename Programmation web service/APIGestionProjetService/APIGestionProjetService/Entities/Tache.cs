using System;
using System.Collections.Generic;

namespace APIGestionProjetService.Entities;

public partial class Tache
{
    public int IdTache { get; set; }

    public string CodeTache { get; set; } = null!;

    public string Nom { get; set; } = null!;

    public string Description { get; set; } = null!;

    public DateTime DateDebut { get; set; }

    public DateTime DateFin { get; set; }

    public DateTime DateTermine { get; set; }

    public string Statut { get; set; } = null!;

    public int? IdProjet { get; set; }

    public virtual Projet? IdProjetNavigation { get; set; }
}
