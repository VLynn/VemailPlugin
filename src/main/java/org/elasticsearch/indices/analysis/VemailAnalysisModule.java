package org.elasticsearch.indices.analysis;

import org.elasticsearch.common.inject.AbstractModule;

/**
 * Created by lynn_lin on 2016/11/24.
 */
public class VemailAnalysisModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(VemailAnalysisService.class).asEagerSingleton();
    }
}
