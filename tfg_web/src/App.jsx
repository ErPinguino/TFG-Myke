// src/App.jsx
import React, { useContext } from 'react';
import { AuthProvider } from './Contexts/AuthProvider';
import { AuthContext }  from './Contexts/AuthContext';
import AuthForm         from './Components/Auth/AuthForm';
import StructureSearch  from './Components/StructureSearch/StructureSearch';
import './App.css'; // asegúrate de importarlo

function Main() {
    const { isAuthenticated, isGuest } = useContext(AuthContext);

    if (!isAuthenticated && !isGuest) {
        return <AuthForm />;
    }
    return <StructureSearch />;
}

export default function App() {
    return (
        <AuthProvider>
            <AppContent />
        </AuthProvider>
    );
}

// Extraemos a un componente para poder usar el contexto
function AppContent() {
    const { isAuthenticated, isGuest } = useContext(AuthContext);

    return (
        <div className="App">
            {
                // sólo mientras mostramos AuthForm
                !isAuthenticated && !isGuest &&
                <video
                    className="background-video"
                    src="/minecraft-video.mp4"
                    muted
                    autoPlay
                    loop
                />
            }
            <Main />
        </div>
    );
}
