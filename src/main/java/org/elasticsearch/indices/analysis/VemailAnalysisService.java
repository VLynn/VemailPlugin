package org.elasticsearch.indices.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.analysis.*;

/**
 * Created by lynn_lin on 2016/11/24.
 */
public class VemailAnalysisService extends AbstractComponent {

    @Inject
    public VemailAnalysisService(final Settings settings, IndicesAnalysisService indicesAnalysisService) {
        super(settings);

        //analyzer
        indicesAnalysisService.analyzerProviderFactories().put("Vemail",
                new PreBuiltAnalyzerProviderFactory("Vemail", AnalyzerScope.GLOBAL, new VemailAnalyzer(settings)));

        //tokenizer
        indicesAnalysisService.tokenizerFactories().put("Vemail",
                new PreBuiltTokenizerFactoryFactory(new TokenizerFactory() {
                    @Override
                    public String name() {
                        return "Vemail";
                    }

                    @Override
                    public Tokenizer create() {
                        return new VemailTokenizer(5, 3);
                    }
                }));
    }

}
