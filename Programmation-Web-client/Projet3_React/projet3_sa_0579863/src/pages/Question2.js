import React, { useState } from 'react';
import { listeSuperHero } from "../pckgDonnees/donnees";

/**
 * Afficher la liste des super hero de manière aléatoire selon le filtre
 * @returns {JSX.Element}
 * @constructor
 */
const Question2 =()=> {
    const [alias, setAlias] = useState('');
    const [superHerosFiltre, setSuperHerosFiltre] = useState(listeSuperHero);

    // Fonction appelée à chaque changement du champ alias
    const handleInputChange = (event) => {
        if (event.target.value === "") {
            setSuperHerosFiltre(listeSuperHero);
            return;
        }
        // Obtention de la valeur saisie et conversion en minuscules
        const inputAlias = event.target.value.toLowerCase();
        // Mise à jour de l'état alias
        setAlias(inputAlias);
        // Filtrage de la liste de super-héros selon l'alias saisi
        const filteredSuperHeros = listeSuperHero.filter(
            (hero) => hero.alias.toLowerCase().includes(inputAlias)
        );
        // Mise à jour de l'état de la liste filtrée
        setSuperHerosFiltre(filteredSuperHeros);
    };

    // Affichage du composant
    return (
        <main>
            <section className="wrap">
                <div className="recherche">
                    {/*--<label htmlFor="alias"><FaSearch icon="search"/></label>*/}
                    <label htmlFor="alias" className="alias-label">
                        <svg stroke="currentColor" fill="currentColor" stroke-width="0"
                             viewBox="0 0 512 512" icon="search"
                             xmlns="http://www.w3.org/2000/svg">
                            <path d="M505 442.7L405.3 343c-4.5-4.5-10.6-7-17-7H372c27.6-35.3 44-79.7 44-128C416
                            93.1 322.9 0 208 0S0 93.1 0 208s93.1 208 208 208c48.3 0 92.7-16.4 128-44v16.3c0 6.4 2.5 12.5
                            7 17l99.7 99.7c9.4 9.4 24.6 9.4 33.9 0l28.3-28.3c9.4-9.4 9.4-24.6.1-34zM208 336c-70.7
                            0-128-57.2-128-128 0-70.7 57.2-128 128-128 70.7 0 128 57.2 128
                            128 0 70.7-57.2 128-128 128z"></path>
                        </svg>
                    </label>
                    <input
                          type="text"
                          id="alias"
                          name="alias"
                          value={alias}
                          placeholder={"Recherche un Alias..."}
                          onChange={handleInputChange}
                    />
                </div>
                {superHerosFiltre.length > 0 && alias!='' &&(
                  <ul className="affiche-hero-filtre visible-scrollbar">
                    {/* Boucle sur la liste de super-héros filtrée
                    et affichage des informations */}
                    {superHerosFiltre.map((hero) => (
                      <li key={hero.id}>
                        <p>Nom : {hero.nom}</p>
                        <p>Prénom : {hero.prenom}</p>
                        <p>Alias : {hero.alias}</p>
                        <p>Ville : {hero.ville}</p>
                      </li>
                    ))}
                  </ul>
                )}
            </section>
        </main>
    );

};

export default Question2;
