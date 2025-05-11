// src/Contexts/AuthProvider.jsx
import React, { useState } from 'react';
import AuthContext from './AuthContext';
import { authService } from '../Components/Services/Services';

export function AuthProvider({ children }) {
    const [isAuthenticated, setIsAuthenticated]     = useState(false);
    const [isGuest, setIsGuest]                     = useState(false);
    const [showRegisterForm, setShowRegisterForm]   = useState(false);

    const login = async ({ username, password }) => {
        try {
            const message = await authService.login({ username, password });
            if (message === 'Login exitoso') {
                setIsAuthenticated(true);
                setIsGuest(false);
                return true;
            }
            return false;
        } catch (e) {
            return false;
        }
    };

    const register = async ({ username, password }) => {
        const res = await authService.register({ username, password });
        // como ahora authService.register devuelve res.data
        if (res?.id) {
            // tras registrarse, volvemos al login
            setShowRegisterForm(false);
            return true;
        }
        return false;
    };

    const loginAsGuest = () => {
        setIsGuest(true);
        setIsAuthenticated(false);
    };

    const logout = () => {
        authService.logout();
        setIsAuthenticated(false);
        setIsGuest(false);
    };

    return (
        <AuthContext.Provider value={{
            isAuthenticated,
            isGuest,
            showRegisterForm,
            setShowRegisterForm,
            login,
            register,
            loginAsGuest,
            logout,
        }}>
            {children}
        </AuthContext.Provider>
    );
}
