# Use Node.js Alpine base image
FROM node:20-alpine AS build

WORKDIR /app
COPY package.json package-lock.json ./
RUN npm ci
COPY public ./public
COPY src ./src
RUN npm run build

FROM nginx:1.27-alpine
COPY --from=build /app/build /usr/share/nginx/html
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]

# Create and set the working directory inside the container

# Copy package.json and package-lock.json to the working directory

# Install dependencies

# Copy the entire codebase to the working directory

# Expose the port your app runs on (replace <PORT_NUMBER> with your app's actual port)

# Define the command to start your application (replace "start" with the actual command to start your app)

