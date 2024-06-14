const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave:false,
  //跨域
  devServer :{
    proxy: {
      '/api': {
        target: 'http://110.41.56.14:8080',
        changeOrigin:true,
      }
    }
  }
})
