package com.wky;

import cn.hutool.core.io.FileUtil;
import com.wky.utils.JaccardUtils;
import com.wky.utils.TextSegmentUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.Set;

/**
 * @author wky
 * @date 2024/09/09
 */

public class Main {
    public static void main(String[] args) {
        // 检查参数数量是否正确
        if (args.length < 3) {
            System.err.println("使用方式: java Main <originTextURL> <originAddTextURL> <targetURL>");
            return;
        }

        String originTextURL = args[0];
        String originAddTextURL = args[1];
        String targetURL = args[2];

        // 校验参数是否为空字符串
        if (isNullOrEmpty(originTextURL)) {
            System.err.println("错误: originTextURL 参数不能为空。");
            return;
        }
        if (isNullOrEmpty(originAddTextURL)) {
            System.err.println("错误: originAddTextURL 参数不能为空。");
            return;
        }
        if (isNullOrEmpty(targetURL)) {
            System.err.println("错误: targetURL 参数不能为空。");
            return;
        }

        // 校验参数是否为有效的路径
        if (isNotValidPath(originTextURL)) {
            System.err.println("错误: originTextURL 参数不是有效的路径。");
            return;
        }
        if (isNotValidPath(originAddTextURL)) {
            System.err.println("错误: originAddTextURL 参数不是有效的路径。");
            return;
        }
        if (isNotValidPath(targetURL)) {
            System.err.println("错误: targetURL 参数不是有效的路径。");
            return;
        }
        // 读文件
        String originText = FileUtil.readUtf8String(originTextURL).trim();
        String originAddText = FileUtil.readUtf8String(originAddTextURL).trim();

        // 分词
        Set<String> originTextWords = TextSegmentUtils.ikSegment(originText);
        Set<String> originAddTextWords = TextSegmentUtils.ikSegment(originAddText);

        // 计算相似度
        String similarity = String.format("%.2f", JaccardUtils.calculateJaccardSimilarity(originTextWords, originAddTextWords));
        System.out.println("相似度：" + similarity);
        FileUtil.writeUtf8String(similarity, targetURL);
    }

    // 检查字符串是否为 null 或空
    private static boolean isNullOrEmpty(String str) {
        return StringUtils.isBlank(str);
    }

    // 检查字符串是否为有效的文件路径
    private static boolean isNotValidPath(String path) {
        File file = new File(path);
        try {
            // 尝试获取绝对路径，检查路径格式是否正确
            System.out.println("路径：" + file.getCanonicalPath());
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}

