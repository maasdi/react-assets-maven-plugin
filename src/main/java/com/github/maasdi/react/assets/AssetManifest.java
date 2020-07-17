package com.github.maasdi.react.assets;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AssetManifest {
    
    private static final String RUNTIME_PREFIX = "static/js/runtime-main";
    
    private static final String STYLES_PREFIX = "static/css";
    
    private static final String JAVASCRIPTS_PREFIX = "static/js";
    
    private Map<String, String> files;
    
    private List<String> entrypoints;

    public Map<String, String> getFiles() {
        return files;
    }

    public void setFiles(Map<String, String> files) {
        this.files = files;
    }

    public List<String> getEntrypoints() {
        return entrypoints;
    }

    public void setEntrypoints(List<String> entrypoints) {
        this.entrypoints = entrypoints;
    }
    
    public List<String> getRuntime() {
        return entrypoints.stream().filter(entrypoint -> entrypoint.startsWith(RUNTIME_PREFIX))
                .collect(Collectors.toList());
    }

    public List<String> getStyles() {
        return entrypoints.stream().filter(entrypoint -> entrypoint.startsWith(STYLES_PREFIX))
                .collect(Collectors.toList());
    }

    public List<String> getScripts() {
        return entrypoints.stream().filter(
                entrypoint -> entrypoint.startsWith(JAVASCRIPTS_PREFIX) && !entrypoint.startsWith(RUNTIME_PREFIX))
                .collect(Collectors.toList());
    }

}
