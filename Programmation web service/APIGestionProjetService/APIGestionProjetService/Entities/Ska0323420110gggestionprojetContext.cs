using System;
using System.Collections.Generic;
using Microsoft.EntityFrameworkCore;

namespace APIGestionProjetService.Entities;

public partial class Ska0323420110gggestionprojetContext : DbContext
{
    public Ska0323420110gggestionprojetContext()
    {
    }

    public Ska0323420110gggestionprojetContext(DbContextOptions<Ska0323420110gggestionprojetContext> options)
        : base(options)
    {
    }

    public virtual DbSet<Projet> Projets { get; set; }

    public virtual DbSet<Tache> Taches { get; set; }

    public virtual DbSet<Utilisateur> Utilisateurs { get; set; }

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) { }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Projet>(entity =>
        {
            entity.HasKey(e => e.IdProjet).HasName("PRIMARY");

            entity.HasIndex(e => e.CodeProjet, "CodeProjet").IsUnique();

            entity.HasIndex(e => e.CodeUtilisateur, "CodeUtilisateur");

            entity.Property(e => e.IdProjet).HasColumnType("int(11)");
            entity.Property(e => e.Client).HasMaxLength(20);
            entity.Property(e => e.CodeProjet)
                .HasMaxLength(100)
                .HasDefaultValueSql("'NULL'");
            entity.Property(e => e.CodeUtilisateur)
                .HasDefaultValueSql("'NULL'")
                .HasColumnType("int(11)");
            entity.Property(e => e.DateDebut).HasColumnType("date");
            entity.Property(e => e.DateFin).HasColumnType("date");
            entity.Property(e => e.DateTermine)
                .HasDefaultValueSql("'NULL'")
                .HasColumnType("date");
            entity.Property(e => e.Description).HasMaxLength(100);
            entity.Property(e => e.Nom).HasMaxLength(45);
            entity.Property(e => e.Statut)
                .HasDefaultValueSql("'''Non Assigné'''")
                .HasColumnType("enum('Non Assigné','À faire','En cours','En pause','En retard','Archivé','Terminé')");

            entity.HasOne(d => d.CodeUtilisateurNavigation).WithMany(p => p.Projets)
                .HasForeignKey(d => d.CodeUtilisateur)
                .OnDelete(DeleteBehavior.Restrict)
                .HasConstraintName("Projets_ibfk_1");
        });

        modelBuilder.Entity<Tache>(entity =>
        {
            entity.HasKey(e => e.IdTache).HasName("PRIMARY");

            entity.HasIndex(e => e.CodeTache, "CodeTache").IsUnique();

            entity.HasIndex(e => e.IdProjet, "IdProjet");

            entity.Property(e => e.IdTache).HasColumnType("int(11)");
            entity.Property(e => e.CodeTache).HasMaxLength(100);
            entity.Property(e => e.DateDebut).HasColumnType("date");
            entity.Property(e => e.DateFin).HasColumnType("date");
            entity.Property(e => e.DateTermine).HasColumnType("date");
            entity.Property(e => e.Description).HasMaxLength(100);
            entity.Property(e => e.IdProjet)
                .HasDefaultValueSql("'NULL'")
                .HasColumnType("int(11)");
            entity.Property(e => e.Nom).HasMaxLength(45);
            entity.Property(e => e.Statut)
                .HasDefaultValueSql("'''À faire'''")
                .HasColumnType("enum('À faire','En cours','En pause','Non requis','En retard','Terminé')");

            entity.HasOne(d => d.IdProjetNavigation).WithMany(p => p.Taches)
                .HasForeignKey(d => d.IdProjet)
                .OnDelete(DeleteBehavior.Restrict)
                .HasConstraintName("Taches_ibfk_1");
        });

        modelBuilder.Entity<Utilisateur>(entity =>
        {
            entity.HasKey(e => e.IdUtilisateur).HasName("PRIMARY");

            entity.HasIndex(e => e.Courriel, "Courriel").IsUnique();

            entity.Property(e => e.IdUtilisateur).HasColumnType("int(11)");
            entity.Property(e => e.Courriel).HasMaxLength(45);
            entity.Property(e => e.Departement).HasMaxLength(45);
            entity.Property(e => e.Nom).HasMaxLength(45);
            entity.Property(e => e.Password).HasMaxLength(15);
            entity.Property(e => e.Prenom).HasMaxLength(45);
            entity.Property(e => e.Titre).HasMaxLength(45);
            entity.Property(e => e.Username).HasMaxLength(45);
        });

        OnModelCreatingPartial(modelBuilder);
    }

    partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
}
