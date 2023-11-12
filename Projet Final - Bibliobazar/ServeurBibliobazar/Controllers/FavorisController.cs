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
    public class FavorisController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public FavorisController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// </summary>
        /// <returns>La liste de tous les favoris existants avec leur membre, titre, prix et état</returns>
        [HttpGet("RecupererTousFavoris")]
        public async Task<ActionResult<IEnumerable<object>>> RecupererTousFavoris()
        {
            var ListFavoris = await DBContext.Favoris
                .Include(f => f.CodeMembreUserNavigation)
                .Include(f => f.CodeAnnonceNumRefNavigation)
                .ThenInclude(a => a.CodeEtatLivre)
                .Where(f => f.CodeMembreUserNavigation != null && f.CodeAnnonceNumRefNavigation != null)
                .Select(f => new
                {
                    CodeMembreUser = f.CodeMembreUser,
                    MembreUsername = f.CodeMembreUserNavigation.Username,
                    CodeAnnonceNumRef = f.CodeAnnonceNumRef,
                    AnnonceTitreLivre = f.CodeAnnonceNumRefNavigation.TitreLivre,
                    AnnonceImageUrl = f.CodeAnnonceNumRefNavigation.Image_Url,
                    AnnoncePrix = f.CodeAnnonceNumRefNavigation.Prix,
                    EtatLivreDescription = f.CodeAnnonceNumRefNavigation.CodeEtatLivre.Description
                })
                .ToListAsync();

            if (ListFavoris.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return ListFavoris;
            }
        }

        /// <summary>
        /// Permet de récupérer tous les favoris d'un membre spécifique
        /// </summary>
        /// <param name="idMembre">Le code utilisateur du membre utilisateur</param>
        /// <returns>La liste de tous les favoris pour ce membre, avec leur titre, prix et état</returns>
        [HttpGet("RecupererFavorisParMembre")]
        public async Task<ActionResult<IEnumerable<object>>> RecupererFavorisParMembre(int idMembre)
        {
            var ListFavoris = await DBContext.Favoris
                .Include(f => f.CodeMembreUserNavigation)
                .Include(f => f.CodeAnnonceNumRefNavigation)
                .ThenInclude(a => a.CodeEtatLivre)
                .Where(f => f.CodeMembreUserNavigation != null && f.CodeAnnonceNumRefNavigation != null && f.CodeMembreUser == idMembre)
                .Select(f => new
                {
                    CodeMembreUser = f.CodeMembreUser,
                    MembreUsername = f.CodeMembreUserNavigation.Username,
                    CodeAnnonceNumRef = f.CodeAnnonceNumRef,
                    AnnonceTitreLivre = f.CodeAnnonceNumRefNavigation.TitreLivre,
                    AnnonceImageUrl = f.CodeAnnonceNumRefNavigation.Image_Url,
                    AnnoncePrix = f.CodeAnnonceNumRefNavigation.Prix,
                    EtatLivreDescription = f.CodeAnnonceNumRefNavigation.CodeEtatLivre.Description,
                    AnnonceStatut = f.CodeAnnonceNumRefNavigation.Statut
                })
                .ToListAsync();

            if (ListFavoris.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return ListFavoris;
            }
        }

        /// <summary>
        /// Créer un nouveau favori reliant un membre avec une annonce
        /// </summary>
        /// <param name="CodeMembre">Le code du membre à qui appartient ce favori</param>
        /// <param name="CodeAnnonce">Le code de l'annonce reliée à ce favori</param>
        /// <returns>Une réponse HTTP de confirmation ou d'erreur</returns>
        [HttpPost("CreerFavoris")]
        public async Task<IActionResult> CreerFavoris(int CodeMembre, int CodeAnnonce)
        {
            // Vérifie si CodeMembreUser et CodeAnnonceNumRef sont valides
            var membre = await DBContext.Membre.FindAsync(CodeMembre);
            var annonce = await DBContext.Annonce.FindAsync(CodeAnnonce);

            if (membre == null || annonce == null)
            {
                return BadRequest("Membre ou annonce invalide.");
            }

            // Vérifie si un favori avec la même combinaison existe déjà
            var existingFavori = await DBContext.Favoris
                .FirstOrDefaultAsync(f =>
                    f.CodeMembreUser == CodeMembre && f.CodeAnnonceNumRef == CodeAnnonce);

            if (existingFavori != null)
            {
                return Conflict("Le favori existe déjà");
            }

            // Crée le nouveau Favori
            var favori = new Favoris
            {
                CodeMembreUser = CodeMembre,
                CodeAnnonceNumRef = CodeAnnonce
            };

            DBContext.Entry(favori).State = EntityState.Added;
            await DBContext.SaveChangesAsync();
            return StatusCode(201, "Favori créé");
        }


        /// <summary>
        /// Supprime un favori selon le membre et l'annonce auquel il est relié (pas grave de supprimer ici)
        /// </summary>
        /// <param name="CodeMembre">Le code utilisateur du membre</param>
        /// <param name="CodeAnnonce">Le numéro de référence de l'annonce</param>
        /// <returns>Réponse concernant la requête</returns>
        [HttpDelete("SupprimerFavoris")]
        public async Task<IActionResult> SupprimerFavoris(int CodeMembre, int CodeAnnonce)
        {
            // Vérifie que le favori existe
            var favori = await DBContext.Favoris
                .FirstOrDefaultAsync(f => f.CodeMembreUser == CodeMembre && f.CodeAnnonceNumRef == CodeAnnonce);

            if (favori == null)
            {
                return NotFound("Ce favori n'existe pas.");
            }

            // Supprime le favori s'il existe
            DBContext.Favoris.Remove(favori);
            await DBContext.SaveChangesAsync();
            return Ok("Favori supprimé avec succès.");
        }
    }
}
