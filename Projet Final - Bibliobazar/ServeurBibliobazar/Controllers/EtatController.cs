using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ServeurBibliobazar.Entities;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Net;
using System.Threading.Tasks;

namespace ServeurBibliobazar.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class EtatController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public EtatController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// </summary>
        /// <returns>La liste des états existants</returns>
        [HttpGet("RecupererTousEtats")]
        public async Task<ActionResult<List<EtatLivre>>> Get()
        {
            var ListEtats = await DBContext.EtatLivre.Select(
                s => new EtatLivre
                {
                    IdEtat = s.IdEtat,
                    Description = s.Description,
                    Statut = s.Statut
                }
            ).ToListAsync();

            if (ListEtats.Count < 0)
            {
                return NotFound();
            }
            else
            {
                return ListEtats;
            }
        }

        /// <summary>
        /// Méthode GET by Statut
        /// </summary>
        /// <returns>La liste de tous les états existants qui sont visibles</returns>
        [HttpGet("RecupererTousEtatsVisibles")]
        public async Task<ActionResult<List<EtatLivre>>> GetEtatsVisibles()
        {
            var etatsVisibles = await DBContext.EtatLivre
                .Where(etat => etat.Statut == "Visible")
                .Select(etat => new EtatLivre
                {
                    IdEtat = etat.IdEtat,
                    Description = etat.Description,
                    Statut = etat.Statut
                })
                .ToListAsync();

            if (etatsVisibles != null && etatsVisibles.Count > 0)
            {
                return etatsVisibles;
            }
            else
            {
                return NotFound();
            }
        }


        /// <summary>
        /// Méthode GET by ID
        /// </summary>
        /// <param name="Id">L'id de l'état recherché</param>
        /// <returns>L'état correspondant à l'Id</returns>
        [HttpGet("RecupererEtatParId")]
        public async Task<ActionResult<EtatLivre>> GetEtatById(int Id)
        {
            EtatLivre etat = await DBContext.EtatLivre.Select(s => new EtatLivre
            {
                IdEtat = s.IdEtat,
                Description = s.Description,
                Statut = s.Statut
            }).FirstOrDefaultAsync(s => s.IdEtat == Id);
            if (etat == null)
            {
                return NotFound();
            }
            else
            {
                return etat;
            }
        }

        /// <summary>
        /// Méthode CREATE (INSERT)
        /// </summary>
        /// <param name="etat">Les informations du nouvel état</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPost("CreerEtat")]
        public async Task<HttpStatusCode> InsertEtat(EtatLivre etat)
        {
            var nouvelEtat = new EtatLivre()
            {
                IdEtat = etat.IdEtat,
                Description = etat.Description,
                Statut = etat.Statut
            };
            DBContext.EtatLivre.Add(nouvelEtat);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }

        /// <summary>
        /// Méthode UPDATE by ID
        /// </summary>
        /// <param name="etat">Les informations de l'état à jour</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPut("MettreEtatAJour")]
        public async Task<HttpStatusCode> UpdateEtat(EtatLivre etat)
        {
            var entity = await DBContext.EtatLivre.FirstOrDefaultAsync(s => s.IdEtat == etat.IdEtat);
            entity.IdEtat = etat.IdEtat;
            entity.Description = etat.Description;
            entity.Statut = etat.Statut;
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

        /// <summary>
        /// Méthode DELETE
        /// </summary>
        /// <param name="Id">Id de l'état à supprimer</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpDelete("SupprimerEtat/{Id}")]
        public async Task<HttpStatusCode> DeleteEtat(int Id)
        {
            var entity = new EtatLivre()
            {
                IdEtat = Id
            };
            DBContext.EtatLivre.Attach(entity);
            DBContext.EtatLivre.Remove(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }
    }
}
