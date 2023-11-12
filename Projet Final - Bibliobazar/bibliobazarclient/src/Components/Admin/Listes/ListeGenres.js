import React, { useState, useEffect } from 'react';

const ListeGenres = () => {
  const [genres, setGenres] = useState([]);
  const [newGenre, setNewGenre] = useState({ description: '', statut: 'Visible' });
  const [editGenre, setEditGenre] = useState({ idGenre: null, description: '' });

  // Récupère la liste des genres
  const refreshGenres = () => {
    fetch('http://localhost:5227/api/Genre/RecupererTousGenres')
      .then((response) => response.json())
      .then((data) => {
        setGenres(data);
      })
      .catch((error) => {
        console.error('Erreur dans la récupération des genres:', error);
        setGenres([]);
      });
  };

  useEffect(() => {
    refreshGenres();
  }, []);

  // Mise à jour du genre - à examiner et bonifier...
  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewGenre({
      ...newGenre,
      [name]: value,
    });
  };

  // Formulaire pour ajouter un nouveau Genre
  const handleSubmit = (e) => {
    e.preventDefault();
    const genreData = {
      description: newGenre.description,
      statut: newGenre.statut,
    };
    // Requête POST pour le nouveau Genre
    fetch('http://localhost:5227/api/Genre/CreerGenre', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(genreData),
    })
      .then((response) => {
        if (response.status === 201) {
          setNewGenre({ description: '', statut: 'Visible' });
          refreshGenres();
        } else {
          console.error('Erreur : Nouveau genre non créé');
        }
      })
      .catch((error) => {
        console.error('Erreur lors de la création du genre:', error);
      });
  };

  // Changer le statut d'un genre
  const changeStatut = (idGenre) => {
    // Recherche le genre par son id
    const genreToUpdate = genres.find((genre) => genre.idGenre === idGenre);
    if (genreToUpdate) {
      // Toggle le statut entre 'Visible' et 'Archivé'
      genreToUpdate.statut = genreToUpdate.statut === 'Visible' ? 'Archivé' : 'Visible';
      // Fais la requête PUT
      fetch('http://localhost:5227/api/Genre/MettreGenreAJour', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(genreToUpdate),
      })
        .then((response) => {
          if (response.status === 200) {
            refreshGenres();
          } else {
            console.error('Erreur : Mise à jour du genre non effectuée');
          }
        })
        .catch((error) => {
          console.error('Erreur lors de la mise à jour du genre:', error);
        });
    }
  };

  // Modifier la description d'un genre
  const editDescription = (idGenre, description) => {
    setEditGenre({ idGenre, description });
  };

  // Soumettre la modification de la description
  const submitDescriptionEdit = () => {
    const genreToUpdate = genres.find((genre) => genre.idGenre === editGenre.idGenre);
    if (genreToUpdate) {
      genreToUpdate.description = editGenre.description;
      fetch('http://localhost:5227/api/Genre/MettreGenreAJour', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(genreToUpdate),
      })
        .then((response) => {
          if (response.status === 200) {
            refreshGenres();
            setEditGenre({ idGenre: null, description: '' });
          } else {
            console.error('Erreur : Mise à jour de la description du genre non effectuée');
          }
        })
        .catch((error) => {
          console.error('Erreur lors de la mise à jour de la description du genre:', error);
        });
    }
  };

  return (
    <section>
      <div>
        <h2>Liste des genres</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            id="description"
            name="description"
            placeholder="Nouveau genre"
            value={newGenre.description}
            onChange={handleInputChange}
            required
          />
          <button type="submit" >Ajouter</button>
          <br />
        </form>
        {genres && genres.length > 0 ? (
          <ul>
            {genres.map((genre) => (
              <li key={genre.idGenre}>
                {editGenre.idGenre === genre.idGenre ? (
                  <div style={{ display: 'flex', alignItems: 'center' }}>
                    <input
                      style={{ fontSize: '30px', flex: 0.24 }}
                      type="text"
                      name="description"
                      value={editGenre.description}
                      onChange={(e) => setEditGenre({ ...editGenre, description: e.target.value })}
                    />
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={submitDescriptionEdit}>Modifier</button>
                  </div>
                ) : (
                  <div style={{ display: 'flex', alignItems: 'center', width: '75%' }}>
                    <strong style={{ fontSize: '30px', flex: 0.5 }}>{genre.description}</strong> <span style={{ fontSize: '30px', flex: 0.2 }}>[{genre.statut}]</span>
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={() => changeStatut(genre.idGenre)}>Changer statut</button>
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={() => editDescription(genre.idGenre, genre.description)}>Modifier le nom du genre</button>
                  </div>
                )}
              </li>
            ))}
          </ul>
        ) : (
          <p>Aucun genre disponible.</p>
        )}
      </div>
    </section>
  );
};

export default ListeGenres;