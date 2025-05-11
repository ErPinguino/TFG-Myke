// src/Components/Auth/AuthForm.jsx
// src/Components/Auth/AuthForm.jsx
import React, { useContext } from 'react';
import { AuthContext }      from '../../Contexts/AuthContext.js';
import LoginForm            from './LoginForm.jsx';
import RegisterForm         from './RegisterForm.jsx';
import './AuthForm.css';
import '../SearchForm/SearchForm.css';  // donde está .minecraft-button

export default function AuthForm() {
    const { showRegisterForm, setShowRegisterForm, loginAsGuest } = useContext(AuthContext);

    return (
        <div className="auth-container">
            {showRegisterForm
                ? <RegisterForm />
                : <LoginForm />
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
