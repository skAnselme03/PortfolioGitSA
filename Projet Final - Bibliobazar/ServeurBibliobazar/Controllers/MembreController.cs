using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.IdentityModel.Tokens;
using ServeurBibliobazar.Entities;
using ServeurBibliobazar.Models;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Net;
using System.Security.Claims;
using System.Security.Cryptography;
using System.Text;
using System.Threading.Tasks;

namespace ServeurBibliobazar.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class MembreController : ControllerBase
    {
        /// <summary>
        /// Initialise la DB
        /// </summary>
        private readonly ska0323_bibzarContext DBContext;

        public MembreController(ska0323_bibzarContext DBContext)
        {
            this.DBContext = DBContext;
        }

        /// <summary>
        /// Méthode GET ALL
        /// Ici, on utilise une méthode un peu différente pour obtenir les informations
        /// de Membre (classe enfant) ET de Utilisateur (classe mère)
        /// </summary>
        /// <returns>La liste de tous les membres existants</returns>
        [HttpGet("RecupererTousMembres")]
        public async Task<ActionResult<IEnumerable<object>>> GetAll()
        {
            var membreList = await DBContext.Membre
                .Join(
                    DBContext.Utilisateur,
                    m => m.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (m, u) => new
                    {
                        m.CodeUtilisateur,
                        m.Username,
                        m.MotDePasse,
                        m.Courriel,
                        m.Telephone,
                        m.Statut,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .ToListAsync();

            if (membreList.Count == 0)
            {
                return NotFound();
            }
            else
            {
                return membreList;
            }
        }

        /// <summary>
        /// Méthode GET by CodeUtilisateur
        /// </summary>
        /// <param name="CodeUtilisateur">Le code utilisateur du membre recherché</param>
        /// <returns>Le membre correspondant au code utilisateur</returns>
        [HttpGet("RecupererMembreParCodeUtilisateur")]
        public async Task<ActionResult<object>> GetMembreByCodeUtilisateur(int CodeUtilisateur)
        {
            var membreInfo = await DBContext.Membre
                .Where(m => m.CodeUtilisateur == CodeUtilisateur)
                .Join(
                    DBContext.Utilisateur,
                    m => m.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (m, u) => new
                    {
                        CodeUtilisateur = m.CodeUtilisateur,
                        Username = m.Username,
                        MotDePasse = m.MotDePasse,
                        Courriel = m.Courriel,
                        Telephone = m.Telephone,
                        Statut = m.Statut,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .FirstOrDefaultAsync();

            if (membreInfo == null)
            {
                return NotFound();
            }
            else
            {
                return membreInfo;
            }
        }

        /// <summary>
        /// Méthode GET by Username
        /// </summary>
        /// <param name="Username">Le username du membre recherché</param>
        /// <returns>Le membre correspondant au username</returns>
        [HttpGet("RecupererMembreParUsername")]
        public async Task<ActionResult<object>> GetMembreByUsername(string Username)
        {
            var membreInfo = await DBContext.Membre
                .Where(m => m.Username == Username)
                .Join(
                    DBContext.Utilisateur,
                    m => m.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (m, u) => new
                    {
                        CodeUtilisateur = m.CodeUtilisateur,
                        Username = m.Username,
                        MotDePasse = m.MotDePasse,
                        Courriel = m.Courriel,
                        Telephone = m.Telephone,
                        Statut = m.Statut,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .FirstOrDefaultAsync();

            if (membreInfo == null)
            {
                return NotFound();
            }
            else
            {
                return membreInfo;
            }
        }

        /// <summary>
        /// Méthode GET by Username and password
        /// </summary>
        /// <param name="requeteConnexion">Le username et le mot de passse entrés</param>
        /// <returns>Le membre correspondant au username et un token d'identification</returns>
        [HttpPost("RecupererMembreParUsernamePassword")]
        public async Task<ActionResult<object>> GetMembreByUsernameAndPassword([FromBody] RequeteConnexion requeteConnexion)
        {
            var Login = requeteConnexion.Login;
            var MotDePasse = requeteConnexion.MotDePasse;
            var membreInfo = await DBContext.Membre
                .Where(m => ((m.Username == Login || m.Courriel == Login) && m.MotDePasse == MotDePasse))
                .Join(
                    DBContext.Utilisateur,
                    m => m.CodeUtilisateur,
                    u => u.IdUtilisateur,
                    (m, u) => new
                    {
                        CodeUtilisateur = m.CodeUtilisateur,
                        Username = m.Username,
                        MotDePasse = m.MotDePasse,
                        Courriel = m.Courriel,
                        Telephone = m.Telephone,
                        Statut = m.Statut,
                        Nom = u.Nom,
                        Prenom = u.Prenom
                    })
                .FirstOrDefaultAsync();

            if (membreInfo == null)
            {
                return NotFound();
            }
            else
            {
                // Génère un JWT token.
                var token = GenerateJwtToken(membreInfo.Username);
                return new { Token = token, MemberInfo = membreInfo };
            }
        }


        /// <summary>
        /// Méthode CREATE (INSERT)
        /// </summary>
        /// <param name="codeUtilisateur">codeUtilisateur du Membre == IdUtilisateur de l'Utilisateur</param>
        /// <param name="username">Nom usager du membre</param>
        /// <param name="motDePasse">mot de passe du membre</param>
        /// <param name="courriel">Courriel du membre</param>
        /// <param name="telephone">Téléphone du membre</param>
        /// <param name="statut">Statut du membre</param>
        /// <param name="nom">Nom de l'utilisateur rattaché au membre</param>
        /// <param name="prenom">Prénom de l'utilisateur rattaché au membre</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPost("CreerMembre")]
        public async Task<IActionResult> InsertMembre(string username, string motDePasse, string courriel, string telephone, string statut, string nom, string prenom)
        {
            // Custom validation logic
            if (string.IsNullOrWhiteSpace(username) || string.IsNullOrWhiteSpace(motDePasse) || string.IsNullOrWhiteSpace(courriel) || string.IsNullOrWhiteSpace(telephone) || string.IsNullOrWhiteSpace(statut) || string.IsNullOrWhiteSpace(nom) || string.IsNullOrWhiteSpace(prenom))
            {
                ModelState.AddModelError("ValidationError", "All fields are required.");
                return BadRequest(new { message = "All fields are required" }); // Return a 400 Bad Request response with a validation error message.
            }

            // On doit créer un nouvel Utilisateur...
            var nouvelUtilisateur = new Utilisateur()
            {
                Nom = nom,
                Prenom = prenom,
            };
            DBContext.Utilisateur.Add(nouvelUtilisateur);
            await DBContext.SaveChangesAsync(); // Sauvegarde l'utilisateur dans la BDD pour pouvoir récupérer son IdUtilisateur

            // ... mais aussi un nouveau Membre avec le même ID
            var nouveauMembre = new Membre()
            {
                CodeUtilisateur = nouvelUtilisateur.IdUtilisateur,
                Username = username,
                MotDePasse = motDePasse,
                Courriel = courriel,
                Telephone = telephone,
                Statut = statut,
            };

            DBContext.Membre.Add(nouveauMembre);

            await DBContext.SaveChangesAsync();
            return StatusCode(201);
        }


        /// <summary>
        /// Méthode UPDATE by code/ID utilisateur
        /// </summary>
        /// <param name="codeUtilisateur">codeUtilisateur du Membre == IdUtilisateur de l'Utilisateur</param>
        /// <param name="username">Nom usager du membre</param>
        /// <param name="motDePasse">mot de passe du membre</param>
        /// <param name="courriel">Courriel du membre</param>
        /// <param name="telephone">Téléphone du membre</param>
        /// <param name="statut">Statut du membre</param>
        /// <param name="nom">Nom de l'utilisateur rattaché au membre</param>
        /// <param name="prenom">Prénom de l'utilisateur rattaché au membre</param>
        /// <returns>Message HTTP si réussite</returns>
        [HttpPut("MettreAJourMembre")]
        public async Task<IActionResult> UpdateMembre(int codeUtilisateur, string motDePasse, string courriel,
            string telephone, string nom, string prenom)
        {
            // Retrouver le Membre existant dans la BDD par son codeUtilisateur
            var existingMembre = await DBContext.Membre.FirstOrDefaultAsync(m => m.CodeUtilisateur == codeUtilisateur);

            if (existingMembre == null)
            {
                return NotFound(); // Membre inexistant
            }

            // Mise à jour du Membre
            existingMembre.MotDePasse = motDePasse;
            existingMembre.Courriel = courriel;
            existingMembre.Telephone = telephone;

            // Il faut trouver l'Utilisateur correspondant dans la BDD avec IdUtilisateur (IdUtilisateur == codeUtilisateur)
            var existingUtilisateur = await DBContext.Utilisateur.FirstOrDefaultAsync(u => u.IdUtilisateur == codeUtilisateur);

            if (existingUtilisateur == null)
            {
                return NotFound(); // Utilisateur inexistant
            }

            // Mise a jour de l'Utilisateur
            existingUtilisateur.Nom = nom;
            existingUtilisateur.Prenom = prenom;

            // Savegarde BDD
            await DBContext.SaveChangesAsync();

            return Ok();
        }

        /// <summary>
        /// Permet de mettre à jour uniquement le statut d'un membre (visible ou archivé)
        /// </summary>
        /// <param name="codeUtilisateur">Code utilisateur du membre que l'on veut modifier</param>
        /// <param name="statut">Nouveau statut que l'on veut mettre à jour</param>
        /// <returns>Un message de confirmation ou d'erreur</returns>
        [HttpPost("ModifierStatutMembre")]
        public async Task<IActionResult> UpdateStatutMembre(int codeUtilisateur, string statut)
        {
            // Retrouver le Membre existant dans la BDD par son CodeUtilisateur
            var existingMembre = await DBContext.Membre.FirstOrDefaultAsync(m => m.CodeUtilisateur == codeUtilisateur);

            if (existingMembre == null)
            {
                return NotFound(); // Annonce non trouvée
            }

            // Mettre à jour le statut de l'annonce
            existingMembre.Statut = statut;
            await DBContext.SaveChangesAsync();

            return Ok();
        }

        /// <summary>
        /// Méthode pour valider les informations de connexion d'un membre
        /// </summary>
        /// <param name="username">Nom usager</param>
        /// <param name="password">Mot de passe</param>
        /// <returns>Le codeUtilisateur de u membre (pour l'utiliser pour récupérer ses annonces)</returns> 
        [HttpGet("VerifierInfosConnexion")]
        public IActionResult CheckMembreCredentials(string username, string password)
        {
            var user = DBContext.Membre.FirstOrDefault(u => u.Username == username && u.MotDePasse == password);

            if (user != null)
            {
                // Generate a JWT token.
                var token = GenerateJwtToken(user.Username);

                // Return both the token and user's CodeUtilisateur.
                return Ok(new { Token = token, CodeUtilisateur = user.CodeUtilisateur });
            }
            else
            {
                return Unauthorized();
            }
        }

        private string GenerateJwtToken(string username)
        {
            // Generate a cryptographically secure 256-bit (32-byte) key
            var keyBytes = new byte[32];
            using (var rng = new RNGCryptoServiceProvider())
            {
                rng.GetBytes(keyBytes);
            }

            var securityKey = new SymmetricSecurityKey(keyBytes);

            var credentials = new SigningCredentials(securityKey, SecurityAlgorithms.HmacSha256);
            var token = new JwtSecurityToken(
                issuer: "YourIssuer",
                audience: "YourAudience",
                claims: new[] { new Claim(ClaimTypes.Name, username) },
                expires: DateTime.Now.AddMinutes(30), // Token expiration time
                signingCredentials: credentials
            );

            return new JwtSecurityTokenHandler().WriteToken(token);
        }
    }
}
