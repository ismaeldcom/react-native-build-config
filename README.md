# React Native - Build config access from JS (android only for now)

Module to expose build config variables set in Gradle to your javascript code in React Native.

Inspired from deprecated [luggit/react-native-build-config](https://github.com/luggit/react-native-build-config).

## Install

```shell
npm i ismaeldcom/react-native-build-config
```

#### For React Native < 0.60 use version 0.1.0

## Native Modules are now Autolinked

## Manually link

1. Include this module in `android/settings.gradle`:
  
  ```
  include ':react-native-build-config'
  include ':app'

  project(':react-native-build-config').projectDir = new File(rootProject.projectDir,
    '../node_modules/react-native-build-config/android')
  ```
2. Add a dependency to your app build in `android/app/build.gradle`:
  
  ```
  dependencies {
      ...
      compile project(':react-native-build-config')
  }
  ```
3. Change your main activity to add a new package, in `android/app/src/main/.../MainActivity.java`:
  
  ```java
  import com.ismaeld.RNBuildConfig.RNBuildConfigPackage; // add import

public class MainApplication extends Application implements ReactApplication {

    private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {

        /* ... */

        @Override
        protected List<ReactPackage> getPackages() {
            return Arrays.<ReactPackage>asList(
                    new MainReactPackage(),
                    new RNBuildConfigPackage(BuildConfig.class), // add the package here
            );
        }
    };
  ```


## Usage

Declare config variables in Gradle, under `android/app/build.gradle`:

```
android {
    defaultConfig {
        buildConfigField "String",  "API_URL",     '"https://myapi.com"'
        buildConfigField "Boolean", "SHOW_ERRORS", "true"
        ...
```

Then access those from javascript:

```js
const BuildConfig = require('react-native-build-config');

BuildConfig.API_URL     // "https://myapi.com"
BuildConfig.SHOW_ERRORS // true
```

Gradle sets some variables by default:

- `VERSION_NAME` and `VERSION_CODE`, both coming from the build settings. Keep in mind the code is a number
- `APPLICATION_ID`: Your package name, eg: `com.Example`
- `DEBUG`: set to `true` when running the app locally
- `BUILD_TYPE` and `FLAVOR`: more build settings
