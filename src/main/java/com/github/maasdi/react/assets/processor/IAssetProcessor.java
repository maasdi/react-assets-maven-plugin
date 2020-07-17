package com.github.maasdi.react.assets.processor;

import com.github.maasdi.react.assets.AssetContex;
import com.github.maasdi.react.assets.AssetManifest;

public interface IAssetProcessor {
    
    void process(AssetManifest manifest, AssetContex ctx)
            throws Exception;

}
