// bridge to all the buildConfigField set in build.gradle and exported via RNBuildConfig
import { NativeModules } from 'react-native'

const { RNBuildConfig } = NativeModules
export default RNBuildConfig
