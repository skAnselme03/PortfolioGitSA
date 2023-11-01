namespace APISuperHero.Models
{
    public class SuperHero
    {
        public int Id { get; set; }
        public String Nom { get; set; } = string.Empty;
        public String Prenom { get; set; } = string.Empty;
        public String NomFamille { get; set; } = String.Empty;
        public String Place { get; set; } = string.Empty;
    }
}
