package org.elasticsearch.index.analysis;

import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.ESLoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lynn_lin on 2016/12/19.
 */
public class VemailTokenizer extends Tokenizer {

    private int preLength;      //完全分割的前缀长度
    private int gapLength;      //间隔分割的间隔长度
    private List<String> tokens = null;        //分词列表
    private int curPos = 0;     //分词列表当前词的位置
    private final CharTermAttribute charTerm = addAttribute(CharTermAttribute.class);
    private final ESLogger logger = ESLoggerFactory.getLogger("Vemail");

    public VemailTokenizer(int preLength, int gapLength) {
        super();
        this.preLength = preLength;
        this.gapLength = gapLength;
    }

    @Override
    public boolean incrementToken() throws IOException {
        this.charTerm.setEmpty();

        if (tokens == null) {
            generateTokens();
        }
        if (curPos == tokens.size()) {
            return false;
        }
        this.charTerm.append(this.tokens.get(curPos));
        curPos++;
        return true;
    }

    @Override
    public void reset() throws IOException {
        super.reset();
        clearAttributes();
        this.curPos = 0;
        this.tokens = null;
    }

    /**
     * 一次性产生分词，将结果存入list
     */
    private void generateTokens() {
        this.tokens = new ArrayList<String>();
        String tokenStr = getStringToToken();
        int splitIndex = 0;     //切割点

        //如果待分词字符串中包含@，截取@之前的部分
        int separatorIndex = tokenStr.indexOf('@');
        if (separatorIndex != -1) {
            tokenStr = tokenStr.substring(0, separatorIndex);
        }
        //如果字符串长度小于preLength，只做前缀切割
        if (tokenStr.length() <= this.preLength) {
            for (splitIndex = 1; splitIndex <= tokenStr.length(); splitIndex++) {
                this.tokens.add(tokenStr.substring(0, splitIndex));
            }
        }
        //如果字符串长度大于preLength，先做前缀切割，再做间隔切割
        else {
            for (int i = 1; i < 5; i++) {
                this.tokens.add(tokenStr.substring(0, i));
            }
            for (splitIndex = this.preLength; splitIndex <= tokenStr.length(); splitIndex += this.gapLength) {
                this.tokens.add(tokenStr.substring(0, splitIndex));
            }
            if (splitIndex != tokenStr.length() + gapLength) {
                this.tokens.add(tokenStr);
            }
        }
    }

    /**
     * 获取需要分词的字符串
     * @return String
     */
    private String getStringToToken() {
        try {
            int upto = 0;
            char[] buffer = charTerm.buffer();
            while (true) {
                final int length = input.read(buffer, upto, buffer.length - upto);
                if (-1 == length)
                    break;
                upto += length;
                if (upto == buffer.length) {
                    buffer = charTerm.resizeBuffer(1 + buffer.length);
                }
            }
            charTerm.setLength(upto);
            String str = charTerm.toString();
            charTerm.setEmpty();
            return str;
        }
        catch (IOException e) {
            logger.warn("getStringToToken throw IO exception.");
        }
        return "";
    }
}