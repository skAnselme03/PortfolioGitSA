using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ServeurBibliobazar.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace ServeurBibliobazar.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NoteController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public NoteController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// </summary>
        /// <returns>La liste de toutes les notes existantes avec leurs membres (vendeur et noteur), titre, évaluation et description</returns>
        [HttpGet("RecupererToutNotes")]
        public async Task<ActionResult<IEnumerable<object>>> RecupererToutNotes()
        {
            var ListNote = await DBContext.Note
                .Include(n => n.CodeMembreUserNavigation)
                .Include(n => n.CodeAnnonceNumRefNavigation)
                .ThenInclude(a => a.CodeMembreUserNavigation)
                .Where(n => n.CodeMembreUserNavigation != null && n.CodeAnnonceNumRefNavigation != null)
                .Select(n => new
                {
                    CodeMembreNoteur = n.CodeMembreUser,
                    NoteurUsername = n.CodeMembreUserNavigation.Username,
                    CodeAnnonce = n.CodeAnnonceNumRef,
                    AnnonceTitre = n.CodeAnnonceNumRefNavigation.TitreLivre,
                    AnnonceVendeur = n.CodeAnnonceNumRefNavigation.CodeMembreUser,
                    VendeurUsername = n.CodeAnnonceNumRefNavigation.CodeMembreUserNavigation.Username,
                    Evaluation = n.Evaluation,
                    Description = n.Description,
                    DateNote = n.DateNote
                })
                .ToListAsync();

            if (ListNote.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return ListNote;
            }
        }

        /// <summary>
        /// Permet de récupérer toutes les notes faites par un membre spécifique
        /// Cela nous permettra de vérifier s'il n'a pas déjà noté une annonce
        /// </summary>
        /// <param name="idMembre">Le code utilisateur du membre utilisateur</param>
        /// <returns>La liste de toutes les notes existantes pour ce membre 
        /// avec leurs membres (vendeur et noteur), titre, évaluation et description</returns>
        [HttpGet("RecupererNotesParMembreNoteur")]
        public async Task<ActionResult<IEnumerable<object>>> RecupererNotesParMembreNoteur(int idMembre)
        {
            var ListNote = await DBContext.Note
                .Include(n => n.CodeMembreUserNavigation)
                .Include(n => n.CodeAnnonceNumRefNavigation)
                .ThenInclude(a => a.CodeMembreUserNavigation)
                .Where(n => n.CodeMembreUserNavigation != null && n.CodeAnnonceNumRefNavigation != null && n.CodeMembreUser == idMembre)
                .Select(n => new
                {
                    CodeMembreNoteur = n.CodeMembreUser,
                    NoteurUsername = n.CodeMembreUserNavigation.Username,
                    CodeAnnonce = n.CodeAnnonceNumRef,
                    AnnonceTitre = n.CodeAnnonceNumRefNavigation.TitreLivre,
                    AnnonceVendeur = n.CodeAnnonceNumRefNavigation.CodeMembreUser,
                    VendeurUsername = n.CodeAnnonceNumRefNavigation.CodeMembreUserNavigation.Username,
                    Evaluation = n.Evaluation,
                    Description = n.Description,
                    DateNote = n.DateNote
                })
                .ToListAsync();

            if (ListNote.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return ListNote;
            }
        }

        /// <summary>
        /// Créé une nouvelle note reliant un membre avec une annonce + une évaluation et une description
        /// </summary>
        /// <param name="CodeMembre">Le code du membre à qui appartient ce favori</param>
        /// <param name="CodeAnnonce">Le code de l'annonce reliée à ce favori</param>
        /// <param name="Evaluation">La note en tant que telle sur 5</param>
        /// <param name="Description">La description de la note</param>
        /// <returns>Une réponse HTTP de confirmation ou d'erreur</returns>
        [HttpPost("CreerNote")]
        public async Task<IActionResult> CreerNote(int CodeMembre, int CodeAnnonce, int Evaluation, string Description)
        {
            // Vérifie si CodeMembreUser et CodeAnnonceNumRef sont valides
            var membre = await DBContext.Membre.FindAsync(CodeMembre);
            var annonce = await DBContext.Annonce.FindAsync(CodeAnnonce);

            if (membre == null || annonce == null)
            {
                return BadRequest("Membre ou annonce invalide.");
            }

            // Vérifie si une note avec la même combinaison existe déjà
            var existingNote = await DBContext.Note
                .FirstOrDefaultAsync(f =>
                    f.CodeMembreUser == CodeMembre && f.CodeAnnonceNumRef == CodeAnnonce);

            if (existingNote != null)
            {
                return Conflict("La note existe déjà");
            }

            // Crée la nouvelle Note
            var note = new Note
            {
                CodeMembreUser = CodeMembre,
                CodeAnnonceNumRef = CodeAnnonce,
                Evaluation = Evaluation,
                Description = Description
            };

            DBContext.Entry(note).State = EntityState.Added;
            await DBContext.SaveChangesAsync();
            return StatusCode(201, "Note créée");
        }

        /// <summary>
        /// Supprime une note selon le membre et l'annonce auquel elle est reliée (clés secondaires donc pas grave de supprimer ici)
        /// </summary>
        /// <param name="CodeMembre">Le code utilisateur du membre</param>
        /// <param name="CodeAnnonce">Le numéro de référence de l'annonce</param>
        /// <returns>Réponse concernant la requête</returns>
        [HttpDelete("SupprimerNote")]
        public async Task<IActionResult> SupprimerNote(int CodeMembre, int CodeAnnonce)
        {
            // Vérifie que la note existe
            var note = await DBContext.Note
                .FirstOrDefaultAsync(f => f.CodeMembreUser == CodeMembre && f.CodeAnnonceNumRef == CodeAnnonce);

            if (note == null)
            {
                return NotFound("Cette note n'existe pas.");
            }

            // Supprime le favori s'il existe
            DBContext.Note.Remove(note);
            await DBContext.SaveChangesAsync();
            return Ok("Note supprimée avec succès.");
        }
    }
}
