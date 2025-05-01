import React from 'react';
import './CoordinatesList.css';

const CoordinatesList = ({ coordinates }) => {
  // Validación de que coordinates sea un array
  if (!coordinates || !Array.isArray(coordinates) || coordinates.length === 0) {
    return (
      <div className="coordinates-list">
        <h3>Found Structures:</h3>
        <p>No structures found</p>
      </div>
    );
  }

  return (
    <div className="coordinates-list">
      <h3>Found Structures:</h3>
      <ul>
        {coordinates.map((coord, index) => (
          <li key={index}>
            X: {coord.x}, Y: {coord.y}, Z: {coord.z}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default CoordinatesList;