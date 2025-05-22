import React, { useState, useEffect, useContext } from 'react';
import SearchForm        from '../SearchForm/SearchForm';
import CoordinatesList   from '../CoordinatesList/CoordinatesList';
import { structureService, seedService } from '../Services/Services';
import { AuthContext }   from '../../Contexts/AuthContext';
import './StructureSearch.css';

export default function StructureSearch() {
  const { isAuthenticated, isGuest, logout } = useContext(AuthContext);

  const [seed, setSeed]                   = useState('');
  const [structureName, setStructureName] = useState('');
  const [x, setX]                         = useState('');
  const [z, setZ]                         = useState('');
  const [radius, setRadius]               = useState('');
  const [count, setCount]                 = useState('5');
  const [availableStructures, setAvailableStructures] = useState([]);
  const [loadingTypes, setLoadingTypes]   = useState(true);
  const [searchLoading, setSearchLoading] = useState(false);
  const [coordinates, setCoordinates]     = useState([]);
  const [error, setError]                 = useState(null);

  const [seeds, setSeeds]         = useState([]);
  const [showSeeds, setShowSeeds] = useState(false);

  // limpiar seeds al cambiar usuario/invitado
  useEffect(() => {
    setSeeds([]);
    setShowSeeds(false);
  }, [isAuthenticated, isGuest]);

  // cargar tipos de estructuras
  useEffect(() => {
    structureService.getAvailableStructures()
        .then(setAvailableStructures)
        .catch(() => console.warn('No pude cargar tipos'))
        .finally(() => setLoadingTypes(false));
  }, []);

  const handleGenerate = async () => {
    // ahora sólo validamos seed y estructura
    if (!seed || !structureName) {
      setError('Introduce seed y estructura');
      return;
    }

    setError(null);
    setSearchLoading(true);
    try {
      // aplica defaults si están vacíos
      const xi     = x      !== '' ? Number(x)      : 0;
      const zi     = z      !== '' ? Number(z)      : 0;
      const r      = radius !== '' ? Number(radius) : 1500;

      const input = {
        seed: seed.trim(),
        structureName,
        x: xi,
        z: zi,
        radius: r,
        count: Number(count),
      };

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

  const handleSaveSeed = async () => {
    if (!seed) return;
    let name = window.prompt('¿Quieres ponerle un nombre a esta seed?', '');
    if (name === null) return; // canceló
    name = name.trim() === '' ? seed : name.trim();

    try {
      const saved = await seedService.add({ seedValue: seed, name });
      setSeeds(prev => [...prev, saved]);
      if (!showSeeds) setShowSeeds(true);
    } catch (e) {
      console.error('Error guardando seed', e);
    }
  };

  const toggleShowSeeds = async () => {
    if (!showSeeds) {
      try {
        const list = await seedService.list();
        setSeeds(list);
      } catch (e) {
        console.error('Error listando seeds', e);
      }
    }
    setShowSeeds(v => !v);
  };

  const handleDeleteSeed = async id => {
    try {
      await seedService.remove(id);
      setSeeds(prev => prev.filter(s => s.id !== id));
    } catch (e) {
      console.error('Error borrando seed', e);
    }
  };

  if (loadingTypes) return <div className="loading">Cargando tipos…</div>;

  return (
      <div className="minecraft-container">
        {(isAuthenticated || isGuest) && (
            <div className="auth-controls">
              <button onClick={logout}>Cerrar sesión</button>
            </div>
        )}

        <h1 className="minecraft-title">Structure Finder</h1>
        {error && <div className="error-message">{error}</div>}

        {isAuthenticated && (
            <div className="seed-controls">
              <button onClick={handleSaveSeed}>Guardar seed</button>
              <button onClick={toggleShowSeeds}>
                {showSeeds ? 'Ocultar mis seeds' : 'Mostrar mis seeds'}
              </button>
            </div>
        )}

        {isAuthenticated && showSeeds && (
            <ul className="seed-list">
              {seeds.length > 0 ? seeds.map(s => (
                  <li key={s.id}>
              <span
                  className="seed-item"
                  onClick={() => setSeed(s.seed_value)}
              >
                {s.name && s.name.trim() !== '' ? s.name : s.seed_value}
              </span>
                    <button
                        className="seed-delete"
                        onClick={() => handleDeleteSeed(s.id)}
                    >✕</button>
                  </li>
              )) : (
                  <li style={{ padding: '.5rem', fontStyle: 'italic' }}>
                    No tienes seeds guardadas.
                  </li>
              )}
            </ul>
        )}

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
  );
}
