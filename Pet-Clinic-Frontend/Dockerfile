# Stage 1: Build the React app
FROM node:lts as builder
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
ARG API_BACKEND_URL
ENV VITE_BACKEND_URL=${API_BACKEND_URL}
RUN npm run build
# Stage 2: Create the production image
FROM nginx:latest
COPY --from=builder /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]