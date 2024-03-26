
const webpack = require("webpack");

module.exports = {
  lintOnSave: false,

  devServer: {
    //proxy: "http://192.168.178.73:8080/",
   // proxy: "http://localhost:8080/",
    proxy: "http://192.168.1.251:8080/",
    port: 3000
  },
  publicPath: "/gestrest/",
  configureWebpack: {
    devtool: "source-map",
    resolve: {
      extensions: [".js"],
      alias: {
        jquery: "jquery/dist/jquery.js"
      }
    },
    plugins: [
      new webpack.ProvidePlugin({
        $: "jquery",
        jQuery: "jquery",
        jquery: "jquery",
        'window.jQuery': "jquery",
        Popper: ["popper.js", "default"],
        Util: "exports-loader?Util!bootstrap/js/dist/util"
      })
    ]
  },
  runtimeCompiler: true
};
