using APIGestionProjetService.Entities;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Net;

namespace APIGestionProjetService.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class UtilisateurController : ControllerBase
    {
        private readonly Ska0323420110gggestionprojetContext DBContext;
        public UtilisateurController(Ska0323420110gggestionprojetContext DBContext)
        {
            this.DBContext = DBContext;
        }

        [HttpGet("GetUtilisateurs")]
        public async Task<ActionResult<List<Utilisateur>>> Get()
        {
            var List = await DBContext.Utilisateurs.Select(
            s => new Utilisateur
            {
                IdUtilisateur = s.IdUtilisateur,
                Nom = s.Nom,
                Prenom = s.Prenom,
                Username = s.Username,
                Password = s.Password,
                Courriel = s.Courriel,
                Titre = s.Titre,
                Departement = s.Departement
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

        [HttpGet("GeUtilisateurParId")]
        public async Task<ActionResult<Utilisateur>> GetUserById(int IdUtilisateur)
        {
#pragma warning disable CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            Utilisateur User = await DBContext.Utilisateurs.Select(s => new Utilisateur
            {
                IdUtilisateur = s.IdUtilisateur,
                Nom = s.Nom,
                Prenom = s.Prenom,
                Username = s.Username,
                Password = s.Password,
                Courriel = s.Courriel,
                Titre = s.Titre,
                Departement = s.Departement
            }).FirstOrDefaultAsync(s => s.IdUtilisateur == IdUtilisateur);
#pragma warning restore CS8600 // Conversion de littéral ayant une valeur null ou d'une éventuelle valeur null en type non-nullable.
            if (User == null)
            {
                return NotFound();
            }
            else
            {
                return User;
            }

        }

        [HttpPost("InsererUtilisateur")]
        public async Task<HttpStatusCode> InsertUser(Utilisateur User)
        {
            var entity = new Utilisateur()
            {
                Nom = User.Nom,
                Prenom = User.Prenom,
                Username = User.Username,
                Password = User.Password,
                Courriel = User.Courriel,
                Titre = User.Titre,
                Departement = User.Departement
            };
            DBContext.Utilisateurs.Add(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }

        [HttpPut("UpdateUtilisateur")]
        public async Task<HttpStatusCode> UpdateUser(Utilisateur User)
        {
            var entity = await DBContext.Utilisateurs.FirstOrDefaultAsync(s => s.IdUtilisateur == User.IdUtilisateur);
#pragma warning disable CS8602 // Déréférencement d'une éventuelle référence null.
            entity.Nom = User.Nom;
#pragma warning restore CS8602 // Déréférencement d'une éventuelle référence null.
            entity.Prenom = User.Prenom;
            entity.Username = User.Username;
            entity.Password = User.Password;
            entity.Courriel = User.Courriel;
            entity.Titre = User.Titre;
            entity.Departement = User.Departement;

            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

        [HttpDelete("SupprimerUtilisateur/{Id}")]
        public async Task<HttpStatusCode> DeleteUser(int IdUtilisateur)
        {
            var entity = new Utilisateur()
            {
                IdUtilisateur = IdUtilisateur
            };
            DBContext.Utilisateurs.Attach(entity);
            DBContext.Utilisateurs.Remove(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }
    }
}
