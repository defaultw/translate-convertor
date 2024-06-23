package com.github.defaultw.plugin.module;

import com.github.defaultw.plugin.domain.service.TranslateServiceManager;
import com.github.defaultw.plugin.infrastructure.DataSetting;
import com.github.defaultw.plugin.infrastructure.DataState;
import com.github.defaultw.plugin.infrastructure.TranslateComboItem;
import com.github.defaultw.plugin.ui.ConfigUi;
import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

/**
 * 翻译服务的配置项
 *
 * @author Default.W
 * @date 2024/6/23
 */
public class ConfigView implements Configurable {

    private final ConfigUi configUi = new ConfigUi();

    @Override
    public String getDisplayName() {
        return "Translate Convertor";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return configUi.getMainPanel();
    }

    @Override
    public boolean isModified() {
        DataState state = DataSetting.getInstance().getState();
        if (state == null) {
            return true;
        }
        return !Objects.equals(state.getTranslateComboItem(), configUi.getTranslateComboBox().getSelectedItem())
                || !Objects.equals(state.getSecretKey(), configUi.getBaiduSecKeyText().getText())
        || !Objects.equals(state.getApiKey(), configUi.getBaiduApiKeyText().getText());
    }

    @Override
    public void apply() {
        DataState state = DataSetting.getInstance().getState();
        if (state == null) {
            return;
        }
        state.setTranslateComboItem((TranslateComboItem) configUi.getTranslateComboBox().getSelectedItem());
        state.setSecretKey(configUi.getBaiduSecKeyText().getText());
        state.setApiKey(configUi.getBaiduApiKeyText().getText());
        // 注册翻译服务
        TranslateServiceManager.getInstance().registerService();
    }
}
