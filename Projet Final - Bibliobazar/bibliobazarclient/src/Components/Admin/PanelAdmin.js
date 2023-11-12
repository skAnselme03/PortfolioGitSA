import React, { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router-dom";
import { useAuth } from '../../AuthContext';

const PanelAdmin = () => {
  const { isAdmin } = useAuth();
  const [members, setMembers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    if (!isAdmin) {
      // Si l'utilisateur n'est pas admin, il est redirigé au Catalogue
      navigate('/Catalogue');
    }

    // Récupère la liste des membres if the user is an admin
    fetch("http://localhost:5227/api/Membre/RecupererTousMembres")
      .then((response) => response.json())
      .then((data) => {
        setMembers(data);
      })
      .catch((error) => {
        console.error("Erreur dans la récupération des membres:", error);
        setMembers([]);
      });
  }, [isAdmin]);

  // Pour désactiver ou réactiver des membres
  const handleStatusChange = async (codeUtilisateur, currentStatus) => {
    const newStatus = currentStatus === "Actif" ? "Inactif" : "Actif";

    const url = `http://localhost:5227/api/Membre/ModifierStatutMembre?codeUtilisateur=${codeUtilisateur}&statut=${newStatus}`;

    try {
      const response = await fetch(url, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
      });

      if (response.ok) {
        const updatedMembers = members.map((member) =>
          member.codeUtilisateur === codeUtilisateur ? { ...member, statut: newStatus } : member
        );

        setMembers(updatedMembers);
      } else {
        console.error("Erreur lors de la mise à jour du statut du membre");
      }
    } catch (error) {
      console.error("Erreur lors de la mise à jour du statut du membre:", error);
    }
  };

  if (!isAdmin) {
    return (
      <div>
        <p>Vous n'êtes pas autorisé à consulter cette page.</p>
      </div>
    );
  }

  return (
    <div>
      <h2>Signalement annonces</h2>
      <p>[À FAIRE - liste des signalements]</p>
      <br />
      <h2>Modifications</h2>
      <ul>
        <li className="ajouter-un-auteur__lien" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '50%' }}>
          <Link to="/ListeGenres">Liste des genres</Link>
        </li>
        <li className="ajouter-un-auteur__lien" style={{ display: 'flex', alignItems: 'center', justifyContent: 'center', width: '50%' }}>
          <Link to="/ListeEtats">Liste des états</Link>
        </li>
      </ul>
      <br />
      <h2>Liste des membres</h2>
      <ul style={{ alignItems: 'center', width: '75%' }}>
        {members.map((member, index) => (
          <li key={index} style={{ fontSize: '20px', flex: 0.5, padding: '5px 20px' }}><strong>{member.username}</strong> [{member.statut}] <button onClick={() => handleStatusChange(member.codeUtilisateur, member.statut)}>Changer le statut</button></li>
        ))}
      </ul>
    </div>
  );
};

export default PanelAdmin;