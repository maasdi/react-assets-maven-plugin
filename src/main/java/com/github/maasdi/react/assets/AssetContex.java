package com.github.maasdi.react.assets;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.logging.Log;

public class AssetContex {

    private Log log;
        
    private List<ReactAssetsResource> resources;
    
    private File assetsDirectory;
    
    private String cssMarkup;
    
    private String jsMarkup;
   
    private String runtimeMarkup;
    
    private String cssAsset;
    
    private String runtimeAsset;
    
    private String jsAsset;

    public Log getLog() {
        return log;
    }
    
    public void setLog(Log log) {
        this.log = log;
    }

    public List<ReactAssetsResource> getResources() {
        return resources;
    }

    public void setResources(List<ReactAssetsResource> resources) {
        this.resources = resources;
    }

    public File getAssetsDirectory() {
        return assetsDirectory;
    }

    public void setAssetsDirectory(File assetsDirectory) {
        this.assetsDirectory = assetsDirectory;
    }

    public String getCssMarkup() {
        return cssMarkup;
    }

    public void setCssMarkup(String cssMarkup) {
        this.cssMarkup = cssMarkup;
    }

    public String getJsMarkup() {
        return jsMarkup;
    }

    public void setJsMarkup(String jsMarkup) {
        this.jsMarkup = jsMarkup;
    }

    public String getRuntimeMarkup() {
        return runtimeMarkup;
    }

    public void setRuntimeMarkup(String runtimeMarkup) {
        this.runtimeMarkup = runtimeMarkup;
    }

    public String getCssAsset() {
        return cssAsset;
    }

    public void setCssAsset(String cssAsset) {
        this.cssAsset = cssAsset;
    }

    public String getRuntimeAsset() {
        return runtimeAsset;
    }

    public void setRuntimeAsset(String runtimeAsset) {
        this.runtimeAsset = runtimeAsset;
    }

    public String getJsAsset() {
        return jsAsset;
    }

    public void setJsAsset(String jsAsset) {
        this.jsAsset = jsAsset;
    }
}
