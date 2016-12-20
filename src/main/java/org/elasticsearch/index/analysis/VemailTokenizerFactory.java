package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.elasticsearch.common.inject.assistedinject.Assisted;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.index.Index;
import org.elasticsearch.index.settings.IndexSettingsService;

/**
 * Created by lynn_lin on 2016/12/20.
 */
public class VemailTokenizerFactory extends AbstractTokenizerFactory {

    private int preLength;      //完全分割的前缀长度
    private int gapLength;      //间隔分割的间隔长度

    public VemailTokenizerFactory(Index index, IndexSettingsService indexSettings, @Assisted String name, @Assisted Settings settings) {
        super(index, indexSettings.getSettings(), name, settings);
        this.preLength = Integer.parseInt(settings.get("preLength", "5"));
        this.gapLength = Integer.parseInt(settings.get("gapLength", "3"));
    }

    @Override
    public Tokenizer create() {
        return new VemailTokenizer(this.preLength, this.gapLength);
    }
}
