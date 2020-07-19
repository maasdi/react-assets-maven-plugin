React Assets Maven Plugin
=======================
[![Build Status](https://travis-ci.org/maasdi/react-assets-maven-plugin.svg?branch=master)](https://travis-ci.org/maasdi/react-assets-maven-plugin/builds)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.github.maasdi/react-assets-maven-plugin/badge.svg?style=flat&logo=appveyor)](https://maven-badges.herokuapp.com/maven-central/com.github.maasdi/react-assets-maven-plugin)

This Maven plugin help you sync view template (JSP, FreeMarker, Thymeleaf) and react build assets during build process.

## Objectives
Build React + Spring application with server rendering view sometimes very messy, 
because `npm run build` generate files with hash, every time code change the hash will change.

Often we come up with workaround by renaming the files to static file name, 
but this can easily break code splitting and lose potential advantages for production [read here](https://create-react-app.dev/docs/production-build/).

This plugin will let you sync those assets and your server rendered view during maven `process-resources` phase.

## Requirements
* Maven 3 and Java 1.8

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
  <version>1.0.1</version>
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
* `cssMarkup` - The CSS markup that will be replace (case sensitive). default value is `<ReactAssetCss/>`.
* `jsMarkup` - The Javascript markup that will be replace (case sensitive). default value is `<ReactAssetJs/>`.
* `runtimeMarkup` - The main-runtime markup that will be replace (case sensitive). default value is `<ReactAssetRuntime/>`.
* `publicUrl` - The react asset `PUBLIC_URL`, default value is `/`
* `inlineRuntimeChunk` - By default runtime-asset will be embed, set this parameter to `false` which will load chunk instead of embedding it in your view.

## Sample

There is sample of Spring Boot + React [here](https://github.com/maasdi/react-assets-maven-plugin-sample)

## Licenses:
* Apache-2.0: http://www.apache.org/licenses/LICENSE-2.0