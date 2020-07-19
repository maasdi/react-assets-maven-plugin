package com.github.maasdi.react.assets.builder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

public class RuntimeAssetBuilder implements IAssetBuilder {
    
    private final File assetsDirectory;
    
    private final String publicUrl;
    
    private final Boolean inlineRuntimeChunk;

    public RuntimeAssetBuilder(File assetsDirectory, String publicUrl, Boolean inlineRuntimeChunk) {
        this.assetsDirectory = assetsDirectory;
        this.publicUrl = publicUrl;
        this.inlineRuntimeChunk = inlineRuntimeChunk;
    }

    @Override
    public String build(List<String> assets) throws Exception {
        if (inlineRuntimeChunk) {
            return this.buildInline(assets);
        } else {
            return this.buildNotInline(assets);
        }
    }
    
    private String buildInline(List<String> assets) throws Exception {
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
    
    private String buildNotInline(List<String> assets) {
        StringJoiner joiner = new StringJoiner("\n");
        for (String asset : assets) {
            StringBuilder builder = new StringBuilder("<script src=\"");
            builder.append(publicUrl);
            builder.append(asset);
            builder.append("\"></script>");
            joiner.add(builder);
        }
        return joiner.toString();
    }

}
