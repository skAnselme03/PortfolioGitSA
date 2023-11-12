using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ServeurBibliobazar.Entities;
using ServeurBibliobazar.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace ServeurBibliobazar.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AnnonceController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public AnnonceController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces
        /// Inclut aussi le genre et l'état de chaque annonce
        /// MÉTHODE ADMIN
        /// </summary>
        /// <returns>La liste détaillée de toutes les annonces</returns>
        [HttpGet("RecupererToutesAnnonces")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnonces()
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .Join(
                    DBContext.Livre,
                    annonce => annonce.NumeroReference,
                    livre => livre.CodeAnnonceNumRef,
                    (annonce, livre) => new
                    {
                        annonce.NumeroReference,
                        annonce.Isbnlivre,
                        annonce.TitreLivre,
                        annonce.Prix,
                        annonce.Image_Url,
                        annonce.DateParutionLivre,
                        annonce.DatePublicationAnnonce,
                        annonce.DateExpirationAnnonce,
                        annonce.Statut,
                        annonce.CodeMembreUser,
                        annonce.CodeEtatLivre,
                        Auteur = new
                        {
                            Nom = livre.CodeAuteur.Nom, 
                            Prenom = livre.CodeAuteur.Prenom, 
                        },
                        Genres = annonce.GenreLivres.Select(gl => gl.CodeGenre.Description),
                        Etat = new
                        {
                            Description = annonce.CodeEtatLivre.Description
                        }
                    })
                .ToListAsync();

            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces actives/visibles
        /// Inclut aussi le genre et l'état de chaque annonce
        /// SEULEMENT LES ANNONCES ACTIVES
        /// </summary>
        /// <returns>La liste détaillée de toutes les annonces actives/visibles</returns>
        [HttpGet("RecupererAnnoncesVisibles")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesVisible()
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .Where(a => a.Statut == "Visible")
                .Join(
                    DBContext.Livre,
                    annonce => annonce.NumeroReference,
                    livre => livre.CodeAnnonceNumRef,
                    (annonce, livre) => new
                    {
                        annonce.NumeroReference,
                        annonce.Isbnlivre,
                        annonce.TitreLivre,
                        annonce.Prix,
                        annonce.Image_Url,
                        annonce.DateParutionLivre,
                        annonce.DatePublicationAnnonce,
                        annonce.DateExpirationAnnonce,
                        annonce.Statut,
                        annonce.CodeMembreUser,
                        annonce.CodeEtatLivre,
                        Auteur = (livre.CodeAuteur != null) ? new
                        {
                            Nom = livre.CodeAuteur.Nom,
                            Prenom = livre.CodeAuteur.Prenom,
                        } : null,
                        Genres = (annonce.GenreLivres != null) ? annonce.GenreLivres.Select(gl => gl.CodeGenre.Description) : null,
                        Etat = (annonce.CodeEtatLivre != null) ? new
                        {
                            Description = annonce.CodeEtatLivre.Description
                        } : null
                    })
                .ToListAsync();

            if (annonces == null || annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /* REDONDANT
        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces
        /// Selon les informations du titre
        /// Inclut aussi le genre et l'état de chaque annonce
        /// </summary>
        /// <param name="titre">Titre ou partie du titre recherché</param>
        /// <returns>La liste détaillée de toutes les annonces contenant au moins en partie le titre demandé</returns>
        [HttpGet("RecupererAnnoncesParTitre")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesByTitre(string titre)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.TitreLivre.Contains(titre))
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description), // Genre
                Etat = new
                    {
                        Description = a.CodeEtatLivre.Description // État
                }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }
        */

        /* REDONDANT
        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces
        /// Selon le genre demandé
        /// Inclut aussi le genre et l'état de chaque annonce
        /// </summary>
        /// <param name="genre">Genre du livre recherché</param>
        /// <returns>La liste détaillée de toutes les annonces selon le genre demandé</returns>
        [HttpGet("RecupererAnnoncesParGenre")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesByGenre(string genre)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.GenreLivres.Any(gl => gl.CodeGenre.Description.Contains(genre)))
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description), // Genre
                Etat = new
                    {
                        Description = a.CodeEtatLivre.Description // État
                    }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }
        */

        /* REDONDANT
        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces
        /// Selon l'état demandé
        /// Inclut aussi le genre et l'état de chaque annonce
        /// </summary>
        /// <param name="etat">État du livre recherché</param>
        /// <returns>La liste détaillée de toutes les annonces selon l'état demandé</returns>
        [HttpGet("RecupererAnnoncesParEtat")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesByEtat(string etat)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.CodeEtatLivre.Description.Contains(etat))
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description), // Genre
                Etat = new
                    {
                        Description = a.CodeEtatLivre.Description // État
                    }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }
        */

        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces visibles
        /// Selon le membre qui les a créé
        /// Inclut aussi le genre et l'état de chaque annonce
        /// </summary>
        /// <param name="idMembre">Le code utilisateur du membre</param>
        /// <returns>La liste de toutes les annonces visibles appartenant à ce membre</returns>
        [HttpGet("RecupererAnnoncesVisiblesParMembre")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesVisiblesByMembre(int idMembre)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.CodeMembreUser.Equals(idMembre) && a.Statut == "Visible")
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description), // Genre
                    Etat = new
                    {
                        Description = a.CodeEtatLivre.Description // État
                    }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /// <summary>
        /// Permet de récupérer toutes les informations de toutes les annonces archivées
        /// Selon le membre qui les a créé
        /// Inclut aussi le genre et l'état de chaque annonce
        /// </summary>
        /// <param name="idMembre">Le code utilisateur du membre</param>
        /// <returns>La liste de toutes les annonces archivées appartenant à ce membre</returns>
        [HttpGet("RecupererAnnoncesArchivesParMembre")]
        public async Task<ActionResult<IEnumerable<object>>> GetAllAnnoncesArchivesByMembre(int idMembre)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.CodeMembreUser.Equals(idMembre) && a.Statut == "Archivé")
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description), // Genre
                    Etat = new
                    {
                        Description = a.CodeEtatLivre.Description // État
                    }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /// <summary>
        /// Méthode de recherche la plus complète 
        /// Sera utilisée pour notre barre de recherche
        /// </summary>
        /// <param name="titre">Titre ou partie du titre recherché</param>
        /// <param name="genreId">Genre du livre recherché</param>
        /// <param name="etatId">État du livre recherché</param>
        /// <returns>La liste de toutes les annonces visibles correspondant à ces critères</returns>
        [HttpGet("RechercherAnnonces")]
        public async Task<ActionResult<IEnumerable<object>>> SearchAnnonces(string titre, int? genreId, int? etatId)
        {
            var annoncesQuery = DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .ThenInclude(gl => gl.CodeGenre)
                .Where(a => a.Statut == "Visible");

            if (!string.IsNullOrEmpty(titre))
            {
                annoncesQuery = annoncesQuery.Where(a => a.TitreLivre.Contains(titre));
            }

            if (genreId.HasValue)
            {
                annoncesQuery = annoncesQuery.Where(a => a.GenreLivres.Any(gl => gl.CodeGenre.IdGenre == genreId));
            }

            if (etatId.HasValue)
            {
                annoncesQuery = annoncesQuery.Where(a => a.CodeEtatLivre.IdEtat == etatId);
            }

            var annonces = await annoncesQuery
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    a.CodeEtatLivre,
                    Genres = a.GenreLivres.Select(gl => gl.CodeGenre.Description),
                    Etat = new
                    {
                        Description = a.CodeEtatLivre.Description
                    }
                })
                .ToListAsync();

            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /// <summary>
        /// Méthode GET BY Id (ou ici Numéro de Référence)
        /// Cela nous permettra de récupérer toutes les informations d'une annonce en particulier
        /// (pour affichage individuel)
        /// </summary>
        /// <param name="numeroReference">Le numéro de référence de l'annonce recherchée</param>
        /// <returns>Toutes les informations concernant cette annonce (Auteur, Genre et État inclus)</returns>
        [HttpGet("RecupererAnnonceParNumeroReference")]
        public async Task<ActionResult<IEnumerable<object>>> GetAnnonceByNumeroReference(int numeroReference)
        {
            var annonces = await DBContext.Annonce
                .Include(a => a.CodeEtatLivre)
                .Include(a => a.GenreLivres)
                .Include(a => a.CodeMembreUserNavigation)
                .Where(a => a.NumeroReference == numeroReference)
                .Select(a => new
                {
                    a.NumeroReference,
                    a.Isbnlivre,
                    a.TitreLivre,
                    a.Prix,
                    a.Image_Url,
                    a.DateParutionLivre,
                    a.DatePublicationAnnonce,
                    a.DateExpirationAnnonce,
                    a.Statut,
                    a.CodeMembreUser,
                    MembreUsername = a.CodeMembreUserNavigation.Username,
                    a.CodeEtatLivre,
                    Auteur = new
                    {
                        Nom = DBContext.Livre
                            .Where(l => l.CodeAnnonceNumRef == a.NumeroReference)
                            .Select(l => l.CodeAuteur.Nom)
                            .FirstOrDefault(),
                        Prenom = DBContext.Livre
                            .Where(l => l.CodeAnnonceNumRef == a.NumeroReference)
                            .Select(l => l.CodeAuteur.Prenom)
                            .FirstOrDefault(),
                        IdAuteur = DBContext.Livre
                            .Where(l => l.CodeAnnonceNumRef == a.NumeroReference)
                            .Select(l => l.CodeAuteur.IdAuteur)
                            .FirstOrDefault()
                    },
                    Genres = a.GenreLivres.Select(gl => new
                    {
                        Description = gl.CodeGenre.Description,
                        CodeGenreId = gl.CodeGenre.IdGenre
                    }),
                    Etat = new
                    {
                        Description = a.CodeEtatLivre.Description
                    }
                })
                .ToListAsync();
            if (annonces.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return annonces;
            }
        }

        /// <summary>
        /// Méthode CREATE (INSERT) pour créer une nouvelle Annonce (+ Livre + GenreLivre)
        /// </summary>
        /// <param name="isbnLivre">ISBN du livre de l'annonce</param>
        /// <param name="titreLivre">Titre du livre de l'annonce</param>
        /// <param name="prix">Prix du livre dans l'annonce</param>
        /// <param name="image_url">Image du livre de l'annonce</param>
        /// <param name="dateParutionLivre">Date de parution du livre de l'annonce</param>
        /// <param name="statut">Statut de l'annonce</param>
        /// <param name="codeMembreUser">Numéro de référence de l'utilisateur ayant publié l'annonce</param>
        /// <param name="codeEtatLivreId">code de l'état du livre de l'annonce</param>
        /// <param name="codeGenreId">Code du genre du livre de l'annonce</param>
        /// <param name="idAuteur">Id de l'auteur du livre de l'annonce</param>
        /// <returns></returns>
        [HttpPost("CreerAnnonce")]
        public async Task<IActionResult> InsertAnnonce(string isbnLivre, string titreLivre, float prix, 
            string image_url, DateTime? dateParutionLivre, string statut, int codeMembreUser, int codeEtatLivreId, 
            int codeGenreId, int idAuteur)
        {
            // D'abord on créé la nouvelle Annonce
            var nouvelleAnnonce = new Annonce
            {
                Isbnlivre = isbnLivre,
                TitreLivre = titreLivre,
                Prix = prix,
                Image_Url = image_url,
                DateParutionLivre = dateParutionLivre,
                Statut = statut,
                CodeMembreUser = codeMembreUser,
                CodeEtatLivreId = codeEtatLivreId
            };
            DBContext.Annonce.Add(nouvelleAnnonce);
            await DBContext.SaveChangesAsync(); // Sauvegarde l'annonce dans la BDD pour pouvoir récupérer son NumeroReference

            // Maintenant on crée un nouveau Livre pour faire le lien entre Annonce et Auteur
            var nouveauLivre = new Livre
            {
                CodeAuteurId = idAuteur,
                CodeAnnonceNumRef = nouvelleAnnonce.NumeroReference
            };
            DBContext.Livre.Add(nouveauLivre);

            // Et enfin on crée un nouveau GenreLivre pour faire le lien entre Annonce et Genre
            var nouveauGenreLivre = new GenreLivre
            {
                CodeGenreId = codeGenreId,
                CodeAnnonceNumRef = nouvelleAnnonce.NumeroReference
            };
            DBContext.GenreLivre.Add(nouveauGenreLivre);

            await DBContext.SaveChangesAsync();
            return StatusCode(201);
        }

        /// <summary>
        /// Permet de modifier toutes les informations d'une annonce
        /// </summary>
        /// <param name="numeroReference">Permet de sélectionner l'annonce à modifier en fonction de son numéro de référence</param>
        /// <param name="isbnLivre">La modification à faire pour l'ISBN</param>
        /// <param name="titreLivre">La modification à faire pour le titre</param>
        /// <param name="prix">La modification à faire pour le prix</param>
        /// <param name="image_url">La modification à faire pour l'image d'illustration</param>
        /// <param name="dateParutionLivre">La modification à faire pour la date de parution</param>
        /// <param name="statut">La modification à faire pour le statut</param>
        /// <param name="codeEtatLivreId">La modification à faire pour l'état du livre</param>
        /// <param name="codeGenreId">La modification à faire pour le genre du livre</param>
        /// <param name="idAuteur">La modification à faire pour l'auteur</param>
        /// <returns>Un message de confirmation ou d'erreur</returns>
        [HttpPost("ModifierAnnonce")]
        public async Task<IActionResult> UpdateAnnonce(int numeroReference, string isbnLivre, string titreLivre, float prix, 
            string image_url, DateTime? dateParutionLivre, string statut, int codeEtatLivreId, int codeGenreId, int idAuteur)
        {
            // Retrouver l'Annonce existante dans la BDD par son NumeroReference
            var existingAnnonce = await DBContext.Annonce
                .Include(a => a.GenreLivres)
                .Include(a => a.CodeEtatLivre)
                .FirstOrDefaultAsync(a => a.NumeroReference == numeroReference);

            if (existingAnnonce == null)
            {
                return NotFound(); // Annonce non trouvée
            }

            // Mettre à jour l'annonce
            existingAnnonce.Isbnlivre = isbnLivre;
            existingAnnonce.TitreLivre = titreLivre;
            existingAnnonce.Prix = prix;
            existingAnnonce.Image_Url = image_url;
            existingAnnonce.DateParutionLivre = dateParutionLivre;
            existingAnnonce.Statut = statut;
            existingAnnonce.CodeEtatLivreId = codeEtatLivreId;

            // Il faut trouver le Livre correspondant dans la BDD avec NumeroReference pour changer l'association avec l'Auteur
            // D'abord en supprimant l'entité Livre existante
            var existingLivre = await DBContext.Livre.FirstOrDefaultAsync(l => l.CodeAnnonceNumRef == numeroReference);
            if (existingLivre != null)
            {
                DBContext.Livre.Remove(existingLivre);
            }
            // Puis en la reconstruisant
            var nouveauLivre = new Livre
            {
                CodeAuteurId = idAuteur,
                CodeAnnonceNumRef = numeroReference
            };
            DBContext.Livre.Add(nouveauLivre);

            // Il faut trouver le GenreLivre dans la BDD avec NumeroReference pour changer l'association avec le Genre
            // Idem, on doit d'abord détruire l'entité GenreLivre existante
            var existingGenre = await DBContext.GenreLivre.FirstOrDefaultAsync(l => l.CodeAnnonceNumRef == numeroReference);
            if (existingGenre != null)
            {
                DBContext.GenreLivre.Remove(existingGenre);
            }
            // Puis la reconstruire
            var nouveauGenreLivre = new GenreLivre
            {
                CodeGenreId = codeGenreId,
                CodeAnnonceNumRef = numeroReference
            };
            DBContext.GenreLivre.Add(nouveauGenreLivre);

            // Savegarde BDD
            await DBContext.SaveChangesAsync();

            return Ok();
        }

        /// <summary>
        /// Permet de modifier uniquement le statut (visible ou archivé) d'une annonce précise
        /// </summary>
        /// <param name="numeroReference">Le numéro de l'annonce que l'on veut modifier</param>
        /// <param name="statut">Le statut à mettre à jour</param>
        /// <returns>Un message de confirmation ou d'erreur</returns>
        [HttpPost("ModifierStatutAnnonce")]
        public async Task<IActionResult> UpdateStatutAnnonce(int numeroReference, string statut)
        {
            // Retrouver l'Annonce existante dans la BDD par son NumeroReference
            var existingAnnonce = await DBContext.Annonce
                .FirstOrDefaultAsync(a => a.NumeroReference == numeroReference);

            if (existingAnnonce == null)
            {
                return NotFound(); // Annonce non trouvée
            }

            // Mettre à jour le statut de l'annonce
            existingAnnonce.Statut = statut;
            await DBContext.SaveChangesAsync();

            return Ok();
        }
    }
}
