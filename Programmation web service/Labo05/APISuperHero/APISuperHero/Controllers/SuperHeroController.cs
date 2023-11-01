using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using APISuperHero.Entities;
using System.Net;

namespace APISuperHero.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SuperHeroController : ControllerBase
    {
        private readonly SuperHeroContext _dbContext;

        public SuperHeroController(SuperHeroContext dbContext)
        {
            _dbContext = dbContext;
        }

        /// <summary>
        /// Récupère une liste de super-héros à partir de la base de données.
        /// </summary>
        /// <returns>Une liste de super-héros sous forme de réponse HTTP.</returns>
        [HttpGet("GetSuperHeros")]
        public async Task<ActionResult<List<SuperHero>>> Get()
        {
            // Récupère la liste des super-héros depuis la base de données
            // en utilisant Entity Framework Core.
            var superHeroes = await _dbContext.Superheros.ToListAsync();

            // Vérifie si la liste est vide.
            if (superHeroes.Count == 0)
            {
                // Si la liste est vide, retourne une réponse HTTP 404 (Not Found).
                return NotFound();
            }
            else
            {
                // Si la liste n'est pas vide, retourne la liste des super-héros en tant que réponse HTTP 200 (OK).
                return Ok(superHeroes);
            }
        }

        /// <summary>
        /// Récupère un super-héros par son ID.
        /// </summary>
        /// <param name="Id">L'ID du super-héros à récupérer.</param>
        /// <returns>Le super-héros correspondant en tant que réponse HTTP.</returns>
        [HttpGet("GetSuperHeroParId/{Id}")]
        public async Task<ActionResult<SuperHero>> GetSuperHeroById(int Id)
        {
            var superhero = await _dbContext.Superheros.FirstOrDefaultAsync(s => s.Id == Id);

            if (superhero == null)
            {
                return NotFound();
            }
            else
            {
                return Ok(superhero);
            }
        }

        /// <summary>
        /// Insère un nouveau super-héros dans la base de données.
        /// </summary>
        /// <param name="superHero">Le super-héros à insérer.</param>
        /// <returns>Le code de statut HTTP résultant de l'opération.</returns>
        [HttpPost("InsererSuperHero")]
        public async Task<HttpStatusCode> InsertSuperHero(SuperHero superHero)
        {
            var entity = new SuperHero()
            {
                Nom = superHero.Nom,
                Prenom = superHero.Prenom,
                NomFamille = superHero.NomFamille,
                Place = superHero.Place
            };
            _dbContext.Superheros.Add(entity);
            await _dbContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }

        /// <summary>
        /// Met à jour un super-héros existant dans la base de données.
        /// </summary>
        /// <param name="superHero">Le super-héros avec les données mises à jour.</param>
        /// <returns>Le code de statut HTTP résultant de l'opération.</returns>
        [HttpPut("UpdateSuperHero")]
        public async Task<ActionResult> UpdateSuperHero(SuperHero superHero)
        {
            var entity = await _dbContext.Superheros.FirstOrDefaultAsync(s => s.Id == superHero.Id);
            if (entity == null)
            {
                return NotFound();
            }

            entity.Nom = superHero.Nom;
            entity.Prenom = superHero.Prenom;
            entity.NomFamille = superHero.NomFamille;
            entity.Place = superHero.Place;
            await _dbContext.SaveChangesAsync();
            return Ok(); // Utilisez Ok() pour retourner une réponse HTTP 200 (OK).
        }

        /// <summary>
        /// Supprime un super-héros de la base de données par son ID.
        /// </summary>
        /// <param name="Id">L'ID du super-héros à supprimer.</param>
        /// <returns>Le code de statut HTTP résultant de l'opération.</returns>
        [HttpDelete("SupprimerSuperHero/{Id}")]
        public async Task<ActionResult> DeleteSuperHero(int Id)
        {
            var entity = await _dbContext.Superheros.FirstOrDefaultAsync(s => s.Id == Id);
            if (entity == null)
            {
                return NotFound();
            }

            _dbContext.Superheros.Remove(entity);
            await _dbContext.SaveChangesAsync();
            return Ok(); // Utilisez Ok() pour retourner une réponse HTTP 200 (OK).
        }
    }
}
