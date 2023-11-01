using System;
using System.Collections.Generic;

namespace APISuperHero.Entities;

public partial class SuperHero
{
    public int Id { get; set; }

    public string Nom { get; set; } = null!;

    public string Prenom { get; set; } = null!;

    public string NomFamille { get; set; } = null!;

    public string Place { get; set; } = null!;
}
