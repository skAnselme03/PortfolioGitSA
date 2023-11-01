using APIGestionProjetService.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Net;

namespace APIGestionProjetService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class TacheController : ControllerBase
    {
        private readonly Ska0323420110gggestionprojetContext DBContext;
        public TacheController(Ska0323420110gggestionprojetContext DBContext)
        {
            this.DBContext = DBContext;
        }

        [HttpGet("GetTaches")]
        public async Task<ActionResult<List<Tache>>> Get()
        {
            var List = await DBContext.Taches.Select(
            s => new Tache
            {
                IdTache = s.IdTache,
                CodeTache = s.CodeTache,
                Nom = s.Nom,
                Description = s.Description,
                DateDebut = s.DateDebut,
                DateFin = s.DateFin,
                DateTermine = s.DateTermine,
                Statut = s.Statut,
                IdProjet = s.IdProjet
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

        [HttpGet("GetTacheParId")]
        public async Task<ActionResult<Tache>> GetsById(int IdTache)
        {
#pragma warning disable CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            Tache tache = await DBContext.Taches
                .Include(t => t.IdProjetNavigation)// Charge la relation avec projet
                .Where(t => t.IdTache == IdTache)
                .Select(t => new Tache
            {
                CodeTache = t.CodeTache,
                Nom = t.Nom,
                Description = t.Description,
                DateDebut = t.DateDebut,
                DateFin = t.DateFin,
                DateTermine = t.DateTermine,
                Statut = t.Statut,
                IdProjet = t.IdProjet
            }).FirstOrDefaultAsync();
#pragma warning restore CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            if (tache == null)
            {
                return NotFound();
            }
            else
            {
                return tache;
            }
        }


        [HttpPost("InsererTache")]
        public async Task<HttpStatusCode> InsertTache(Tache tache)
        {
            var entity = new Tache()
            {
                CodeTache = tache.CodeTache,
                Nom = tache.Nom,
                Description = tache.Description,
                DateDebut = tache.DateDebut,
                DateFin = tache.DateFin,
                DateTermine = tache.DateTermine,
                Statut = tache.Statut,
                IdProjet = tache.IdProjetNavigation.IdProjet
            };
            DBContext.Taches.Add(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }



        [HttpPut("UpdateTache")]
        public async Task<HttpStatusCode> UpdateTache(Tache tache)
        {
            var entity = await DBContext.Taches.FirstOrDefaultAsync(s => s.IdTache == tache.IdTache);
            entity.CodeTache = tache.CodeTache;
            entity.Nom = tache.Nom;
            entity.Description = tache.Description;
            entity.DateDebut = tache.DateDebut;
            entity.DateFin = tache.DateFin;
            entity.DateTermine = tache.DateTermine;
            entity.Statut = tache.Statut;
            entity.IdProjet = tache.IdProjetNavigation.IdProjet;

            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

        [HttpDelete("SupprimerTache/{Id}")]
        public async Task<HttpStatusCode> DeleteTache(int IdTache)
        {
            var entity = new Tache()
            {
                IdTache = IdTache
            };
            DBContext.Taches.Attach(entity);
            DBContext.Taches.Remove(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }
    }
}