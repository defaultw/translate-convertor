package com.github.defaultw.plugin.ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 基础页面
 *
 * @author Default.W
 * @date 2024/6/18
 */
public class PageComponentUi {
    private JPanel mainPanel;
    private JPanel selectPanel;
    private JTextField i18nTextField;
    private JButton i18nSelectButton;
    private JTextField translateTextField;
    private JButton translateSelectButton;
    private JRadioButton enableRadio;
    private JRadioButton disenableRadio;
    private JButton executeButton;
    private JLabel i18nFileLabel;
    private JLabel translateFileLabel;
    private JLabel comLogLabel;
    private JLabel logText;
    private JTextArea textArea;
    private JScrollPane scrollPane;

    /**
     * 构建页面
     * @param type 1: translate页面; 2: convertor页面
     */
    public PageComponentUi(int type) {
        if (type == 1) {
            translateFileLabel.setVisible(false);
            translateTextField.setVisible(false);
            translateSelectButton.setVisible(false);

            comLogLabel.setVisible(false);
            enableRadio.setVisible(false);
            disenableRadio.setVisible(false);
        } else if (type == 2) {
            // 添加翻译后文件
            translateSelectButton.addActionListener(e -> {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.showOpenDialog(selectPanel);
                File file = fileChooser.getSelectedFile();
                if (file != null) {
                    translateTextField.setText(file.getPath());
                }
            });

            // 设置单选, 默认选中disenable
            disenableRadio.setSelected(true);
            ActionListener singleSelect = e -> {
                enableRadio.setSelected(e.getSource() == enableRadio);
                disenableRadio.setSelected(e.getSource() == disenableRadio);
            };
            enableRadio.addActionListener(singleSelect);
            disenableRadio.addActionListener(singleSelect);
        }
        i18nSelectButton.addActionListener( e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(selectPanel);
            File file = fileChooser.getSelectedFile();
            if (file != null) {
                i18nTextField.setText(file.getPath());
            }
        });

    }

    public JPanel getMainPanel() {
        return this.mainPanel;
    }

}
