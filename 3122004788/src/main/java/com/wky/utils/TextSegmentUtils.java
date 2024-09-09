package com.wky.utils;

import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wky
 * @date 2024/09/09
 */
public class TextSegmentUtils {

    public static Set<String> ikSegment(String text) {
        Set<String> words = new HashSet<>();
        IKSegmenter ik = new IKSegmenter(new StringReader(text), true);
        try {
            Lexeme lexeme;
            while ((lexeme = ik.next()) != null) {
                words.add(lexeme.getLexemeText());
            }
        } catch (IOException e) {
            System.err.println("分词出错：" + e.getMessage());
        }
        return words;
    }

}
