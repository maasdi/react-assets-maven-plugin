React Assets Maven Plugin
=======================
[![Build Status](https://travis-ci.org/maasdi/react-assets-maven-plugin.svg?branch=master)](https://travis-ci.org/maasdi/react-assets-maven-plugin/builds)

This Maven plugin lets you process your server render view template to link with react build assets.

## Objectives
Build ReactJS app with server rendering feature on top of java framework sometimes very messy,
because compiled files naming are dynamically generated.

Often we come up with solution to rename the compiled file to static predeine naming, but this
can easily break the code spliting or can caused something unexpected because of server cache.

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
    <meta name="theme-color" content="#000000" />
    <meta name="description" content="Presto" />
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
                <version>1.0.0-RELEASE</version>
                <executions>
                    <execution>
                        <configuration>
							<assetsDirectory>${project.basedir}/src/main/resources/public</assetsDirectory>
                            <resources>
                                <resources>
                                    <file>${project.basedir}/src/main/resources/templates/app.html</file>
                                    <outputDir>${project.basedir}/target/classes/templates</outputDir>
                                </resources>
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
* `resources` - The resources to process, possibly jsp, fremarker template or any html file.
* `manifestName` - The manifest file name, default value is `asset-manifest.json`.
* `cssMarkup` - The css markup that will be replace during resource is processed. default value is `<ReactAssetCss/>`.
* `jsMarkup` - The javascript markup that will be replace during resource is processed. default value is `<ReactAssetJs/>`.
* `runtimeMarkup` - The main-runtime markup that will be replace during resource is processed. default value is `<ReactAssetRuntime/>`.

## Licenses:
* Apache-2.0: http://www.apache.org/licenses/LICENSE-2.0