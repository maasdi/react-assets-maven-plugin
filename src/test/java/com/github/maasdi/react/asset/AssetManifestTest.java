package com.github.maasdi.react.asset;

import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import com.github.maasdi.react.assets.AssetManifest;
import com.google.gson.Gson;

public class AssetManifestTest {

    @Test
    public void shouldParseCorrectly() throws Exception {
        final String path = "src/test/resources";
        final File assetsDirectory = new File(path);
        final String manifestName = "test-asset-manifest.json";
        
        AssetManifest manifest = new Gson().fromJson(
                new FileReader(assetsDirectory.getPath() + File.separator + manifestName), AssetManifest.class);
        
        Assert.assertFalse(manifest.getEntrypoints().isEmpty());
        Assert.assertEquals(Arrays.asList("static/js/runtime-main.test.js"), manifest.getRuntime());
        Assert.assertEquals(Arrays.asList("static/js/2.test.chunk.js", "static/js/main.test.chunk.js"), manifest.getScripts());
        Assert.assertEquals(Arrays.asList("static/css/2.test.chunk.css"), manifest.getStyles());
    }
}
