import React from 'react';
import './MinecraftMap.css';

const MinecraftMap = ({ coordinates }) => {
  if (!coordinates || coordinates.length === 0) {
    return (
      <div className="map-container">
        <div className="minecraft-map">No biome found</div>
      </div>
    );
  }
  return (
    <div className="map-container">
      <div className="minecraft-map">
        Found {coordinates.length} biome{coordinates.length > 1 ? 's' : ''}
      </div>
    </div>
  );
};

export default MinecraftMap;