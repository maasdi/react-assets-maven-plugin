package com.github.maasdi.react.assets.builder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RuntimeAssetBuilder implements IAssetBuilder {
    
    private final File assetsDirectory;

    public RuntimeAssetBuilder(File assetsDirectory) {
        this.assetsDirectory = assetsDirectory;
    }

    @Override
    public String build(List<String> assets) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append("<script>");
        for (String asset : assets) {
            String runtimeAsset = new String(
                    Files.readAllBytes(Paths.get(assetsDirectory.getPath() + File.separator + asset)), "utf-8");
            builder.append(runtimeAsset.substring(0,
                    runtimeAsset.lastIndexOf(";") > 0 ? runtimeAsset.lastIndexOf(";") : runtimeAsset.length()));
        }
        builder.append("</script>");
        return builder.toString();
    }

}
