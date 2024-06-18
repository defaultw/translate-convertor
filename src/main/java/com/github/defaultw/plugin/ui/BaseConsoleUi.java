package com.github.defaultw.plugin.ui;

import javax.swing.*;

/**
 * tab页面底座
 *
 * @author Default.W
 * @date 2024/6/18
 */
public class BaseConsoleUi {

    private JPanel mainPanel;
    private JTabbedPane tabbedPane;

    public void addTab(String title, JPanel panel) {
        tabbedPane.add(title, panel);
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
