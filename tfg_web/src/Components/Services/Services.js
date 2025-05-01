import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/minecraftProject',
  timeout: 5000,
  headers: {
    'Content-Type': 'application/json'
  }
});

export const structureService = {
  getAvailableStructures: async () => {
    try {
      const response = await api.get('/structures/available');
      console.log('Available structures response:', response.data);
      return response.data;
    } catch (error) {
      console.error('Error fetching structures:', error);
      throw new Error(error.response?.data || 'Error al obtener estructuras disponibles');
    }
  },

  findStructure: async (searchData) => {
    try {
      console.log('Sending search request:', searchData);
      
      // Convert structure name to lowercase to match backend expectations
      const formattedData = {
        structureName: searchData.structureName.toLowerCase(),
        seed: typeof searchData.seed === 'string' ? parseInt(searchData.seed) : searchData.seed,
        x: searchData.x || 0,
        z: searchData.z || 0,
        radius: searchData.radius || 2000
      };

      console.log('Formatted request data:', formattedData);
      
      const response = await api.post('/structures/find', formattedData);
      console.log('Structure search response:', response.data);
      
      if (response.data.success) {
        return {
          success: true,
          message: response.data.message,
          coordinates: response.data.coordinates
        };
      } else {
        throw new Error(response.data.message);
      }
    } catch (error) {
      console.error('Error searching structure:', error);
      throw new Error(error.response?.data || 'Error al buscar estructura');
    }
  }
};

export default api;