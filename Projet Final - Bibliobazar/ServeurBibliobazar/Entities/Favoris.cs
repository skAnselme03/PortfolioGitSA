using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class Favoris
    {
        [Key]
        public int CodeMembreUser { get; set; }
        [Key]
        public int CodeAnnonceNumRef { get; set; }

        public virtual Annonce CodeAnnonceNumRefNavigation { get; set; }
        public virtual Membre CodeMembreUserNavigation { get; set; }
    }
}
