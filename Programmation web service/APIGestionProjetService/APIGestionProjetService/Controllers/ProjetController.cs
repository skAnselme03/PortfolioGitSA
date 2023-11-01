using APIGestionProjetService.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Net;

namespace APIGestionProjetService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class ProjetController : ControllerBase
    {
        private readonly Ska0323420110gggestionprojetContext DBContext;
        public ProjetController(Ska0323420110gggestionprojetContext DBContext)
        {
            this.DBContext = DBContext;
        }

        [HttpGet("GetProjets")]
        public async Task<ActionResult<List<Projet>>> Get()
        {
            var List = await DBContext.Projets.Select(
            s => new Projet
            {
                IdProjet = s.IdProjet,
                CodeProjet = s.CodeProjet,
                Nom = s.Nom,
                Description = s.Description,
                DateDebut = s.DateDebut,
                DateFin = s.DateFin,
                DateTermine = s.DateTermine,
                Client = s.Client,
                Statut = s.Statut,
                CodeUtilisateur = s.CodeUtilisateurNavigation.IdUtilisateur
            }
            ).ToListAsync();
            if (List.Count < 0)
            {
                return NotFound();
            }
            else
            {
                return List;
            }
        }

        [HttpGet("GetProjetParId")]
        public async Task<ActionResult<Projet>> GetsById(int IdProjet)
        {
#pragma warning disable CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            Projet Project = await DBContext.Projets
                .Include(p => p.CodeUtilisateurNavigation)// Charge la relation
                .Where(p => p.IdProjet == IdProjet)
                .Select(s => new Projet
                {
                    CodeProjet = s.CodeProjet,
                    Nom = s.Nom,
                    Description = s.Description,
                    DateDebut = s.DateDebut,
                    DateFin = s.DateFin,
                    DateTermine = s.DateTermine,
                    Client = s.Client,
                    Statut = s.Statut,
                    CodeUtilisateur = s.CodeUtilisateurNavigation.IdUtilisateur
                }).FirstOrDefaultAsync();
#pragma warning restore CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            if (Project == null)
            {
                return NotFound();
            }
            else
            {
                return Project;
            }

        }


        [HttpPost("InsererProjet")]
        public async Task<HttpStatusCode> InsertProject(Projet projet)
        {
            var entity = new Projet()
            {
                Nom = projet.Nom,
                Description = projet.Description,
                DateDebut = projet.DateDebut,
                DateFin = projet.DateFin,
                DateTermine = projet.DateTermine,
                Client = projet.Client,
                Statut = projet.Statut,
                CodeUtilisateur = projet.CodeUtilisateurNavigation.IdUtilisateur
            };
            DBContext.Projets.Add(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }


        [HttpPut("UpdateProjet")]
        public async Task<HttpStatusCode> UpdateProject(Projet Project)
        {
            var entity = await DBContext.Projets.FirstOrDefaultAsync(s => s.IdProjet == Project.IdProjet);
            entity.CodeProjet = Project.CodeProjet;
            entity.Nom = Project.Nom;
            entity.Description = Project.Description;
            entity.DateDebut = Project.DateDebut;
            entity.DateFin = Project.DateFin;
            entity.DateTermine = Project.DateTermine;
            entity.Client = Project.Client;
            entity.Statut = Project.Statut;
            entity.CodeUtilisateur = Project.CodeUtilisateurNavigation.IdUtilisateur;

            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

        [HttpDelete("SupprimerProjet/{Id}")]
        public async Task<HttpStatusCode> DeleteProject(int IdProjet)
        {
            var entity = new Projet()
            {
                IdProjet = IdProjet
            };
            DBContext.Projets.Attach(entity);
            DBContext.Projets.Remove(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

    }
}
