using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;

namespace APISuperHero.Models
{
    public class ContextSuperHero : DbContext
    {
        public ContextSuperHero(DbContextOptions<ContextSuperHero> options)
: base(options)
        {
        }
        public DbSet<SuperHero> LesSuperHeros { get; set; } = null!;
    }
}
