package com.github.defaultw.plugin.handler;

import cn.hutool.core.collection.CollUtil;
import com.github.defaultw.plugin.domain.model.bo.TranslateResultBO;
import com.github.defaultw.plugin.domain.service.TranslateService;
import com.github.defaultw.plugin.domain.service.TranslateServiceManager;
import com.github.defaultw.plugin.handler.bo.ConvertorBO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 处理文件转换逻辑
 *
 * @author Default.W
 * @date 2024/6/16
 */
public class TranslateConvertorHandler {

    public static ConvertorBO processTranslate(ConvertorBO convertor) {
        StringBuilder message = new StringBuilder();
        try {
            // 翻译后的字段,包括 调取翻译服务 和 直接从翻译文件中读取
            Map<String, String> translateFields;
            if (Boolean.TRUE.equals(convertor.getTranslateNeeded())) {
                // 通过翻译服务获取
                translateFields = loadTranslateMapData(convertor);
            } else {
                // 直接从翻译文件读取
                translateFields = loadFileFieldMapData(convertor.getTranslateFilePath());
            }
            List<String> updatedLines = updateI18nFile(convertor.getSourceFilePath(), translateFields, message, convertor);

            if (!updatedLines.isEmpty()) {
                overwriteFile(convertor.getSourceFilePath(), updatedLines);
                message.append("i18n file has been successfully updated.\n");
            } else {
                message.append("No changes were made to the i18n file.\n");
            }
        } catch (IOException e) {
            message.append("An error occurred while processing files: ").append(e.getMessage()).append("\n");
        }
        convertor.setResult(message.toString());
        return convertor;
    }

    private static Map<String, String> loadTranslateMapData(ConvertorBO convertor) throws IOException {
        Map<String, String> translateFields = new HashMap<>();
        Map<String, String> sourceFields = loadFileFieldMapData(convertor.getSourceFilePath());
        StringBuilder text = new StringBuilder();
        // 构建待翻译文本内容
        sourceFields.forEach((key, value) -> {
            text.append(value).append("\n");
        });
        TranslateService translateService = TranslateServiceManager.getInstance().getTranslate("baidu");
        List<TranslateResultBO> translate = translateService.translate(text.toString());
        if (CollUtil.isNotEmpty(translate)) {
            int i = 0;
            for (Map.Entry<String, String> en : sourceFields.entrySet()) {
                translateFields.put(en.getKey(), translate.get(i++).getDst());
            }
        }
        return translateFields;
    }

    private static Map<String, String> loadFileFieldMapData(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .filter(line -> line.contains("="))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    private static List<String> updateI18nFile(
            String filePath, Map<String, String> translations, StringBuilder message, ConvertorBO convertor) throws IOException {
        List<String> lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .collect(Collectors.toList());

        boolean hasUntranslated = false;
        int notFoundKeyCount = 0;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (line.contains("=")) {
                String[] kvArr = line.split("=", 2);
                String key = kvArr[0];
                if (translations.containsKey(key)) {
                    lines.set(i, key + "=" + translations.get(key));
                } else {
                    if (Boolean.TRUE.equals(convertor.getCompleteLog())) {
                        message.append(String.format("[%d] Key '%s' not found in translation, no change made.%n", i + 1, kvArr[0]))
                                .append("\n");
                        notFoundKeyCount++;
                    }
                    hasUntranslated = true;
                }
            }
        }
        if (hasUntranslated) {
            message.append(notFoundKeyCount).append(" keys were not found in the translation file.\n");
        }
        return lines;
    }

    private static void overwriteFile(String filePath, List<String> updatedLines) throws IOException {
        Files.write(Paths.get(filePath), updatedLines, StandardCharsets.UTF_8);
    }

}
