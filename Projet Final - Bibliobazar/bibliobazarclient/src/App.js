import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import { useState } from "react";
import './Assets/styles/main.css';
import { AuthProvider } from './AuthContext';
import Entete from "./Components/Entete/Entete";
import Acceuil from "./Components/Acceuil/Acceuil";
import Catalogue from './Components/Acceuil/Catalogue';
import Annonce from './Components/Annonce/Annonce';
import SeConnecter from "./Components/Connexion/SeConnecter/SeConnecter";
import CreerUnCompte from "./Components/Connexion/CreerUnCompte/CreerUnCompte";
import SeDeconnecter from "./Components/Connexion/Deconnexion/SeDeconnecter";
import Profil from "./Components/Membre/Profil";
import CreerAnnonce from "./Components/Annonce/CreerAnnonce";
import CreerAuteur from './Components/Annonce/CreerAuteur';
import ModifierAnnonce from './Components/Annonce/ModifierAnnonce';
import Noter from './Components/Annonce/Noter';
import PanelAdmin from "./Components/Admin/PanelAdmin";
import ListeGenres from "./Components/Admin/Listes/ListeGenres";
import ListeEtats from "./Components/Admin/Listes/ListeEtats";
import PiedDePage from "./Components/PiedDePage/footer";

function App() {
  // État de connexion
  const [estConnecter, setEstConnecte] = useState(false);

  const handleLogin = (membre) => {
    // Fonction pour gérer la connexion
    setEstConnecte(true); // Mettre l'état de connexion à true
  };
  const handleLogout = () => {
    // Fonction pour gérer la déconnexion
    console.log('Bouton déconnexion cliqué (App.js)');
    setEstConnecte(false); // Mettre l'état de connexion à false
  };

  console.log('Membre Connecté = ' + estConnecter);
  
  return (
    <AuthProvider>
      <Router>
        <section className="App">
          <Entete estConnecter={estConnecter} handleLogout={handleLogout} />
          <Routes>
            <Route path="/" element={<Acceuil />} />
            <Route path="/Catalogue" element={<Catalogue />} />
            <Route path="/Annonce/:numeroReference" element={<Annonce />} />

            <Route path="/SeConnecter" element={<SeConnecter handleLogin={handleLogin} estConnecter={estConnecter} />} />
            <Route path="/CreerUnCompte" element={<CreerUnCompte />} />

            {/* TODO: Conditionnellement afficher ces routes
                        si c'est connecté et si c'est un membre */}
            <Route path="/Profil" element={<Profil />} />
            <Route path="/CreerAnnonce" element={<CreerAnnonce />} />
            <Route path="/CreerAuteur" element={<CreerAuteur />} />
            <Route path="/ModifierAnnonce/:numeroReference" element={<ModifierAnnonce />} />
            <Route path="/Noter/:numeroReference" element={<Noter />} />

            {/* TODO: Conditionnellement afficher la route des listes
                        du panel admin si c'est connecté et si c'est un admin */}
            <Route path="/PanelAdmin" element={<PanelAdmin />} />
            <Route path="/ListeGenres" element={<ListeGenres />} />
            <Route path="/ListeEtats" element={<ListeEtats />} />

            {/* Conditionnellement afficher la route de déconnexion */}
            <Route path="/SeDeconnecter" element={<SeDeconnecter onLogout={handleLogout} />} />
          </Routes>
          <PiedDePage />
        </section>
      </Router>
    </AuthProvider>
  );
}

export default App;