import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'
import fs from 'fs'
import path from 'path'
import { fileURLToPath } from 'url'

const __filename = fileURLToPath(import.meta.url)
const __dirname  = path.dirname(__filename)

export default defineConfig({
  plugins: [react()],
  server: {
    https: {
      pfx: fs.readFileSync(path.resolve(__dirname, 'keystore.p12')),
      passphrase: 'miPasswordSeguro'
    },
    port: 5176,   // coincide con https://localhost:5176
    proxy: {
      '/minecraftProject': {
        target: 'http://localhost:8081',  // ← cambia a HTTP:8081
        changeOrigin: true,
        secure: false,

        // 1) reescritura nativa de dominio y ruta
        cookieDomainRewrite: '',   // elimina el Domain=backend
        cookiePathRewrite:   '/',  // fuerza Path=/

        // 2) por si quieres reforzar SameSite/Secure en las cookies:
        configure: proxy => {
          proxy.on('proxyRes', (proxyRes, req, res) => {
            const setCookie = proxyRes.headers['set-cookie']
            if (!setCookie) return

            const rewritten = setCookie.map(cookie =>
                cookie
                    .replace(/; ?Domain=[^;]+/i, '')    // borra Domain original
                    .replace(/; ?SameSite=[^;]+/i, '')  // borra SameSite original
                + '; SameSite=None; Secure'          // añade SameSite=None; Secure
            )
            res.setHeader('Set-Cookie', rewritten)
          })
        }
      }
    }
  }
})
