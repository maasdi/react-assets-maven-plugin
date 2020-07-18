React Assets Maven Plugin
=======================
[![Build Status](https://travis-ci.org/maasdi/react-assets-maven-plugin.svg?branch=master)](https://travis-ci.org/maasdi/react-assets-maven-plugin/builds)

This Maven plugin help you sync view template (JSP, FreeMarker, Thymeleaf) and react build assets during build process.

## Objectives
Build ReactJS + Spring application with server rendering view sometimes very messy, because `react-scripts build` generated files with random suffix.

Often we come up with solution to rename react build files to static file name, but this
can easily break code splitting and lose cache busting feature.

This plugin will let you sync react build assets and your view by replacing ReactAsset markup during maven `process-resources` phase.


## Usage
Given that you have template `/src/main/resources/templates/app.ftlh`

```html
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <link rel="icon" href="/favicon.ico" />
    <meta name="viewport" content="width=device-width,initial-scale=1" />
    <link rel="manifest" href="/manifest.json" />
    <title>Test Server Render HTML</title>
    <ReactAssetCss/> <!-- will replace by plugin -->
  </head>
  <body>
    <noscript>You need to enable JavaScript to run this app.</noscript>
    <div id="root"></div>
    <ReactAssetRuntime/> <!-- will replace by plugin -->
    <ReactAssetJs/> <!-- will replace by plugin -->
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
             <file>${project.basedir}/src/main/resources/templates/app.ftlh</file>
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
* `resources` - The list of resource to process, possibly JSP, FreeMarker template or Thymeleaf file.
* `manifestName` - The react manifest file name, default value is `asset-manifest.json`.
* `cssMarkup` - The css markup that will be replace (case sensitive). default value is `<ReactAssetCss/>`.
* `jsMarkup` - The javascript markup that will be replace (case sensitive). default value is `<ReactAssetJs/>`.
* `runtimeMarkup` - The main-runtime markup that will be replace (case sensitive). default value is `<ReactAssetRuntime/>`.

## Licenses:
* Apache-2.0: http://www.apache.org/licenses/LICENSE-2.0