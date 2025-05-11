// src/Contexts/AuthContext.js
import { createContext } from 'react';

export const AuthContext = createContext({
    isAuthenticated: false,
    isGuest: false,
    showRegisterForm: false,
    setShowRegisterForm: () => {},
    login: async () => false,
    register: async () => false,
    loginAsGuest: () => {},
    logout: () => {},
});

// opcionalmente para importarlo sin llaves:
export default AuthContext;
