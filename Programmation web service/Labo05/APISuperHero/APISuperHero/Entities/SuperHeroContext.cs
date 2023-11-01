using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace APISuperHero.Entities;

public partial class SuperHeroContext : DbContext
{
    public SuperHeroContext()
    {
    }

    public SuperHeroContext(DbContextOptions<SuperHeroContext> options)
        : base(options)
    {
    }

    public virtual DbSet<SuperHero> Superheros { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    { }
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<SuperHero>(entity =>
        {
            entity.HasKey(e => e.Id).HasName("PRIMARY");

            entity.ToTable("superhero", "APISuperHero");

            entity.Property(e => e.Nom).HasMaxLength(45);
            entity.Property(e => e.NomFamille).HasMaxLength(45);
            entity.Property(e => e.Place).HasMaxLength(45);
            entity.Property(e => e.Prenom).HasMaxLength(45);
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
