# Etapa de compilación
FROM node:18-alpine AS build

# Directorio de trabajo
WORKDIR /app

# Copiar package.json y package-lock.json
COPY package*.json ./

# Instalar dependencias
RUN npm ci

# Copiar el resto del código fuente
COPY . .

# Construir la aplicación
RUN npm run build

# Etapa de producción
FROM nginx:alpine

# Copiar la configuración personalizada de nginx si es necesaria
COPY default.conf /etc/nginx/conf.d/default.conf

# Copiar los archivos de compilación desde la etapa anterior
COPY --from=build /app/dist /usr/share/nginx/html
# Exponer el puerto 80
EXPOSE 80

# Comando para iniciar nginx
CMD ["nginx", "-g", "daemon off;"]