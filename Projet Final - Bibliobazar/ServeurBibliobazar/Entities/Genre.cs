using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Genre
    {
        public int IdGenre { get; set; }
        public string Description { get; set; }
        public string Statut { get; set; }
        public virtual ICollection<GenreLivre> GenreLivres { get; set; }
    }
}
