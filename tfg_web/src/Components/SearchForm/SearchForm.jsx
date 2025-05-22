import React from 'react';
import './SearchForm.css';

export default function SearchForm({
                                       seed, setSeed,
                                       x, setX,
                                       z, setZ,
                                       radius, setRadius,
                                       count, setCount,
                                       option, setOption,
                                       options = [],
                                       label,
                                       onGenerate,
                                       disabled
                                   }) {
    return (
        <div className="input-container">
            <div className="input-field">
                <label>World Seed:</label>
                <input
                    type="text"
                    value={seed}
                    onChange={e => setSeed(e.target.value)}
                    className="minecraft-input"
                />
            </div>

            <div className="input-field">
                <label>{label}:</label>
                <select
                    value={option}
                    onChange={e => setOption(e.target.value)}
                    className="minecraft-input"
                >
                    <option value="" disabled>Choose {label.toLowerCase()}…</option>
                    {Array.isArray(options) && options.map(o => (
                        <option key={o} value={o}>
                            {o.replace(/_/g, ' ')}
                        </option>
                    ))}
                </select>
            </div>

            <div className="input-field">
                <label>X coordinate:</label>
                <input
                    type="number"
                    value={x}
                    onChange={e => setX(e.target.value)}
                    placeholder="0"
                    className="minecraft-input"
                />
            </div>

            <div className="input-field">
                <label>Z coordinate:</label>
                <input
                    type="number"
                    value={z}
                    onChange={e => setZ(e.target.value)}
                    placeholder="0"
                    className="minecraft-input"
                />
            </div>

            <div className="input-field">
                <label>Radius (blocks):</label>
                <input
                    type="number"
                    value={radius}
                    onChange={e => setRadius(e.target.value)}
                    placeholder="1500"
                    className="minecraft-input"
                />
            </div>

            <div className="input-field">
                <label>Count:</label>
                <input
                    type="number"
                    value={count}
                    onChange={e => setCount(e.target.value)}
                    className="minecraft-input"
                />
            </div>

            <button
                onClick={onGenerate}
                // dejamos sólo seed y opción como obligatorios
                disabled={!seed || !option || disabled}
                className="minecraft-button"
            >
                Find {label}
            </button>
        </div>
    );
}
