import React from 'react';
import Navigation from '../components/Navigation';
import { listeSuperHero } from "../pckgDonnees/donnees";

/**
 * Get un entier aléatoire selon la plage maximum fournis
 * @param maximum: maximum du nombre aléatoire
 * @returns {number} un entier aléatoire
 */
function getEntierAleatoire(maximum){
    return Math.floor(Math.random() * Math.floor(maximum));
}

/**
 * Afficher la liste des super hero de manière aléatoire
 * @returns {JSX.Element}
 * @constructor
 */
const Question1 =()=> {
    // On définit la valeur maximale de la liste de superhéros
    const max = listeSuperHero.length;
    // Nombre aléatoire de superhéros à afficher générer
    const nbSHToShow = getEntierAleatoire(max);
    // Liste vide pour stocker les superhéros à afficher
    const shToAfficher = [];
    
    // Parcourir  la liste de superhéros pour en choisir aléatoirement nbSHToShow
    for (let i = 0; i < nbSHToShow; i++) {
      // On choisit un index aléatoire dans la liste de superhéros
      const randomIndex = getEntierAleatoire(max);
      // On ajoute le superhéros correspondant à cet index dans la liste shToAfficher
      shToAfficher.push(listeSuperHero[randomIndex]);
    }

    // Afficher une liste de super hero aléatoire
    return (
        <main>
            <section className="lst_heros">
                <h2 className="titre_entete_heros">Liste Heros</h2>
                <table>
                    <thead>
                        <tr>
                            <th>Prénom</th>
                            <th>Nom</th>
                            <th>Alias</th>
                            <th>Ville</th>
                        </tr>
                    </thead>
                    <tbody>
                        {shToAfficher.map((hero) => (
                            <tr key={hero.id}>
                                <td>{hero.prenom}</td>
                                <td>{hero.nom}</td>
                                <td>{hero.alias}</td>
                                <td>{hero.ville}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            </section>
        < /main>
    );
}
export default Question1;
