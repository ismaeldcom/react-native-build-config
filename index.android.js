// bridge to all the buildConfigField set in build.gradle and exported via RNBuildConfig
const {NativeModules} = require('react-native')
module.exports = NativeModules.RNBuildConfig
