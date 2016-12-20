package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Analyzer;
import org.elasticsearch.common.settings.Settings;

/**
 * Created by lynn_lin on 2016/12/19.
 */
public class VemailAnalyzer extends Analyzer {

    private int preLength;      //完全分割的前缀长度
    private int gapLength;      //间隔分割的间隔长度

    public VemailAnalyzer(Settings settings) {
        this.preLength = Integer.parseInt(settings.get("preLength", "5"));
        this.gapLength = Integer.parseInt(settings.get("gapLength", "3"));
    }

    @Override
    protected TokenStreamComponents createComponents(String s) {
        return new TokenStreamComponents(new VemailTokenizer(this.preLength, this.gapLength));
    }

}
