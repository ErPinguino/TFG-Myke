import React from 'react';
import './CoordinatesList.css';

const CoordinatesList = ({ coordinates }) => {
  if (!coordinates || !Array.isArray(coordinates) || coordinates.length === 0) {
    return (
      <div className="coordinates-list">
        <h3>Found Biome Coordinates:</h3>
        <p>No biome found</p>
      </div>
    );
  }
  return (
    <div className="coordinates-list">
      <h3>Found Biome Coordinates:</h3>
      <ul>
        {coordinates.map((coord, index) => (
          <li key={index}>
            X: {coord.x}, Z: {coord.z}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CoordinatesList;