// src/Components/Services/Services.js
import axios from 'axios';

axios.defaults.withCredentials = true;

const BASE_URL      = '/minecraftProject';
const authApi       = axios.create({ baseURL: `${BASE_URL}/auth`,      withCredentials: true });
const seedsApi      = axios.create({ baseURL: `${BASE_URL}/seeds`,     withCredentials: true });
const structureApi  = axios.create({ baseURL: `${BASE_URL}/structures`, withCredentials: true });

export const authService = {
    register: ({ username, password }) =>
        authApi.post('/register', { username, password }).then(res => res.data),

    login: async ({ username, password }) => {
        const res = await authApi.post('/login', { username, password });
        return res.data.message;
    },

    logout: () =>
        authApi.post('/logout'),
};

export const seedService = {
    // GET /minecraftProject/seeds
    list: () =>
        seedsApi.get('').then(res => res.data),

    // POST /minecraftProject/seeds  { seed_value, name }
    add: ({ seedValue, name }) =>
        seedsApi.post('', {
            seed_value: seedValue,
            name
        }).then(res => res.data),

    // DELETE /minecraftProject/seeds/{id}
    remove: id =>
        seedsApi.delete(`/${id}`),
};

export const structureService = {
    // GET /minecraftProject/structures/types
    getAvailableStructures: () =>
        structureApi.get('/types').then(res => res.data),

    // POST /minecraftProject/structures/search
    findStructures: input =>
        structureApi.post('/search', input).then(res => res.data),
};
