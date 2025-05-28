// src/App.jsx - Video siempre de fondo
import React, { useContext, useState, useRef, useEffect } from 'react';
import { AuthProvider } from './Contexts/AuthProvider';
import { AuthContext  } from './Contexts/AuthContext';
import AuthForm         from './Components/Auth/AuthForm';
import StructureSearch  from './Components/StructureSearch/StructureSearch';
import './App.css';

function Main() {
  const { isAuthenticated, isGuest } = useContext(AuthContext);

  if (!isAuthenticated && !isGuest) {
    return (
      <div className="login-wrapper">
        <AuthForm />
      </div>
    );
  }
  return (
    <div className="finder-wrapper">
      <StructureSearch />
    </div>
  );
}

export default function App() {
  return (
    <AuthProvider>
      <AppContent />
    </AuthProvider>
  );
}

function AppContent() {
  const [isMuted, setIsMuted]           = useState(true);
  const [showPlayButton, setShowPlayButton] = useState(false);
  const videoRef                        = useRef(null);

  useEffect(() => {
    const video = videoRef.current;
    if (!video) return;

    const attemptPlay = async () => {
      try {
        video.muted  = true;
        video.volume = 0;
        await video.play();
        setShowPlayButton(false);
      } catch {
        setShowPlayButton(true);
      }
    };

    if (video.readyState >= 2) {
      attemptPlay();
    } else {
      video.addEventListener('loadeddata', attemptPlay);
      return () => video.removeEventListener('loadeddata', attemptPlay);
    }
  }, []);

  const toggleMute = () => {
    const video    = videoRef.current;
    const newMuted = !isMuted;
    setIsMuted(newMuted);
    video.muted    = newMuted;
    video.volume   = newMuted ? 0 : 1;
  };

  const handlePlayClick = async () => {
    const video = videoRef.current;
    try {
      video.muted  = true;
      video.volume = 0;
      await video.play();
      setShowPlayButton(false);
    } catch (error) {
      console.error(error);
    }
  };

  return (
    <div className="App">
      <video
        ref={videoRef}
        className="background-video"
        src="https://tfgmykebucket.s3.eu-west-3.amazonaws.com/minecraft-video.mp4"
        muted
        autoPlay
        loop
        playsInline
        preload="metadata"
      />

      {showPlayButton && (
        <button
          className="video-play-btn"
          onClick={handlePlayClick}
        >
          ▶️ Reproducir fondo
        </button>
      )}

      <button
        className="mute-toggle-btn"
        onClick={toggleMute}
      >
        {isMuted ? '🔇' : '🔊'}
      </button>

      <Main />
    </div>
  );
}
