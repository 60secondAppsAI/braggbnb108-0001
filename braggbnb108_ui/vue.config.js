module.exports = {
  chainWebpack: config => {
    config
    .plugin('html')
    .tap(args => {
      args[0].title = 'braggbnb108'
      return args
    })
  }
}
//const { defineConfig } = require("@vue/cli-service");    
//module.exports = defineConfig({
//  transpileDependencies: true,
//});
