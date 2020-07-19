package com.github.maasdi.react.asset.processor;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.maasdi.react.assets.AssetContex;
import com.github.maasdi.react.assets.AssetManifest;
import com.github.maasdi.react.assets.ReactAssetsResource;
import com.github.maasdi.react.assets.builder.CssAssetBuilder;
import com.github.maasdi.react.assets.builder.JsAssetBuilder;
import com.github.maasdi.react.assets.builder.RuntimeAssetBuilder;
import com.github.maasdi.react.assets.processor.IAssetProcessor;
import com.github.maasdi.react.assets.processor.SimpleAssetProcessor;
import com.google.gson.Gson;

public class SimpleAssetProcessorTest {

    @Test
    public void shouldProcessCorrectly() throws Exception {
        final String path = "src/test/resources";
        final File assetsDirectory = new File(path);
        final String manifestName = "test-asset-manifest.json";
        final String filePath = path + File.separator + "server.html";
        final String outputDir = path + File.separator + "output";
        final String outputPath = outputDir + File.separator + "server.html";
        
        // Make sure file exists
        File outFile = new File(outputPath);
        if (!outFile.exists()) {
            outFile.createNewFile();
        }
        
        AssetManifest manifest = new Gson().fromJson(
                new FileReader(assetsDirectory.getPath() + File.separator + manifestName), AssetManifest.class);
        
        List<ReactAssetsResource> resources = new ArrayList<ReactAssetsResource>();
        ReactAssetsResource resource = new ReactAssetsResource();
        resource.setFile(new File(filePath));
        resource.setOutputDir(new File(outputDir));
        resources.add(resource);
        
        AssetContex ctx = new AssetContex();
        ctx.setResources(resources);
        ctx.setAssetsDirectory(assetsDirectory);
        ctx.setCssMarkup("<ReactAssetCss/>");
        ctx.setJsMarkup("<ReactAssetJs/>");
        ctx.setRuntimeMarkup("<ReactAssetRuntime/>");
        ctx.setCssAsset(new CssAssetBuilder("/").build(manifest.getStyles()));
        ctx.setRuntimeAsset(new RuntimeAssetBuilder(ctx.getAssetsDirectory(), "/", true).build(manifest.getRuntime()));
        ctx.setJsAsset(new JsAssetBuilder("/").build(manifest.getScripts()));
        
        IAssetProcessor processor = new SimpleAssetProcessor();
        processor.process(manifest, ctx);
        
        String outputFile = new String(
                Files.readAllBytes(Paths.get(outputPath)), "utf-8");
        
        Assert.assertFalse(outputFile.contains("<ReactAssetCss/>"));
        Assert.assertFalse(outputFile.contains("<ReactAssetJs/>"));
        Assert.assertFalse(outputFile.contains("<ReactAssetRuntime/>"));
        Assert.assertTrue(outputFile.contains("static/css/2.test.chunk.css"));
    }
}
