package org.elasticsearch.service;

import org.elasticsearch.common.inject.AbstractModule;

/**
 * Created by lynn_lin on 2016/11/24.
 */
public class MyAnalysisModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(MyAnalysisService.class).asEagerSingleton();
    }
}
