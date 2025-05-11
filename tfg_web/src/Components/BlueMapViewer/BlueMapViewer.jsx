// src/Components/BlueMapViewer/BlueMapViewer.jsx
import React from 'react';
import './BlueMapViewer.css';

export default function BlueMapViewer() {
    return (
        <div className="bluemap-container">
            <iframe
                title="BlueMap Viewer"
                src="http://localhost:8100/"
                frameBorder="0"
                allowFullScreen
            />
        </div>
    );
}
