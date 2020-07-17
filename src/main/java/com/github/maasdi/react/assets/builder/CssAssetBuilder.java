package com.github.maasdi.react.assets.builder;

import java.util.List;
import java.util.StringJoiner;

public class CssAssetBuilder implements IAssetBuilder {

    @Override
    public String build(List<String> assets) {
        StringJoiner joiner = new StringJoiner("\n");
        
        for (String asset : assets) {
            StringBuilder builder = new StringBuilder("<link href=\"");
            builder.append(asset);
            builder.append("\" rel=\"stylesheet\">");
            joiner.add(builder);
        }
        return joiner.toString();
    }

}
