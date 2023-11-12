import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

export function useAuth() {
  return useContext(AuthContext);
}

export function AuthProvider({ children }) {
  const [membreConnecte, setMembreConnecte] = useState(null);
  const [estConnecter, setEstConnecte] = useState(false);
  const [isAdmin, setIsAdmin] = useState(false);

  const login = (membre) => {
    setMembreConnecte(membre);
    setEstConnecte(true);
  };

  const logAdmin = () => {
    setIsAdmin(true);
  };

  const logout = () => {
    setMembreConnecte(null);
    setEstConnecte(false);
    setIsAdmin(false);
  };

  return (
    <AuthContext.Provider value={{ membreConnecte, estConnecter, isAdmin, login, logAdmin, logout }}>
      {children}
    </AuthContext.Provider>
  );
}