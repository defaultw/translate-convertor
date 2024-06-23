package com.github.defaultw.plugin.ui;

import com.github.defaultw.plugin.infrastructure.DataSetting;
import com.github.defaultw.plugin.infrastructure.DataState;
import com.github.defaultw.plugin.infrastructure.TranslateComboItem;

import javax.swing.*;

/**
 * 配置页面UI
 *
 * @author Default.W
 * @date 2024/6/23
 */
public class ConfigUi {
    private JComboBox<TranslateComboItem> translateComboBox;
    private JTextField baiduApiKeyText;
    private JTextField baiduSecKeyText;
    private JLabel apiKeyLabel;
    private JLabel SecKeyLabel;
    private JPanel mainPanel;

    public ConfigUi() {
        translateComboBox.addItem(new TranslateComboItem("baidu", "百度翻译"));

        DataState state = DataSetting.getInstance().getState();
        if (state != null) {
            if (state.getTranslateComboItem() == null) {
                translateComboBox.setSelectedItem(0);
                state.setTranslateComboItem((TranslateComboItem) translateComboBox.getSelectedItem());
            } else {
                translateComboBox.setSelectedItem(state.getTranslateComboItem());
            }
            baiduApiKeyText.setText(state.getApiKey());
            baiduSecKeyText.setText(state.getSecretKey());
        }
    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

    public JComboBox<TranslateComboItem> getTranslateComboBox() {
        return translateComboBox;
    }

    public JTextField getBaiduApiKeyText() {
        return baiduApiKeyText;
    }

    public JTextField getBaiduSecKeyText() {
        return baiduSecKeyText;
    }

}
