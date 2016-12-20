package org.elasticsearch.index.analysis;

/**
 * Created by lynn_lin on 2016/12/19.
 */
public class VemailAnalysisBinderProcessor extends AnalysisModule.AnalysisBinderProcessor{

    @Override
    public void processAnalyzers(AnalyzersBindings analyzersBindings) {
        analyzersBindings.processAnalyzer("Vemail", VemailAnalyzerProvider.class);
    }

    @Override
    public void processTokenizers(TokenizersBindings tokenizersBindings) {
        tokenizersBindings.processTokenizer("Vemail", VemailTokenizerFactory.class);
    }

}

