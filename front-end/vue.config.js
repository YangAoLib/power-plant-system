const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  configureWebpack: {
    resolve: {
      fallback: { path: require.resolve('path-browserify') }
    }
  },
  devServer: {
    // 地址与端口
    host: 'localhost',
    port: 8080,
    // 运行成功后自动打开浏览器
    open: true
  }
})
