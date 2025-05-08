import axios from 'axios';
axios.defaults.withCredentials = true;

const authApi      = axios.create({ baseURL: 'http://localhost:8081/minecraftProject/auth' });
const seedsApi     = axios.create({ baseURL: 'http://localhost:8081/minecraftProject/seeds' });
const structureApi = axios.create({ baseURL: 'http://localhost:8081/minecraftProject/structures' });

export const authService = {
    register: ({ username, password }) =>
        authApi.post('/register', { username, password }),

    login: async ({ username, password }) => {
        const res = await authApi.post('/login', { username, password });
        // el back devuelve { message: "Login exitoso" } ó { message: "Credenciales inválidas" }
        return res.data.message;
    },
};

export const seedService = {
    list: ()   => seedsApi.get('/').then(r => r.data),
    add: s     => seedsApi.post('/', { seed_value: s }).then(r => r.data),
    remove: id => seedsApi.delete(`/${id}`),
};

export const structureService = {
    getAvailableStructures: () => structureApi.get('/types').then(r => r.data),
    findStructures: input       => structureApi.post('/search', input).then(r => r.data),
};
