# React Native - Build config access from JS

Module to expose build config variables set in Gradle to your javascript code in React Native.

iOS version greatly contributed by [maddijoyce](https://github.com/maddijoyce)

## Install

```shell
npm i react-native-build-config
```

:warning: For React Native < 0.60, use version 0.1.0 and use manual linking (see below)		

## Usage

Declare config variables in Gradle, under `android/app/build.gradle`:

```
android {
    defaultConfig {
        buildConfigField "String",  "API_URL",     '"https://myapi.com"'
        buildConfigField "Boolean", "SHOW_ERRORS", "true"
        ...
```

Or declare them in your info.plist file in your ios project.

```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE plist PUBLIC "-//Apple//DTD PLIST 1.0//EN" "http://www.apple.com/DTDs/PropertyList-1.0.dtd">
<plist version="1.0">
	<dict>
  <key>API_URL</key>
	<string>https://myapi.com</string>
  <key>SHOW_ERRORS</key>
  <true />
  ...
```

Then access those from javascript:

```js
const BuildConfig = require('react-native-build-config')

BuildConfig.API_URL // "https://myapi.com"
BuildConfig.SHOW_ERRORS // true
```

Gradle sets some variables by default:

- `VERSION_NAME` and `VERSION_CODE`, both coming from the build settings. Keep in mind the code is a number
- `APPLICATION_ID`: Your package name, eg: `com.Example`
- `DEBUG`: set to `true` when running the app locally
- `BUILD_TYPE` and `FLAVOR`: more build settings

## ProGuard/R8

ProGuard/R8 by default removes `BuildConfig` class from release Android build.
JavaScript `BuildConfig` object will be `undefined`.

To keep it add the following line to `proguard-rules.pro` file

```
-keep class **.BuildConfig { *; }
```

You can replace `**` with `your.package.name`.

## Linking on React Native < 0.60

Native modules are auto-linked since v0.60. If you have a lower version, you need this:

1.  Include this module in `android/settings.gradle`:

```
include ':react-native-build-config'
include ':app'

project(':react-native-build-config').projectDir = new File(rootProject.projectDir,
  '../node_modules/react-native-build-config/android')
```

2.  Add a dependency to your app build in `android/app/build.gradle`:

```
dependencies {
    ...
    compile project(':react-native-build-config')
}
```

3.  Change your main activity to add a new package, in `android/app/src/main/.../MainActivity.java`:

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
