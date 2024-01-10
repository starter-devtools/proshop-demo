import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

const API_DETAILS = {
  target: "http://localhost:8080/proshop",
  changeOrigin: true
} 

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [react()],
  server: {
    proxy : {
      "/api" : API_DETAILS,
      "/auth": API_DETAILS
    }
  }
})
