import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

export default defineConfig({
  plugins: [vue()],
  server: {
    open: false,
    port: 3004,
    // 设置反向代理，跨域
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:8082/', //后端服务器IP和端口
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      },
    },
  },

})
