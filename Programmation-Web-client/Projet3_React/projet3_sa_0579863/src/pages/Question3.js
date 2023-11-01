import React, { useState } from 'react';
import { listeSuperHero } from "../pckgDonnees/donnees";
import {livres} from "../pckgDonnees/donnees";

/**
 * Afficher la liste des super hero de manière aléatoire selon le filtre
 * @returns {JSX.Element}
 * @constructor
 */
const Question3 =()=> {
   // Affichage du composant
    return (
         <main className="wrap-livres">
             <div className="livres">
                <section className="affiche_livres">
                <header>
                    <h1>Liste livres</h1>
                </header>
                {livres.map((livre) => (
                    <div className="vue-livre" key={livre.id}>
                        <div className="box">
                           <h2>{livre.titre}</h2>
                           <p>Écrit par : {livre.auteur}</p>
                           <p>Genre : {livre.genre}</p>
                           <p>Éditer par : {livre.editeur}</p>
                           <p>Pubilé le : {livre.date_publication}</p>
                        </div>
                    </div>
                ))}
            </section>
             </div>
         </main>
    );

};

export default Question3;
