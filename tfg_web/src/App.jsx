// src/App.jsx
import React, { useContext, useState, useRef } from 'react';
import { AuthProvider } from './Contexts/AuthProvider';
import { AuthContext }  from './Contexts/AuthContext';
import AuthForm         from './Components/Auth/AuthForm';
import StructureSearch  from './Components/StructureSearch/StructureSearch';
import './App.css';

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

function AppContent() {
    const { isAuthenticated, isGuest } = useContext(AuthContext);
    const [isMuted, setIsMuted] = useState(true);
    const videoRef = useRef(null);

    const toggleMute = () => {
        setIsMuted(prev => !prev);
        if (videoRef.current) {
            videoRef.current.muted = !isMuted;
        }
    };

    return (
        <div className="App">
            {(!isAuthenticated && !isGuest) && (
                <>
                    <video
                        ref={videoRef}
                        className="background-video"
                        src="/minecraft-video.mp4"
                        muted={isMuted}
                        autoPlay
                        loop
                    />
                    <button
                        className="mute-toggle-btn"
                        onClick={toggleMute}
                    >
                        {isMuted ? '🔇' : '🔊'}
                    </button>
                </>
            )}

            <Main />
        </div>
    );
}
