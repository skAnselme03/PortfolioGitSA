using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class EtatLivre
    {
        public EtatLivre()
        {
            Annonce = new HashSet<Annonce>();
        }

        public int IdEtat { get; set; }
        public string Description { get; set; }
        public string Statut { get; set; }

        public virtual ICollection<Annonce> Annonce { get; set; }
    }
}
