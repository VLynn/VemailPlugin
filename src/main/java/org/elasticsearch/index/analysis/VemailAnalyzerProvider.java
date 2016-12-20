package org.elasticsearch.index.analysis;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

/**
 * Created by lynn_lin on 2016/12/20.
 */
public class VemailAnalyzerProvider extends AbstractIndexAnalyzerProvider<VemailAnalyzer> {

    private final VemailAnalyzer analyzer;

    @Inject
    public VemailAnalyzerProvider(Index index, IndexSettingsService indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings.getSettings(), name, settings);
        analyzer = new VemailAnalyzer(settings);
    }

    @Override
    public VemailAnalyzer get() {
        return this.analyzer;
    }
}
