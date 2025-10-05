import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    host: true, // Permite que el servidor sea accesible desde la red
    port: 5173  // El puerto que estamos usando en docker-compose
  }
})
