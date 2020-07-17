package com.github.maasdi.react.assets;

import java.io.File;

import org.apache.maven.plugins.annotations.Parameter;

public class ReactAssetsResource {

    @Parameter(required = true, readonly = true)
    private File file;
    
    @Parameter(required = true, readonly = true)
    private File outputDir;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public File getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(File outputDir) {
        this.outputDir = outputDir;
    }

    @Override
    public String toString() {
        return "ReactAssetsResource {file=" + file + ", outputDir=" + outputDir + "}";
    }
    
}
