// src/App.jsx
import React, { useContext } from 'react';
import { AuthProvider } from './Contexts/AuthProvider';
import { AuthContext }  from './Contexts/AuthContext';
import AuthForm         from './Components/Auth/AuthForm';
import StructureSearch  from './Components/StructureSearch/StructureSearch';

function Main() {
    const { isAuthenticated, isGuest } = useContext(AuthContext);

    // Si ni logueado ni guest, muestro form de login/registro
    if (!isAuthenticated && !isGuest) {
        return <AuthForm />;
    }

    // Si es invitado o está autenticado, paso al buscador
    return <StructureSearch />;
}

export default function App() {
    return (
        <AuthProvider>
            <Main />
        </AuthProvider>
    );
}
