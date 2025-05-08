import React, { useState, useContext } from 'react';
import { AuthContext } from '../../Contexts/AuthContext';
import './AuthForm.css';

export default function RegisterForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError]       = useState('');
    const { register }            = useContext(AuthContext);

    const handleSubmit = async e => {
        e.preventDefault();
        setError('');
        const ok = await register({ username, password });
        if (!ok) {
            setError('Falló el registro');
        }
        // si ok=true, volverá automáticamente a la pantalla de login
    };

    return (
        <form className="auth-form" onSubmit={handleSubmit}>
            {error && <div className="error">{error}</div>}
            <label>Usuario</label>
            <input value={username} onChange={e => setUsername(e.target.value)} required />
            <label>Contraseña</label>
            <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
            <button type="submit">Registrarme</button>
        </form>
    );
}
