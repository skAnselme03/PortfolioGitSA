using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Annonce
    {
        public Annonce()
        {
            Signalement = new HashSet<Signalement>();
        }

        public int NumeroReference { get; set; }
        public string Isbnlivre { get; set; }
        public string TitreLivre { get; set; }
        public float Prix { get; set; }
        public string Image_Url { get; set; }
        public DateTime? DateParutionLivre { get; set; }
        public DateTime? DatePublicationAnnonce { get; set; }
        public DateTime? DateExpirationAnnonce { get; set; }
        public string Statut { get; set; }
        public int CodeMembreUser { get; set; }
        public int CodeEtatLivreId { get; set; }

        public virtual EtatLivre CodeEtatLivre { get; set; }
        public virtual Membre CodeMembreUserNavigation { get; set; }
        public virtual ICollection<Signalement> Signalement { get; set; }
        public virtual ICollection<GenreLivre> GenreLivres { get; set; }
    }
}
