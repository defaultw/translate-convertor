package com.github.defaultw.plugin.ui;

import com.github.defaultw.plugin.domain.model.bo.BaiduTranslateConfigBO;
import com.github.defaultw.plugin.domain.service.TranslateServiceManager;
import com.github.defaultw.plugin.domain.service.impl.BaiduTranslateServiceImpl;
import com.github.defaultw.plugin.infrastructure.DataSetting;
import com.github.defaultw.plugin.infrastructure.DataState;
import com.github.defaultw.plugin.infrastructure.Language;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * 翻译设置页面
 *
 * @author Default.W
 * @date 2024/6/17
 */
public class SettingUI implements Configurable {
    private JPanel mainPanel;
    private JComboBox<Language> fromComboBox;
    private JComboBox<Language> toComboBox;
    private JLabel fromLabel;
    private JLabel toLabel;
    private JTextField apiKeyTextField;
    private JTextField secretKeyTextField;
    private JLabel apiKeyLabel;
    private JLabel secretKeyLabel;


    private BaseConsoleUi baseConsoleUi;

    public SettingUI(BaseConsoleUi baseConsoleUi) {
        this.baseConsoleUi = baseConsoleUi;
        this.mainPanel.setSize(200, 100);

        DataState state = DataSetting.getInstance().getState();
        fromComboBox.addItem(new Language("zh", "中文"));
        fromComboBox.addItem(new Language("en", "英文"));

        toComboBox.addItem(new Language("en", "英文"));
        toComboBox.addItem(new Language("zh", "中文"));

        if (state != null){
            fromComboBox.setSelectedItem(state.getFrom());
            toComboBox.setSelectedItem(state.getTo());
            apiKeyTextField.setText(state.getApiKey());
            secretKeyTextField.setText(state.getSecretKey());
        }
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) String getDisplayName() {
        return "Config";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return true;
    }

    @Override
    public void apply() throws ConfigurationException {
        DataState dataState = DataSetting.getInstance().getState();
        if (dataState == null) {
            return;
        }
        dataState.setFrom((Language) fromComboBox.getSelectedItem());
        dataState.setTo((Language) toComboBox.getSelectedItem());
        dataState.setApiKey(apiKeyTextField.getText());
        dataState.setSecretKey(secretKeyTextField.getText());
        // 注册百度翻译服务
        BaiduTranslateConfigBO bdConfig = new BaiduTranslateConfigBO();
        bdConfig.setFrom(dataState.getFrom().getCode());
        bdConfig.setTo(dataState.getTo().getCode());
        bdConfig.setApiKey(dataState.getApiKey());
        bdConfig.setSecretKey(dataState.getSecretKey());
        TranslateServiceManager.getInstance().registerService("baidu", new BaiduTranslateServiceImpl(bdConfig));
    }
}
