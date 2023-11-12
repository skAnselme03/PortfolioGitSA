using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Signalement
    {
        public int NumeroIdentification { get; set; }
        public DateTime DateSignalement { get; set; }
        public string Description { get; set; }
        public string Statut { get; set; }
        public int CodeAnnonceNumRef { get; set; }
        public int? CodeAdminUser { get; set; }
        public int CodeMembreUser { get; set; }

        public virtual Administrateur CodeAdminUserNavigation { get; set; }
        public virtual Annonce CodeAnnonceNumRefNavigation { get; set; }
        public virtual Membre CodeMembreUserNavigation { get; set; }
    }
}
