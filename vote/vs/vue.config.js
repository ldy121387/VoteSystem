const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  //跨域
  devServer :{
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
        changeOrigin:true,
      }
    }
  }
})
