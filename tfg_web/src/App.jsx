import React, { useContext } from 'react';
import { AuthContext } from './Contexts/AuthContext';
import AuthForm from './Components/Auth/AuthForm';
import StructureSearch from './Components/StructureSearch/StructureSearch';

export default function App() {
    const { isAuthenticated, isGuest } = useContext(AuthContext);

    // si no ha hecho login ni invitado → AuthForm
    if (!isAuthenticated && !isGuest) {
        return <AuthForm />;
    }

    // si ha entrado (login o invitado) → buscador + mapa
    return <StructureSearch />;
}

