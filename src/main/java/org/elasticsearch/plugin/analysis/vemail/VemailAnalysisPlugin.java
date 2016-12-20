package org.elasticsearch.plugin.analysis.vemail;

import org.elasticsearch.common.inject.Module;
import org.elasticsearch.index.analysis.AnalysisModule;
import org.elasticsearch.index.analysis.VemailAnalysisBinderProcessor;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.indices.analysis.VemailAnalysisModule;

import java.util.Collection;
import java.util.Collections;

/**
 * Created by lynn_lin on 2016/11/18.
 */
public class VemailAnalysisPlugin extends Plugin{

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
        return Collections.singletonList(new VemailAnalysisModule());
    }

    public void onModule(AnalysisModule module) {
        module.addProcessor(new VemailAnalysisBinderProcessor());
    }
}
