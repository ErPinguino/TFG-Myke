import React from 'react';
import './SearchForm.css'; 

const SearchForm = ({ seed, setSeed, selectedStructure, setSelectedStructure, onGenerate }) => {
  // Mantener sincronizado con CubiomesWrapper.java
  const structures = {
    'Village': 1,
    'Woodland Mansion': 2,
    'Ocean Monument': 3,
    'Nether Fortress': 4,
    'Desert Temple': 5,
    'Stronghold': 6,
    'Mineshaft': 7,
    'End City': 8,
    'Ancient City': 9,
    'End Portal': 10,
    'Pillager Outpost': 11,
    'Bastion Remnant': 12
  };

  return (
    <div className="input-container">
      <div className="input-field">
        <label>World Seed:</label>
        <input 
          type="text" 
          value={seed}
          onChange={(e) => setSeed(e.target.value)}
          className="minecraft-input"
          placeholder="Enter a seed number..."
        />
      </div>
      
      <div className="input-field">
        <label>Select Structure:</label>
        <select 
          value={selectedStructure}
          onChange={(e) => setSelectedStructure(e.target.value)}
          className="minecraft-input"
        >
          <option value="">Select...</option>
          {Object.entries(structures).map(([name, id]) => (
            <option key={id} value={name}>
              {name}
            </option>
          ))}
        </select>
      </div>
      
      <button 
        onClick={onGenerate}
        className="minecraft-button"
        disabled={!seed || !selectedStructure}
      >
        Find Structures
      </button>
    </div>
  );
};

export default SearchForm;