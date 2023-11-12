﻿using System;
using System.Collections.Generic;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class GenreLivre
    {
        public int CodeGenreId { get; set; }
        public int CodeAnnonceNumRef { get; set; }

        public virtual Annonce CodeAnnonceNumRefNavigation { get; set; }
        public virtual Genre CodeGenre { get; set; }
    }
}
