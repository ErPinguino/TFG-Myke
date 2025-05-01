import React, { useState, useEffect } from 'react';
import SearchForm from '../SearchForm/SearchForm';
import MinecraftMap from '../MinecraftMap/MinecraftMap';
import CoordinatesList from '../CoordinatesList/CoordinatesList';
import { structureService } from '../Services/Services';
import './StructureSearch.css';

const StructureSearch = () => {
  const [seed, setSeed] = useState('');
  const [selectedStructure, setSelectedStructure] = useState('');
  const [availableStructures, setAvailableStructures] = useState({});
  const [coordinates, setCoordinates] = useState(null);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState(null);

  // Cargar estructuras disponibles al montar el componente
  useEffect(() => {
    loadAvailableStructures();
  }, []);

  const loadAvailableStructures = async () => {
    try {
      const structures = await structureService.getAvailableStructures();
      setAvailableStructures(structures);
    } catch (err) {
      setError('Error loading available structures');
    }
  };

  
  const handleGenerate = async () => {
    if (!seed || !selectedStructure) {
      setError('Please enter a seed and select a structure');
      return;
    }

    try {
      setLoading(true);
      setError(null);
      
      const searchData = {
        structureName: selectedStructure,
        seed: parseInt(seed),
        x: 0,
        z: 0,
        radius: 2000
      };

      const response = await structureService.findStructure(searchData);
      console.log('Response from server:', response); // Debug log
      
      if (response.success) {
        // Convert single coordinate to array format
        const coordArray = [{
          x: response.coordinates.x,
          z: response.coordinates.z
        }];
        setCoordinates(coordArray);
        setError(null);
      } else {
        setError(response.message);
        setCoordinates(null);
      }
    } catch (err) {
      console.error('Error in handleGenerate:', err); // Debug log
      setError(err.message || 'Error finding structure');
      setCoordinates(null);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="minecraft-container">
      <h1 className="minecraft-title">Minecraft Structure Finder</h1>
      
      {error && (
        <div className="error-message">{error}</div>
      )}

      <SearchForm 
        seed={seed}
        setSeed={setSeed}
        selectedStructure={selectedStructure}
        setSelectedStructure={setSelectedStructure}
        structures={availableStructures}
        onGenerate={handleGenerate}
        disabled={loading}
      />
      
      {loading ? (
        <div className="loading">Searching structures...</div>
      ) : (
        <>
          {coordinates && coordinates.length > 0 && (
            <>
              <MinecraftMap coordinates={coordinates} />
              <CoordinatesList coordinates={coordinates} />
            </>
          )}
        </>
      )}
    </div>
  );
};

export default StructureSearch;