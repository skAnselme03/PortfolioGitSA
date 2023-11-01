using APISuperHero.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using System.Collections.Generic;
using System.Linq;

namespace APISuperHero.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SuperHeroController : ControllerBase
    {
        private readonly ContextSuperHero _context;

        public SuperHeroController(ContextSuperHero context)
        {
            _context = context;

            if (_context.LesSuperHeros.Count() == 0)
            {
                // Ajoutez des super-héros fictifs si la base de données est vide
                _context.LesSuperHeros.Add(new SuperHero
                {
                    Id = 1,
                    Nom = "Black",
                    Prenom = "Natasha",
                    NomFamille = "Romanoff",
                    Place = "Moscow"
                });

                _context.LesSuperHeros.Add(new SuperHero
                {
                    Id = 2,
                    Nom = "Spider",
                    Prenom = "Peter",
                    NomFamille = "Parker",
                    Place = "New York"
                });

                _context.SaveChanges();
            }
        }

        [HttpGet]
        public IEnumerable<SuperHero> GetAll()
        {
            return _context.LesSuperHeros.ToList();
        }

        [HttpGet("{id}", Name = "GetSuperHero")]
        public IActionResult GetById(int id)
        {
            var item = _context.LesSuperHeros.FirstOrDefault(t => t.Id == id);
            if (item == null)
            {
                return NotFound();
            }
            return new ObjectResult(item);
        }

        [HttpPost]
        public IActionResult Creer([FromBody] SuperHero item)
        {
            if (item == null)
            {
                return BadRequest();
            }

            _context.LesSuperHeros.Add(item);
            _context.SaveChanges();

            return CreatedAtRoute("GetSuperHero", new { id = item.Id }, item);
        }

        [HttpPut("{id}")]
        public IActionResult Update(int id, [FromBody] SuperHero item)
        {
            if (item == null || item.Id != id)
            {
                return BadRequest();
            }

            var superHero = _context.LesSuperHeros.FirstOrDefault(t => t.Id == id);
            if (superHero == null)
            {
                return NotFound();
            }

            superHero.Nom = item.Nom;
            superHero.Prenom = item.Prenom;
            superHero.NomFamille = item.NomFamille;
            superHero.Place = item.Place;

            _context.LesSuperHeros.Update(superHero);
            _context.SaveChanges();
            return new NoContentResult();
        }

        [HttpDelete("{id}")]
        public IActionResult Delete(int id)
        {
            var superHero = _context.LesSuperHeros.FirstOrDefault(t => t.Id == id);
            if (superHero == null)
            {
                return NotFound();
            }

            _context.LesSuperHeros.Remove(superHero);
            _context.SaveChanges();
            return new NoContentResult();
        }
    }
}
