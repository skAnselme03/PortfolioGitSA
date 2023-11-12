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
    public class AuteurController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public AuteurController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// </summary>
        /// <returns>La liste de tous les auteurs existants</returns>
        [HttpGet("RecupererTousAuteurs")]
        public async Task<ActionResult<List<Auteur>>> Get()
        {
            var ListAuteurs = await DBContext.Auteur.Select(
                s => new Auteur
                {
                    IdAuteur = s.IdAuteur,
                    Nom = s.Nom,
                    Prenom = s.Prenom,
                    DateNaissance = s.DateNaissance,
                    Statut = s.Statut
                }
            ).ToListAsync();

            if (ListAuteurs.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return ListAuteurs;
            }
        }

        /// <summary>
        /// Méthode GET by Statut
        /// </summary>
        /// <returns>La liste de tous les auteurs visibles</returns>
        [HttpGet("RecupererTousAuteursVisibles")]
        public async Task<ActionResult<List<Auteur>>> GetAuteursVisibles()
        {
            var auteursVisibles = await DBContext.Auteur
                .Where(auteur => auteur.Statut == "Visible")
                .OrderBy(auteur => auteur.Nom)  // Le résultat sera ordonné par "Nom" de A à Z
                .Select(auteur => new Auteur
                {
                    IdAuteur = auteur.IdAuteur,
                    Nom = auteur.Nom,
                    Prenom = auteur.Prenom,
                    DateNaissance = auteur.DateNaissance,
                    Statut = auteur.Statut
                })
                .ToListAsync();

            if (auteursVisibles.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return auteursVisibles;
            }
        }

        /// <summary>
        /// Méthode GET by ID
        /// </summary>
        /// <param name="Id">L'id de l'auteur recherché</param>
        /// <returns>L'auteur correspondant à l'Id</returns>
        [HttpGet("RecupererAuteurParId")]
        public async Task<ActionResult<Auteur>> GetAuteurById(int Id)
        {
            Auteur auteur = await DBContext.Auteur.Select(s => new Auteur
            {
                IdAuteur = s.IdAuteur,
                Nom = s.Nom,
                Prenom = s.Prenom,
                DateNaissance = s.DateNaissance,
                Statut = s.Statut
            }).FirstOrDefaultAsync(s => s.IdAuteur == Id);
            if (auteur == null)
            {
                return NotFound();
            }
            else
            {
                return auteur;
            }
        }

        /// <summary>
        /// Méthode CREATE (INSERT)
        /// </summary>
        /// <param name="auteur">Les informations du nouvel auteur</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPost("CreerAuteur")]
        public async Task<HttpStatusCode> InsertAuteur(Auteur auteur)
        {
            var nouvelAuteur = new Auteur()
            {
                IdAuteur = auteur.IdAuteur,
                Nom = auteur.Nom,
                Prenom = auteur.Prenom,
                DateNaissance = auteur.DateNaissance,
                Statut = auteur.Statut
            };
            DBContext.Auteur.Add(nouvelAuteur);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }

        /// <summary>
        /// Méthode UPDATE by ID
        /// </summary>
        /// <param name="auteur">Les informations de l'auteur à jour</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPut("MettreAuteurAJour")]
        public async Task<HttpStatusCode> UpdateAuteur(Auteur auteur)
        {
            var entity = await DBContext.Auteur.FirstOrDefaultAsync(s => s.IdAuteur == auteur.IdAuteur);
            entity.IdAuteur = auteur.IdAuteur;
            entity.Nom = auteur.Nom;
            entity.Prenom = auteur.Prenom;
            entity.DateNaissance = auteur.DateNaissance;
            entity.Statut = auteur.Statut;
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }
    }
}
