package com.github.maasdi.react.asset.builder;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.github.maasdi.react.assets.builder.CssAssetBuilder;
import com.github.maasdi.react.assets.builder.IAssetBuilder;
import com.github.maasdi.react.assets.builder.JsAssetBuilder;

public class JsAssetBuilderTest {

    @Test
    public void shouldBuildCssCorrectly() throws Exception {
        List<String> assets = Arrays.asList("/static/js/test.js");
        IAssetBuilder assetBuilder = new JsAssetBuilder();
        
        Assert.assertEquals("<script src=\"/static/js/test.js\"></script>", assetBuilder.build(assets));
    }
    
    @Test
    public void shouldBuildMultipleCssCorrectly() throws Exception {
        List<String> assets = Arrays.asList("/static/js/3039.js", "/static/js/2000.js");
        IAssetBuilder assetBuilder = new CssAssetBuilder();

        Assert.assertEquals(
                "<link href=\"/static/js/3039.js\" rel=\"stylesheet\">\n" + 
                "<link href=\"/static/js/2000.js\" rel=\"stylesheet\">",
                assetBuilder.build(assets));
    }
}
