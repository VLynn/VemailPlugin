package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Tokenizer;

import java.io.IOException;

/**
 * Created by lynn_lin on 2016/12/19.
 */
public class VemailTokenizer extends Tokenizer {

    public VemailTokenizer(int preLength, int gapLength) {

    }

    @Override
    public boolean incrementToken() throws IOException {
        return false;
    }
}
