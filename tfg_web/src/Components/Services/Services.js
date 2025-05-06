import axios from 'axios';

// Apunta al mismo mapping que tu controlador Spring Boot:
const API_BASE = '/minecraftProject/structures';

export const structureService = {
  // GET /minecraftProject/structures/types
  getAvailableStructures: () =>
      axios
          .get(`${API_BASE}/types`)
          .then(res => res.data),

  // POST /minecraftProject/structures/search
  findStructures: input =>
      axios
          .post(`${API_BASE}/search`, input)
          .then(res => res.data),
};
