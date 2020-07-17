package com.github.maasdi.react.asset.builder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.maasdi.react.assets.builder.CssAssetBuilder;
import com.github.maasdi.react.assets.builder.IAssetBuilder;

public class CssAssetBuilderTest {

    @Test
    public void shouldBuildCssCorrectly() throws Exception {
        List<String> assets = Arrays.asList("/static/css/test.css");
        IAssetBuilder assetBuilder = new CssAssetBuilder();
        
        Assert.assertEquals("<link href=\"/static/css/test.css\" rel=\"stylesheet\">", assetBuilder.build(assets));
    }
    
    @Test
    public void shouldBuildMultipleCssCorrectly() throws Exception {
        List<String> assets = Arrays.asList("/static/css/css1.css", "/static/css/css2.css");
        IAssetBuilder assetBuilder = new CssAssetBuilder();

        Assert.assertEquals(
                "<link href=\"/static/css/css1.css\" rel=\"stylesheet\">\n<link href=\"/static/css/css2.css\" rel=\"stylesheet\">",
                assetBuilder.build(assets));
    }
}
