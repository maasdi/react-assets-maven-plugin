React Assets Maven Plugin
=======================
[![Build Status](https://travis-ci.org/maasdi/react-assets-maven-plugin.svg?branch=master)](https://travis-ci.org/maasdi/react-assets-maven-plugin/builds)

This Maven plugin lets you update react build assets in your view template during build process.

## Objectives
Build ReactJS application with server rendering feature sometimes very messy, because compiled files naming are dynamically generated.

Often we come up with solution to rename the compiled file to static predefine naming, but this
can easily break the code splitting or can caused something unexpected because of server cache.

This plugin will let you connecting the react assets and server render view during build time.


## Usage
Given that you have template `/src/main/resources/templates/app.html`
```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <link rel="icon" href="/favicon.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="manifest" href="/manifest.json" />
    <title>Test Server Render HTML</title>
    <ReactAssetCss/> <!-- Will replace by plugin -->
  </head>
  <body>
    <noscript>You need to enable JavaScript to run this app.</noscript>
    <div id="root"></div>
    <ReactAssetRuntime/> <!-- Will replace by plugin -->
    <ReactAssetJs/> <!-- Will replace by plugin -->
  </body>
</html>
```

Then 

```xml
<plugin>
  <groupId>com.github.maasdi</groupId>
  <artifactId>react-assets-maven-plugin</artifactId>
  <version>1.0.0</version>
  <executions>
    <execution>
      <configuration>
        <assetsDirectory>${project.basedir}/src/main/resources/public</assetsDirectory>
         <resources>
           <resource>
             <file>${project.basedir}/src/main/resources/templates/app.html</file>
             <outputDir>${project.basedir}/target/classes/templates</outputDir>
           </resource>
         </resources>
      </configuration>
      <goals>
        <goal>process-asset</goal>
      </goals>
    </execution>
  </executions>
</plugin>
```

## Options
* `assetsDirectory` - The react assets directory, this directory should be react build folder or folder with similar structure.
* `resources` - The resources to process, possibly jsp, FreeMarker template or any html file.
* `manifestName` - The manifest file name, default value is `asset-manifest.json`.
* `cssMarkup` - The css markup that will be replace. default value is `<ReactAssetCss/>`.
* `jsMarkup` - The javascript markup that will be replace. default value is `<ReactAssetJs/>`.
* `runtimeMarkup` - The main-runtime markup that will be replace. default value is `<ReactAssetRuntime/>`.

## Licenses:
* Apache-2.0: http://www.apache.org/licenses/LICENSE-2.0