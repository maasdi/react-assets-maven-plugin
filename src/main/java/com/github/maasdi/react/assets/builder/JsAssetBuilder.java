package com.github.maasdi.react.assets.builder;

import java.util.List;
import java.util.StringJoiner;

public class JsAssetBuilder implements IAssetBuilder {

    private final String publicUrl;
    
    public JsAssetBuilder(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    @Override
    public String build(List<String> assets) {
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
