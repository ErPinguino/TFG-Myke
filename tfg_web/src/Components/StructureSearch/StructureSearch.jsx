// src/Components/StructureSearch/StructureSearch.jsx
import React, { useState, useEffect } from 'react';
import SearchForm from '../SearchForm/SearchForm';
import CoordinatesList from '../CoordinatesList/CoordinatesList';
import BlueMapViewer from '../BlueMapViewer/BlueMapViewer';
import { structureService } from '../Services/Services';
import './StructureSearch.css';

export default function StructureSearch() {
  const [seed, setSeed] = useState('');
  const [structureName, setStructureName] = useState('');
  const [x, setX] = useState('');
  const [z, setZ] = useState('');
  const [radius, setRadius] = useState('');
  const [count, setCount] = useState('5');
  const [availableStructures, setAvailableStructures] = useState([]);
  const [loadingTypes, setLoadingTypes] = useState(true);
  const [searchLoading, setSearchLoading] = useState(false);
  const [coordinates, setCoordinates] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    structureService.getAvailableStructures()
        .then(types => setAvailableStructures(types))
        .catch(() => console.warn('No pude cargar tipos'))
        .finally(() => setLoadingTypes(false));
  }, []);

  const handleGenerate = async () => {
    if (!seed || !structureName || !x || !z || !radius) {
      setError('Introduce seed, estructura, coordenadas X/Z y radio');
      return;
    }
    setError(null);
    setSearchLoading(true);

    try {
      const input = { seed: seed.trim(), structureName, x: Number(x), z: Number(z), radius: Number(radius), count: Number(count) };
      const res = await structureService.findStructures(input);
      if (res.found) {
        setCoordinates(res.coordinates);
      } else {
        setError(res.message);
        setCoordinates([]);
      }
    } catch (e) {
      setError(e.toString());
      setCoordinates([]);
    } finally {
      setSearchLoading(false);
    }
  };

  if (loadingTypes) return <div className="loading">Cargando tipos…</div>;

  return (
      <div className="viewer-container">
        {/* PANEL IZQUIERDO: Formulario + lista de coords */}
        <div className="minecraft-container">
          <h1 className="minecraft-title">Structure Finder</h1>
          {error && <div className="error-message">{error}</div>}
          <SearchForm
              seed={seed} setSeed={setSeed}
              x={x} setX={setX}
              z={z} setZ={setZ}
              radius={radius} setRadius={setRadius}
              count={count} setCount={setCount}
              option={structureName} setOption={setStructureName}
              options={availableStructures} label="Structure"
              onGenerate={handleGenerate} disabled={searchLoading}
          />
          {searchLoading && <div className="loading">Buscando…</div>}
          {!searchLoading && coordinates.length > 0 && (
              <CoordinatesList coordinates={coordinates} />
          )}
        </div>

        {/* PANEL DERECHO: ÚNICO visor de BlueMap */}
        <div className="bluemap-wrapper">
          <BlueMapViewer />
        </div>
      </div>
  );
}
