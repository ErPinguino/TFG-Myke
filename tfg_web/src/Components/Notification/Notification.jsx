// src/Components/Shared/Notification.jsx
import React, { useEffect } from 'react';
import './Notification.css';

export default function Notification({ message, type = 'info', onClose, duration = 3000 }) {
    useEffect(() => {
        const id = setTimeout(onClose, duration);
        return () => clearTimeout(id);
    }, [onClose, duration]);

    return (
        <div className={`notification notification--${type}`}>
            {message}
            <button className="notification__close" onClick={onClose}>×</button>
        </div>
    );
}
