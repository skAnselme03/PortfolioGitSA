using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Administrateur
    {
        public Administrateur()
        {
            Signalement = new HashSet<Signalement>();
        }

        public int CodeUtilisateur { get; set; }
        public int CodeEmploye { get; set; }

        public virtual Utilisateur CodeUtilisateurNavigation { get; set; }
        public virtual ICollection<Signalement> Signalement { get; set; }
    }
}
