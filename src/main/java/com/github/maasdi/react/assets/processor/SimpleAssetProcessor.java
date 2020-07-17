package com.github.maasdi.react.assets.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.github.maasdi.react.assets.AssetContex;
import com.github.maasdi.react.assets.AssetManifest;
import com.github.maasdi.react.assets.ReactAssetsResource;

public class SimpleAssetProcessor implements IAssetProcessor {

    @Override
    public void process(AssetManifest manifest, AssetContex ctx)
            throws Exception {
        for (ReactAssetsResource resource : ctx.getResources()) {
            this.processInternal(resource, ctx);
        }
    }
    
    private void processInternal(ReactAssetsResource resource, AssetContex ctx) throws Exception {
        this.debug(ctx, "Process: " + resource);
        File file = resource.getFile();
        String content = new String(Files.readAllBytes(Paths.get(file.getPath())));
        content = content.replaceAll(ctx.getCssMarkup(), ctx.getCssAsset());
        content = content.replaceAll(ctx.getRuntimeMarkup(), ctx.getRuntimeAsset());
        content = content.replaceAll(ctx.getJsMarkup(), ctx.getJsAsset());

        final String outputFile = resource.getOutputDir().getPath() + File.separator + file.getName();
        this.writeToOutput(outputFile, content);
        this.debug(ctx, "Result: " + outputFile);
    }

    private void writeToOutput(String outputFile, String content) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(outputFile));
            writer.write(content);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

    }
    
    private void debug(AssetContex ctx, String message) {
        if (ctx.getLog() != null) {
            ctx.getLog().debug(message);
        }
    }
}
