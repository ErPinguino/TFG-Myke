import React, { useState } from 'react';
import BiomeSearch from './Components/BiomeSearch/BiomeSearch';
import StructureSearch from './Components/StructureSearch/StructureSearch';
import './App.css';

export default function App() {
  const [mode, setMode] = useState('biome');
  return (
    <div className="App">
      <div className="mode-switcher">
        <button onClick={() => setMode('biome')}
                className={mode === 'biome' ? 'active' : ''}>
          Find Biome
        </button>
        <button onClick={() => setMode('structure')}
                className={mode === 'structure' ? 'active' : ''}>
          Find Structure
        </button>
      </div>
      {mode === 'biome' ? <BiomeSearch /> : <StructureSearch />}
    </div>
  );
}
