#Stage 1: hago el builder para la imagen de node
FROM node:20-alpine AS builder
WORKDIR /app

#cacheo las dependencias copiando los archivos de configuracion primero, asi no pesa tanto
COPY package.json package-lock.json ./

#podria iniciarlo con npm install pero es mejor npm ci porque s mas rapido  y optimo
RUN npm ci

#despues le digo que tiene que copiar el resto del codigo fuente y compilar
COPY . .
RUN npm run build

#Stage 2: runtime es lo que va a produccion
FROM nginx:1.25-alpine AS runtime

#copio los archivos ya complados en el stage 1 builder
COPY --from=builder /app/dist /usr/share/nginx/html

#Aca copeo las configuraciones personalizadas de el archivo ngnix para soportar el enrutamiento
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]