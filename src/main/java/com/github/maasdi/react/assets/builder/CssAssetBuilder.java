package com.github.maasdi.react.assets.builder;

import java.util.List;
import java.util.StringJoiner;

public class CssAssetBuilder implements IAssetBuilder {
    
    private final String publicUrl;

    public CssAssetBuilder(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    @Override
    public String build(List<String> assets) {
        StringJoiner joiner = new StringJoiner("\n");
        
        for (String asset : assets) {
            StringBuilder builder = new StringBuilder("<link href=\"");
            builder.append(publicUrl);
            builder.append(asset);
            builder.append("\" rel=\"stylesheet\">");
            joiner.add(builder);
        }
        return joiner.toString();
    }

}
