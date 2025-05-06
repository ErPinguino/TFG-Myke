// src/Services/Services.js
import axios from 'axios';

const API_BASE = '/minecraftProject/structures';

export const structureService = {
  getAvailableStructures: () =>
      axios.get(`${API_BASE}/types`).then(res => res.data),

  findStructures: (input) =>
      // input.seed es STRING, x,z,radius,count números
      axios.post(`${API_BASE}/search`, input).then(res => res.data),
};
