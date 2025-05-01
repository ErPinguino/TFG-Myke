import React from 'react';
import './MinecraftMap.css';

const MinecraftMap = ({ coordinates }) => {
  if (!coordinates || coordinates.length === 0) {
    return (
      <div className="map-container">
        <div className="minecraft-map">
          No structures found
        </div>
      </div>
    );
  }

  return (
    <div className="map-container">
      <div className="minecraft-map">
        {/* Aquí irá tu lógica de visualización del mapa */}
        Found {coordinates.length} structures
      </div>
    </div>
  );
};

export default MinecraftMap;