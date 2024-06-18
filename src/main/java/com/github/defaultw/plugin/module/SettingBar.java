package com.github.defaultw.plugin.module;

import com.github.defaultw.plugin.ui.SettingUI;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

/**
 * TODO
 *
 * @author Default.W
 * @date 2024/6/17
 */
public class SettingBar extends DumbAwareAction {

    private ViewBars panel;

    public SettingBar(ViewBars panel) {
        super("Config", "Click to setting", IconLoader.findIcon("/icons/setting.svg"));
        this.panel = panel;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        ShowSettingsUtil.getInstance().editConfigurable(panel.getProject(), new SettingUI(panel.getBaseConsoleUi()));
    }
}
