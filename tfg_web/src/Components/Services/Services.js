import axios from 'axios';

axios.defaults.withCredentials = true;


const BASE_URL = '/minecraftProject';

const authApi      = axios.create({ baseURL: `${BASE_URL}/auth`, withCredentials: true });
const seedsApi     = axios.create({ baseURL: `${BASE_URL}/seeds`, withCredentials: true });
const structureApi = axios.create({ baseURL: `${BASE_URL}/structures`, withCredentials: true });

export const authService = {
    register: ({ username, password }) =>
        authApi.post('/register', { username, password })
            .then(res => res.data),

    login: async ({ username, password }) => {
        const res = await authApi.post('/login', { username, password });
        return res.data.message;
    },

    logout: () =>
        authApi.post('/logout'),
};

export const seedService = {
    list: () =>
        seedsApi.get('/').then(res => res.data),

    add: seedValue =>
        seedsApi.post('/', { seed_value: seedValue })
            .then(res => res.data),

    remove: id =>
        seedsApi.delete(`/${id}`),
};

export const structureService = {
    getAvailableStructures: () =>
        structureApi.get('/types').then(res => res.data),

    findStructures: input =>
        structureApi.post('/search', input).then(res => res.data),
};
