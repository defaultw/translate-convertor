package com.github.defaultw.plugin.handler;

import com.github.defaultw.plugin.handler.bo.ConvertorBO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 处理文件转换逻辑
 *
 * @author Default.W
 * @date 2024/6/16
 */
public class TranslateConvertorHandler {

    public static ConvertorBO handler(ConvertorBO convertor) {

        Optional<String> i18nFilePath = Optional.ofNullable(convertor.getSourceFilePath());
        Optional<String> translatedFilePath = Optional.ofNullable(convertor.getTranslateFilePath());

        if (i18nFilePath.isEmpty() || translatedFilePath.isEmpty()) {
            convertor.setResult("Both the i18n original file and translated kv file must be specified.");
            return convertor;
        }

        StringBuilder message = new StringBuilder();

        try {
            Map<String, String> translations = loadTranslations(translatedFilePath.get());
            List<String> updatedLines = updateLines(i18nFilePath.get(), translations, message, convertor);

            if (!updatedLines.isEmpty()) {
                overwriteFile(i18nFilePath.get(), updatedLines);
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

    private static Map<String, String> loadTranslations(String filePath) throws IOException {
        return Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .filter(line -> line.contains("="))
                .map(line -> line.split("=", 2))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    private static List<String> updateLines(
            String filePath, Map<String, String> translations, StringBuilder message, ConvertorBO convertor) throws IOException {
        List<String> lines = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)
                .collect(Collectors.toList());

        boolean hasUntranslated = false;
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i).trim();
            if (!line.isEmpty() && line.contains("=")) {
                String[] kvArr = line.split("=", 2);
                String key = kvArr[0];
                if (translations.containsKey(key)) {
                    lines.set(i, key + "=" + translations.get(key));
                } else {
                    if (Boolean.TRUE.equals(convertor.getCompleteLog())) {
                        message.append(String.format("[%d] Key '%s' not found in translation, no change made.%n", i + 1, kvArr[0]))
                                .append("\n");
                    }
                    hasUntranslated = true;
                }
            }
        }
        if (hasUntranslated) {
            message.append("Some keys were not found in the translation file.\n");
        }
        return lines;
    }

    private static void overwriteFile(String filePath, List<String> updatedLines) throws IOException {
        Files.write(Paths.get(filePath), updatedLines, StandardCharsets.UTF_8);
    }


}
