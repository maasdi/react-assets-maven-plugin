package com.github.maasdi.react.asset.builder;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.maasdi.react.assets.builder.IAssetBuilder;
import com.github.maasdi.react.assets.builder.RuntimeAssetBuilder;

public class RuntimeAssetBuilderTest {

    @Test
    public void shouldBuildCssCorrectly() throws Exception {
        List<String> assets = Arrays.asList("/static/js/runtime-main.test.js");
        
        String path = "src/test/resources";
        
        File assetsDirectory = new File(path);
        
        IAssetBuilder assetBuilder = new RuntimeAssetBuilder(assetsDirectory);
        
        Assert.assertEquals("<script>function(){console.log('this is runtime test')}</script>", assetBuilder.build(assets));
    }
    
}
