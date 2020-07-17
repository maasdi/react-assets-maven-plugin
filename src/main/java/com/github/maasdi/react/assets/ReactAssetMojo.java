package com.github.maasdi.react.assets;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.github.maasdi.react.assets.builder.CssAssetBuilder;
import com.github.maasdi.react.assets.builder.JsAssetBuilder;
import com.github.maasdi.react.assets.builder.RuntimeAssetBuilder;
import com.github.maasdi.react.assets.processor.IAssetProcessor;
import com.github.maasdi.react.assets.processor.SimpleAssetProcessor;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;
import java.util.List;

/**
 * Provide goal <code>react-asset-maven-plugin:process-asset</code>.
 * 
 * Will process given resources
 */
@Mojo(name = "process-asset", defaultPhase = LifecyclePhase.PROCESS_RESOURCES)
public class ReactAssetMojo extends AbstractMojo {

    private Gson gson = new Gson();

    @Parameter(required = false, defaultValue = "asset-manifest.json", readonly = true)
    private String manifestName;

    @Parameter(required = true, readonly = true)
    private List<ReactAssetsResource> resources;

    @Parameter(required = true, readonly = true)
    private File assetsDirectory;
    
    @Parameter(required = false, defaultValue = "<ReactAssetCss/>", readonly = true)
    private String cssMarkup;
    
    @Parameter(required = false, defaultValue = "<ReactAssetJs/>", readonly = true)
    private String jsMarkup;
    
    @Parameter(required = false, defaultValue = "<ReactAssetRuntime/>", readonly = true)
    private String runtimeMarkup;

    private IAssetProcessor processor = new SimpleAssetProcessor();

    public void execute() throws MojoExecutionException {
        try {
            getLog().info("Start processing " + manifestName);
            AssetManifest manifest = gson.fromJson(
                    new FileReader(assetsDirectory.getPath() + File.separator + manifestName), AssetManifest.class);
            
            AssetContex ctx = new AssetContex();
            ctx.setLog(getLog());
            ctx.setResources(resources);
            ctx.setAssetsDirectory(assetsDirectory);
            ctx.setCssMarkup(cssMarkup);
            ctx.setJsMarkup(jsMarkup);
            ctx.setRuntimeMarkup(runtimeMarkup);
            ctx.setCssAsset(new CssAssetBuilder().build(manifest.getStyles()));
            ctx.setRuntimeAsset(new RuntimeAssetBuilder(ctx.getAssetsDirectory()).build(manifest.getRuntime()));
            ctx.setJsAsset(new JsAssetBuilder().build(manifest.getScripts()));
            
            processor.process(manifest, ctx);

            getLog().info("End processing " + manifestName);
        } catch (final Exception e) {
            getLog().error("Error to process " + manifestName, e);
            throw new MojoExecutionException("Error to process resources", e);
        }
    }
}
