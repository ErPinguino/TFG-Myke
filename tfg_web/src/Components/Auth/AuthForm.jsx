// src/Components/Auth/AuthForm.jsx

import React, { useContext, useState } from 'react';
import { AuthContext }      from '../../Contexts/AuthContext.js';
import LoginForm            from './LoginForm.jsx';
import RegisterForm         from './RegisterForm.jsx';
import Notification         from '../Notification/Notification.jsx';
import './AuthForm.css';
import '../SearchForm/SearchForm.css';  // donde está .minecraft-button

export default function AuthForm() {
    const { showRegisterForm, setShowRegisterForm, loginAsGuest } = useContext(AuthContext);

    // Estado para la notificación: { message: string, type: 'success'|'error'|'info' }
    const [notification, setNotification] = useState(null);

    return (
        <div className="auth-container">
            {/* Si hay notificación, la mostramos */}
            {notification && (
                <Notification
                    message={notification.message}
                    type={notification.type}
                    onClose={() => setNotification(null)}
                />
            )}

            {/* Elegimos qué formulario mostrar */}
            {showRegisterForm
                ? <RegisterForm onNotify={setNotification} />
                : <LoginForm    onNotify={setNotification} />
            }

            <div className="auth-choose">
                <h2>¿Qué deseas hacer?</h2>

                <button
                    className="minecraft-button"
                    onClick={() => setShowRegisterForm(v => !v)}
                >
                    {showRegisterForm ? '← Volver al login' : 'Registrarme'}
                </button>

                {!showRegisterForm && (
                    <button
                        className="minecraft-button"
                        onClick={loginAsGuest}
                    >
                        Entrar como invitado
                    </button>
                )}
            </div>
        </div>
    );
}
