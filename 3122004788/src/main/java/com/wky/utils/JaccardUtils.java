package com.wky.utils;

import org.apache.commons.collections4.SetUtils;

import java.util.Set;

/**
 * @author wky
 * @date 2024/09/09
 */
public class JaccardUtils {

    // 计算 Jaccard 相似度
    public static double calculateJaccardSimilarity(Set<String> set1, Set<String> set2) {
        int intersectionSize = SetUtils.intersection(set1, set2).size();
        int unionSize = SetUtils.union(set1, set2).size();
        return (unionSize == 0) ? 0.0 : (double) intersectionSize / unionSize;
    }
}
