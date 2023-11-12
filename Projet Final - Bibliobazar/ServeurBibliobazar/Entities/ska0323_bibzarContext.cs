using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

// Code scaffolded by EF Core assumes nullable reference types (NRTs) are not used or disabled.
// If you have enabled NRTs for your project, then un-comment the following line:
// #nullable disable

namespace ServeurBibliobazar.Entities
{
    public partial class ska0323_bibzarContext : DbContext
    {
        public ska0323_bibzarContext()
        {
        }

        public ska0323_bibzarContext(DbContextOptions<ska0323_bibzarContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Administrateur> Administrateur { get; set; }
        public virtual DbSet<Annonce> Annonce { get; set; }
        public virtual DbSet<Auteur> Auteur { get; set; }
        public virtual DbSet<EtatLivre> EtatLivre { get; set; }
        public virtual DbSet<Favoris> Favoris { get; set; }
        public virtual DbSet<Genre> Genre { get; set; }
        public virtual DbSet<GenreLivre> GenreLivre { get; set; }
        public virtual DbSet<Livre> Livre { get; set; }
        public virtual DbSet<Membre> Membre { get; set; }
        public virtual DbSet<Note> Note { get; set; }
        public virtual DbSet<Signalement> Signalement { get; set; }
        public virtual DbSet<Utilisateur> Utilisateur { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            /*if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseMySQL("server=mysql-ska0323.alwaysdata.net;port=3306;user=ska0323_bibzar;password=bibliobaZar2023;database=ska0323_bibzar");
            }*/
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Administrateur>(entity =>
            {
                entity.HasKey(e => e.CodeUtilisateur)
                    .HasName("PRIMARY");

                entity.HasIndex(e => e.CodeEmploye)
                    .HasName("codeEmploye")
                    .IsUnique();

                entity.Property(e => e.CodeUtilisateur)
                    .HasColumnName("codeUtilisateur")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeEmploye)
                    .HasColumnName("codeEmploye")
                    .HasColumnType("int(10)");

                entity.HasOne(d => d.CodeUtilisateurNavigation)
                    .WithOne(p => p.Administrateur)
                    .HasForeignKey<Administrateur>(d => d.CodeUtilisateur)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Administrateur_ibfk_1");
            });

            modelBuilder.Entity<Annonce>(entity =>
            {
                entity.HasKey(e => e.NumeroReference)
                    .HasName("PRIMARY");

                entity.HasIndex(e => e.CodeEtatLivreId)
                    .HasName("codeEtatLivreId");

                entity.HasIndex(e => e.CodeMembreUser)
                    .HasName("codeMembreUser");

                entity.HasIndex(e => e.Isbnlivre)
                    .HasName("ISBNLivre")
                    .IsUnique();

                entity.Property(e => e.NumeroReference)
                    .HasColumnName("numeroReference")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeEtatLivreId)
                    .HasColumnName("codeEtatLivreId")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeMembreUser)
                    .HasColumnName("codeMembreUser")
                    .HasColumnType("int(10)");

                entity.Property(e => e.DateExpirationAnnonce)
                    .HasColumnName("dateExpirationAnnonce")
                    .HasColumnType("date")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DateParutionLivre)
                    .HasColumnName("dateParutionLivre")
                    .HasColumnType("date")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.DatePublicationAnnonce)
                    .HasColumnName("datePublicationAnnonce")
                    .HasColumnType("date")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Isbnlivre)
                    .HasColumnName("ISBNLivre")
                    .HasMaxLength(100)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);

                entity.Property(e => e.TitreLivre)
                    .IsRequired()
                    .HasColumnName("titreLivre")
                    .HasMaxLength(25);

                entity.Property(e => e.Prix)
                    .HasColumnName("prix")
                    .HasColumnType("float");

                entity.Property(e => e.Image_Url)
                    .IsRequired()
                    .HasColumnName("image_url")
                    .HasMaxLength(255);

                entity.HasOne(d => d.CodeEtatLivre)
                    .WithMany(p => p.Annonce)
                    .HasForeignKey(d => d.CodeEtatLivreId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Annonce_ibfk_2");

                entity.HasOne(d => d.CodeMembreUserNavigation)
                    .WithMany(p => p.Annonce)
                    .HasForeignKey(d => d.CodeMembreUser)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Annonce_ibfk_1");
            });

            modelBuilder.Entity<Auteur>(entity =>
            {
                entity.HasKey(e => e.IdAuteur)
                    .HasName("PRIMARY");

                entity.Property(e => e.IdAuteur)
                    .HasColumnName("idAuteur")
                    .HasColumnType("int(10)");

                entity.Property(e => e.DateNaissance)
                    .HasColumnName("dateNaissance")
                    .HasColumnType("date")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Nom)
                    .IsRequired()
                    .HasColumnName("nom")
                    .HasMaxLength(25);

                entity.Property(e => e.Prenom)
                    .IsRequired()
                    .HasColumnName("prenom")
                    .HasMaxLength(25);

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);
            });

            modelBuilder.Entity<EtatLivre>(entity =>
            {
                entity.HasKey(e => e.IdEtat)
                    .HasName("PRIMARY");

                entity.Property(e => e.IdEtat)
                    .HasColumnName("idEtat")
                    .HasColumnType("int(10)");

                entity.Property(e => e.Description)
                    .IsRequired()
                    .HasColumnName("description")
                    .HasMaxLength(100);

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);
            });

            modelBuilder.Entity<Favoris>(entity =>
            {
                modelBuilder.Entity<Favoris>().HasKey(f => new { f.CodeMembreUser, f.CodeAnnonceNumRef });

                entity.HasIndex(e => e.CodeAnnonceNumRef)
                    .HasName("codeAnnonceNumRef");

                entity.HasIndex(e => e.CodeMembreUser)
                    .HasName("codeMembreUser");

                entity.Property(e => e.CodeAnnonceNumRef)
                    .HasColumnName("codeAnnonceNumRef")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeMembreUser)
                    .HasColumnName("codeMembreUser")
                    .HasColumnType("int(10)");

                entity.HasOne(d => d.CodeAnnonceNumRefNavigation)
                    .WithMany()
                    .HasForeignKey(d => d.CodeAnnonceNumRef)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Favoris_ibfk_2");

                entity.HasOne(d => d.CodeMembreUserNavigation)
                    .WithMany()
                    .HasForeignKey(d => d.CodeMembreUser)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Favoris_ibfk_1");
            });

            modelBuilder.Entity<Genre>(entity =>
            {
                entity.HasKey(e => e.IdGenre)
                    .HasName("PRIMARY");

                entity.Property(e => e.IdGenre)
                    .HasColumnName("idGenre")
                    .HasColumnType("int(10)");

                entity.Property(e => e.Description)
                    .IsRequired()
                    .HasColumnName("description")
                    .HasMaxLength(100);

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);
            });

            modelBuilder.Entity<GenreLivre>(entity =>
            {
                entity.HasKey(gl => new { gl.CodeAnnonceNumRef, gl.CodeGenreId });

                entity.HasOne(gl => gl.CodeAnnonceNumRefNavigation)
                    .WithMany(a => a.GenreLivres)
                    .HasForeignKey(gl => gl.CodeAnnonceNumRef)
                    .OnDelete(DeleteBehavior.ClientSetNull);

                entity.HasOne(gl => gl.CodeGenre)
                    .WithMany(g => g.GenreLivres)
                    .HasForeignKey(gl => gl.CodeGenreId)
                    .OnDelete(DeleteBehavior.ClientSetNull);
            });

            modelBuilder.Entity<Livre>(entity =>
            {
                entity.HasKey(l => new { l.CodeAuteurId, l.CodeAnnonceNumRef });

                entity.HasIndex(e => e.CodeAnnonceNumRef)
                    .HasName("codeAnnonceNumRef");

                entity.HasIndex(e => e.CodeAuteurId)
                    .HasName("codeAuteurId");

                entity.Property(e => e.CodeAnnonceNumRef)
                    .HasColumnName("codeAnnonceNumRef")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeAuteurId)
                    .HasColumnName("codeAuteurId")
                    .HasColumnType("int(10)");

                entity.HasOne(d => d.CodeAnnonceNumRefNavigation)
                    .WithMany()
                    .HasForeignKey(d => d.CodeAnnonceNumRef)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Livre_ibfk_2");

                entity.HasOne(d => d.CodeAuteur)
                    .WithMany()
                    .HasForeignKey(d => d.CodeAuteurId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Livre_ibfk_1");
            });

            modelBuilder.Entity<Membre>(entity =>
            {
                entity.HasKey(e => e.CodeUtilisateur)
                    .HasName("PRIMARY");

                entity.HasIndex(e => e.Courriel)
                    .HasName("courriel")
                    .IsUnique();

                entity.HasIndex(e => e.Username)
                    .HasName("username")
                    .IsUnique();

                entity.Property(e => e.CodeUtilisateur)
                    .HasColumnName("codeUtilisateur")
                    .HasColumnType("int(10)");

                entity.Property(e => e.Courriel)
                    .IsRequired()
                    .HasColumnName("courriel")
                    .HasMaxLength(100);

                entity.Property(e => e.MotDePasse)
                    .IsRequired()
                    .HasColumnName("motDePasse")
                    .HasMaxLength(25);

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);

                entity.Property(e => e.Telephone)
                    .HasColumnName("telephone")
                    .HasMaxLength(25)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Username)
                    .IsRequired()
                    .HasColumnName("username")
                    .HasMaxLength(25);

                entity.HasOne(d => d.CodeUtilisateurNavigation)
                    .WithOne(p => p.Membre)
                    .HasForeignKey<Membre>(d => d.CodeUtilisateur)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Membre_ibfk_1");
            });

            modelBuilder.Entity<Note>(entity =>
            {
                modelBuilder.Entity<Note>().HasKey(f => new { f.CodeMembreUser, f.CodeAnnonceNumRef });

                entity.HasIndex(e => e.CodeAnnonceNumRef)
                    .HasName("codeAnnonceNumRef");

                entity.HasIndex(e => e.CodeMembreUser)
                    .HasName("codeMembreUser");

                entity.Property(e => e.CodeAnnonceNumRef)
                    .HasColumnName("codeAnnonceNumRef")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeMembreUser)
                    .HasColumnName("codeMembreUser")
                    .HasColumnType("int(10)");

                entity.Property(e => e.DateNote)
                    .HasColumnName("dateNote")
                    .HasColumnType("date");

                entity.Property(e => e.Description)
                    .HasColumnName("description")
                    .HasMaxLength(100)
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.Evaluation)
                    .HasColumnName("evaluation")
                    .HasColumnType("int(10)");

                entity.HasOne(d => d.CodeAnnonceNumRefNavigation)
                    .WithMany()
                    .HasForeignKey(d => d.CodeAnnonceNumRef)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Note_ibfk_2");

                entity.HasOne(d => d.CodeMembreUserNavigation)
                    .WithMany()
                    .HasForeignKey(d => d.CodeMembreUser)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Note_ibfk_1");
            });

            modelBuilder.Entity<Signalement>(entity =>
            {
                entity.HasKey(e => e.NumeroIdentification)
                    .HasName("PRIMARY");

                entity.HasIndex(e => e.CodeAdminUser)
                    .HasName("codeAdminUser");

                entity.HasIndex(e => e.CodeAnnonceNumRef)
                    .HasName("codeAnnonceNumRef");

                entity.HasIndex(e => e.CodeMembreUser)
                    .HasName("codeMembreUser");

                entity.Property(e => e.NumeroIdentification)
                    .HasColumnName("numeroIdentification")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeAdminUser)
                    .HasColumnName("codeAdminUser")
                    .HasColumnType("int(10)")
                    .HasDefaultValueSql("'NULL'");

                entity.Property(e => e.CodeAnnonceNumRef)
                    .HasColumnName("codeAnnonceNumRef")
                    .HasColumnType("int(10)");

                entity.Property(e => e.CodeMembreUser)
                    .HasColumnName("codeMembreUser")
                    .HasColumnType("int(10)");

                entity.Property(e => e.DateSignalement)
                    .HasColumnName("dateSignalement")
                    .HasColumnType("date");

                entity.Property(e => e.Description)
                    .IsRequired()
                    .HasColumnName("description")
                    .HasMaxLength(100);

                entity.Property(e => e.Statut)
                    .IsRequired()
                    .HasColumnName("statut")
                    .HasMaxLength(25);

                entity.HasOne(d => d.CodeAdminUserNavigation)
                    .WithMany(p => p.Signalement)
                    .HasForeignKey(d => d.CodeAdminUser)
                    .HasConstraintName("Signalement_ibfk_2");

                entity.HasOne(d => d.CodeAnnonceNumRefNavigation)
                    .WithMany(p => p.Signalement)
                    .HasForeignKey(d => d.CodeAnnonceNumRef)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Signalement_ibfk_1");

                entity.HasOne(d => d.CodeMembreUserNavigation)
                    .WithMany(p => p.Signalement)
                    .HasForeignKey(d => d.CodeMembreUser)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("Signalement_ibfk_3");
            });

            modelBuilder.Entity<Utilisateur>(entity =>
            {
                entity.HasKey(e => e.IdUtilisateur)
                    .HasName("PRIMARY");

                entity.Property(e => e.IdUtilisateur)
                    .HasColumnName("idUtilisateur")
                    .HasColumnType("int(10)");

                entity.Property(e => e.Nom)
                    .IsRequired()
                    .HasColumnName("nom")
                    .HasMaxLength(25);

                entity.Property(e => e.Prenom)
                    .IsRequired()
                    .HasColumnName("prenom")
                    .HasMaxLength(25);
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
