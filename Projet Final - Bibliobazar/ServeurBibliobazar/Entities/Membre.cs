using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Membre
    {
        public Membre()
        {
            Annonce = new HashSet<Annonce>();
            Signalement = new HashSet<Signalement>();
        }

        public int CodeUtilisateur { get; set; }
        public string Username { get; set; }
        public string MotDePasse { get; set; }
        public string Courriel { get; set; }
        public string Telephone { get; set; }
        public string Statut { get; set; }

        public virtual Utilisateur CodeUtilisateurNavigation { get; set; }
        public virtual ICollection<Annonce> Annonce { get; set; }
        public virtual ICollection<Signalement> Signalement { get; set; }
    }
}
