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
    public class AdministrateurController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public AdministrateurController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// Ici, on utilise une méthode un peu différente pour obtenir les informations
        /// de Administrateur (classe enfant) ET de Utilisateur (classe mère)
        /// </summary>
        /// <returns>La liste de tous les administrateurs existants</returns>
        [HttpGet("RecupererTousAdmins")]
        public async Task<ActionResult<IEnumerable<object>>> GetAll()
        {
            var adminList = await DBContext.Administrateur
                .Join(
                    DBContext.Utilisateur,
                    a => a.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (a, u) => new
                    {
                        a.CodeUtilisateur,
                        a.CodeEmploye,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .ToListAsync();

            if (adminList.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return adminList;
            }
        }

        /// <summary>
        /// Méthode GET by CodeUtilisateur
        /// </summary>
        /// <param name="CodeUtilisateur">Le code utilisateur de l'administrateur recherché</param>
        /// <returns>L'administrateur correspondant au code utilisateur</returns>
        [HttpGet("RecupererAdminParCodeUtilisateur")]
        public async Task<ActionResult<object>> GetAdminByCodeUtilisateur(int CodeUtilisateur)
        {
            var adminInfo = await DBContext.Administrateur
                .Where(a => a.CodeUtilisateur == CodeUtilisateur)
                .Join(
                    DBContext.Utilisateur,
                    a => a.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (a, u) => new
                    {
                        CodeUtilisateur = a.CodeUtilisateur,
                        CodeEmploye = a.CodeEmploye,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .FirstOrDefaultAsync();

            if (adminInfo == null)
            {
                return NotFound();
            }
            else
            {
                return adminInfo;
            }
        }

        /// <summary>
        /// Méthode GET by CodeEmploye
        /// </summary>
        /// <param name="CodeEmploye">Le code employé de l'administrateur recherché</param>
        /// <returns>L'administrateur correspondant au code employé</returns>
        [HttpGet("RecupererAdminParCodeEmploye")]
        public async Task<ActionResult<object>> GetAdminByCodeEmploye(int CodeEmploye)
        {
            var adminInfo = await DBContext.Administrateur
                .Where(a => a.CodeEmploye == CodeEmploye)
                .Join(
                    DBContext.Utilisateur,
                    a => a.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (a, u) => new
                    {
                        CodeUtilisateur = a.CodeUtilisateur,
                        CodeEmploye = a.CodeEmploye,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .FirstOrDefaultAsync();

            if (adminInfo == null)
            {
                return NotFound();
            }
            else
            {
                return adminInfo;
            }
        }


        // On ne devrait pas avoir besoin de méthodes supplémentaires pour la classe Administrateur
        // (peut-être un Update ou un Create à la limite, mais à voir)
    }
}
