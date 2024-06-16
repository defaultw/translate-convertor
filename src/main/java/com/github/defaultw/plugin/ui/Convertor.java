package com.github.defaultw.plugin.ui;

import com.github.defaultw.plugin.handler.TranslateConvertorHandler;
import com.github.defaultw.plugin.handler.bo.ConvertorBO;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 插件主页面
 *
 * @author Default.W
 * @date 2024/6/16
 */
public class Convertor {
    private JPanel mainPanel;
    private JPanel selectPanel;
    private JLabel sourceFile;
    private JTextField sourceFilePath;
    private JButton sourceButton;
    private JLabel translateFile;
    private JButton translateButton;
    private JTextField translateFilePath;
    private JButton exeButton;
    private JTextArea result;
    private JLabel completeLog;
    private JRadioButton enableRadio;
    private JRadioButton disenableRadio;

    public Convertor() {

        disenableRadio.setSelected(true);
        ActionListener radioSelectListener = e -> {
            if (e.getSource() == enableRadio) {
                enableRadio.setSelected(true);
                disenableRadio.setSelected(false);
            } else if (e.getSource() == disenableRadio) {
                enableRadio.setSelected(false);
                disenableRadio.setSelected(true);
            }
        };
        enableRadio.addActionListener(radioSelectListener);
        disenableRadio.addActionListener(radioSelectListener);

        sourceButton.addActionListener( e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(selectPanel);
            File file = fileChooser.getSelectedFile();
            sourceFilePath.setText(file.getPath());
        });

        translateButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.showOpenDialog(selectPanel);
            File file = fileChooser.getSelectedFile();
            translateFilePath.setText(file.getPath());
        });

        exeButton.addActionListener(e -> {
            ConvertorBO convertor = new ConvertorBO();
            convertor.setSourceFilePath(sourceFilePath.getText());
            convertor.setTranslateFilePath(translateFilePath.getText());
            convertor.setCompleteLog(enableRadio.isSelected());
            ConvertorBO handler = TranslateConvertorHandler.handler(convertor);
            result.setText(handler.getResult());
        });

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
