package com.github.defaultw.plugin.ui;

import javax.swing.*;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/17
 */
public class ConsoleUI {
    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel translate;
    private JPanel convert;
    private JTextField i18nTextField;
    private JButton i18nSelectButton;
    private JTextField translateTextField;
    private JButton translateSelectButton;
    private JRadioButton enableRadioButton;
    private JRadioButton disenableRadioButton;
    private JButton executeButton;
    private JTextPane logTextPane;

    private JLabel i18nFileLabel;
    private JLabel translateLabel;
    private JLabel logLabel;
    private JTextField textField1;
    private JButton tSelectButton;
    private JRadioButton tRadioButton;
    private JRadioButton tDisenableRadioButton;
    private JTextPane tLogTextPane;
    private JLabel tI18nFile;
    private JButton executeButton1;

    public ConsoleUI() {
        Convertor convertor = new Convertor();
        tabbedPane.add("Test", convertor.getMainPanel());
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JPanel getTranslate() {
        return translate;
    }
}
