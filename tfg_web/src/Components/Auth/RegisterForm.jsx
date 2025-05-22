// src/Components/Auth/RegisterForm.jsx
import React, { useState, useContext } from 'react';
import { AuthContext } from '../../Contexts/AuthContext.js';
import './AuthForm.css';

export default function RegisterForm({ onNotify }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const { register, setShowRegisterForm } = useContext(AuthContext);

    const handleSubmit = async e => {
        e.preventDefault();
        try {
            const ok = await register({ username, password });
            if (ok) {
                onNotify({ message: '¡Registro exitoso! Ya puedes iniciar sesión.', type: 'success' });
                // Tras un par de segundos, volvemos al formulario de login
                setTimeout(() => {
                    setShowRegisterForm(false);
                }, 2000);
            } else {
                onNotify({ message: 'Falló el registro. Inténtalo de nuevo.', type: 'error' });
            }
        } catch (err) {
            onNotify({ message: 'Error al registrar: ' + err.message, type: 'error' });
        }
    };

    return (
        <form className="auth-form" onSubmit={handleSubmit}>
            <label>Usuario</label>
            <input
                value={username}
                onChange={e => setUsername(e.target.value)}
                required
                className="minecraft-input"
            />

            <label>Contraseña</label>
            <input
                type="password"
                value={password}
                onChange={e => setPassword(e.target.value)}
                required
                className="minecraft-input"
            />

            <button type="submit" className="minecraft-button">
                Registrarme
            </button>
        </form>
    );
}
