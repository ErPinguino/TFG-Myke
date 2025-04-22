import React, { useState } from 'react';
import './ResourceMapGenerator.css';

const ResourceMapGenerator = () => {
  const [seed, setSeed] = useState('');
  const [selectedResource, setSelectedResource] = useState('');
  const [coordinates, setCoordinates] = useState([]);

  const resources = [
    'Diamond Ore',
    'Iron Ore',
    'Gold Ore',
    'Emerald Ore',
    'Ancient Debris'
  ];

  const handleGenerate = () => {
    // TODO: Implement map generation logic
    const mockCoordinates = [
      { x: 64, y: 12, z: -128 },
      { x: -32, y: 8, z: 96 },
    ];
    setCoordinates(mockCoordinates);
  };

  return (
    <div className="minecraft-container">
      <h1 className="minecraft-title">Minecraft Resource Map Generator</h1>
      
      <div className="input-container">
        <div className="input-field">
          <label>World Seed:</label>
          <input 
            type="text" 
            value={seed}
            onChange={(e) => setSeed(e.target.value)}
            className="minecraft-input"
          />
        </div>
        
        <div className="input-field">
          <label>Select Resource:</label>
          <select 
            value={selectedResource}
            onChange={(e) => setSelectedResource(e.target.value)}
            className="minecraft-input"
          >
            <option value="">Select...</option>
            {resources.map(resource => (
              <option key={resource} value={resource}>{resource}</option>
            ))}
          </select>
        </div>
        
        <button 
          onClick={handleGenerate}
          className="minecraft-button"
        >
          Generate Map
        </button>
      </div>

      <div className="map-container">
        {/* Placeholder for the interactive map */}
        <div className="minecraft-map">
          Map Display Area
        </div>
      </div>

      <div className="coordinates-list">
        <h3>Found Resources:</h3>
        <ul>
          {coordinates.map((coord, index) => (
            <li key={index}>
              X: {coord.x}, Y: {coord.y}, Z: {coord.z}
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default ResourceMapGenerator;