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
    public class GenreController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public GenreController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// </summary>
        /// <returns>La liste des genres existants</returns>
        [HttpGet("RecupererTousGenres")]
        public async Task<ActionResult<List<Genre>>> Get()
        {
            var ListGenre = await DBContext.Genre.Select(
                s => new Genre
                {
                    IdGenre = s.IdGenre,
                    Description = s.Description,
                    Statut = s.Statut
                }
            ).ToListAsync();

            if (ListGenre.Count < 0)
            {
                return NotFound();
            }
            else
            {
                return ListGenre;
            }
        }

        /// <summary>
        /// Méthode GET by Statut
        /// </summary>
        /// <returns>La liste de tous les genres visibles</returns>
        [HttpGet("RecupererTousGenresVisibles")]
        public async Task<ActionResult<List<Genre>>> GetGenresVisibles()
        {
            var genresVisibles = await DBContext.Genre
                .Where(genre => genre.Statut == "Visible")
                .Select(genre => new Genre
                {
                    IdGenre = genre.IdGenre,
                    Description = genre.Description,
                    Statut = genre.Statut
                })
                .ToListAsync();

            if (genresVisibles == null || genresVisibles.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return genresVisibles;
            }
        }

        /// <summary>
        /// Méthode GET by ID
        /// </summary>
        /// <param name="Id">L'id du genre recherché</param>
        /// <returns>Le genre correspondant à l'Id</returns>
        [HttpGet("RecupererGenreParId")]
        public async Task<ActionResult<Genre>> GetGenreById(int Id)
        {
            Genre genre = await DBContext.Genre.Select(s => new Genre
            {
                IdGenre = s.IdGenre,
                Description = s.Description,
                Statut = s.Statut
            }).FirstOrDefaultAsync(s => s.IdGenre == Id);
            if (genre == null)
            {
                return NotFound();
            }
            else
            {
                return genre;
            }
        }

        /// <summary>
        /// Méthode CREATE (INSERT)
        /// </summary>
        /// <param name="genre">Les informations du nouveau genre</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPost("CreerGenre")]
        public async Task<HttpStatusCode> InsertGenre(Genre genre)
        {
            var nouveauGenre = new Genre()
            {
                IdGenre = genre.IdGenre,
                Description = genre.Description,
                Statut = genre.Statut
            };
            DBContext.Genre.Add(nouveauGenre);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.Created;
        }

        /// <summary>
        /// Méthode UPDATE by ID
        /// </summary>
        /// <param name="genre">Les informations du genre à jour</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPut("MettreGenreAJour")]
        public async Task<HttpStatusCode> UpdateGenre(Genre genre)
        {
            var entity = await DBContext.Genre.FirstOrDefaultAsync(s => s.IdGenre == genre.IdGenre);
            entity.IdGenre = genre.IdGenre;
            entity.Description = genre.Description;
            entity.Statut = genre.Statut;
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }

        /// <summary>
        /// Méthode DELETE
        /// </summary>
        /// <param name="Id">Id du genre à supprimer</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpDelete("SupprimerGenre/{Id}")]
        public async Task<HttpStatusCode> DeleteGenre(int Id)
        {
            var entity = new Genre()
            {
                IdGenre = Id
            };
            DBContext.Genre.Attach(entity);
            DBContext.Genre.Remove(entity);
            await DBContext.SaveChangesAsync();
            return HttpStatusCode.OK;
        }
    }
}
