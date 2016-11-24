package org.elasticsearch.plugin;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.service.MyAnalysisModule;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by lynn_lin on 2016/11/18.
 */
public class MyAnalysisPlugin extends Plugin{

    @Override
    public String description() {
        return "This is a simple es plugin example.";
    }

    @Override
    public String name() {
        return "My Plugin";
    }

    @Override
    public Collection<Module> nodeModules() {
        return Collections.singletonList(new MyAnalysisModule());
    }
}
