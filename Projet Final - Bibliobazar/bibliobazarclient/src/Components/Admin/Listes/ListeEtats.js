import React, { useState, useEffect } from 'react';

const ListeEtats = () => {
  const [etats, setEtats] = useState([]);
  const [newEtat, setNewEtat] = useState({ description: '', statut: 'Visible' });
  const [editEtat, setEditEtat] = useState({ idEtat: null, description: '' });

  // Récupère la liste des états
  const refreshEtats = () => {
    fetch('http://localhost:5227/api/Etat/RecupererTousEtats')
      .then((response) => response.json())
      .then((data) => {
        setEtats(data);
      })
      .catch((error) => {
        console.error('Erreur dans la récupération des états:', error);
        setEtats([]);
      });
  };

  useEffect(() => {
    refreshEtats();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setNewEtat({
      ...newEtat,
      [name]: value,
    });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const etatData = {
      description: newEtat.description,
      statut: newEtat.statut,
    };

    fetch('http://localhost:5227/api/Etat/CreerEtat', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(etatData),
    })
      .then((response) => {
        if (response.status === 201) {
          setNewEtat({ description: '', statut: 'Visible' });
          refreshEtats();
        } else {
          console.error('Erreur : Nouvel état non créé');
        }
      })
      .catch((error) => {
        console.error('Erreur dans la création de état:', error);
      });
  };

  const changeStatut = (idEtat) => {
    const etatToUpdate = etats.find((etat) => etat.idEtat === idEtat);
    if (etatToUpdate) {
      etatToUpdate.statut = etatToUpdate.statut === 'Visible' ? 'Archivé' : 'Visible';

      fetch('http://localhost:5227/api/Etat/MettreEtatAJour', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(etatToUpdate),
      })
        .then((response) => {
          if (response.status === 200) {
            refreshEtats();
          } else {
            console.error('Erreur : Mise à jour de état non effectuée');
          }
        })
        .catch((error) => {
          console.error('Erreur lors de la mise à jour de état:', error);
        });
    }
  };

  const editDescription = (idEtat, description) => {
    setEditEtat({ idEtat, description });
  };

  const submitDescriptionEdit = () => {
    const etatToUpdate = etats.find((etat) => etat.idEtat === editEtat.idEtat);
    if (etatToUpdate) {
      etatToUpdate.description = editEtat.description;
      fetch('http://localhost:5227/api/Etat/MettreEtatAJour', {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(etatToUpdate),
      })
        .then((response) => {
          if (response.status === 200) {
            refreshEtats();
            setEditEtat({ idEtat: null, description: '' });
          } else {
            console.error('Erreur : Mise à jour de la description du état non effectuée');
          }
        })
        .catch((error) => {
          console.error('Erreur lors de la mise à jour de la description du état:', error);
        });
    }
  };

  return (
    <section>
      <div>
        <h2>Liste des états</h2>
        <form onSubmit={handleSubmit}>
          <input
            type="text"
            id="description"
            name="description"
            placeholder="Nouvel état"
            value={newEtat.description}
            onChange={handleInputChange}
            required
          />
          <button type="submit">Ajouter</button>
          <br />
        </form>
        {etats && etats.length > 0 ? (
          <ul>
            {etats.map((etat) => (
              <li key={etat.idEtat}>
                {editEtat.idEtat === etat.idEtat ? (
                  <div style={{ display: 'flex', alignItems: 'center' }}>
                    <input
                      style={{ fontSize: '30px', flex: 0.24 }}
                      type="text"
                      name="description"
                      value={editEtat.description}
                      onChange={(e) => setEditEtat({ ...editEtat, description: e.target.value })}
                    />
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={submitDescriptionEdit}>Modifier</button>
                  </div>
                ) : (
                  <div style={{ display: 'flex', alignItems: 'center', width: '75%' }}>
                    <strong style={{ fontSize: '30px', flex: 0.5 }}>{etat.description}</strong> <span style={{ fontSize: '30px', flex: 0.2 }}>[{etat.statut}]</span>
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={() => changeStatut(etat.idEtat)}>Changer statut</button>
                    <button className="ajouter-un-auteur__lien" style={{ flex: 0.5 }} onClick={() => editDescription(etat.idEtat, etat.description)}>Modifier le nom de l'état</button>
                  </div>
                )}
              </li>
            ))}
          </ul>
        ) : (
          <p>Aucun état disponible.</p>
        )}
      </div>
    </section>
  );
};

export default ListeEtats;